package cancha.directa.dto.request;

import jakarta.validation.constraints.Size;

import java.util.List;

public record RoleRequest(
        @Size(max = 3, message = "The user cannot have more than 3 roles") List<String> roles
) {
}
