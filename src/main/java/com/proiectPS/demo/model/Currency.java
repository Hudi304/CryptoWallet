package com.proiectPS.demo.model;

import com.proiectPS.demo.utils.CurrencyEventListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EntityListeners(CurrencyEventListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)


public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private double priceInDollars;

    public Currency (String name, double priceInDollars){
        this.name = name;
        this.priceInDollars = priceInDollars;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPriceInDollars() {
        return priceInDollars;
    }
    public void setPriceInDollars(double priceInDollars) {
        this.priceInDollars = priceInDollars;
    }
}
