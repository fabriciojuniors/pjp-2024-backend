import configuracao.Conexao;
import jakarta.persistence.EntityManager;
import modelo.Carro;

import java.util.List;

public class Buscas {
    public static void main(String[] args) {
        EntityManager em = Conexao.getEntityManager();

        //BUSCA 1 REGISTRO POR ID
        Carro carro = em.find(Carro.class, 1);
        System.out.println(carro.getModelo());
        System.out.println(carro.getMarca().getNome());

        //BUSCA JPQL
        //SELECT * FROM CARROS;
        List<Carro> carros = em.createQuery("SELECT carro " +
                                "FROM Carro carro " +
                                "WHERE carro.modelo = ''",
                        Carro.class)
                .getResultList();

        for (Carro c : carros) {
            System.out.println(c.getModelo());
        }
    }
}
