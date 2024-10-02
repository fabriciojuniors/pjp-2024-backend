package com.senac.mini_banco.model;


import jakarta.persistence.*;

@Entity
@Table(name = "contas_bancarias")
public class ContaBancaria {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "numero_conta", nullable = false, length = 20)
    private String numeroConta;

    @Column(name = "digito_conta", nullable = false, length = 2)
    private String digitoConta;

    @Column(name = "numero_agencia", nullable = false, length = 10)
    private String numeroAgencia;

    @Column(name = "digito_agencia", nullable = false, length = 2)
    private String digitoAgencia;

    @Column(name = "banco", nullable = false)
    @Enumerated(EnumType.STRING)
    private Banco banco;

    @JoinColumn(name = "id_cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public ContaBancaria(Integer id, String numeroConta, String digitoConta, String numeroAgencia, String digitoAgencia, Banco banco, Cliente cliente) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.digitoConta = digitoConta;
        this.numeroAgencia = numeroAgencia;
        this.digitoAgencia = digitoAgencia;
        this.banco = banco;
        this.cliente = cliente;
    }

    public ContaBancaria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(String digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(String digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
