package modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "marcas")
public class Marca {

    @Column(name = "codigo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome",length = 150, unique = true, nullable = false)
    private String nome;

    public Marca() {
    }

    public Marca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
