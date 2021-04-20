package com.proiectPS.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiectPS.demo.DTO.TransactionRequest;
import com.proiectPS.demo.DTO.TransactionResponse;
import com.proiectPS.demo.model.*;
import com.proiectPS.demo.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final CurrencyRepository currencyRepository;
    private final TransactionRepository transactionRepository;
    private final CoinGekoResurce coinGekoResurce;
    private final  CoinRepository coinRepository;
    private final CoinMarketInfoRepository coinMarketInfoRepository;
    private final CryptoTransactionRepository cryptoTransactionRepository;

    public UserController(UserRepository userRepository,
                          AdminRepository adminRepository,
                          CurrencyRepository currencyRepository,
                          TransactionRepository transactionRepository,
                          CoinRepository coinRepository,
                          CoinGekoResurce coinGekoResurce,
                          CoinMarketInfoRepository  coinMarketInfoRepository,
                          CryptoTransactionRepository  cryptoTransactionRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.currencyRepository = currencyRepository;
        this.transactionRepository = transactionRepository;
        this.coinRepository = coinRepository;
        this.coinGekoResurce = coinGekoResurce;
        this.coinMarketInfoRepository = coinMarketInfoRepository;
        this.cryptoTransactionRepository = cryptoTransactionRepository;
    }

    @GetMapping("/users/currencies")
    public List<Currency> fetchUserCurrencies(){
        System.out.println("got a GET on /user/currencys ");
        List<Currency> curr  = (List<Currency>) currencyRepository.findAll();

        System.out.println(curr);
        return curr;
    }

    @GetMapping("/users/coins")
    public List<Coin> fetchCoins(){
        System.out.println("got a GET on /user/coins ");
        List<Coin> coins  =  (List<Coin>)coinRepository.findAll();
       //coinGekoResurce.updateCoinInfo();
        //System.out.println(curr);
        return coins;
    }

    @GetMapping("/users/coinInfo")
    public List<CoinMarketInfo> fetchCoinsInfo(){
        System.out.println("got a GET on /user/coinInfo ");
        List<CoinMarketInfo> coinInfo  =  (List<CoinMarketInfo>)coinMarketInfoRepository.findAll();
        Collections.sort(coinInfo);
        return coinInfo;
    }

    @GetMapping("/users/coinsMarketInfo")
    public List<CoinMarketInfo> fetchCoinMarketInfo(){
        List<CoinMarketInfo> coinInfo  =  (List<CoinMarketInfo>)coinMarketInfoRepository.findAll();
        coinMarketInfoRepository.saveAll( coinGekoResurce.getCoinMarketInfo());
        return coinInfo;
    }

    @PostMapping("/users/transactions")
    public TransactionResponse transaction(@RequestBody TransactionRequest transactionRequest){
        System.out.println("got a POST on /user/transactions ");
        //System.out.println(transactionRequest.toString());
        TransactionResponse transactionResponse = new TransactionResponse();
        CryptoTransaction transaction = new CryptoTransaction();
        try{
            transaction.coinName = transactionRequest.coinID;
            transaction.userNickname = transactionRequest.userNickname;
            transaction.amount = transactionRequest.transactionAmount;
            transaction.aquisitionPrice = transactionRequest.coinPrice;
            transaction.aquisitionGMTDate = transactionRequest.currentDate;
            if(transaction.amount > 0){
                transaction.type = "BUY";
            }else{
                transaction.type = "SELL";
            }

            if(transaction.userNickname == null || transaction.userNickname == "") {
                throw new Exception("SESSION EXPIRED");
            }
            if(transaction.amount == 0) {
                throw new Exception("TRANSACTION AMOUNT CAN NOT BE 0");
            }
            if(transaction.coinName != null
                    && transaction.coinName != ""
                    && transaction.aquisitionGMTDate != null
                    && transaction.userNickname != null
                    && transaction.userNickname != ""
            ){
                cryptoTransactionRepository.save(transaction);
                transactionResponse.status = "ACCEPTED";
            }else {
                throw  new Exception("INVALID TRANSACTION");
            }
        }catch (Exception e){
            transactionResponse.status = "REJECTED : " + e.getLocalizedMessage();
        }
        //System.out.println(transaction.toString());

        return transactionResponse;
    }

    @PostMapping("/users/tansact")
    public List<CryptoTransaction> getTransactions(@RequestBody String userName ){
        List<CryptoTransaction> transactions =  new ArrayList<>();
        System.out.println("get Transactions");
        transactions = cryptoTransactionRepository.findAllByUserNickname(userName);
        System.out.println(transactions.toString());

        return transactions;
    }

}
