package com.opbaquero.conexionaback.security.repository;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    List<User> findByAccount(Account account);

    @Query("select account from User u where u.userName=?1")
    Account findByUsernameWithAccount(String username);

}
