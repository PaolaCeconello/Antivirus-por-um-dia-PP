public class Inimigo extends Personagem{
    // atributos 
    private int idInimigo;

    // construtor
    public Inimigo(int valor, int idInimigo){
        super(valor, valor); // quem vai aleatorizar o valor e quem chama
        this.setIdInimigo(idInimigo);
    }

    // gets e sets
    public int getIdInimigo(){
        return this.idInimigo;
    }

    public void setIdInimigo(int idInimigo){
        this.idInimigo = idInimigo;
    }

    // implementacao do Atacar
    @Override
    public void atacar(Personagem alvo){
        Dado dado = new Dado();
        int valor = dado.rolar(1,6);

        if (valor % 2 == 0){
            alvo.setDef(alvo.getDef() - this.getAtk());
        }
        
    }
}