package uz.pdp.hackerrank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.hackerrank.service.user.UserService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/{userId}/block")
    public ResponseEntity<Boolean> blockUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.blockUser(userId));
    }

    @PutMapping("/{userId}/unblock")
    public ResponseEntity<Boolean> unBlockUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.unblockUser(userId));
    }

}
