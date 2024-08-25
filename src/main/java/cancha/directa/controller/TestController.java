package cancha.directa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("getMethod")
    public ResponseEntity<String> getMethod(){
        return new ResponseEntity<>("getMethod", HttpStatus.OK);
    }

    @PostMapping("postMethod")
    public ResponseEntity<String> postMethod(){
        return new ResponseEntity<>("postMethod", HttpStatus.OK);
    }

}
