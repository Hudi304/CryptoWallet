package com.proiectPS.demo.repository;


import com.proiectPS.demo.model.CryptoTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CryptoTransactionRepository extends CrudRepository<CryptoTransaction,Long> {

    List<CryptoTransaction> findAllByUserNickname(String userNickname);


}
