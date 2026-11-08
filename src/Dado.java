import java.util.Random;

public class Dado {
    private Random aleatorio;

    public Dado() {
        this.aleatorio = new Random();
    }

    public int rolar(int min, int max) {
        return this.aleatorio.nextInt(max - min + 1) + min;
    }
}