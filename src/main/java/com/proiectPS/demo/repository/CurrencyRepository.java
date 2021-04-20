package com.proiectPS.demo.repository;

import com.proiectPS.demo.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency,Long> {
    Currency findFirstById(Long id);

    Currency findFirstByName(String name);
}
