package uz.pdp.appjparealemailauditing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparealemailauditing.entity.User;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

   boolean existsByEmail(String email);
}
