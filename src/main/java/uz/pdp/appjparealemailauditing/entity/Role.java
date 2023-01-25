package uz.pdp.appjparealemailauditing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.appjparealemailauditing.entity.enums.RolName;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RolName name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
