package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
POR FAZER:
* Acabar gameResult() * CONCLUIDO
* Fazer getAuthorsPanel()
* Verificar o move() CORRIGIR * CONCLUIDO
* Partilhar o codigo com o professor * CONCLUIDO
* QUANDO FAZEMOS UM SEGUNDO JOGO O gameOver() NÃO FUNCIONA * CONCLUIDO
* APAGAR A INFO DA PEÇA NA POSIÇÃO ANTIGA
*/

public class GameManager {
    int boardSize;
    int vezDeJogar = 0;
    String resultadoJogo = "";
    Tabuleiro gameBoard = new Tabuleiro();
    int jogadaPretaValida;
    int jogadaPretaInvalida;
    int jogadaBrancaValida;
    int jogadaBrancaInvalida;
    int jogadasSemComer;
    boolean houveCaptura;

    public GameManager() {
    }

    public boolean loadGame(File file) {
        // HASHMAP PARA GUARDAR AS PEÇAS
        System.out.println("LOADGAME\n");
        try {
            int count = 2;
            int numlinhas = 0;
            int x;
            int y = 0;
            int numpecas = 0;
            String[] partes;
            gameBoard.pecaHashMap = new HashMap<>();
            gameBoard.numPecasBrancas = 0;
            gameBoard.numPecasPretas = 0;

            // LEITURA DE FICHEIROS
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            // VARIAVEIS
        /*
        int count = 2;
        int numlinhas = 0;
        int x;
        int y = 0;
        int numpecas = 0;

         */
            //int size = 0;

            // CICLO DE LEITURA
            while (line != null) {
                numlinhas++;
                if (numlinhas == 1) {
                    this.boardSize = Integer.parseInt(line); //ESTÁ COMENTADO PARA TESTAR A FUNÇÃO
                } else if (numlinhas == 2) {
                    numpecas = Integer.parseInt(line);
                }
                if (numlinhas >= 3 && count < numpecas + 2) {

                    partes = line.split(":");

                    String id = partes[0];

                    int tipo = Integer.parseInt(partes[1]);

                    int equipa = Integer.parseInt(partes[2].trim());
                    if (equipa == 0) {
                        gameBoard.numPecasPretas++;
                    } else {
                        gameBoard.numPecasBrancas++;
                    }


                    String nome = partes[3].trim();

                    Peca peca = new Peca(id, tipo, equipa, nome, 0, 0, "em jogo");
                    gameBoard.pecaHashMap.put(id, peca);
                    count++;
                }
                if (numlinhas > numpecas + 2) {
                    partes = line.split(":", boardSize);
                    x = 0;

                    for (String id : partes) {
                        if (!Objects.equals(id, "0")) {
                            gameBoard.pecaHashMap.get(id).posX = x; // ERRO AO TENTAR ABRIR UM JOGO A MEIO DE UM JOGO;
                            gameBoard.pecaHashMap.get(id).posY = y;
                        }
                        x++;
                    }
                    y++;
                }
                line = reader.readLine();
            }
            reader.close();
            System.out.println(gameBoard.numPecasPretas);
            System.out.println(gameBoard.numPecasBrancas);
           // gameOver();
            return true;
        }catch (IOException e){
            return false;
        }
    }
    public int getBoardSize() {
        System.out.println("BOARDSIZE\n");
        return boardSize;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        System.out.println("MOVE\n");
        //vezDeJogar = 0;
        // SE O boardSize = 4, ENTÃO X E Y SÓ PODEM CHEGAR A 3
        // SE O boardSize = 8, ENTÃO X E Y SÓ PODEM CHEGAR A 7
        // Peca peca0 = new Peca(); // SITIO DE PARTIDA / PECA QUE ATACA
        //Peca peca1 = new Peca(); //SITIO ONDE QUERO IR / PECA QUE É ATACADA

        if (x0 >= 0 && x0 <= boardSize - 1 && y0 >= 0 && y0 <= boardSize - 1 && x1 >= 0 && x1 <= boardSize - 1 && y1 >= 0 && y1 <= boardSize - 1) { // VER SE AS COORDENADAS ESTÃO DENTRO DO TABULEIRO
            for (Peca peca : gameBoard.pecaHashMap.values()) {
                if (peca.posX == x0 && peca.posY == y0) {
                    // VALIDAR QUEM ESTÁ A JOGAR
                    if (getCurrentTeamID() == 1 && peca.equipaPeca == 0) {
                        jogadaBrancaInvalida++;
                        //peca.jogadaInvalida++;
                        return false;
                    } else if (getCurrentTeamID() == 0 && peca.equipaPeca == 1) {
                        jogadaPretaInvalida++;
                        //peca.jogadaInvalida++;
                        return false;
                    } else {

                        switch (peca.tipoPeca) {
                            case 0:
                                // FALTA A DIAGONAL (ACHO QUE NÃO FALTA)
                                if ((x1 - x0 >= -1 && x1 - x0 <= 1) && (y1 - y0 >= -1 && y1 - y0 <= 1)) { // ESTÁ BEM
                                    // peca0Temp(peca, peca0, x0, y0);
                            /*
                            if (peca.posX == x0 && peca.posY == y0) {
                                peca0.posX = peca.posX;
                                peca0.posY = peca.posY;
                                peca0.equipaPeca = peca.equipaPeca;
                                peca0.idPeca = peca.idPeca;
                            }
                            */
                                    // peca1Temp(peca, obterPeca, x1, y1);
                             /*
                            if (peca.posX == x1 && peca.posY == y1) {
                                obterPeca.idPeca = peca.idPeca;
                                obterPeca.posX = peca.posX;
                                obterPeca.posY = peca.posY;
                                obterPeca.equipaPeca = peca.equipaPeca;
                            }
                             */Peca peca1 = obterPeca(x1, y1);
                                    if (peca1 == null) {
                                        // CASO DE ANDAR PARA UMA CASA VAZIA
                                        peca.posX = x1;
                                        peca.posY = y1;
                                        if(houveCaptura) {
                                            jogadasSemComer++;
                                        }
                                        getPieceInfoAsString(Integer.parseInt(peca.idPeca));
                                        //peca.jogadaValida++;
                                        if (getCurrentTeamID() == 0) {
                                            jogadaPretaValida++;
                                            vezDeJogar = 1;
                                        } else {
                                            jogadaBrancaValida++;
                                            vezDeJogar = 0;
                                        }
                                        return true;
                                    } else {
                                        if (peca.equipaPeca != peca1.equipaPeca) {
                                            // peca.jogadaValida++;
                                            //System.out.println(peca.jogadaValida);
                                            gameBoard.pecaHashMap.get(peca1.idPeca).notInJogo();
                                            getSquareInfo(x1, y1);
                                            //gameBoard.pecaHashMap.remove(peca1.idPeca);
                                            peca.posX = x1;
                                            peca.posY = y1;
                                            if(houveCaptura) {
                                                jogadasSemComer++;
                                            }
                                            if(!houveCaptura) {
                                                houveCaptura = true;
                                            }
                                            if (peca.equipaPeca == 0) {// PECA PRETA COME
                                                jogadaPretaValida++;
                                                gameBoard.capturadasPorPretas++;
                                                gameBoard.numPecasBrancas--;
                                                vezDeJogar = 1;
                                            } else if (peca.equipaPeca == 1) {
                                                jogadaBrancaValida++;
                                                gameBoard.capturadasPorBrancas++;
                                                gameBoard.numPecasPretas--;
                                                vezDeJogar = 0;
                                                // NÃO PRECISO DESTA LINHA
                                            }
                                            //ATUALIZAR AS PECAS DO TABULEIRO
                                            return true;
                                        } else {
                                            if (peca.equipaPeca == 1) {
                                                jogadaBrancaInvalida++;
                                                //peca.jogadaInvalida++;
                                            } else {
                                                jogadaPretaInvalida++;
                                            }
                                        }
                                        return false;
                                    }
                                }
                                ///////////////////////////////
                                // NÃO TENHO A CERTEZA DISTO //
                                if (peca.equipaPeca == 1) {
                                    jogadaBrancaInvalida++;
                                    //peca.jogadaInvalida++;
                                } else {
                                    jogadaPretaInvalida++;
                                }
                                //peca.jogadaInvalida++;         //
                                return false;             //
                            ///////////////////////////////
                        }
                    }
                }
            }
        }
        //jogadaInvalida++;

        return false;
    }

