package com.Inventory.Inventory.TokenServices;


import com.Inventory.Inventory.TokaenEntity.User;
import com.Inventory.Inventory.TokenRepo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class service {

    @Autowired
    private UserRepo repo;



    public List<User> getUsers(){
       return repo.findAll();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User creatUser(User User){
        User.setUserId(UUID.randomUUID().toString());
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        return repo.save(User);

    }













}
