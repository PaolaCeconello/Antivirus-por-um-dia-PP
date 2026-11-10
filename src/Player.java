public class Player extends Personagem{
    private String nomePlayer;

    public Player(String nomePlayer, int atk, int def){
        super(atk, def);
        this.setNomePlayer(nomePlayer);
    }

    public Player(String nomePlayer){
        this(nomePlayer, 2, 6);
    }

    public String getNomePlayer(){
        return this.nomePlayer;
    }

    public void setNomePlayer(String nomePlayer){
        this.nomePlayer = nomePlayer;
    }

    @Override
    public void atacar(Personagem alvo){
        alvo.setDef(alvo.getDef() - this.getAtk());
    }

    public void procurarPlayer(){
        Dado dado = new Dado();
        int valor = dado.rolar(1,6);

        if (valor == 4){
            this.setDef(this.getDef() + 1);
        } else if (valor == 5) {
            this.setDef(this.getDef() + 2);
        } else if (valor == 6){
            // TIRA DE VIDA TD MUNDO DO SETOR (DOS INIMIGOS )
        }
    }

    // depende do mundo e to setor
    // public void movimentarPlayer(Direcao d)
}