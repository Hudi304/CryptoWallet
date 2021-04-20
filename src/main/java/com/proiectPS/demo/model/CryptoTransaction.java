package com.proiectPS.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//todo pretty sure "Transaction" is a SQL keyword
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CryptoTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    public String coinName;
    public double aquisitionPrice;
    public String userNickname;
    public String aquisitionGMTDate;
    public double amount;
    public String type;

    @Override
    public String toString() {
        return "CryptoTransaction{" +
                "pk=" + pk +
                ", coinName='" + coinName + '\'' +
                ", aquisitionPrice=" + aquisitionPrice +
                ", userNickname='" + userNickname + '\'' +
                ", aquisitionGMTDate='" + aquisitionGMTDate + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
