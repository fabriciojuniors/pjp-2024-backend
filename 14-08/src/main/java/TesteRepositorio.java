import modelo.Marca;
import repositorios.MarcaRepository;

import java.util.List;

public class TesteRepositorio {

    public static void main(String[] args) {
        //CRIAR MARCA
        Marca novaMarca = new Marca();
        novaMarca.setNome("Chevrolet");
        MarcaRepository.criar(novaMarca);

        //LISTAR TODAS AS MARCAS
        List<Marca> marcas = MarcaRepository.listar();

        for (Marca marca : marcas) {
            System.out.println(marca.getNome());
        }
    }

}
