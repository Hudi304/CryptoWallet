package com.proiectPS.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiectPS.demo.DTO.LoginRequest;
import com.proiectPS.demo.DTO.LoginResponse;
import com.proiectPS.demo.DTO.RegisterRequest;
import com.proiectPS.demo.DTO.RegisterResponse;
import com.proiectPS.demo.model.User;
import com.proiectPS.demo.repository.AdminRepository;
import com.proiectPS.demo.repository.CurrencyRepository;
import com.proiectPS.demo.repository.TransactionRepository;
import com.proiectPS.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentificationController {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    public AuthentificationController(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        System.out.println("got a POSt on /login");
        LoginResponse logRes  =  new LoginResponse();
        boolean foundUser  = userRepository.existsByNicknameAndPassword(loginRequest.nickname,loginRequest.password);
        boolean foundAdmin  = adminRepository.existsByNicknameAndPassword(loginRequest.nickname,loginRequest.password);
        if(foundUser || foundAdmin){
            logRes.foundUser =  true;
            System.out.println("User exists in the dataBase");
        }
        if(foundAdmin){
            logRes.isAdmin = true;
            System.out.println("User" + loginRequest.nickname + " logged in as admin =" + logRes.isAdmin );
        }else{
            logRes.isAdmin =  false;
        }
        return logRes;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("/register : got RegisterRequest " + registerRequest.nickname + " " + registerRequest.password);
        RegisterResponse regRes =  new RegisterResponse(false ,false ,false );
        User newUser = new User(registerRequest.nickname,
                registerRequest.email,
                registerRequest.password);
        //todo validari
        if( userRepository.existsByNickname(newUser.getNickname()) &&
                adminRepository.existsAdminByNickname(newUser.getNickname()))
        {
            regRes.nicknameAlreadyUsed = true;
            System.out.println("Username already exists");
        }
        if(userRepository.existsByEmail(newUser.getEmail()) &&
                adminRepository.existsAdminByEmail(newUser.getEmail()))
        {
            regRes.emailAlreadyUsed = true;
            System.out.println("Email already exists");
        }

        if(!regRes.emailAlreadyUsed &&
                !regRes.nicknameAlreadyUsed)
        {
            userRepository.save(newUser);
            System.out.println("saved user");
        }

        return regRes;
        //todo cred cca ar trebui sa returneze un token care sa-mi dea dupa aia voie sa accesez restul paginilor
        //todo send a register respons (valid, date invalide, user already exists, email already used etc...)
    }



}
