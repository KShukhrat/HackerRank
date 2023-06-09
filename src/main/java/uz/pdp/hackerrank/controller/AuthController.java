package uz.pdp.hackerrank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hackerrank.entity.dto.LoginDto;
import uz.pdp.hackerrank.entity.dto.UserCreateDto;
import uz.pdp.hackerrank.entity.dto.response.JwtResponse;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    @PostMapping("sign-up")
    public ResponseEntity<UserEntity> signUp(
            @RequestBody UserCreateDto userCreated
    ){
        return ResponseEntity.ok(userService.save(userCreated));
    }

    @GetMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody LoginDto login
    ) {
        return ResponseEntity.ok(userService.login(login));
    }
}
