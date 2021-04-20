package com.proiectPS.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoinMarketInfo implements Comparable<CoinMarketInfo>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    private String id;
    private String symbol;
    private String name;
    private String image;

    private double current_price;
    private long market_cap;
    private int market_cap_rank;
    private long fully_diluted_valuation;

    private long total_volume;
    private long high_24h;

    private double low_24h;
    private double price_change_24h;

    private double price_change_percentage_24h;
    private long market_cap_change_24h;
    //-----------------------------------------------
    private double market_cap_change_percentage_24h;
    private long circulating_supply;

    private long total_supply;
    private long max_supply;
    //----------------------------------------------
    private long ath;
    private double ath_change_percentage;

    private double atl;
    private double atl_change_percentage;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar ath_date;


    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar atl_date;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar last_updated;

    @Override
    public String toString() {
        return "CoinMarketInfo{" +
                "pk=" + pk +
                ", id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", current_price=" + current_price +
                ", market_cap=" + market_cap +
                ", market_cap_rank=" + market_cap_rank +
                ", fully_diluted_valuation=" + fully_diluted_valuation +
                ", total_volume=" + total_volume +
                ", high_24h=" + high_24h +
                ", low_24h=" + low_24h +
                ", price_change_24h=" + price_change_24h +
                ", price_change_percentage_24h=" + price_change_percentage_24h +
                ", market_cap_change_24h=" + market_cap_change_24h +
                ", market_cap_change_percentage_24h=" + market_cap_change_percentage_24h +
                ", circulating_supply=" + circulating_supply +
                ", total_supply=" + total_supply +
                ", max_supply=" + max_supply +
                ", ath=" + ath +
                ", ath_change_percentage=" + ath_change_percentage +
                ", ath_date=" + ath_date +
                ", atl=" + atl +
                ", atl_change_percentage=" + atl_change_percentage +
                ", atl_date=" + atl_date +
                ", last_updated=" + last_updated +
                '}';
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public long getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(long market_cap) {
        this.market_cap = market_cap;
    }

    public int getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(int market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public long getFully_diluted_valuation() {
        return fully_diluted_valuation;
    }

    public void setFully_diluted_valuation(long fully_diluted_valuation) {
        this.fully_diluted_valuation = fully_diluted_valuation;
    }

    public long getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(long total_volume) {
        this.total_volume = total_volume;
    }

    public long getHigh_24h() {
        return high_24h;
    }

    public void setHigh_24h(long high_24h) {
        this.high_24h = high_24h;
    }

    public double getLow_24h() {
        return low_24h;
    }

    public void setLow_24h(double low_24h) {
        this.low_24h = low_24h;
    }

    public double getPrice_change_24h() {
        return price_change_24h;
    }

    public void setPrice_change_24h(double price_change_24h) {
        this.price_change_24h = price_change_24h;
    }

    public double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public void setPrice_change_percentage_24h(double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }

    public long getMarket_cap_change_24h() {
        return market_cap_change_24h;
    }

    public void setMarket_cap_change_24h(long market_cap_change_24h) {
        this.market_cap_change_24h = market_cap_change_24h;
    }

    public double getMarket_cap_change_percentage_24h() {
        return market_cap_change_percentage_24h;
    }

    public void setMarket_cap_change_percentage_24h(double market_cap_change_percentage_24h) {
        this.market_cap_change_percentage_24h = market_cap_change_percentage_24h;
    }

    public long getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(long circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public long getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(long total_supply) {
        this.total_supply = total_supply;
    }

    public long getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(long max_supply) {
        this.max_supply = max_supply;
    }

    public long getAth() {
        return ath;
    }

    public void setAth(long ath) {
        this.ath = ath;
    }

    public double getAth_change_percentage() {
        return ath_change_percentage;
    }

    public void setAth_change_percentage(double ath_change_percentage) {
        this.ath_change_percentage = ath_change_percentage;
    }

    public Calendar getAth_date() {
        return ath_date;
    }

    public void setAth_date(Calendar ath_date) {
        this.ath_date = ath_date;
    }

    public double getAtl() {
        return atl;
    }

    public void setAtl(double atl) {
        this.atl = atl;
    }

    public double getAtl_change_percentage() {
        return atl_change_percentage;
    }

    public void setAtl_change_percentage(double atl_change_percentage) {
        this.atl_change_percentage = atl_change_percentage;
    }

    public Calendar getAtl_date() {
        return atl_date;
    }

    public void setAtl_date(Calendar atl_date) {
        this.atl_date = atl_date;
    }

    public Calendar getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Calendar last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public int compareTo(CoinMarketInfo coin) {
        if(this.getMarket_cap_rank() == coin.getMarket_cap_rank())
            return 0;
        else if(this.getMarket_cap_rank() > coin.getMarket_cap_rank())
            return 1;
        else
            return -1;
    }
}

