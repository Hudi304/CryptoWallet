package com.proiectPS.demo.DTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterResponse {

    public Boolean nicknameAlreadyUsed;
    public Boolean emailAlreadyUsed;
    public Boolean emailLooksWeird;


}
