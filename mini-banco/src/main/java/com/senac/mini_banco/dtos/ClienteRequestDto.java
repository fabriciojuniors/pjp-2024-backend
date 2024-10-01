package com.senac.mini_banco.dtos;

import java.time.LocalDate;

public record ClienteRequestDto(String nome,
                                LocalDate dataNascimento,
                                String email,
                                String telefoneContato,
                                String contatoAdicional,
                                String profissao,
                                double limiteCredito) {
}
