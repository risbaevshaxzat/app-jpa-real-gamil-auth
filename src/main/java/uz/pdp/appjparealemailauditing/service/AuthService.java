package uz.pdp.appjparealemailauditing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appjparealemailauditing.entity.Role;
import uz.pdp.appjparealemailauditing.entity.User;
import uz.pdp.appjparealemailauditing.entity.enums.RolName;
import uz.pdp.appjparealemailauditing.payload.ApiResponse;
import uz.pdp.appjparealemailauditing.payload.RegistrDto;
import uz.pdp.appjparealemailauditing.repo.RoleRepo;
import uz.pdp.appjparealemailauditing.repo.UserRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.apache.coyote.http11.Constants.a;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    JavaMailSender javaMailSender;

    public ApiResponse registerUser(RegistrDto registrDto){
        User user = new User();
        boolean existsByEmail = userRepo.existsByEmail(registrDto.getEmail());
        if (existsByEmail){
            return new ApiResponse("Bunday gmail mavjud" ,false);
        }
        user.setFirstName(registrDto.getFirstName());
        user.setLastName(registrDto.getLastName());
        user.setEmail(registrDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrDto.getPassword()));
        user.setRoles((List<Role>) Collections.singleton(roleRepo.findByRoleName(RolName.ROLE_USER)));
        user.setGmailCode(UUID.randomUUID().toString());


        userRepo.save(user);
        sendEmail(user.getEmail(), user.getGmailCode());

        return  new ApiResponse("Royxattam ottingiz Accauntni tastiqlash uchin emailni kiriting ",true);


    }
    public Boolean sendEmail(String sendingEmile , String emailCode){
        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("TsestPdp@pdp.com");
            simpleMailMessage.setTo(sendingEmile);
            simpleMailMessage.setSubject("Accaunt tastiqlash");
            simpleMailMessage.setText("<a href='http://localhost:8080/api/auth/verifyEmail?emailCode=" + emailCode + "+&email=" + sendingEmile + "'>Tastiqlaing</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
