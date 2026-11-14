public class SetorOculto extends Setor {
    
    public SetorOculto (Pos coord, boolean[] portas){
        super(coord, portas);
    }
    
    @Override
    public boolean podeProcurar(){
        return true;
    }

    //Rola um dado com dois numeros, ataca caso role 1 e 
    // não ataca caso caia 2
    public boolean resolverAtaque(Player player, Inimigo inimigo) {
        Dado dado = new Dado();
        int valor = dado.rolar(1,2);

        if (valor == 1) {
            player.atacar(inimigo);
            return true;
        }

        return false;
    }
}