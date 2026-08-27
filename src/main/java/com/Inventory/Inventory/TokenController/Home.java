package com.Inventory.Inventory.TokenController;


import com.Inventory.Inventory.TokaenEntity.User;
import com.Inventory.Inventory.TokaenEntity.loginDetails;
import com.Inventory.Inventory.TokenServices.loginServices;
import com.Inventory.Inventory.TokenServices.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class Home {

    @Autowired
    private loginServices lgservice;
    @Autowired
    private service ser;


    @GetMapping("/user")
    public List<User> getUser(){
        List<User> userList = ser.getUsers();
        if(userList==null){
            throw new RuntimeException("Data are not saved on database");
        }
        return  userList;
    }

    @PostMapping("/loginUser")
    public loginDetails login(@RequestBody loginDetails login){
        return lgservice.saveLoginDetails(login);

    }


}
