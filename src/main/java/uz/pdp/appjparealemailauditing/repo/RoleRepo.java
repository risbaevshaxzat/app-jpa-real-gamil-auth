package uz.pdp.appjparealemailauditing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparealemailauditing.entity.Role;
import uz.pdp.appjparealemailauditing.entity.enums.RolName;

public interface RoleRepo extends JpaRepository<Role , Integer> {

    Role findByRoleName(RolName rolName);
}
