package com.proiectPS.demo.repository;

import com.proiectPS.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Boolean existsByEmail(String email);
    Boolean existsByNickname(String nickname);

    Boolean existsByNicknameAndPassword(String nickname, String password);

    User findFirstById(Long id);
    User findFirstByNickname(String nickanme);
    User findFirstByEmail(String email);

    List<User> findAllByNickname(String nickname);
    List<User> findByNickname(String nickname);

    void deleteByNickname(String nickname);







}
