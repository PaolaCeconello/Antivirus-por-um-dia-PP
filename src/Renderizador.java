import java.util.List;
import java.util.Arrays;

public class Renderizador {

    // Constantes auxiliares
    public static final int NORTE = 1, SUL = 2, LESTE = 3, OESTE = 4;

    // Monta o tabuleiro em um vetor de strings
    public String[] montarTabuleiro(Tabuleiro tabuleiro, Player player1, PlayerSuporte player2) {
        String[] linhas = new String[12];

        // Adiciona um vazio as linhas para que seja possível somar strings
        Arrays.fill(linhas, "");

        // Definição linha 0
        linhas[0] = "       1   2   3   4   5";

        // Percorre todos os setores, que são divididos em 3 linhas (percorridas com i)
        for (int line = 0; line < 5; line++) {
            for (int i = 0; i < 3; i++) {
                for (int column = 0; column < 5; column++) {

                    Pos coord = new Pos(line, column); // Variável auxiliar
                    Pos coordBaixo = new Pos(line + 1, column);
                    Pos coordDir = new Pos(line, column + 1);

                    if ((i == 0) && (line > 0))
                        continue;

                    // Adiciona o número da linha à esquerda do tabuleiro
                    if (column == 0) {
                        if (i == 1)
                            linhas[(line + 1) * 2 + i - 1] += String.format("%d    |", line + 1);
                        else
                            linhas[(line + 1) * 2 + i - 1] += "     |";
                    }

                    // Adiciona a primeira linha da linha 1, que não tem portas
                    if (i == 0) {
                        linhas[1] += "---|";
                    }

                    if (i == 1) {
                        // Confere se o setor é o central (escreve C) ou a fonte de infecção (escreve
                        // X).
                        // Se não for nenhum dos dois, adiciona um espaço vazio.
                        if ((line == 2) && (column == 2))
                            linhas[6] += " C ";
                        else if ((tabuleiro.getFonteDeInfeccao().getX() == line)
                                && (tabuleiro.getFonteDeInfeccao().getY() == column))
                            linhas[(line + 1) * 2 + i - 1] += " X ";
                        else if ((player1.getSetorAtual() == player2.getSetorAtual())
                                && (tabuleiro.getSetor(coord) == player1.getSetorAtual()))
                            linhas[(line + 1) * 2 + i - 1] += " P ";
                        else if (tabuleiro.getSetor(coord) == player1.getSetorAtual())
                            linhas[(line + 1) * 2 + i - 1] += "P1 ";
                        else if (tabuleiro.getSetor(coord) == player2.getSetorAtual())
                            linhas[(line + 1) * 2 + i - 1] += "P2 ";
                        else
                            linhas[(line + 1) * 2 + i - 1] += "   ";

                        // Confere se o setor foi visitado e se tem porta no lado LESTE.
                        // Se as duas condições forem satisfeitas, desenha a porta.
                        if (((tabuleiro.getSetor(coord).getVisitado()) && (tabuleiro.getSetor(coord).getPortas(LESTE)))
                                || ((tabuleiro.getSetor(coordDir).getVisitado())
                                        && (tabuleiro.getSetor(coordDir).getPortas(OESTE))))
                            linhas[(line + 1) * 2 + i - 1] += "*";
                        else
                            linhas[(line + 1) * 2 + i - 1] += "|";

                    } else if (i == 2) {

                        // Confere se o setor foi visitado e se tem porta no lado SUL.
                        // Se as duas condições forem satisfeitas, desenha a porta.
                        if (((tabuleiro.getSetor(coord).getVisitado()) && (tabuleiro.getSetor(coord).getPortas(SUL)))
                                || ((tabuleiro.getSetor(coordBaixo).getVisitado())
                                        && (tabuleiro.getSetor(coord).getPortas(NORTE))))
                            linhas[(line + 1) * 2 + i - 1] += "-*-|";
                        else
                            linhas[(line + 1) * 2 + i - 1] += "---|";
                    }

                }
            }
        }
        return linhas;
    }

