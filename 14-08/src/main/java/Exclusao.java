import configuracao.Conexao;
import modelo.Marca;

public class Exclusao {
    public static void main(String[] args) {
        Marca marca = Conexao.getEntityManager()
                .find(Marca.class, 1);

        Conexao.getEntityManager().getTransaction().begin();

        Conexao.getEntityManager().remove(marca);

        Conexao.getEntityManager().getTransaction().commit();
    }
}
