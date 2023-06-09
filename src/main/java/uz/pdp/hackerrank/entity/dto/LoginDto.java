package uz.pdp.hackerrank.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDto {
    @NotBlank(message = "username cannot be blank or empty")
    @NotNull(message = "username cannot be null")
    private String username;
    @NotBlank(message = "password cannot be blank or empty")
    @NotNull(message = "password cannot be null")
    private String password;
}
