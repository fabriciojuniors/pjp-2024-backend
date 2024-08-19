import configuracao.Conexao;
import modelo.Marca;

public class Atualizacao {
    public static void main(String[] args) {
        Marca marca = Conexao.getEntityManager()
                .find(Marca.class, 1);

        System.out.println(marca.getNome());

        marca.setNome("Nike atualizado");

        Conexao.getEntityManager().getTransaction().begin();

        Conexao.getEntityManager().merge(marca);

        Conexao.getEntityManager().getTransaction().commit();
    }
}
