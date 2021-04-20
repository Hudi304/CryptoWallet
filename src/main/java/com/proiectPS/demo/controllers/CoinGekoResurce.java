package com.proiectPS.demo.controllers;


import com.proiectPS.demo.model.Coin;
import com.proiectPS.demo.model.CoinMarketInfo;
import com.proiectPS.demo.repository.CoinMarketInfoRepository;
import com.proiectPS.demo.repository.CoinRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import javax.swing.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

@RestController
@EnableAsync
public class CoinGekoResurce {

    //TODo chain of responsability bahavioral
    // Chain of Responsibility is a behavioral design pattern that
    // lets you pass requests along a chain of handlers.
    // Upon receiving a request, each handler decides either to process the request
    // or to pass it to the next handler in the chain.

    // de la Repo pana pe frontend fiecare calsa prin care trec datele le verifica,
    // daca datele sunt corecte metoda poate genera un rezultat corect
    // urmatoarea clasa/metoda care preia rezultatul preia si

    //TODO Mediator bahavioral
    // Mediator is a behavioral design pattern that lets you reduce chaotic dependencies
    // between objects. The pattern restricts direct communications between the objects
    // and forces them to collaborate only via a mediator object.

    // Controllerele sunt mediatori pentru diferitele tipuri de date

    //todo Memento bahavioral
    // Memento is a behavioral design pattern that lets you save and restore the previous state
    // of an object without revealing the details of its implementation.

    // nu e implementat dawr ar fi foarte util

    // todo Facade  structural
    //  Facade is a structural design pattern that provides a simplified interface
    //  to a library, a framework, or any other complex set of classes.

    // ne obliga Spring-ul sa-l avem  in repository-uri ar trebui sa-l implementez si pe service-uri

    // todo Decorator structural
    //  Decorator is a structural design pattern that lets you attach new behaviors to objects
    //  by placing these objects inside special wrapper objects that contain the behaviors.

    // il folosesc pe Frontend pentru a avea un singur tip de obiect care agrega date din mai multe API call-uri,
    // doar ca in typescript nu ma obiga sa creez clase noi

    //todo Observer  Behavioral Patterns
    // Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple
    // objects about any events that happen to the object they’re observing.

    // nu il am inca dar voi avea nevoie de el pentru a notifica userii
    // daca scade sau creste pretul la un currncy pe care il detin


    //todo daca tot updatezi la 5 minute datele ce ar fi sa le si salvezi?

    public static final String GET_COINS_LIST = "https://api.coingecko.com/api/v3/coins/list?include_platform=false";
    public static final String GET_COINS_MARKET_INFO_LIST = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";

    List<Coin> coins;
    RestTemplate restTemplate;
    CoinRepository coinRepository;
    CoinMarketInfoRepository coinMarketInfoRepository;
    List<CoinMarketInfo> coinMarketInfoList;

    Timer timer = new Timer();
    List<CoinMarketInfo> newCoinMarketInfoList;
    Iterator<CoinMarketInfo> iterator;


    public CoinGekoResurce(CoinRepository coinRepository, CoinMarketInfoRepository coinMarketInfoRepository){
        this.coins = new ArrayList<>();
        this.coinMarketInfoList = new ArrayList<>();
        this.restTemplate =  new RestTemplate();
        this.coinRepository = coinRepository;
        this.coinMarketInfoRepository = coinMarketInfoRepository;
        this.newCoinMarketInfoList = getCoinMarketInfo();
        this.iterator = newCoinMarketInfoList.iterator();
    }

