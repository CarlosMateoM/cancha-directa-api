package cancha.directa.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "token", "status"})
public record LoginResponse(String message,
                            String token,
                            boolean status) {
}
