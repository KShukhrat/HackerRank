package uz.pdp.hackerrank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hackerrank.entity.dto.UserCreateDto;
import uz.pdp.hackerrank.entity.user.UserEntity;
import uz.pdp.hackerrank.service.user.UserService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/{userId}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> blockUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.blockUser(userId));
    }

    @PutMapping("/{userId}/unblock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> unBlockUser(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.unblockUser(userId));
    }

    @PutMapping("/{userId}/add")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Boolean> addAdmin(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.addAdmin(userId));
    }

    @PutMapping("/{userId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUserById(@PathVariable UUID userId){
        userService.deleteById(userId);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("{userId}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserEntity> edit(@RequestBody UserCreateDto userCreateDto,
                                           @PathVariable UUID userId,
                                           @RequestParam Boolean hasBlocked){
        return ResponseEntity.ok(userService.edit(userCreateDto,userId,hasBlocked));
    }

}
