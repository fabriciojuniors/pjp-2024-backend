package com.senac.produtos.controller;

import com.senac.produtos.model.Produto;
import com.senac.produtos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public Page<Produto> findAll(@RequestParam(name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                 @RequestParam(name = "quantidadeRegistro", required = false, defaultValue = "10") int quantidadeRegistro) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidadeRegistro);
        return produtoRepository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public Produto findById(@PathVariable("id") Integer id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            return produtoOpt.get();
        } else {
            throw new EntityNotFoundException("Produto não encontrado.");
        }
    }

    @PostMapping
    public Produto create(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("{id}")
    public Produto update(@PathVariable("id") Integer id,
                          @RequestBody Produto produto) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            Produto produtoSalvo = produtoOpt.get();

            produtoSalvo.setNome(produto.getNome());
            produtoSalvo.setValor(produto.getValor());

            return produtoRepository.save(produtoSalvo);

        } else {
            throw new EntityNotFoundException("Produto não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        produtoRepository.deleteById(id);
    }

}
