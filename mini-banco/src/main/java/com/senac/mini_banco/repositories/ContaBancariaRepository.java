package com.senac.mini_banco.repositories;

import com.senac.mini_banco.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

}
