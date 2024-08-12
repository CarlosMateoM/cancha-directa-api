package cancha.directa.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank  String firstName,
        @NotBlank  String lastName,
        @NotBlank  String email,
        @NotBlank String password,
        @Valid RoleRequest roleRequest
) {
}
