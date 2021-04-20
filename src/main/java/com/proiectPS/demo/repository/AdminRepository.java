package com.proiectPS.demo.repository;

import com.proiectPS.demo.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

    Admin findFirstByNickname(String nickname);
    Boolean existsAdminByNickname(String nickname);
    Boolean existsAdminByEmail(String email);

    Boolean existsByNicknameAndPassword(String nickname, String password);


}
