package uz.pdp.hackerrank.service.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.entity.dto.LoginDto;
import uz.pdp.hackerrank.entity.dto.UserCreateDto;
import uz.pdp.hackerrank.entity.dto.response.JwtResponse;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.entity.user.UserRole;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.repository.UserRepository;
import uz.pdp.hackerrank.service.JwtService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserEntity save(UserCreateDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public JwtResponse login(LoginDto login) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(login.getUsername())
                .orElseThrow(
                        () -> new DataNotFoundException("USER NOT FOUND")
                );

        if(passwordEncoder.matches(login.getPassword(), userEntity.getPassword())) {
            String accessToken = jwtService.generateAccessToken(userEntity);
            return JwtResponse.builder().accessToken(accessToken).build();
        }
        throw new DataNotFoundException("USER NOT FOUND");
    }

    public Boolean blockUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("USER NOT FOUND")
        );
        userEntity.setIsBlocked(true);
        userRepository.save(userEntity);
        return true;
    }

    public Boolean unblockUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("USER NOT FOUND")
        );
        userEntity.setIsBlocked(false);
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public Boolean addAdmin(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("USER NOT FOUND")
        );
        userEntity.setRoles(List.of(UserRole.valueOf("ADMIN")));
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public void deleteById(UUID userId) {
        UserEntity found = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("UserId not found")
        );
        userRepository.deleteById(userId);
    }

    @Override
    public UserEntity edit(UserCreateDto user, UUID id,Boolean hasBlocked ) {
        UserEntity found = userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("UserId not found")
        );
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setUpdatedDate(found.getCreatedDate());
        userEntity.setId(found.getId());
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getById(UUID userId) {
        return userRepository.findUserEntityById(userId);
    }

    @Override
    public List<UserEntity> getFromPoints() {
        Sort sort = Sort.by(Sort.Direction.DESC, "userPoints");
        return userRepository.findAll(sort);
    }
}
