package com.example.ecommerce.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    private String email;
    private String password;

}