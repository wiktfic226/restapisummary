package pl.fis.restapisummary.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.fis.restapisummary.entity.user.UserEntity;
import pl.fis.restapisummary.service.user.UserService;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ACCESS_ALL')")
    public ResponseEntity<UserEntity> getUser(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ACCESS_ALL')")
    public ResponseEntity<Void> deleteUser(@RequestParam String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ACCESS_ALL')")
    public ResponseEntity<Void> updatePassword(@RequestParam String username, @RequestBody String password) {
        userService.updatePassword(username, password);
        return ResponseEntity.ok().build();
    }
}
