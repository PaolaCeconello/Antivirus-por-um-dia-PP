import java.util.Random;

public class Dado {
    // atributos
    private Random aleatorio;

    // construtor
    public Dado() {
        this.aleatorio = new Random();
    }

    // metodos
    public int rolar(int min, int max) {
        return this.aleatorio.nextInt(max - min + 1) + min;
    }
}