import modelo.Carro;
import repositorios.CarroRepository;

import java.util.List;

public class TesteAbstract {

    public static void main(String[] args) {
        CarroRepository carroRepository = new CarroRepository();
        List<Carro> carros = carroRepository.findAll();

        carros.forEach(c -> System.out.println(c.getAno()));
    }
}
