package com.proiectPS.demo.utils;

import com.proiectPS.demo.model.Currency;
import com.proiectPS.demo.model.User;

import javax.persistence.PostPersist;

public class UserEventListener {

    @PostPersist
    public void notifyCreation(User user){
        System.out.println(
                "Created new Currency : "+ user.getNickname());
    }
}
