import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo{
    private int turno;                  // quantos ciclos de 25 já foram
    private boolean fim;                // se o jogo acabou ou nao
    private Tabuleiro tabuleiro;        // o tabuleiro do jogo
    private List<Player> players;       // a lista do jogo
    private Scanner entrada;            // input do teclado que sera usado no jogo
    private Dado dado;                  // dado para aleatorização
    private Renderizador renderizador;  // Renderizador da tela
    /* renderizador n deveria ser static? fiquei pensando só p nao ter que criar um atributo mas n
    sei se faz sentido   */

    public Jogo() {
        this.turno = 0;
        this.fim = false;
        this.players = new ArrayList<>();
        this.entrada = new Scanner(System.in);
        this.dado = new Dado();
        this.renderizador = new Renderizador();
    }

    // cria o tabuleiro, sorteia a fonte de infecção e cria os players
    public void iniciarMundo() {
        this.tabuleiro = new Tabuleiro();
        //talvez faça sentido criar um metodo privado inicializa tabuleiro? pra por tudo isso
        int x, y;
        do {
            x = this.dado.rolar(0,4);
            y = this.dado.rolar(0,4);
        } while(x == 2 && y == 2); //caiu bem no meio, que não pode, então rola de novo

        Pos fonte = new Pos();
        fonte.setX(x);
        fonte.setY(y);
        tabuleiro.setFonteDeInfeccao(fonte);

        // cria os personagens e add eles no array
        Player p1 = new Player("P1");
        PlayerSuporte p2 = new PlayerSuporte("P2",p1)
        this.players.add(p1);
        this.players.add(p2);
    }

    public void jogar() {
        while (!fim) {
            turno++;

            for (Player p : players) {
                if (p.isVivo() && !fim) {
                    turnoPlayer(p);
                }
            }

            if(!fim){
                turnoInimigos();
            }  

            if (turno >= 25 || todososplayersmorreram ){
                fim = true;
            }
        }
    }

    private void turnoPlayer(Player p){
        Renderizador.renderizarTela(tabuleiro, players.get(0), (PlayerSuporte) players.get(1));
        
        if(p.getSetor().temInimigosVivos){
            mover(p);
            if(fim){
                return; // chegou na posição x e ganhou o jogo
            }
        }

        for (int i = 1; i <= 2; i++) { //executa as duas ações
            executarAcao(p);
        }
    }

    private void executarAcao(Player p){
        System.out.println("1 - Atacar");
        System.out.println("2 - Procurar");
        if (p instanceof PlayerSuporte) { 
            System.out.println("3 - Recuperar defesa");
        }

        int opcao = Integer.parseInt(input.nextLine());

        switch(opcao) {
            case 1: 
                atacar(p);
                break;
            case 2:
                procurar(p);
                break;
            case 3:
                suportar((PlayerSuporte) p);
                break;
        }
    }
}