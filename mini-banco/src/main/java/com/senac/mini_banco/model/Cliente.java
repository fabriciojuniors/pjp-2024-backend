package com.senac.mini_banco.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
