package pl.fis.restapisummary.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.fis.restapisummary.config.PasswordEncoderConfig;
import pl.fis.restapisummary.entity.permission.Permission;
import pl.fis.restapisummary.entity.user.UserEntity;
import pl.fis.restapisummary.exception.NotFoundException;
import pl.fis.restapisummary.repository.permission.PermissionRepository;
import pl.fis.restapisummary.repository.user.UserRepository;
import pl.fis.restapisummary.security.UserPermission;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    @Override
    public void run(String... args) throws Exception {
        Permission decimalRead = permissionRepository
                .findByName(UserPermission.DECIMAL_READ.toString())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "Permission decimal read does not exists"));
        Permission decimalWrite = permissionRepository
                .findByName(UserPermission.DECIMAL_WRITE.toString())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "Permission decimal write does not exists"));
        Permission multiplierRead = permissionRepository
                .findByName(UserPermission.MULTIPLIER_READ.toString())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "Permission multiplier READ does not exists"));
        Permission multiplierWrite = permissionRepository
                .findByName(UserPermission.MULTIPLIER_WRITE.toString())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "Permission multiplier write does not exists"));
        Permission accessAll = permissionRepository
                .findByName(UserPermission.ACCESS_ALL.toString())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND, "Permission access all does not exists"));
        UserEntity user = new UserEntity(null, "user1", passwordEncoderConfig.passwordEncoder().encode("user"), new ArrayList<>());
        user.getAuthorities().addAll(List.of(decimalRead, decimalWrite));
        UserEntity user2 = new UserEntity(null, "user2", passwordEncoderConfig.passwordEncoder().encode("user"), new ArrayList<>());
        user2.getAuthorities().addAll(List.of(multiplierRead, multiplierWrite));
        UserEntity user3 = new UserEntity(null, "user3", passwordEncoderConfig.passwordEncoder().encode("user"), new ArrayList<>());
        user3.getAuthorities().add(accessAll);
        userRepository.saveAll(List.of(user, user2, user3));
    }
}
