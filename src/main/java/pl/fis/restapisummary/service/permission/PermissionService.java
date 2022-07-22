package pl.fis.restapisummary.service.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fis.restapisummary.repository.permission.PermissionRepository;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
}
