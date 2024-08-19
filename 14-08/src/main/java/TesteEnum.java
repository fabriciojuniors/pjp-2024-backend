import enums.TipoCarro;
import modelo.Carro;

public class TesteEnum {
    public static void main(String[] args) {
        Carro carro = new Carro();

        carro.setTipoCarro(TipoCarro.SEDAN);
    }
}
