package pl.fis.restapisummary.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.fis.restapisummary.entity.permission.Permission;
import pl.fis.restapisummary.repository.permission.PermissionRepository;
import pl.fis.restapisummary.security.UserPermission;

import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class PermissionSeeder implements CommandLineRunner {
    private final PermissionRepository permissionRepository;
    @Override
    public void run(String... args) throws Exception {
        Permission permission = new Permission(null, UserPermission.DECIMAL_READ.toString());
        Permission permission2 = new Permission(null, UserPermission.DECIMAL_WRITE.toString());
        Permission permission3 = new Permission(null, UserPermission.MULTIPLIER_READ.toString());
        Permission permission4 = new Permission(null, UserPermission.MULTIPLIER_WRITE.toString());
        Permission permission5 = new Permission(null, UserPermission.ACCESS_ALL.toString());
        permissionRepository.saveAll(List.of(permission, permission2, permission3, permission4, permission5));
    }
}
