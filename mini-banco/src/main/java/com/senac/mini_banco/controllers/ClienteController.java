package com.senac.mini_banco.controllers;

import com.senac.mini_banco.model.Cliente;
import com.senac.mini_banco.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    //@Autowired
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }


}
