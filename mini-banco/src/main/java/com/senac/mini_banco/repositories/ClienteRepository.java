package com.senac.mini_banco.repositories;

import com.senac.mini_banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente, Integer> {
}
