package com.Inventory.Inventory.TokenRepo;

import com.Inventory.Inventory.TokaenEntity.User;
import com.Inventory.Inventory.TokaenEntity.loginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<loginDetails, Integer > {


}
