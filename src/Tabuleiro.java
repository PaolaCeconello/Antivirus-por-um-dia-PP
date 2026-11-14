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
        criarSetores();
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
        int y = coord.getY();
       
        Setor s = this.matriz[x][y];

        return s;
    }

    private void criarSetores(){
        Dado dado = new Dado();

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                boolean[] portas = new boolean[5];
                portas[1] = (i > 0);
                portas[2] = (i < 4);
                portas[3] = (j < 4);
                portas[4] = (j > 0);
                Pos coord = new Pos(i, j);

                int tipo = dado.rolar(1, 3);

                if ((i == 2 && j == 2) || tipo == 1)
                    this.matriz[i][j] = new SetorNormal(coord, portas);
                else if (tipo == 2)
                    this.matriz[i][j] = new SetorOculto(coord, portas);
                else
                    this.matriz[i][j] = new SetorPrivado(coord, portas);
            }
        }
    }
}
