import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo{
    private int turno;                  // quantos ciclos de 25 já foram
    private boolean fim;                // se o jogo acabou ou nao
    private Tabuleiro tabuleiro;        // o tabuleiro do jogo
    private List<Player> players;       // a lista do jogo
    private Scanner entrada;            // input do teclado que sera usado no jogo
    private Renderizador renderizador;  // Renderizador da tela
    private Player vencedor;            // o Player que venceu o jogo

    public Jogo() {
        this.turno = 0;
        this.fim = false;
        this.players = new ArrayList<>();
        this.entrada = new Scanner(System.in);
        this.renderizador = new Renderizador();
        this.vencedor = null;
    }

    // cria o tabuleiro e cria os players
    public void iniciarMundo() {

        this.tabuleiro = new Tabuleiro();
        
        // cria os personagens e add eles no array
        Player p1 = new Player("P1");
        PlayerSuporte p2 = new PlayerSuporte("P2",p1);
        this.players.add(p1);
        this.players.add(p2);

        // coloca os herois no centro do tabuleiro
        Pos centro = new Pos(2,2);
        Setor setorCentral = tabuleiro.getSetor(centro);
        setorCentral.setVisitado(); // para o setor central não gerar inimigos
        p1.setSetorAtual(setorCentral);
        p2.setSetorAtual(setorCentral);
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

            // após o fim checa quantos personagens estão vivos
            int vivos = 0;
            for (Player p : players) {
                if (p.isVivo()) {
                    vivos++;
                }
            }

            if (turno >= 25 || vivos == 0){
                fim = true;
            }
        }

        // mostra como o jogo terminou
        if (vencedor != null) {
            System.out.println(vencedor.getNomePlayer() + " encontrou a fonte de infecção e venceu!");
        } else {
            System.out.println("A infecção ganhou, boa tentativa!");
        }
    }

    private void turnoPlayer(Player p){
        this.renderizador.renderizarTela(tabuleiro, players.get(0), (PlayerSuporte) players.get(1));
        
        if(!p.getSetorAtual().temInimigosVivos()){
            mover(p);
            if(fim){
                return; // chegou na posição x e ganhou o jogo
            }
            //chama de novo pois trocou de setor
            this.renderizador.renderizarTela(tabuleiro, players.get(0), (PlayerSuporte) players.get(1));
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

        int opcao = Integer.parseInt(entrada.nextLine());

        switch(opcao) {
            case 1: 
                atacar(p);
                break;
            case 2:
                procurar(p);
                break;
            case 3:
                if (p instanceof PlayerSuporte){
                    suportar((PlayerSuporte) p);
                }
                break;
        }
        p.getSetorAtual().removerMortos(); //retira os inimigos que morreram do setor
    }


    private void mover(Player p) {

        Setor atual = p.getSetorAtual();
        System.out.println("0 - Ficar | 1 - Norte | 2 - Sul | 3 - Leste | 4 - Oeste");
        int opcao = Integer.parseInt(entrada.nextLine());
        if (opcao == 0) {
            return;
        }

        int x = atual.getCoord().getX();   
        int y = atual.getCoord().getY();   
        int porta = opcao;             // 0=Norte, 1=Sul, 2=Leste, 3=Oeste ? ta certo isso ?

        if (!atual.getPortas(porta)) {
            System.out.println("Ali é uma parede");
            return;
        }
        //sera que tem problema estar invertido?
        if (opcao == 1) {
            x--;   // vai pro norte
        } else if (opcao == 2) {
            x++;   // vai pro sul         
        } else if (opcao == 3) {
            y++;   //vai pra direita         
        } else {
            y--;   //vai pra esquerda         
        }
        
        // movimenta em si o player
        Pos destino = new Pos(x,y);
        p.movimentarPlayer(tabuleiro.getSetor(destino));

        //verifica se chegou no fim
        Pos fonte = tabuleiro.getFonteDeInfeccao();
        if (x == fonte.getX() && y == fonte.getY()) {
            vencedor = p;
            fim = true;
        }
    }

    private void atacar(Player p) {
        Setor setor = p.getSetorAtual();
        List<Inimigo> vivos = setor.getInimigos();

        if (vivos.isEmpty()) {
            System.out.println("Não há inimigos vivos neste setor!");
            return;
        }

        System.out.println("Qual inimigo atacar?");
        for (int i = 0; i < vivos.size(); i++) {
            Inimigo inimigo = vivos.get(i);
            System.out.println((i + 1) + ": Inimigo " + inimigo.getIdInimigo()
                    + " (" + inimigo.getAtk() + "/" + inimigo.getDef() + ")");
        }
        int opcao = Integer.parseInt(entrada.nextLine());
        Inimigo alvo = vivos.get(opcao - 1);

        //resultados do ataque
        boolean acertou = setor.resolverAtaque(p, alvo);
        if (!acertou) {
            System.out.println("O ataque não acertou o vírus");
        } else if (!alvo.isVivo()) {
            System.out.println("Inimigo " + alvo.getIdInimigo() + " eliminado!");
        } else {
            System.out.println("Inimigo " + alvo.getIdInimigo() + " ficou com "
                    + alvo.getAtk() + "/" + alvo.getDef());
        }
    }

    private void procurar(Player p) {
        if (!p.getSetorAtual().podeProcurar()) {
            System.out.println("O setor é privado.");
            return;
        }
        p.procurarPlayer();
        System.out.println(p.getNomePlayer() + " procurou no setor."); //falta dar feedback visual do que aconteceu?
    }

    private void suportar(PlayerSuporte suporte) {
        System.out.println("1 - Recuperar a sua defesa");
        System.out.println("2 - Recuperar a defesa do outro Player");
        int opcao = Integer.parseInt(entrada.nextLine());
        
        //essa parte ficou meio confusa se o jeito de usar as funcoes ta certo
        if (opcao == 1) {
            suporte.suportar();
        } else {
            suporte.suportar(suporte.getSimples());
        }
    }
    // inimigos atacam
    private void turnoInimigos() {
        for (Player p : players) {
            if (!p.isVivo()) {
                continue; 
            }
            for (Inimigo inimigo : p.getSetorAtual().getInimigos()) {
                if (inimigo.isVivo() && p.isVivo()) {
                    int defAntes = p.getDef();
                    inimigo.atacar(p);
                    //jeito de ver se o ataque aconteceu ou nao
                    if (p.getDef() < defAntes) {
                        System.out.println("Inimigo " + inimigo.getIdInimigo() + " acertou " + p.getNomePlayer());
                    } else {
                        System.out.println("Inimigo " + inimigo.getIdInimigo() + " errou " + p.getNomePlayer());
                    }
                }
            }
            if (!p.isVivo()) {
                System.out.println(p.getNomePlayer() + " foi eliminado");
            }
        }
    }
}
