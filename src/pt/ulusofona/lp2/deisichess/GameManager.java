package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.ArrayList;

public class GameManager {
    int boardSize;
    Tabuleiro gameBoard;

    public GameManager() {
    }

    boolean loadGame(File file) throws IOException {
        // HASHMAP PARA GUARDAR AS PEÇAS
        gameBoard.pecaHashMap = new HashMap<>();
        // LEITURA DE FICHEIROS
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        // VARIAVEIS
        int count = 2;
        int numlinhas = 0;
        int x;
        int y = 0;
        int numpecas = 0;
        int size = 0;

        // CICLO DE LEITURA
        while (line != null) {
            numlinhas++;
            if (numlinhas == 1) {
                this.boardSize = Integer.parseInt(line); //ESTÁ COMENTADO PARA TESTAR A FUNÇÃO
                size = Integer.parseInt(line);
                System.out.println("size tabuleiro = " + line + "\n");
            } else if (numlinhas == 2) {
                System.out.println("num pecas = " + line + "\n");
                numpecas = Integer.parseInt(line);
            }
            if (numlinhas >= 3 && count < numpecas + 2) {

                String[] partes = line.split(":");

                String id = partes[0];
                System.out.println("ID: " + id + "\n");

                int tipo = Integer.parseInt(partes[1]);
                System.out.println("tipo: " + tipo + "\n");

                int equipa = Integer.parseInt(partes[2].trim());
                System.out.println("equipa: " + equipa + "\n");

                String nome = partes[3].trim();
                System.out.println("nome: " + nome + "\n");

                Peca peca = new Peca(id, tipo, equipa, nome, 0, 0, true);
                gameBoard.pecaHashMap.put(id, peca);
                System.out.println(peca);
                count++;
            }
            if (numlinhas > numpecas + 2) {
                String[] partes = line.split(":", size);
                x = 0;

                for (String pos : partes) {
                    if (!Objects.equals(pos, "0")) {
                        gameBoard.pecaHashMap.get(pos).posX = x;
                        gameBoard.pecaHashMap.get(pos).posY = y;
                        System.out.println(gameBoard.pecaHashMap.get(pos).toString());
                    }
                    x++;
                    System.out.println("pos: " + pos);
                }
                y++;
            }
            line = reader.readLine();
        }
        reader.close();
        return true;
    }

    int getBoardSize() {
        return boardSize;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        // SE O boardSize = 4, ENTÃO X E Y SÓ PODEM CHEGAR A 4
        // SE O boardSize = 8, ENTÃO X E Y SÓ PODEM CHEGAR A 8
        // x0 e y0
        int equipa = 0;

        if (x0 >= 0 && x0 <= boardSize - 1 && y0 >= 0 && y0 <= boardSize - 1 && x1 >= 0 && x1 <= boardSize - 1 && y1 >= 0 && y1 <= boardSize - 1) {
            for (Peca peca : gameBoard.pecaHashMap.values()) {
                if (peca.posX == x1 && peca.posY == y1) {
                    equipa = peca.equipaPeca;
                }
                switch (peca.tipoPeca) {
                    case 0:
                        if (x1 == x0 + 1 || y1 == y0 + 1 || x1 == x0 - 1 || y1 == y0 - 1) {

                        }
                }
                if (peca.isEmJogo() && peca.posX == x0 && peca.posY == y0) {
                    peca.posX = x1;
                    peca.posY = y1;
                    return true;
                }
            }
        }
        return false;
    }


    String[] getSquareInfo(int x, int y) {
        String[] squareInfo = new String[5];
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.posX == x && peca.posY == y) {
                squareInfo[0] = peca.idPeca;
                squareInfo[1] = String.valueOf(peca.tipoPeca);
                squareInfo[2] = String.valueOf(peca.equipaPeca);
                squareInfo[3] = peca.nomePeca;
                squareInfo[4] = null;
            }
        }
        return squareInfo;
    }

    String[] getPieceInfo(int ID) {
        String[] pieceInfo = new String[7];
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (Integer.parseInt(peca.idPeca) == ID) {
                pieceInfo[0] = peca.idPeca;
                pieceInfo[1] = String.valueOf(peca.tipoPeca);
                pieceInfo[2] = String.valueOf(peca.equipaPeca);
                pieceInfo[3] = peca.nomePeca;
                pieceInfo[4] = String.valueOf(peca.emJogo);
                pieceInfo[5] = String.valueOf(peca.posX);
                pieceInfo[6] = String.valueOf(peca.posY);
            }
        }
        return pieceInfo;
    }

    String getPieceInfoAsString(int ID) {
        String info = "";
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (Integer.parseInt(peca.idPeca) == ID) {
                info = peca.toString();
            }
        }
        return info;
    }

    /*
    int getCurrentTeamID() {
        //FAZER O MOVE PRIMEIRO
    }
    */

    boolean gameOver() {
        // CASO DE EMPATE
        if (gameBoard.numPecasBrancas == 1 && gameBoard.numPecasPretas == 1) {
            return true;
        }
        // VITÓRIA PARA AS BRANCOS
        else if (gameBoard.numPecasBrancas > 0 && gameBoard.numPecasPretas == 0) {
            return true;
        }
        // VITÓRIA PARA AS PRETAS
        else if (gameBoard.numPecasBrancas == 0 && gameBoard.numPecasPretas < 0) {
            return true;
        }
        // JOGO A DECORRER
        else {
            return false;
        }
    }

    /*
    ArrayList<String> getGameResults() {

    }
    */

    /*
    JPanel getAuthorsPanel() {
    }
    */
}
