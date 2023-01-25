package uz.pdp.appjparealemailauditing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjparealemailauditing.payload.ApiResponse;
import uz.pdp.appjparealemailauditing.payload.RegistrDto;
import uz.pdp.appjparealemailauditing.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {

    @Autowired
    AuthService authService;


    @PostMapping("/registr")
    public HttpEntity<?>  registerUser(@RequestBody RegistrDto registrDto){
        ApiResponse apiResponse = authService.registerUser(registrDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
}
