package uz.pdp.hackerrank.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hackerrank.entity.dto.LoginDto;
import uz.pdp.hackerrank.entity.dto.UserCreateDto;
import uz.pdp.hackerrank.entity.dto.response.JwtResponse;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.exception.RequestValidationException;
import uz.pdp.hackerrank.service.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserEntity> signUp(@Valid
                                             @RequestBody UserCreateDto userCreated,
                                             BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userCreated));
    }
    @GetMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(
            @RequestBody LoginDto loginDto,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
