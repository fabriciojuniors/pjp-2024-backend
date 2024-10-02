package com.senac.mini_banco.controllers;

import com.senac.mini_banco.dtos.ClienteResponseDto;
import com.senac.mini_banco.dtos.ContaBancariaResponseDto;
import com.senac.mini_banco.model.Banco;
import com.senac.mini_banco.model.ContaBancaria;
import com.senac.mini_banco.repositories.ContaBancariaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("contas-bancarias")
public class ContaBancariaController {

    private final ContaBancariaRepository contaBancariaRepository;

    public ContaBancariaController(ContaBancariaRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }

    @GetMapping
    public ResponseEntity<Page<ContaBancariaResponseDto>> findAll(@RequestParam (name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                                      @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return ResponseEntity.ok(contaBancariaRepository.findAll(pageRequest)
                .map(contaBancaria -> ContaBancariaResponseDto.toDto(contaBancaria)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ContaBancariaResponseDto> findById(@PathVariable("id") Integer id) {
        Optional<ContaBancaria> contaBancariaOpt = contaBancariaRepository.findById(id);

        if (!contaBancariaOpt.isPresent()) {
            throw new EntityNotFoundException("Conta bancária não encontrada");
        }

        return ResponseEntity.ok(ContaBancariaResponseDto.toDto(contaBancariaOpt.get()));
    }

    @PostMapping
    public ResponseEntity<ContaBancariaResponseDto> save(@RequestBody ContaBancaria contaBancaria) {
        contaBancariaRepository.save(contaBancaria);
        return ResponseEntity
                .created(URI.create("/contas-bancarias/" + contaBancaria.getId()))
//                .status(201)
                .body(ContaBancariaResponseDto.toDto(contaBancaria));
    }

    @PutMapping("{id}")
    public ResponseEntity<ContaBancariaResponseDto> update(@PathVariable("id") Integer id,
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

        return ResponseEntity.ok(ContaBancariaResponseDto.toDto(contaBancariaRepository.save(contaBancariaSalva)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        Optional<ContaBancaria> contaBancariaOpt = contaBancariaRepository.findById(id);

        if (contaBancariaOpt.isPresent()) {
          ContaBancaria contaBancaria = contaBancariaOpt.get();

          if (contaBancaria.getBanco() == Banco.BANCO_DO_BRASIL) {
                throw new RuntimeException("A conta não pode ser excluída");
          } else {
              contaBancariaRepository.delete(contaBancaria);
          }
        }

    }
}
