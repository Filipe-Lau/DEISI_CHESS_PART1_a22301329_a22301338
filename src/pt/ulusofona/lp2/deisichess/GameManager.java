package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameManager {
    Tabuleiro gameBoard = new Tabuleiro();
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
               gameBoard.boardSize = Integer.parseInt(line); //ESTÁ COMENTADO PARA TESTAR A FUNÇÃO
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
                if(equipa == 1) {
                    gameBoard.numPecasBrancas++;
                }
                else {
                    gameBoard.numPecasPretas++;
                }
                System.out.println("equipa: " + equipa + "\n");

                String nome = partes[3].trim();
                System.out.println("nome: " + nome + "\n");

                Peca peca = new Peca(id, tipo, equipa, nome, 0, 0);
                gameBoard.pecaHashMap.put(id, peca);
                System.out.println(peca);
                count++;
            }
            if (numlinhas > numpecas + 2) {
                String[] partes = line.split(":", size);
                x = 0;

                for (String pos : partes) {
                    if (!Objects.equals(pos, "0")){
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



    int getBoardSize(){
        return gameBoard.boardSize;
    }

    /*
    boolean move(int x0, int y0, int x1, int y1){
        // SE O boardSize = 4, ENTÃO X E Y SÓ PODEM CHEGAR A 4
        // SE O boardSize = 8, ENTÃO X E Y SÓ PODEM CHEGAR A 8

        //SE COMER BRANCA,
        //peca.pecaFoiComida();
        //se for 1 branco comido
        //se for 0 preto comido
    }

     */

    String[] getSquareInfo(int x, int y){
        String[]infoSquare = new String[5];
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if(peca.posX == x && peca.posY == y){
                infoSquare[0] = peca.idPeca;
                infoSquare[1] = String.valueOf(peca.tipoPeca);
                infoSquare[2] = String.valueOf(peca.equipaPeca);
                infoSquare[3] = peca.nomePeca;
                infoSquare[4] = null;
            }
        }
        return infoSquare;
    }
    String[] getPieceInfo(int ID){
        String[]infoPiece = new String[7];
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if(Integer.parseInt(peca.idPeca) == ID){
                infoPiece[0] = peca.idPeca;
                infoPiece[1] = String.valueOf(peca.tipoPeca);
                infoPiece[2] = String.valueOf(peca.equipaPeca);
                infoPiece[3] = peca.nomePeca;
                infoPiece[4] = String.valueOf(peca.foiComida);
                infoPiece[5] = String.valueOf(peca.posX);
                infoPiece[6] = String.valueOf(peca.posY);
            }
        }
        return infoPiece;
    }

    /* String getPieceInfoAsString(int ID){

    }
    int getCurrentTeamID(){

    while(!gameOver) {



    }

    }
    boolean gameOver(){

    }
    ArrayList<String> getGameResults() {

    }
    JPanel getAuthorsPanel(){

    }


     */



}
