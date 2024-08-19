package repositorios;

import configuracao.Conexao;
import modelo.Marca;

import java.util.List;

public class MarcaRepository {

    //CRUD - CREATE, READ, UPDATE E DELETE - LISTAR TODOS

    //CRIAR
    public static Marca criar(Marca marca) {
        //Abrir transação
        Conexao.getEntityManager().getTransaction().begin();
        //Salvar o objeto
        Conexao.getEntityManager().persist(marca);
        //Fechar a transação
        Conexao.getEntityManager().getTransaction().commit();

        return marca;
    }

    //LER POR ID
    public static Marca encontrar(int id) {
        return Conexao.getEntityManager().find(Marca.class, id);
    }

    //ATUALIZAR
    public static Marca atualizar(Marca marca) {
        Conexao.getEntityManager().getTransaction().begin();
        Conexao.getEntityManager().merge(marca);
        Conexao.getEntityManager().getTransaction().commit();
        return marca;
    }

    //EXCLUIR
    public static void excluir(Marca marca) {
        Conexao.getEntityManager().getTransaction().begin();
        Conexao.getEntityManager().remove(marca);
        Conexao.getEntityManager().getTransaction().commit();
    }

    //LISTAR TODOS
    public static List<Marca> listar() {
        return Conexao.getEntityManager()
                .createQuery("select m from Marca m", Marca.class)
                .getResultList();
    }

}
