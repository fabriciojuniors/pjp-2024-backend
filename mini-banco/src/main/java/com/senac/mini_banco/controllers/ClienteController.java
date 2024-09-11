package com.senac.mini_banco.controllers;

import com.senac.mini_banco.model.Cliente;
import com.senac.mini_banco.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("{id}")
    public Cliente findById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @PostMapping
    public Cliente save(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    @PutMapping("{id}")
    public Cliente update(@PathVariable("id") Integer id, @RequestBody Cliente clienteRequisicao) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            Cliente clienteSalvo = clienteOpt.get();
            clienteSalvo.setNome(clienteRequisicao.getNome());

            return clienteRepository.save(clienteSalvo);
        } else {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        clienteRepository.deleteById(id);
    }
}
