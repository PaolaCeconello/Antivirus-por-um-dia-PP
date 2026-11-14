import java.util.List;

public class Tabuleiro {
    private Setor[][] matriz;
    private Pos fonteDeInfeccao;

    public Tabuleiro(){
        Dado dado = new Dado();
        
        this.matriz = new Setor[5][5];
        
        int x, y;
        do {
            x = dado.rolar(0,4);
            y = dado.rolar(0,4);
        } while(x == 2 && y == 2);
        
        this.fonteDeInfeccao = new Pos(x,y);
    }

    public void setMatriz (Setor [][] matriz){
        this.matriz = matriz;
    }

    public Setor[][] getMatriz(){
        return matriz;
    }

    public void setFonteDeInfeccao(Pos fi){
        this.fonteDeInfeccao = fi;
    }

    public Pos getFonteDeInfeccao(){
        return this.fonteDeInfeccao;
    }

    public Setor getSetor (Pos coord){
        int x = coord.getX();
        int y = coord.getX();
       
        Setor s = this.matriz[x][y];

        return s;
    }
}
