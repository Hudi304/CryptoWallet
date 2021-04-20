package com.proiectPS.demo.repository;

import com.proiectPS.demo.model.Currency;
import com.proiectPS.demo.model.Transasction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends CrudRepository<Transasction,Long> {

    Transasction findFirstById(Long id);
}
