package com.Inventory.Inventory.TokenModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String JwtTokene;
    private String username;
}