    public void getCoinInfo() {
        HttpHeaders headers =  new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity =  new HttpEntity<>("parameters",headers);
        try{
            String data = restTemplate.exchange(GET_COINS_LIST, HttpMethod.GET,entity , String.class).getBody();
            JSONArray array  = new JSONArray(data);

            for(int i = 0 ; i< array.length(); i++){
                JSONObject jsonCoin = array.getJSONObject(i);
                Coin coin = new Coin();
                coin.setId(jsonCoin.getString("id"));
                coin.setSymbol(jsonCoin.getString("symbol"));
                coin.setName(jsonCoin.getString("name"));
                coins.add(coin);
            }
            System.out.println(coins);
            coinRepository.saveAll(coins);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<CoinMarketInfo> getCoinMarketInfo() {
        System.out.println("calling CoinGekko for data ...");

        List<CoinMarketInfo> coinMarketInfoList = new ArrayList<>();
        HttpHeaders headers =  new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity =  new HttpEntity<>("parameters",headers);
        try{
            String data = restTemplate.exchange(GET_COINS_MARKET_INFO_LIST, HttpMethod.GET,entity , String.class).getBody();
            JSONArray array  = new JSONArray(data);
            for(int i = 0 ; i< array.length(); i++){
                JSONObject jsonCoinInfo = array.getJSONObject(i);
                CoinMarketInfo coinMarketInfo = new CoinMarketInfo();
                    coinMarketInfo.setId(
                                jsonCoinInfo.getString("id"));
                    coinMarketInfo.setSymbol(
                                jsonCoinInfo.getString("symbol"));
                    coinMarketInfo.setName(
                                jsonCoinInfo.getString("name"));
                    coinMarketInfo.setImage(
                                jsonCoinInfo.getString("image"));
                    coinMarketInfo.setCurrent_price(
                        jsonCoinInfo.getLong("current_price"));
                    coinMarketInfo.setMarket_cap(
                        jsonCoinInfo.getLong("market_cap"));
                    coinMarketInfo.setMarket_cap_rank(
                        jsonCoinInfo.getInt("market_cap_rank"));
                    if(jsonCoinInfo.has("fully_diluted_valuation") && !jsonCoinInfo.isNull("fully_diluted_valuation")){
                        coinMarketInfo.setFully_diluted_valuation(
                                jsonCoinInfo.getLong("fully_diluted_valuation"));
                    }

                    coinMarketInfo.setTotal_volume(
                        jsonCoinInfo.getLong("total_volume"));
                    coinMarketInfo.setHigh_24h(
                        jsonCoinInfo.getLong("high_24h"));

                    coinMarketInfo.setLow_24h(
                        jsonCoinInfo.getDouble("low_24h"));
                    coinMarketInfo.setPrice_change_24h(
                        jsonCoinInfo.getDouble("price_change_24h"));

                    coinMarketInfo.setPrice_change_percentage_24h(
                        jsonCoinInfo.getDouble("price_change_percentage_24h"));
                    coinMarketInfo.setMarket_cap_change_24h(
                        jsonCoinInfo.getLong("market_cap_change_24h"));
                    //------------------------------------------------------------------------------------------
                    coinMarketInfo.setMarket_cap_change_percentage_24h(
                        jsonCoinInfo.getDouble("market_cap_change_percentage_24h"));
                    coinMarketInfo.setCirculating_supply(
                        jsonCoinInfo.getLong("circulating_supply"));
                    if(jsonCoinInfo.has("total_supply") && !jsonCoinInfo.isNull("total_supply")) {
                        coinMarketInfo.setTotal_supply(
                                jsonCoinInfo.getLong("total_supply"));
                    }
                    if(jsonCoinInfo.has("max_supply") && !jsonCoinInfo.isNull("max_supply")) {
                        coinMarketInfo.setMax_supply(
                                jsonCoinInfo.getLong("max_supply"));
                    }
                    //--------------------------------------------------------------------------
                    coinMarketInfo.setAth(
                        jsonCoinInfo.getLong("ath"));
                    coinMarketInfo.setAth_change_percentage(
                        jsonCoinInfo.getDouble("ath_change_percentage"));

                    coinMarketInfo.setAth_date(
                            getCalendarFromISO(jsonCoinInfo.getString("ath_date")));
                    coinMarketInfo.setAtl(
                            jsonCoinInfo.getDouble("atl"));

                    coinMarketInfo.setLast_updated(
                        getCalendarFromISO(jsonCoinInfo.getString("last_updated")));

                    coinMarketInfo.setAtl_change_percentage(
                            jsonCoinInfo.getDouble("atl_change_percentage"));

                    coinMarketInfo.setAtl_date(
                            getCalendarFromISO(jsonCoinInfo.getString("atl_date"))
                    );
                coinMarketInfoList.add(coinMarketInfo);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return coinMarketInfoList;
    }

    //TODO : ASSUMPTION is mother of all fuckups ... you asumed they are unique
    public void updateCoinPrices(String coinID) {
        HttpHeaders headers =  new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity =  new HttpEntity<>("parameters",headers);
        try{
            String data = restTemplate.exchange(
                    "https://api.coingecko.com/api/v3/coins/" + coinID + "/market_chart?vs_currency=usd&days=1&interval=minutely",
                    HttpMethod.GET,entity , String.class).getBody();
            JSONObject jsonData  = new JSONObject(data);
            JSONArray prices = jsonData.getJSONArray("prices");
            JSONArray timePriceEl = prices.getJSONArray(prices.length()-1);
            CoinMarketInfo coin = coinMarketInfoRepository.findById(coinID);
            coin.setCurrent_price(timePriceEl.getDouble(1));
            coinMarketInfoRepository.save(coin);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Async
    @Transactional
    @Scheduled(fixedRate = 300000) // 5 MINUTE5
    public void updateCoinInfo(){
        System.out.println("UPDATING coinMarketInfo ... : " + java.time.LocalTime.now());
        List<CoinMarketInfo> newCoinMarketInfoList = getCoinMarketInfo();
        for (CoinMarketInfo newCoin :  newCoinMarketInfoList ) {
            try{
                List<CoinMarketInfo> oldCoins = coinMarketInfoRepository.findAllBySymbol(newCoin.getSymbol());
                newCoin.setCurrent_price(oldCoins.get(0).getCurrent_price());
                if (oldCoins.isEmpty()){
                    coinMarketInfoRepository.save(newCoin);
                }else {
                    coinMarketInfoRepository.deleteAll(oldCoins);
                    coinMarketInfoRepository.save(newCoin);
                }
            }catch (Exception e){
                System.out.println(e);
            }
            //updateCoinPrices(newCoin.getId());
        }
    }
    @Async
    @Transactional
    @Scheduled(fixedRate = 10000,initialDelay = 10000) // 10 s
    public void updateCoinPrice(){
        if(iterator.hasNext()){
            String coin = iterator.next().getId();
            updateCoinPrices(coin);
            System.out.println("UPDATED " + coin + " " + java.time.LocalTime.now());
        }else{
            iterator = newCoinMarketInfoList.iterator();
        }
    }



    public static Calendar getCalendarFromISO(String datestring) {
        //    "ath_date": "2021-04-10T02:32:48.074Z",
        String[] dateAndTime = datestring.split("[-:.TZ]+");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.set(Calendar.YEAR, Integer.parseInt(dateAndTime[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateAndTime[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateAndTime[2]));

            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateAndTime[3]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(dateAndTime[4]));
            calendar.set(Calendar.SECOND, Integer.parseInt(dateAndTime[5]));

            calendar.set(Calendar.MILLISECOND, Integer.parseInt(dateAndTime[6]));
        return calendar;
    }
}
