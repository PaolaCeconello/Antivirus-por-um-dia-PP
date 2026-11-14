public class PlayerSuporte extends Player{

    private Player simples;

    public PlayerSuporte(String nomePlayer, Player simples){
        super(nomePlayer, 1, 7);
        this.setSimples(simples);
    }

    public Player getSimples(){
        return this.simples;
    }

    public void setSimples(Player simples){
        if (simples != null){
            this.simples = simples;
        }
    }
    public void suportar(Player playerUm){
        if (playerUm.isVivo() && this.isVivo() && playerUm.setorAtual() == this.getSetorAtual()){
            this.setDef(this.getDef() + 2);
        }
    }

    public void suportar(Player playerUm){
        if (playerUm.isVivo() && this.isVivo() && playerUm.getSetorAtual() == this.getSetorAtual()){
            playerUm.setDef(playerUm.getDef() + 2);
        }
    }
}