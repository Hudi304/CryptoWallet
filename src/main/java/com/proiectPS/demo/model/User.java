package com.proiectPS.demo.model;


import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickname;
    private String password;
    private String email;


//    @OneToMany
//    private List<Transasction> transactions;

    public User(String name, String email) {
        this.nickname = name;
        this.email = email;
        this.password = "12354";
    }

    public User(String name, String email, String password) {
        this.nickname = name;
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
