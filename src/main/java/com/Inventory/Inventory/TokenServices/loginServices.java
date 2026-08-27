package com.Inventory.Inventory.TokenServices;

import com.Inventory.Inventory.TokenRepo.LoginRepo;
import com.Inventory.Inventory.TokaenEntity.loginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class loginServices {


    @Autowired
    LoginRepo repo;

    @Autowired
    public PasswordEncoder passwordEncoder;




    public loginDetails saveLoginDetails(loginDetails login){
        login.setUser_username(UUID.randomUUID().toString());
        login.setUser_password(passwordEncoder.encode(login.getUser_password()));
     loginDetails newlogin = repo.save(login);
     return newlogin;

    }
}
