package com.proiectPS.demo.repository;

import com.proiectPS.demo.model.Admin;
import com.proiectPS.demo.model.Coin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends CrudRepository<Coin, String> {



}
