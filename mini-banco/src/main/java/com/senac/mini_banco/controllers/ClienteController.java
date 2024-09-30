package com.senac.mini_banco.controllers;

import com.senac.mini_banco.model.Cliente;
import com.senac.mini_banco.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Page<Cliente>> findAll(@RequestParam(name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                                @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return ResponseEntity.ok(clienteRepository.findAll(pageRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        //return ResponseEntity.status(201).body(cliente);
        return ResponseEntity
                .created(URI.create("/clientes/" + cliente.getId()))
                .body(cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Integer id, @RequestBody Cliente clienteRequisicao) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            Cliente clienteSalvo = clienteOpt.get();
            clienteSalvo.setNome(clienteRequisicao.getNome());

            return ResponseEntity
                    .ok(clienteRepository.save(clienteSalvo));
        } else {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        clienteRepository.deleteById(id);
        //return ResponseEntity.noContent().build();
    }
}
