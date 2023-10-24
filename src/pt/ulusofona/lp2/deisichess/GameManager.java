package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class GameManager {
    int boardSize;
    HashMap<String,Peca> pecaHashMap;
    public GameManager() {
    }

      boolean loadGame(File file) throws IOException {
        // HASHMAP PARA GUARDAR AS PEÇAS
        this.pecaHashMap = new HashMap<>();
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

                Peca peca = new Peca(id, tipo, equipa, nome, 0, 0);
                pecaHashMap.put(id, peca);
                System.out.println(peca);
                count++;
            }
            if (numlinhas > numpecas + 2) {
                String[] partes = line.split(":", size);
                x = 0;

                for (String pos : partes) {
                    if (!Objects.equals(pos, "0")){
                        pecaHashMap.get(pos).posX = x;
                        pecaHashMap.get(pos).posY = y;
                        System.out.println(pecaHashMap.get(pos).toString());
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
        return boardSize;
    }

    /*
    boolean move(int x0, int y0, int x1, int y1){
        // SE O boardSize = 4, ENTÃO X E Y SÓ PODEM CHEGAR A 4
        // SE O boardSize = 8, ENTÃO X E Y SÓ PODEM CHEGAR A 8
    }
    /*
    String[] getSquareInfo(int x, int y){

    }
    String[] getPieceInfo(int ID){

    }

    String getPieceInfoAsString(int ID){

    }
    int getCurrentTeamID(){

    }
    boolean gameOver(){

    }
    ArrayList<String> getGameResults(){

    }
    JPanel getAuthorsPanel(){

    }

*/


}
