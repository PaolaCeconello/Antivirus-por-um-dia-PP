import java.util.List;
import java.util.ArrayList;

public abstract class Setor{
    private Pos coord;
    private boolean[] portas;
    private boolean visitado;
    private List<Inimigo> inimigosNoSetor;

public Setor(Pos coord, boolean[] portas){
    this.coord = coord;
    this.portas = portas;
    this.visitado = false;
    this.inimigosNoSetor = new ArrayList<>();
} 

private void gerarInimigos(){
    Dado dado = new Dado();
    int quantidade = dado.rolar(1,3);
    
    for (int i = 0; i < quantidade; i++){
        int valor = dado.rolar(1,3);
        Inimigo inimigo = new Inimigo(valor, i+1);
        
        inimigosNoSetor.add(inimigo);
    }
}

public void setCoord (Pos coord){
    this.coord = coord;
}

public Pos getCoord(){
    return this.coord;
}

public void setVisitado(){
    if (this.visitado == false)
        this.visitado = true;
}

public boolean getVisitado(){
    return this.visitado;
}
    
public boolean getPortas(int i){
    return this.portas[i];
}

public void visitar(){
    if (!this.visitado) {
        setVisitado();
        gerarInimigos();
    }
}

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