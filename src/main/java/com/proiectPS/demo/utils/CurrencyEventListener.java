package com.proiectPS.demo.utils;

import com.proiectPS.demo.model.Currency;

import javax.persistence.PostPersist;

public class CurrencyEventListener {

    @PostPersist
    public void notifyCreation(Currency currency){
        System.out.println("Created new Currency : " + currency.getName() + " = " + currency.getPriceInDollars());
    }

}
