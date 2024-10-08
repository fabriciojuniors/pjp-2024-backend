package com.senac.mini_banco.controllers;

import com.senac.mini_banco.dtos.ContaBancariaRequestDto;
import com.senac.mini_banco.dtos.ContaBancariaResponseDto;
import com.senac.mini_banco.exceptions.RegraNegocioException;
import com.senac.mini_banco.model.Banco;
import com.senac.mini_banco.model.ContaBancaria;
import com.senac.mini_banco.repositories.ClienteRepository;
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
    private final ClienteRepository clienteRepository;

    public ContaBancariaController(ContaBancariaRepository contaBancariaRepository, ClienteRepository clienteRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
        this.clienteRepository = clienteRepository;
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
    public ResponseEntity<ContaBancariaResponseDto> save(@RequestBody ContaBancariaRequestDto dto) {
        ContaBancaria contaBancaria = dto.toContaBancaria(new ContaBancaria(),
                clienteRepository);
        contaBancariaRepository.save(contaBancaria);
        return ResponseEntity
                .created(URI.create("/contas-bancarias/" + contaBancaria.getId()))
//                .status(201)
                .body(ContaBancariaResponseDto.toDto(contaBancaria));
    }

    @PutMapping("{id}")
    public ResponseEntity<ContaBancariaResponseDto> update(@PathVariable("id") Integer id,
                                @RequestBody ContaBancariaRequestDto dto) {
        Optional<ContaBancaria> contaBancariaOpt = contaBancariaRepository.findById(id);

        if (!contaBancariaOpt.isPresent()) {
            throw new EntityNotFoundException("Conta não encontrada");
        }

        ContaBancaria contaBancariaSalva = dto.toContaBancaria(
                contaBancariaOpt.get(), clienteRepository);

        return ResponseEntity.ok(ContaBancariaResponseDto.toDto(contaBancariaRepository.save(contaBancariaSalva)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        Optional<ContaBancaria> contaBancariaOpt = contaBancariaRepository.findById(id);

        if (contaBancariaOpt.isPresent()) {
          ContaBancaria contaBancaria = contaBancariaOpt.get();

          if (contaBancaria.getBanco() == Banco.BANCO_DO_BRASIL) {
                throw new RegraNegocioException("A conta não pode ser excluída", 1);
          } else {
              contaBancariaRepository.delete(contaBancaria);
          }
        }

    }
}
