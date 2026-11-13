import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo{
    private int turno;              // quantos ciclos de 25 já foram?
    private boolean fim;            // se o jogo acabou ou nao
    private Tabuleiro tabuleiro;    // o tabuleiro do jogo
    private List<Player> players;   // a lista do jogo
    private Scanner entrada;          // input do teclado que sera usado no jogo
    private Dado dado;              // dado para aleatorização
    private Renderizador renderizador; 
    /* renderizador n deveria ser static? */

    public Jogo() {
        this.turno = 0;
        this.fim = false;
        this.players = new ArrayList<>();
        this.entrada = new Scanner(System.in);
        this.dado = new Dado();
        this.renderizador = new Renderizador();
    }

    public void iniciarMundo() {
        // vai ter que criar tabuleiro, sortear a fonte da infecção
        this.tabuleiro = new Tabuleiro();
        //talvez faça sentido criar um metodo privado inicializa tabuleiro? 
        int x, y;
        do {
            x = this.dado.rolar(0,4);
            y = this.dado.rolar(0,4);
        } while(x == 2 && y == 2); //caiu bem no meio, que não pode, então rola de novo

        Pos fonte = new Pos();
        fonte.setX(x);
        fonte.setY(y);
        tabuleiro.setFonteDeInfeccao(fonte);
        // tem que criar os players e colocar eles no centro.
        // work in progress...
        Player p1 = new Player("P1");
        PlayerSuporte p2 = new PlayerSuporte("P2",p1)
        this.players.add(p1);
        this.players.add(p2);
    }

    public void jogar() {
        while (!fim) {
            turno++;
            Renderizador.renderizarTela(tabuleiro, players.get(0), (PlayerSuporte) players.get(1));
            //roda o turno do p1, vem o turno do p2, turno dos inimigos, renderizações

            if (turno >= 25){
                fim = true;
            }
        }
    }
}