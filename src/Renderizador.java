import java.util.List;
import java.util.Arrays;

public class Renderizador {

    public static final int NORTE = 1, SUL = 2, LESTE = 3, OESTE = 4;

    public String[] montarTabuleiro(Tabuleiro tabuleiro) {
        String[] linhas = new String[12];
        Pos coord = new Pos();

        Arrays.fill(linhas, "");

        linhas[0] = "       1   2   3   4   5";

        for (int line = 0; line < 5; line++) {
            for (int i = 0; i < 3; i++) {

                if ((i == 0) && (line > 0))
                    continue;

                for (int column = 0; column < 5; column++) {

                    coord.setX(line);
                    coord.setY(column);

                    if (column == 0) {
                        if (i == 1)
                            linhas[(line+1)*2+i-1] += String.format("%d    |", line + 1);
                        else
                            linhas[(line+1)*2+i-1] += "     |";
                    }

                    if ((line == 0) && (i == 0))
                        linhas[1] += "---|";

                    if (i == 1) {

                        if ((line == 2) && (column == 2))
                            linhas[6] += " C ";
                        else if ((tabuleiro.getFonteDeInfeccao().getX() == line)
                                && (tabuleiro.getFonteDeInfeccao().getY() == column))
                            linhas[(line+1)*2+i-1] += " X ";
                        else
                            linhas[(line+1)*2+i-1] += "   ";

                        if ((tabuleiro.getSetor(coord).getVisitado()) && (tabuleiro.getSetor(coord).getPortas(LESTE)))
                            linhas[(line+1)*2+i-1] += "*";
                        else
                            linhas[(line+1)*2+i-1] += "|";

                    } else if (i == 2) {

                        if ((tabuleiro.getSetor(coord).getVisitado()) && (tabuleiro.getSetor(coord).getPortas(SUL)))
                            linhas[(line+1)*2+i-1] += "-*-|";
                        else
                            linhas[(line+1)*2+i-1] += "---|";

                    }

                }
            }
        }
        return linhas;
    }

    public String[] montarSetor(Setor setor, Player player1, PlayerSuporte player2) {
        String[] linhas = new String[10];
        List<Inimigo> inimigos = setor.getInimigos();

        linhas[0] = String.format("  Setor [%d,%d]  ", setor.getCoord().getX(), setor.getCoord().getY());
        linhas[1] = "";

        // linha 0
        if (setor.getPortas(NORTE))
            linhas[2] = "|------*------|";
        else 
            linhas[2] = "|-------------|";

        linhas[3] = "| ";
        int tam = inimigos.size();
        for(int j = 0; j < 3; j++) {
            if (j >= tam)
                linhas[3] += "    ";
            else
                linhas[3] += String.format("%d/%d ", inimigos.get(j).getAtk(), inimigos.get(j).getDef());
        }
        linhas[3] = linhas[3] += "|";
        
        linhas[4] = "|             |";

        // linha 3
        if (setor.getPortas(OESTE)) {
            linhas[5] = "*";
        } else { 
            linhas[5] = "|";
        }
        linhas[5] += "             ";

        if (setor.getPortas(LESTE)) {
            linhas[5] += "*";
        } else { 
            linhas[5] += "|";
        }

        // linha 4
        if ((player1.isVivo()) && (player1.getSetorAtual() == setor)) {
            linhas[6] = "|  P1   ";
        } else {
            linhas[6] = "|       ";
        }

        if ((player2.isVivo()) && (player2.getSetorAtual() == setor)) {
            linhas[6] += "P2    |";
        } else {
            linhas[6] += "      |";
        }

        // linha 5
        if ((player1.isVivo()) && (player1.getSetorAtual() == setor)) {
            if (player1.getDef() >= 10)
                linhas[7] = String.format("|  %d/%2d ", player1.getAtk(), player1.getDef());
            else
                linhas[7] = String.format("|  %d/%d  ", player1.getAtk(), player1.getDef());
        } else {
            linhas[7] = "|       ";
        }

        if ((player2.isVivo()) && (player2.getSetorAtual() == setor)) {
            if (player2.getDef() >= 10)
                linhas[7] += String.format("%d/%2d  |", player2.getAtk(), player2.getDef());
            else 
                linhas[7] += String.format("%d/%d   |", player2.getAtk(), player1.getDef());
        } else {
            linhas[7] += "      |";
        }
        
        // linha 6
        if (setor.getPortas(SUL)){
            linhas[8] = "|------*------|";
        } else {
            linhas[8] = ("|-------------|");
        }

        linhas[9] = "";

        return linhas;
    }

    public void renderizarTela(Tabuleiro tabuleiro, Player player1, PlayerSuporte player2) {
        String[] tabMontado = montarTabuleiro(tabuleiro);
        String[] painel1 = null;
        String[] painel2 = null;

        if ((player1.isVivo()) || ((player2.isVivo()) && (player1.getSetorAtual() == player2.getSetorAtual())))   
            painel1 = montarSetor(player1.getSetorAtual(), player1, player2);

        if ((player2.isVivo()) && (player2.getSetorAtual() != player1.getSetorAtual()))
            painel2 = montarSetor(player2.getSetorAtual(), player1, player2);

        for (int i = 0; i < 12; i++) {
            if (i >= 2) {
                if (((painel1 == painel2) && (painel1 != null)) || ((painel1 != null) && (painel2 == null))) {
                    System.out.println(tabMontado[i] + "   " + painel1[i-2]);
                }
                else if ((painel2 != null) && (painel1 == null)) {
                    System.out.println(tabMontado[i] + "   " + painel2[i-2]);
                } else {
                    System.out.println(tabMontado[i] + "   " + painel1[i-2] + "   " + painel2[i-2]);
                }
            } else {
                System.out.println(tabMontado[i]);
            }
        }
    }

}