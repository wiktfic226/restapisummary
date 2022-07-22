package pl.fis.restapisummary.service.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.fis.restapisummary.config.PasswordEncoderConfig;
import pl.fis.restapisummary.entity.permission.Permission;
import pl.fis.restapisummary.entity.user.UserEntity;
import pl.fis.restapisummary.exception.NotFoundException;
import pl.fis.restapisummary.repository.permission.PermissionRepository;
import pl.fis.restapisummary.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public void createUser(UserEntity user) {
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public void addPermissionToUser(String username, String permissionName) {
        Permission permission = permissionRepository
                .findByName(permissionName)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        String.format("Permission with name %s does not exists", permissionName)));
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        String.format("User with username %s does not exists", username)));
        user.getAuthorities().add(permission);
        userRepository.save(user);
    }
}
