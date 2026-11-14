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
    public int suportar(){
        if (this.isVivo()){
            this.setDef(this.getDef() + 2);
            return 1;
        }
        
        return 0;
    }

    public int suportar(Player playerUm){
        if (playerUm.isVivo() && this.isVivo() && playerUm.getSetorAtual() == this.getSetorAtual()){
            playerUm.setDef(playerUm.getDef() + 2);
            return 1;
        }

        return 0;
    }
}