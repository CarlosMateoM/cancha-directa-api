package cancha.directa.service;

import cancha.directa.model.Permission;
import cancha.directa.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionService {

    private PermissionRepository permissionRepository;

    public void save(Permission permission){
        permissionRepository.save(permission);
    }
}
