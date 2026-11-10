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
    public void suportar(){
        if (this.isVivo()){
            this.setDef(this.getDef() + 2);

        }
    }

    public void suportar(Player playerUm){
        // VERIFICAR SE ESTÁ NO MESMO SETOR AQUI
        // como n tem setor ainda nao consigo fazer isso
        if (playerUm.isVivo() && this.isVivo()){
            playerUm.setDef(playerUm.getDef() + 2);
        }       
    }
}