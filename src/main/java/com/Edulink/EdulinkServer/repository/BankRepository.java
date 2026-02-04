package com.Edulink.EdulinkServer.repository;

import com.Edulink.EdulinkServer.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    List<Bank> findAllBySlugIgnoreCase(String slug);

}
