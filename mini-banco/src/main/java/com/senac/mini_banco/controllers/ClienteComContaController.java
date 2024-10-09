package com.senac.mini_banco.controllers;

import com.senac.mini_banco.dtos.ClienteComContaDto;
import com.senac.mini_banco.model.Cliente;
import com.senac.mini_banco.repositories.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("clientes-com-contas")
public class ClienteComContaController {

    private final ClienteRepository clienteRepository;

    public ClienteComContaController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClienteComContaDto>> find() {
        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteComContaDto> retornos = clientes.stream()
                .map(cliente -> ClienteComContaDto.toDto(cliente))
                .toList();

        return ResponseEntity.ok(retornos);
    }
}
