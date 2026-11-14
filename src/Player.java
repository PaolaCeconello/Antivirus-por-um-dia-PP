public class Player extends Personagem{
    // atributos 
    private String nomePlayer;

    // construtores
    public Player(String nomePlayer, int atk, int def){
        super(atk, def);
        this.setNomePlayer(nomePlayer);
    }

    public Player(String nomePlayer){
        this(nomePlayer, 2, 6);
    }

    // gets e sets
    public String getNomePlayer(){
        return this.nomePlayer;
    }

    public void setNomePlayer(String nomePlayer){
        this.nomePlayer = nomePlayer;
    }

    // outros metodos 
    @Override
    public void atacar(Personagem alvo){
        alvo.setDef(alvo.getDef() - this.getAtk());
    }

    public void procurarPlayer(){

        // verifica se pode pode procurar e se o setor ja foi definido
        if (this.setorAtual == null || !this.setorAtual.podeProcurar()){
            return;
        }

        Dado dado = new Dado();
        int valor = dado.rolar(1,6);

        if (valor == 4){
            this.setDef(this.getDef() + 1);
        } else if (valor == 5) {
            this.setDef(this.getDef() + 2);
        } else if (valor == 6){
            for (Inimigo inimigo : this.setorAtual.getInimigos()){
                inimigo.setDef(inimigo.getDef() - 1);
            }
        }
    }

    public void movimentarPlayer(Setor setor){
        if (setorAtual == null || setor == null){
            return;
        }
        
        if (this.setorAtual.temInimigosVivos()){
            return;
        }

        this.setSetorAtual(setor);
        setor.visitar();
    }
}