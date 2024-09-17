package com.senac.mini_banco.controllers;

import com.senac.mini_banco.model.ContaBancaria;
import com.senac.mini_banco.repositories.ContaBancariaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("contas-bancarias")
public class ContaBancariaController {

    private final ContaBancariaRepository contaBancariaRepository;

    public ContaBancariaController(ContaBancariaRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }

    @GetMapping
    public Page<ContaBancaria> findAll(@RequestParam (name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                       @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return contaBancariaRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ContaBancaria findById(@PathVariable("id") Integer id) {
        Optional<ContaBancaria> contaBancariaOpt = contaBancariaRepository.findById(id);

        if (!contaBancariaOpt.isPresent()) {
            throw new EntityNotFoundException("Conta bancária não encontrada");
        }

        return contaBancariaOpt.get();
    }

    @PostMapping
    public ContaBancaria save(@RequestBody ContaBancaria contaBancaria) {
        contaBancariaRepository.save(contaBancaria);
        return contaBancaria;
    }

    @PutMapping("{id}")
    public ContaBancaria update(@PathVariable("id") Integer id,
                                @RequestBody ContaBancaria contaBancaria) {
        Optional<ContaBancaria> contaBancariaOpt = contaBancariaRepository.findById(id);

        if (!contaBancariaOpt.isPresent()) {
            throw new EntityNotFoundException("Conta não encontrada");
        }

        ContaBancaria contaBancariaSalva = contaBancariaOpt.get();
        contaBancariaSalva.setBanco(contaBancaria.getBanco());
        contaBancariaSalva.setNumeroConta(contaBancaria.getNumeroConta());
        contaBancariaSalva.setDigitoConta(contaBancaria.getDigitoConta());
        contaBancariaSalva.setNumeroAgencia(contaBancaria.getDigitoAgencia());
        contaBancariaSalva.setDigitoAgencia(contaBancaria.getDigitoAgencia());

        return contaBancariaRepository.save(contaBancariaSalva);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        contaBancariaRepository.deleteById(id);
    }
}