    // Monta um setor expandido em um vetor de strings
    public String[] montarSetor(Setor setor, Player player1, PlayerSuporte player2) {
        String[] linhas = new String[10];
        List<Inimigo> inimigos = setor.getInimigos(); // Variável auxiliar

        // Definição das linhas 0 e 1
        linhas[0] = String.format("  Setor [%d,%d]  ", setor.getCoord().getX() + 1, setor.getCoord().getY() + 1);
        linhas[1] = "";

        // Definição da linha 2. Se houver porta no NORTE, a desenha.
        if (setor.getPortas(NORTE))
            linhas[2] = "|------*------|";
        else
            linhas[2] = "|-------------|";

        // Definição da linha 3. Desenha todos os inimigos do setor como ATK/DEF.
        linhas[3] = "| ";
        int tam = inimigos.size();
        for (int j = 0; j < 3; j++) {
            if (j >= tam)
                linhas[3] += "    ";
            else
                linhas[3] += String.format("%d/%d ", inimigos.get(j).getAtk(), inimigos.get(j).getDef());
        }
        linhas[3] += "|";

        // Definição da linha 4
        linhas[4] = "|             |";

        // Definição da linha 5.
        // Se houver portas no OESTE ou LESTE as desenha, inserindo um espaço em branco
        // no meio.
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

        // Definição linhas 6 e 7.
        // Desenha os players dentro do setor como PX (linha 6) e ATK/DEF (linha 7).
        if ((player1.isVivo()) && (player1.getSetorAtual() == setor)) {
            linhas[6] = "|  P1   ";
            if (player1.getDef() >= 10)
                linhas[7] = String.format("|  %d/%2d ", player1.getAtk(), player1.getDef());
            else
                linhas[7] = String.format("|  %d/%d  ", player1.getAtk(), player1.getDef());
        } else {
            linhas[6] = "|       ";
            linhas[7] = "|       ";
        }

        if ((player2.isVivo()) && (player2.getSetorAtual() == setor)) {
            linhas[6] += "P2    |";
            if (player2.getDef() >= 10)
                linhas[7] += String.format("%d/%2d  |", player2.getAtk(), player2.getDef());
            else
                linhas[7] += String.format("%d/%d   |", player2.getAtk(), player2.getDef());
        } else {
            linhas[6] += "      |";
            linhas[7] += "      |";
        }

        // Definição linha 8. Se houver porta no SUL, a desenha.
        if (setor.getPortas(SUL)) {
            linhas[8] = "|------*------|";
        } else {
            linhas[8] = ("|-------------|");
        }

        // Definição linha 9.
        linhas[9] = "";

        return linhas;
    }

    // Efetivamente desenha os tabuleiros e setores montados.
    public void renderizarTela(Tabuleiro tabuleiro, Player player1, PlayerSuporte player2) {
        String[] tabMontado = montarTabuleiro(tabuleiro, player1, player2);
        String[] painel1 = null;
        String[] painel2 = null;

        // Confere a necessidade de montar dois setores diferentes, no caso em que os
        // dois players estão
        // vivos e em setores diferentes.
        if ((player1.isVivo()) || ((player2.isVivo()) && (player1.getSetorAtual() == player2.getSetorAtual())))
            painel1 = montarSetor(player1.getSetorAtual(), player1, player2);

        if ((player2.isVivo()) && (player2.getSetorAtual() != player1.getSetorAtual()))
            painel2 = montarSetor(player2.getSetorAtual(), player1, player2);

        // Imprime o tabuleiro e setores lado a lado como indicado na descrição do
        // projeto.
        for (int i = 0; i < 12; i++) {
            if (i >= 2) {
                if (((painel1 == painel2) && (painel1 != null)) || ((painel1 != null) && (painel2 == null))) {
                    System.out.println(tabMontado[i] + "   " + painel1[i - 2]);
                } else if ((painel2 != null) && (painel1 == null)) {
                    System.out.println(tabMontado[i] + "   " + painel2[i - 2]);
                } else {
                    System.out.println(tabMontado[i] + "   " + painel1[i - 2] + "   " + painel2[i - 2]);
                }
            } else {
                System.out.println(tabMontado[i]);
            }
        }
    }

}