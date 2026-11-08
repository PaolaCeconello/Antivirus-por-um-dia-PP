public class Inimigo extends Personagem{
    private int idInimigo;

    public Inimigo(int valor, int idInimigo){
        super(valor, valor); // quem vai aleatorizar o valor e quem chama
        this.setIdInimigo(idInimigo);
    }

    public int getIdInimigo(){
        return this.idInimigo;
    }

    public void setIdInimigo(int idInimigo){
        this.idInimigo = idInimigo;
    }

    @Override
    public void atacar(Personagem alvo){
        int valor = Dado.rolar(1,6);

        if (valor % 2 == 0){
            alvo.setDef(alvo.getDef() - this.getAtk());
        }
        
    }
}