package cancha.directa.service;

import cancha.directa.model.Role;
import cancha.directa.model.User;
import cancha.directa.repository.RoleRepository;
import cancha.directa.repository.UserRepository;
import cancha.directa.dto.request.LoginRequest;
import cancha.directa.dto.request.RegisterRequest;
import cancha.directa.dto.response.LoginResponse;
import cancha.directa.dto.response.RegisterResponse;
import cancha.directa.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private JwtUtils jwtUtils;

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private List<SimpleGrantedAuthority> createSimpleGrantedAuthorityList(User user) {

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

        user.getRoles().forEach(role -> simpleGrantedAuthorityList.add(
                new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))
        ));

        user.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> simpleGrantedAuthorityList.add(
                        new SimpleGrantedAuthority(permission.getPermission())
                ));

        return simpleGrantedAuthorityList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = createSimpleGrantedAuthorityList(user);
        
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNoExpired(),
                user.isCredentialNoExpired(),
                user.isAccountNoLocked(),
                simpleGrantedAuthorityList
        );
    }

    public Authentication authenticate(String email, String password) {

        UserDetails userDetails = loadUserByUsername(email);

        if(userDetails == null) {
            throw new BadCredentialsException("invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());

    }

    public LoginResponse login(LoginRequest authLoginRequest) {

        String email = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateToken(authentication);

        return new LoginResponse("user logged successfuly", token,true);
    }

    public RegisterResponse register(RegisterRequest registerRequest) {

        String firstName = registerRequest.firstName();
        String lastName = registerRequest.lastName();
        String email = registerRequest.email();
        String password = registerRequest.password();
        List<String> roles = registerRequest.roleRequest().roles();

        Optional<User> userExits = userRepository.findUserByEmail(email);

        if(userExits.isPresent()){
            throw new IllegalArgumentException("Email already exits");
        }

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setAccountNoExpired(true);
        user.setAccountNoLocked(true);
        user.setCredentialNoExpired(true);

        User userSaved = userRepository.save(user);

        Set<Role> roleSet = new HashSet<>(roleRepository.findRolesByRoleEnumIn(roles));

        if(roleSet.isEmpty()){
            throw new IllegalArgumentException("The specified role(s) do not exist.");
        }

        userSaved.setRoles(roleSet);

        userSaved = userRepository.save(userSaved);
        
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = createSimpleGrantedAuthorityList(userSaved);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userSaved.getEmail(),
                userSaved.getPassword(),
                simpleGrantedAuthorityList
        );

        String token = jwtUtils.generateToken(authentication);

        return new RegisterResponse("user created successfully", token);
    }


}
