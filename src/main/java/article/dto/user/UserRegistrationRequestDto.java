package article.dto.user;

import article.annotation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@FieldMatch(
        first = "password",
        second = "repeatPassword",
        message = "Passwords do not match"
)
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;
}
