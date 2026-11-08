public abstract class Personagem implements Atacante{
    private int ATK; 
    private int DEF;
    // setor ainda nao foi criado
    // private Setor setorAtual; 
    private boolean vivo;

    // obs: colocar o Setor aqui dps
    public Personagem(int ATK, int DEF) {
        setATK(ATK);
        setDEF(DEF);
    }

    public abstract void atacar(Personagem alvo);

    public int getATK(){
        return this.ATK;
    }

    public int getDEF(){
        return this.DEF;
    }

    // colocar o getSetor aqui

    public boolean getVivo(){
        return this.vivo;
    }

    public void setATK(int ATK){
        this.ATK = ATK;
        return;
    }

    public void setDEF(int DEF){
        this.DEF = DEF;

        if (DEF <= 0){
            this.vivo = false;
        } else {
            this.vivo = true;
        }
        return;
    }
}