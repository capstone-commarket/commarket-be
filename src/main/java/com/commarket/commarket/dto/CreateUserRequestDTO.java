package com.commarket.commarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {
    private  String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String name;

    private String nickname;
}
