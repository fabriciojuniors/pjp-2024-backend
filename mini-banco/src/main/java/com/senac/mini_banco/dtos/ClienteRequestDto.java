package com.senac.mini_banco.dtos;

import com.senac.mini_banco.model.Cliente;

import java.time.LocalDate;

public record ClienteRequestDto(String nome,
                                LocalDate dataNascimento,
                                String email,
                                String telefoneContato,
                                String contatoAdicional,
                                String profissao,
                                double limiteCredito) {

    public Cliente toCliente(Cliente cliente) {
        cliente.setNome(this.nome());
        cliente.setEmail(this.email());
        cliente.setContatoAdicional(this.contatoAdicional());
        cliente.setProfissao(this.profissao());
        cliente.setDataNascimento(this.dataNascimento());
        cliente.setLimiteCredito(this.limiteCredito());
        cliente.setTelefoneContato(this.telefoneContato());

        return cliente;
    }

}
