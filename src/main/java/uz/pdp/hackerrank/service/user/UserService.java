package uz.pdp.hackerrank.service.user;

import uz.pdp.hackerrank.entity.dto.LoginDto;
import uz.pdp.hackerrank.entity.dto.UserCreateDto;
import uz.pdp.hackerrank.entity.dto.response.JwtResponse;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.entity.user.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserEntity save(UserCreateDto userDto);
    JwtResponse login(LoginDto login);
    Boolean blockUser(UUID userId);
    Boolean unblockUser(UUID userId);
    Boolean addAdmin(UUID userId);

    void deleteById(UUID userId);

    UserEntity edit(UserCreateDto user, UUID id, Boolean hasBlocked);
    UserEntity getById(UUID userId);

    List<UserEntity> getFromPoints();
}
