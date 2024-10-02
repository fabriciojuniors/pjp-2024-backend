package com.senac.mini_banco.dtos;

import com.senac.mini_banco.model.Banco;
import com.senac.mini_banco.model.ContaBancaria;

public record ContaBancariaResponseDto(String numeroConta,
                                       String numeroAgencia,
                                       Banco banco,
                                       ClienteResponseDto cliente) {

    public static ContaBancariaResponseDto toDto(ContaBancaria contaBancaria) {
        return new ContaBancariaResponseDto(contaBancaria.getNumeroConta(),
                contaBancaria.getNumeroAgencia(),
                contaBancaria.getBanco(),
                ClienteResponseDto.toDto(contaBancaria.getCliente()));
    }

}
