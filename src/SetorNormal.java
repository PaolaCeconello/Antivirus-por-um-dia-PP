public class SetorNormal extends Setor {
    
    public SetorNormal (Pos coord, boolean[] portas){
        super(coord, portas);
    }
    
    @Override
    public boolean podeProcurar(){
        return true;
    }

    public boolean resolverAtaque(Player player, Inimigo inimigo){
        player.atacar(inimigo);
        return true;
    }
}

