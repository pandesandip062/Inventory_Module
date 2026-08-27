package com.Inventory.Inventory.TokaenEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Login_Details")
public class loginDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;
    private String user_username;
    private String user_password;
}
