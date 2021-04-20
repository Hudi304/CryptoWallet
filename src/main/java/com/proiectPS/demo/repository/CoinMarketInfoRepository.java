package com.proiectPS.demo.repository;

import com.proiectPS.demo.model.CoinMarketInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CoinMarketInfoRepository extends CrudRepository<CoinMarketInfo,Long> {

    CoinMarketInfo findFirstByName(String name);
    boolean removeByName(String name);
    void removeByPk(long pk);
    List<CoinMarketInfo> findAllBySymbol(String symbol);
    boolean existsById(String ID);
    CoinMarketInfo findById(String coinID);
    List<CoinMarketInfo> findAllById(String coinID);

}
