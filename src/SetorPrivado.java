public class SetorPrivado extends Setor {
    
    public SetorPrivado (Pos coord, boolean[] portas){
        super(coord, portas);
    }
    
    @Override
    public boolean podeProcurar(){
        return false;
    }

    public boolean resolverAtaque(Player player, Inimigo inimigo){
        player.atacar(inimigo);
        return true;
    }
}