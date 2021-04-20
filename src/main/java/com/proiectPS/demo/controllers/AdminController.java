package com.proiectPS.demo.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.proiectPS.demo.DTO.LoginRequest;
import com.proiectPS.demo.DTO.LoginResponse;
import com.proiectPS.demo.DTO.RegisterRequest;
import com.proiectPS.demo.DTO.RegisterResponse;
import com.proiectPS.demo.model.*;
import com.proiectPS.demo.repository.AdminRepository;
import com.proiectPS.demo.repository.CurrencyRepository;
import com.proiectPS.demo.repository.TransactionRepository;
import com.proiectPS.demo.repository.UserRepository;
import org.json.JSONException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {


    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionRepository transactionRepository;
    private final CoinGekoResurce coinGekoResurce;
    ObjectMapper mapper;

    private RestTemplate restTemplate;

    public AdminController(UserRepository userRepository,
                           AdminRepository adminRepository,
                           CurrencyRepository currencyRepository,
                           TransactionRepository transactionRepository,
                           CoinGekoResurce coinGekoResurce) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.currencyRepository = currencyRepository;
        this.transactionRepository = transactionRepository;
        this.coinGekoResurce = coinGekoResurce;
        mapper =  new ObjectMapper();
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        System.out.println("Got a GET on /users");
        return (List<User>) userRepository.findAll();

    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        System.out.println("got a POSt on /users");
        userRepository.save(user);
    }



    // Admin FETCHING

    @GetMapping("/admin/currencies")
    public List<Currency> fetchAdminCurrencies(){
        System.out.println("got a GET on /admin/currencys ");
        List<Currency> curr  = (List<Currency>) currencyRepository.findAll();
        System.out.println(curr);
//        coinGekoResurce.getCoinInfo();
        return curr;
    }

    @GetMapping("/admin/transactions")
    public  List<Transasction> fetchtransactionas(){
        System.out.println("got GET on /admin/transactions");
        List<Transasction> transactions = new ArrayList<>();// = (List<Transasction>) transactionRepository.findAll();
        return transactions;
    }

    @GetMapping("/admin/admins")
    public  List<Admin> fetchAdmins(){
        System.out.println("got GET on /admin/admins");
        List<Admin> admins = (List<Admin>) adminRepository.findAll();
        return admins;
    }

    //admin SAVE 
    @PostMapping("/admin/currencies/save")
    public ResponseEntity saveCurrency(@RequestBody Currency currency){
        try {
            currencyRepository.save(currency);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/admin/transactions/save")
    public ResponseEntity saveTransaction(@RequestBody Transasction transasction ){
        try {
            transactionRepository.save(transasction);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/admin/admin/save")
    public ResponseEntity saveAdmin(@RequestBody Admin admin ){
        try {
            adminRepository.save(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // admin DELETE


    // vezi ca ai pus "/" inainte nu stii ce face
    @PostMapping("/admin/users/remove")
    public ResponseEntity removeUser(@RequestBody String nicmane){
        System.out.println("got a post on /admin/users/remove REMOVE");
        User usr = userRepository.findFirstByNickname(nicmane);
        try {
            userRepository.delete(usr);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/currencies/remove")
    public ResponseEntity removeCurrency(@RequestBody long id ){
        System.out.println("got a post on /admin/currencies/remove REMOVE");
        Currency curr =  currencyRepository.findFirstById(id);
        try {
            currencyRepository.delete(curr);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }





}
