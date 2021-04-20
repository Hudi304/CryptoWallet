package com.proiectPS.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Transasction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Currency curr;

    private double amount;

    @OneToOne
    private User from;
    @OneToOne
    private User to;

    public Transasction(Currency currencie, double amount, User from, User to){
        this.curr = currencie;
        this.amount = amount;
        this.from = from;
        this.to =  to;
    }

    //private String dateTime;




}
