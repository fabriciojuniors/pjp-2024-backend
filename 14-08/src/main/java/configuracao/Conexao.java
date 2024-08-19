package configuracao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Conexao {
    private final static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("projetoPU");

    private final static EntityManager em = emf.createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }
}
