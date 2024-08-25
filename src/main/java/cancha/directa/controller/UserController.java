package cancha.directa.controller;

import cancha.directa.dto.UserDTO;
import cancha.directa.model.User;
import cancha.directa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

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
        return ResponseEntity.ok("hola mundo");
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

        return ResponseEntity.created(new URI("api/v1/user/save")).build();
    }
}