package com.senac.mini_banco.dtos;

import com.senac.mini_banco.model.Banco;
import com.senac.mini_banco.model.ContaBancaria;
import com.senac.mini_banco.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public record ContaBancariaRequestDto(String numeroConta,
                                      String digitoConta,
                                      String numeroAgencia,
                                      String digitoAgencia,
                                      Banco banco,
                                      Integer idCliente) {

    public ContaBancaria toContaBancaria(ContaBancaria contaBancaria,
                                         ClienteRepository clienteRepository) {
        contaBancaria.setNumeroConta(this.numeroConta());
        contaBancaria.setDigitoConta(this.digitoConta());
        contaBancaria.setNumeroAgencia(this.numeroAgencia());
        contaBancaria.setDigitoAgencia(this.digitoAgencia());
        contaBancaria.setBanco(this.banco());

        contaBancaria.setCliente(clienteRepository
                .getReferenceById(this.idCliente()));

        return contaBancaria;
    }
}

