package cancha.directa.controller;

import cancha.directa.dto.impl.UserDTO;
import cancha.directa.model.User;
import cancha.directa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController (IUserService userService){
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Test api/v1/user");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        Optional<User> userOptional = this.userService.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .rol(user.getRol())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();

            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll (){
        List<User> users = this.userService.findAll();

        if(users.isEmpty()){
           return ResponseEntity.noContent().build();
        }

        List<UserDTO> userDTOS = users.stream()

                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .rol(user.getRol())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .phone(user.getPhone())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build())
        .toList();

        return ResponseEntity.ok(userDTOS);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) throws URISyntaxException {
        if(userDTO.getEmail().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        this.userService.save(User.builder()
                .rol(userDTO.getRol())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build());

        return ResponseEntity
                .created(new URI("api/v1/user/save"))
                .body(userDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        Optional<User> userOptional = this.userService.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            user.setRol(userDTO.getRol());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setPassword(user.getPassword());

            this.userService.save(user);
            return ResponseEntity.ok("User updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        if(id != null){
            this.userService.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.badRequest().build();
    }
}