package com.senac.mini_banco.dtos;

import com.senac.mini_banco.model.Cliente;

import java.time.LocalDate;

public record ClienteResponseDto(Integer id,
                                 String nome,
                                 String email,
                                 LocalDate dataNascimento) {

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ClienteResponseDto(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getDataNascimento());
    }

}
