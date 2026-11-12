import java.util.List;
import java.util.ArrayList;

public abstract class Setor{
    protected Pos coord;
    protected boolean[] portas = new boolean [4];
    protected boolean visitado;
    protected List<Inimigo> inimigosNoSetor = new ArrayList<>();

public void gerarInimigos(){
    for (Inimigo inimigo : inimigosNoSetor){
        Dado dado = new Dado();
        int valor = dado.rolar(1,3);
        inimigo.setAtk(valor);
        inimigo.setDef(valor);
    }
}

public void setCoord (Pos coord){
    this.coord = coord;
}

public Pos getCoord(){
    return this.coord;
}

public void setVisitado(){
    if (this.visitado = false)
        this.visitado = true;
}

public boolean getVisitado(){
    return this.visitado;
}
    
public boolean getPortas(int portas){
    return this.portas[portas];
}

//visitar(): void oq essa função faz???

public List<Inimigo> getInimigos(){
    return new ArrayList<>(inimigosNoSetor);
}

public boolean temInimigosVivos(){
    for (Inimigo inimigo : inimigosNoSetor) {
    if (inimigo.isVivo()) {
        return true;
    }}
    return false;
}

public abstract boolean podeProcurar();
public abstract boolean resolverAtaque(Player player, Inimigo inimigo);

}