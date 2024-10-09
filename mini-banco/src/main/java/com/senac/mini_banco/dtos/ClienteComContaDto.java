package com.senac.mini_banco.dtos;

import com.senac.mini_banco.model.Cliente;

import java.util.List;

public record ClienteComContaDto(String nome,
                                 double limiteCredito,
                                 List<String> numeroDasContas) {

    public static ClienteComContaDto toDto(Cliente cliente) {
        return new ClienteComContaDto(cliente.getNome(),
                cliente.getLimiteCredito(),
                cliente.getContas().stream()
                        .map(conta -> conta.getNumeroConta())
                        .toList());
    }

}
