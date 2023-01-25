package uz.pdp.appjparealemailauditing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    private List<Role> roles;

    @Column(unique = true ,nullable = false)
    private String email;//users@gmailcom


    @Column(nullable = false)
    private String password;//user123

    @CreationTimestamp
    @Column(nullable = false,unique = false)
    private Timestamp creatAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    private boolean accountNonExpired=true;

    private boolean accountNonLocked=true;

    private boolean credentialsNonExpired=true;

    private boolean isEnabled;

    private String gmailCode;

    /////Bu usernining xiquqlari

    @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }
    ///Usrning usernameni qaytaruchi metod;

    @Override
    public String getUsername() {
        return this.email;
    }

    //Acauntnig amal qilish muddatin qaytaradi
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
