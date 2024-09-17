package com.senac.mini_banco.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "telefone_contato", nullable = false,length = 12)
    private String telefoneContato;

    @Column(name = "contato_adicional", length = 12)
    private String contatoAdicional;

    @Column(name = "profissao", length = 20)
    private String profissao;

    @Column(name = "limite_credito", nullable = false)
    private double limiteCredito;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ContaBancaria> contas = new ArrayList<>();

    public Cliente(Integer id, String nome, LocalDate dataNascimento, String email, String telefoneContato, String contatoAdicional, String profissao, double limiteCredito) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefoneContato = telefoneContato;
        this.contatoAdicional = contatoAdicional;
        this.profissao = profissao;
        this.limiteCredito = limiteCredito;
    }

    public Cliente() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getContatoAdicional() {
        return contatoAdicional;
    }

    public void setContatoAdicional(String contatoAdicional) {
        this.contatoAdicional = contatoAdicional;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
}
