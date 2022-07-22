package pl.fis.restapisummary.controller.register;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapisummary.entity.user.UserEntity;
import pl.fis.restapisummary.service.register.RegisterService;

@RestController
@RequestMapping(path = "/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserEntity user) {
        registerService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> addAuthorityToUser(@RequestBody AddAuthorityToUser addAuthorityToUser) {
        registerService.addPermissionToUser(addAuthorityToUser.getUsername(), addAuthorityToUser.getPermissionName());
        return ResponseEntity.ok().build();
    }

    @Data
    class AddAuthorityToUser {
        private String username;
        private String permissionName;
    }
}
