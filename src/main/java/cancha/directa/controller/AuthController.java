package cancha.directa.controller;

import cancha.directa.dto.request.LoginRequest;
import cancha.directa.dto.request.RegisterRequest;
import cancha.directa.dto.response.LoginResponse;
import cancha.directa.dto.response.RegisterResponse;
import cancha.directa.service.UserDetailServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private UserDetailServiceImpl userDetailService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest loginRequest
    ) {

        LoginResponse loginResponse = userDetailService.login(loginRequest);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody @Valid RegisterRequest registerRequest
    ){

        RegisterResponse registerResponse = userDetailService.register(registerRequest);

        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<LoginResponse> test() {
        return new ResponseEntity<>(new LoginResponse("test", "test", true), HttpStatus.OK);
    }

    @GetMapping("user")
    public String user() {
        return "user";
    }

}