    /*
    public Peca peca0Temp(Peca peca, Peca peca0, int x0, int y0) {
        if (peca.posX == x0 && peca.posY == y0) {
            peca0.posX = peca.posX;
            peca0.posY = peca.posY;
            peca0.equipaPeca = peca.equipaPeca;
            peca0.idPeca = peca.idPeca;
            peca0.jogadaValida = peca.jogadaValida;
            peca0.jogadaInvalida = peca.jogadaInvalida;
        }
        return peca0;
    }

     */

    public Peca obterPeca(int x1, int y1) {//É A RAZÃO DE DAR MAL NO estaEmJogo() OU NÃO (ACHO EU); REVER ESTA FUNÇÃO
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.posX == x1 && peca.posY == y1) {
                return peca;
            }
        }
        return null;
    }

    /*
    public Peca peca1Temp(Peca peca, Peca peca1, int x1, int y1) {//É A RAZÃO DE DAR MAL NO estaEmJogo() OU NÃO (ACHO EU); REVER ESTA FUNÇÃO
        for (Peca peca2 : gameBoard.pecaHashMap.values()) {
            if (peca.posX == x1 && peca.posY == y1) {
                peca1.posX = peca.posX;
                peca1.posY = peca.posY;
                peca1.equipaPeca = peca.equipaPeca;
                peca1.idPeca = peca.idPeca;
            } else {
                return null;
            }
        }
        return peca1;
    }

    /*
    public int totalValidasPretas() {
        int validasPretas = 0;
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.equipaPeca == 0) {
                validasPretas += peca.jogadaValida;
            }
        }
        return validasPretas;
    }

    public int totalInvalidasPretas() {
        int invalidasPretas = 0;
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.equipaPeca == 1) {
                invalidasPretas += peca.jogadaInvalida;
            }
        }
        return invalidasPretas;
    }

    public int totalValidasBrancas() {
        int validasBrancas = 0;
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.equipaPeca == 1) {
                validasBrancas += peca.jogadaValida;
            }
        }
        return validasBrancas;
    }

    public int totalInvalidasBrancas() {
        int invalidasBrancas = 0;
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.equipaPeca == 1) {
                invalidasBrancas += peca.jogadaInvalida;
            }
        }
        return invalidasBrancas;
    }
*/
    public String[] getSquareInfo(int x, int y) { // FALTA TIRAR A IMAGEM QUANDO A PECA NÃO ESTÁ EM JOGO
        System.out.println("SQUAREINFO\n");
        String[] squareInfo = new String[5];
        if (x < 0 || x >= boardSize && y < 0 || y >= boardSize) {
            return null;
        }
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (peca.posX == x && peca.posY == y) {
                if (peca.estado.equals("capturado")) {
                    gameBoard.pecaHashMap.remove(peca.idPeca);
                    //squareInfo[4] = "";
                }
                squareInfo[0] = peca.idPeca;
                squareInfo[1] = String.valueOf(peca.tipoPeca);
                squareInfo[2] = String.valueOf(peca.equipaPeca);
                squareInfo[3] = peca.nomePeca;
                if (peca.equipaPeca == 1) {
                    squareInfo[4] = "pecaBranca1.png";
                } else {
                    squareInfo[4] = "pecaPreta2.png";
                }
                return squareInfo; // squareinfo preenchido
            }
        }
        return new String[0]; // squareinfo vazio
    }

    public String[] getPieceInfo(int ID) {  //FUNCAO PARA OS TESTES
        System.out.println("PIECEINFO\n");
        String[] pieceInfo = new String[7];
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (Integer.parseInt(peca.idPeca) == ID) {
                pieceInfo[0] = peca.idPeca;
                pieceInfo[1] = String.valueOf(peca.tipoPeca);
                pieceInfo[2] = String.valueOf(peca.equipaPeca);
                pieceInfo[3] = peca.nomePeca;
                pieceInfo[4] = String.valueOf(peca.estado);
                pieceInfo[5] = String.valueOf(peca.posX);
                pieceInfo[6] = String.valueOf(peca.posY);
            }
        }
        return pieceInfo;
    }

    public String getPieceInfoAsString(int ID) {
        System.out.println("PIECEINFOASSTRING\n");
        String info = null;
        for (Peca peca : gameBoard.pecaHashMap.values()) {
            if (Integer.parseInt(peca.idPeca) == ID) {
                if (peca.estado.equals("capturado")) { // SE A PECA FOI CAPTURADA NÃO MOSTRA MENSAGEM
                    return info;
                }
                info = peca.toString();
            }
        }
        return info; // SE O ID NÃO EXISTIR NÃO MOSTRA MENSAGEM
    }

    public int getCurrentTeamID() {
        System.out.println("GETCURRENTTEAMID\n");
        return vezDeJogar;
    }

    public boolean gameOver() {
        System.out.println("GAMEOVER\n");
        // CASO DE EMPATE
        //System.out.println("Num pecas brancas: " + gameBoard.numPecasBrancas);
        //System.out.println("Num pecas pretas: " + gameBoard.numPecasPretas);
        if (gameBoard.numPecasBrancas == 1 && gameBoard.numPecasPretas == 1) {
            resultadoJogo = "EMPATE";
            return true;
        }
        // VITÓRIA PARA AS BRANCAS
        else if (gameBoard.numPecasBrancas > 0 && gameBoard.numPecasPretas == 0) {
            resultadoJogo = "VENCERAM AS BRANCAS";
            return true;
        }
        // VITÓRIA PARA AS PRETAS
        else if (gameBoard.numPecasBrancas == 0 && gameBoard.numPecasPretas > 0) {
            resultadoJogo = "VENCERAM AS PRETAS";
            return true;
        }
        // EMPATE POR AFOGAMENTO
        else if(houveCaptura && jogadasSemComer >= 10){
            resultadoJogo = "EMPATE POR AFOGAMENTO";
            return true;
        }
        // JOGO A DECORRER
        else {
            return false;
        }
    }

    public ArrayList<String> getGameResults() {
        System.out.println("GETGAMERESULTS\n");

        ArrayList<String> gameResult = new ArrayList<>();

        gameResult.add("JOGO DE CRAZY CHESS\n");
        gameResult.add("Resultado: " + resultadoJogo + "\n");
        gameResult.add("---\n");
        gameResult.add("Equipa das Pretas\n");
        gameResult.add(gameBoard.capturadasPorPretas + "\n");
        gameResult.add(jogadaPretaValida + "\n"); // NUMERO DE JOGADAS
        gameResult.add(jogadaPretaInvalida + "\n"); // NUMERO DE TENTATIVAS INVALIDAS
        gameResult.add("Equipa das Brancas\n");
        gameResult.add(gameBoard.capturadasPorBrancas + "\n");
        gameResult.add(jogadaBrancaValida + "\n"); // NUMERO DE JOGADAS
        gameResult.add(jogadaBrancaInvalida + "\n"); // NUMERO DE TENTATIVAS INVALIDAS

        return gameResult;
    }


    public JPanel getAuthorsPanel() {
        JPanel frame = new JPanel();
        frame.setLayout(null);
        frame.setSize(1000,1000);
        frame.setVisible(true);


        return frame;

    }

}