package com.pack.bank.api.repository;

import com.pack.bank.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByAccountNumber(Long accountNumber);

    public Optional<Account> findAccountByAccountNumber(Long accountNumber);

    public boolean existsById(Long id); // Spring Data JPA provides this method by default

    //@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.id = :id")
    //boolean existsByAccountId(@Param("id") Long id);

}