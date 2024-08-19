package modelo;

import enums.TipoCarro;
import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "modelo", nullable = false, length = 120)
    private String modelo;

    @Column(name = "ano", nullable = false)
    private int ano;

    @Column(name = "cor", length = 50)
    private String cor;

    @Column(name = "placa", length = 7, unique = true)
    private String placa;

    @JoinColumn(name = "id_marca", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Marca marca;

    @Column(name = "tipo_carro")
    @Enumerated(EnumType.STRING)
    private TipoCarro tipoCarro;

    /*
    * EAGER --> select * from carros join marcas on carros.id_marca = marca.id;
    * LAZY  --> select * from carros; select * from marcas where id = carro.id;
    * */

    /*
    * UM - UM --> @OneToOne
    * UM - MUITOS --> @OneToMany
    * MUITOS - UM --> @ManyToOne
    * MUITOS - MUITOS --> @ManyToMany
    * */

    /*
    * ManyToOne
    * 1 - Many --> Carro
    * 2 - One  --> Marca
    * */

    public Carro() {
    }

    public Carro(int id, String modelo, int ano, String cor, String placa, Marca marca,
                 TipoCarro tipoCarro) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.placa = placa;
        this.marca = marca;
        this.tipoCarro = tipoCarro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoCarro getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(TipoCarro tipoCarro) {
        this.tipoCarro = tipoCarro;
    }
}
