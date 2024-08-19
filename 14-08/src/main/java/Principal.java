import configuracao.Conexao;
import modelo.Carro;
import modelo.Marca;

public class Principal {
    public static void main(String[] args) {
        Marca nike = new Marca();
        nike.setNome("nike");

        //INICIA A TRANSAÇÃO COM O BANCO DE DADOS
        Conexao.getEntityManager()
                .getTransaction().begin();

        // SALVAR O OBJETO
        Conexao.getEntityManager()
                .persist(nike);

        //EFETIVAR TRANSAÇÃO
        Conexao.getEntityManager()
                .getTransaction().commit();

        Marca marca = Conexao.getEntityManager()
                .find(Marca.class, 1);

        System.out.println(marca.getId() + " - " + marca.getNome());


        Carro carro = new Carro();
        carro.setAno(2024);
        carro.setCor("preto");
        carro.setModelo("NOVO LANÇAMENTO DA NIKE");
        carro.setPlaca("AAA7810");
        carro.setMarca(marca);

        Conexao.getEntityManager().getTransaction().begin();
        Conexao.getEntityManager().persist(carro);
        Conexao.getEntityManager().getTransaction().commit();


    }
}
