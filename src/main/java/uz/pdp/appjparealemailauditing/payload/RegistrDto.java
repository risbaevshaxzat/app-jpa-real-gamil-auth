package uz.pdp.appjparealemailauditing.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegistrDto {
    @NotNull
    @Length(min = 5 , max = 50)
    private String firstName;

    @NotNull
    @Length(min = 5 , max = 50)
    private String lastName;

    @NotNull
    @Email
    private String email;//users@gmailcom

    @NotNull
    private String password;//user123


}
