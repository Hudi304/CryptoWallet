package com.proiectPS.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    public String userNickname;
    public String coinID;
    public double coinPrice;
    public double transactionAmount;
    public String currentDate;


    @Override
    public String toString() {
        return "TransactionRequest{ \n" +
                "userNickname='" + userNickname + '\'' + "\n" +
                ", coinID='" + coinID + '\'' +"\n" +
                ", coinPrice=" + coinPrice +"\n" +
                ", transactionAmount=" + transactionAmount +"\n" +
                ", currentDate='" + currentDate + '\'' +"\n" +
                '}' + "\n";
    }
}



