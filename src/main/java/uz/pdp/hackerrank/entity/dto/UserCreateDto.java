package uz.pdp.hackerrank.entity.dto;
import lombok.*;
import uz.pdp.hackerrank.entity.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
    private String name;
    @NotBlank(message = "username cannot be blank or empty")
    private String username;
    @NotBlank(message = "password cannot be blank or empty")

    private String password;
    private List<UserRole> roles;
}
