// comentar depois

public abstract class Personagem implements Atacante{
    protected int atk; 
    protected int def;
    // setor ainda nao foi criado
    // private Setor setorAtual; 

    // obs: colocar o Setor aqui dps
    public Personagem(int atk, int def) {
        this.setAtk(atk);
        this.setDef(def);
    }

    public int getAtk(){
        return this.atk;
    }

    public int getDef(){
        return this.def;
    }

    // colocar o getSetor aqui

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