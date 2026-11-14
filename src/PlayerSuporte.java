public class PlayerSuporte extends Player{
    // atributos 
    private Player simples;

    // construtores
    public PlayerSuporte(String nomePlayer, Player simples){
        super(nomePlayer, 1, 7);
        this.setSimples(simples);
    }

    // gets e sets
    public Player getSimples(){
        return this.simples;
    }

    public void setSimples(Player simples){
        if (simples != null){
            this.simples = simples;
        }
    }

    // outros metodos
    public void suportar(){
        if (this.isVivo()){
            this.setDef(this.getDef() + 2);
        }
    }

    public void suportar(Player playerUm){
        if (playerUm.isVivo() && this.isVivo() && playerUm.getSetorAtual() == this.getSetorAtual()){
            playerUm.setDef(playerUm.getDef() + 2);
        }
    }
}