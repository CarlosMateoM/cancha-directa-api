package cancha.directa.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class RegisterNotFoundException extends ResourceNotFoundException {
    public RegisterNotFoundException(String message){
        super(message);
    }
}
