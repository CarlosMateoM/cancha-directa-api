package cancha.directa;

import cancha.directa.enums.PermissionType;
import cancha.directa.enums.RoleType;
import cancha.directa.model.Permission;
import cancha.directa.model.Role;
import cancha.directa.model.User;
import cancha.directa.repository.PermissionRepository;
import cancha.directa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SpringBootApplication
public class CanchaDirectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanchaDirectaApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedDatabase(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	){
		return  args -> {

			Set<Permission> permissions = Arrays.stream(PermissionType.values())
					.map(permissionType -> {
						Permission permission = new Permission();
						permission.setPermission(permissionType.name());
						return permission;
					})
					.collect(Collectors.toSet());

			Role adminRole = new Role();
			adminRole.setRoleEnum(RoleType.ADMIN);
			adminRole.setPermissions(permissions);

			Role userRole = new Role();
			userRole.setRoleEnum(RoleType.USER);



			User admin = new User();
			admin.setFirstName("admin");
			admin.setLastName("admin");
			admin.setEmail("admin@correo.com");
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setEnabled(true);
			admin.setAccountNoExpired(true);
			admin.setAccountNoLocked(true);
			admin.setCredentialNoExpired(true);
			admin.setRoles(Set.of(adminRole));

			User user = new User();
			user.setFirstName("user");
			user.setLastName("user");
			user.setEmail("user@correo.com");
			user.setPassword(passwordEncoder.encode("password"));
			user.setEnabled(true);
			user.setAccountNoExpired(true);
			user.setAccountNoLocked(true);
			user.setCredentialNoExpired(true);
			user.setRoles(Set.of(userRole));


			userRepository.saveAll(List.of(admin, user));
		};
	}
}
