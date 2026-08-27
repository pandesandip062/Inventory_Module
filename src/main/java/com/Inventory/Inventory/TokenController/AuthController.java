package com.Inventory.Inventory.TokenController;


import com.Inventory.Inventory.Secuity.JwtHelper;
import com.Inventory.Inventory.TokaenEntity.User;
import com.Inventory.Inventory.TokenModels.JwtRequest;
import com.Inventory.Inventory.TokenModels.JwtResponse;
import com.Inventory.Inventory.TokenServices.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    @Autowired
    service ser;





    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .JwtTokene(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";

    }

   /* @PostMapping("/save")
    public user createUser(@RequestBody user User){
       return ser.creatUser(User);

    }*/

    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUSer= ser.creatUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdUSer);
    }

}
