public abstract class Personagem implements Atacante{
    // atributos 
    protected int atk; 
    protected int def;
    protected Setor setorAtual;

    // construtor
    public Personagem(int atk, int def) {
        this.setAtk(atk);
        this.setDef(def);
    }

    // gets e sets
    public int getAtk(){
        return this.atk;
    }

    public int getDef(){
        return this.def;
    }

    public Setor getSetorAtual(){
        return this.setorAtual;
    }

    public void setSetorAtual(Setor setorAtual){
        this.setorAtual = setorAtual;
    }
    
    public boolean isVivo(){
        return (this.def > 0);
    }

    public void setAtk(int atk){
        if (atk >= 0){
            this.atk = atk;

        }
    }
    public void setDef(int def){
        this.def = def;
    }
}