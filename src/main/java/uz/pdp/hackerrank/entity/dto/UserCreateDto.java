package uz.pdp.hackerrank.entity.dto;
import lombok.*;
import uz.pdp.hackerrank.entity.user.UserRole;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    private String name;

    private String username;

    private String password;

    private List<UserRole> roles;

    private Boolean hasBlocked;

}
