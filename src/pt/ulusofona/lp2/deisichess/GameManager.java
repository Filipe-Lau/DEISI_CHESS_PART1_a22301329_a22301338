package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
POR FAZER:
* Acabar o getAuthorsPanel()
*/

public class GameManager {
    int boardSize;
    int vezDeJogar = 0;
    Tabuleiro gameBoard = new Tabuleiro();
    int jogadaPretaValida; // CRIAR UMA NOVA CLASSE CHAMADA "GAMERESULT"
    int jogadaPretaInvalida; // CRIAR UMA NOVA CLASSE CHAMADA "GAMERESULT"
    int jogadaBrancaValida; // CRIAR UMA NOVA CLASSE CHAMADA "GAMERESULT"
    int jogadaBrancaInvalida; // CRIAR UMA NOVA CLASSE CHAMADA "GAMERESULT"
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
            jogadaPretaValida = 0;
            jogadaPretaInvalida = 0;
            jogadaBrancaValida = 0;
            jogadaBrancaInvalida = 0;
            jogadasSemComer = 0;
            vezDeJogar = 0;
            gameBoard.pecasEmJogo = new HashMap<>();
            //gameBoard.pecasCapturadas = new HashMap<>();
            gameBoard.setNumPecasBrancas(0); // fazer set
            gameBoard.setNumPecasPretas(0); // fazer set
            gameBoard.setCapturadasPorPretas(0);
            gameBoard.setCapturadasPorBrancas(0);

            // LEITURA DE FICHEIROS
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            // CICLO DE LEITURA
            while (line != null) {
                numlinhas++;
                if (numlinhas == 1) {
                    this.boardSize = Integer.parseInt(line); // ESTÁ COMENTADO PARA TESTAR A FUNÇÃO
                } else if (numlinhas == 2) {
                    numpecas = Integer.parseInt(line);
                }
                if (numlinhas >= 3 && count < numpecas + 2) {

                    partes = line.split(":");

                    String id = partes[0];

                    int tipo = Integer.parseInt(partes[1]);

                    int equipa = Integer.parseInt(partes[2].trim());
                    if (equipa == 0) {
                        gameBoard.aumentaNumPecasPretas();
                    } else {
                        gameBoard.aumentaNumPecasBrancas();
                    }

                    String nome = partes[3].trim();

                    Peca peca = new Peca(id, tipo, equipa, nome);
                    gameBoard.getPecasEmJogo().put(id, peca);
                    count++;
                }
                if (numlinhas > numpecas + 2) {
                    partes = line.split(":", boardSize);
                    x = 0;

                    for (String id : partes) {
                        if (!Objects.equals(id, "0")) {
                            gameBoard.getPecasEmJogo().get(id).setPosX(x);
                            gameBoard.getPecasEmJogo().get(id).setPosY(y);
                            gameBoard.getPecasEmJogo().get(id).setEstado("em jogo");
                        }
                        x++;
                    }
                    y++;
                }
                line = reader.readLine();
            }

            reader.close();

            for (Peca peca : gameBoard.getPecasEmJogo().values()) {
                if (peca.getPosX() == -1 && peca.getPosY() == -1) {
                    peca.notInJogo();
                    if (peca.getEquipaPeca() == 1) {
                        gameBoard.pecaBrancaComida();
                    } else {
                        gameBoard.pecaPretaComida();
                    }
                    //gameBoard.getPecasCapturadas().put(peca.getIdPeca(), peca);
                }
            }
            /*
            for (Peca pecaCapturada : gameBoard.getPecasCapturadas().values()) { // CALCULA O NUM DE PEÇAS PARA SABER SE O JOGO TERMINOU
                if (pecaCapturada.getEquipaPeca() == 1) {
                    gameBoard.pecaBrancaComida();
                } else {
                    gameBoard.pecaPretaComida();
                }
            }

             */
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int getBoardSize() {
        System.out.println("BOARDSIZE\n");
        return boardSize;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        if (x0 >= 0 && x0 <= boardSize - 1 && y0 >= 0 && y0 <= boardSize - 1 && x1 >= 0 && x1 <= boardSize - 1 && y1 >= 0 && y1 <= boardSize - 1) { // VER SE AS COORDENADAS ESTÃO DENTRO DO TABULEIRO
            for (Peca peca : gameBoard.getPecasEmJogo().values()) {
                if (peca.getPosX() == x0 && peca.getPosY() == y0) {
                    // VALIDAR QUEM ESTÁ A JOGAR
                    if (getCurrentTeamID() == 1 && peca.getEquipaPeca() == 0) {
                        jogadaBrancaInvalida++;
                        return false;
                    } else if (getCurrentTeamID() == 0 && peca.getEquipaPeca() == 1) {
                        jogadaPretaInvalida++;
                        return false;
                    } else {

                        switch (peca.getTipoPeca()) {
                            case 0:
                                if ((x1 - x0 >= -1 && x1 - x0 <= 1) && (y1 - y0 >= -1 && y1 - y0 <= 1)) { // ESTÁ BEM
                                    Peca peca1 = obterPeca(x1, y1);
                                    if (peca1 == null) {// CASO DE ANDAR PARA UMA CASA VAZIA
                                        moverParaPosicaoVazia(peca, x1, y1);
                                        return true;
                                    } else {
                                        if (peca.getEquipaPeca() != peca1.getEquipaPeca()) {
                                            atacar(peca, peca1, x1, y1);
                                            return true;
                                        } else {
                                            contadorJogadaInvalida(peca);
                                        }
                                        return false;
                                    }
                                }
                                contadorJogadaInvalida(peca);
                                return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Peca obterPeca(int x1, int y1) {
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getPosX() == x1 && peca.getPosY() == y1) {
                return peca;
            }
        }
        return null;
    }

    public void moverParaPosicaoVazia(Peca peca, int x1, int y1) {
        peca.setPosX(x1);
        peca.setPosY(y1);
        if (houveCaptura) {
            jogadasSemComer++;
        }
        if (getCurrentTeamID() == 0) {
            jogadaPretaValida++;
            vezDeJogar = 1;
        } else {
            jogadaBrancaValida++;
            vezDeJogar = 0;
        }
    }

    public void atacar(Peca peca, Peca peca1, int x1, int y1) {
        gameBoard.getPecasEmJogo().get(peca1.getIdPeca()).notInJogo();
        peca1.setPosX(-1); // METEMOS A -1 PARA FICAR FORA DO TABULEIRO, PARA O SQUAREINFO FUNCIONAR
        peca1.setPosY(-1);
        //gameBoard.getPecasCapturadas().put(peca1.getIdPeca(), peca1);
        //gameBoard.getPecasEmJogo().remove(peca1.getIdPeca());
        peca.setPosX(x1);
        peca.setPosY(y1);

        if (peca.getEquipaPeca() == 0) { // PECA PRETA COME
            jogadaPretaValida++;
            gameBoard.capturaPorPretas();
            gameBoard.pecaBrancaComida();
            vezDeJogar = 1;
        } else {
            jogadaBrancaValida++;
            gameBoard.capturaPorBrancas();
            gameBoard.pecaPretaComida();
            vezDeJogar = 0;
        }

        if (houveCaptura) {
            jogadasSemComer++;
        }
        if (!houveCaptura) {
            houveCaptura = true;
        }
    }

    public void contadorJogadaInvalida(Peca peca) {
        if (peca.getEquipaPeca() == 1) {
            jogadaBrancaInvalida++;
        } else {
            jogadaPretaInvalida++;
        }
    }

    public String[] getSquareInfo(int x, int y) { // FALTA TIRAR A IMAGEM QUANDO A PECA NÃO ESTÁ EM JOGO
        System.out.println("SQUAREINFO\n");
        String[] squareInfo = new String[5];
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            return null;
        }
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getPosX() == x && peca.getPosY() == y) {
                squareInfo[0] = peca.getIdPeca();
                squareInfo[1] = String.valueOf(peca.getTipoPeca());
                squareInfo[2] = String.valueOf(peca.getEquipaPeca());
                squareInfo[3] = peca.getNomePeca();
                if (peca.getEquipaPeca() == 1) {
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
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (Integer.parseInt(peca.getIdPeca()) == ID) {
                pieceInfo[0] = peca.getIdPeca();
                pieceInfo[1] = String.valueOf(peca.getTipoPeca());
                pieceInfo[2] = String.valueOf(peca.getEquipaPeca());
                pieceInfo[3] = peca.getNomePeca();
                pieceInfo[4] = String.valueOf(peca.getEstado());
                if (peca.getEstado().equals("capturado")){
                    pieceInfo[5] = "";
                    pieceInfo[6] = "";
                }
                pieceInfo[5] = String.valueOf(peca.getPosX());
                pieceInfo[6] = String.valueOf(peca.getPosY());
            }
        }
/*
        for (Peca peca : gameBoard.getPecasCapturadas().values()) {
            if (Integer.parseInt(peca.getIdPeca()) == ID) {
                pieceInfo[0] = peca.getIdPeca();
                pieceInfo[1] = String.valueOf(peca.getTipoPeca());
                pieceInfo[2] = String.valueOf(peca.getEquipaPeca());
                pieceInfo[3] = peca.getNomePeca();
                pieceInfo[4] = String.valueOf(peca.getEstado());
                pieceInfo[5] = "";
                pieceInfo[6] = "";
            }
        }
*/
        return pieceInfo;
    }

    public String getPieceInfoAsString(int ID) {
        System.out.println("PIECEINFOASSTRING\n");
        String info = "";
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (Integer.parseInt(peca.getIdPeca()) == ID) {
                info = peca.toString();
            }
        }
        return info;
    }

    public int getCurrentTeamID() {
        return vezDeJogar;
    }

    public boolean gameOver() {
        // CASO DE EMPATE
        if (gameBoard.getNumPecasBrancas() == 1 && gameBoard.getNumPecasPretas() == 1) {
            gameBoard.setResultadoJogo("EMPATE");
            return true;
        }
        // VITÓRIA PARA AS BRANCAS
        else if (gameBoard.getNumPecasBrancas() > 0 && gameBoard.getNumPecasPretas() == 0) {
            gameBoard.setResultadoJogo("VENCERAM AS BRANCAS");
            return true;
        }
        // VITÓRIA PARA AS PRETAS
        else if (gameBoard.getNumPecasBrancas() == 0 && gameBoard.getNumPecasPretas() > 0) {
            gameBoard.setResultadoJogo("VENCERAM AS PRETAS");
            return true;
        }
        // EMPATE POR EXAUSTÃO
        else if (houveCaptura && jogadasSemComer >= 10) {
            gameBoard.setResultadoJogo("EMPATE");
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

        gameResult.add("JOGO DE CRAZY CHESS");
        gameResult.add("Resultado: " + gameBoard.getResultadoJogo());
        gameResult.add("---");
        gameResult.add("Equipa das Pretas");
        gameResult.add(gameBoard.getCapturadasPorPretas() + "");
        gameResult.add(jogadaPretaValida + ""); // NUMERO DE JOGADAS
        gameResult.add(jogadaPretaInvalida + ""); // NUMERO DE TENTATIVAS INVALIDAS
        gameResult.add("Equipa das Brancas");
        gameResult.add(gameBoard.getCapturadasPorBrancas() + "");
        gameResult.add(jogadaBrancaValida + ""); // NUMERO DE JOGADAS
        gameResult.add(jogadaBrancaInvalida + ""); // NUMERO DE TENTATIVAS INVALIDAS

        return gameResult;
    }

    public JPanel getAuthorsPanel() {

        ImageIcon image = new ImageIcon("pecaPreta2.png");

        JLabel label = new JLabel();
        label.setIcon(image);
        label.setText("I did it, but at what cost?");
        label.setForeground(Color.WHITE);


        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0,0,250,250);
        panel.add(label);

        /*
        JPanel panel2 = new JPanel();
        panel.setBackground(Color.RED);
        panel.setBounds(250,0,250,250);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750, 750);
        frame.setVisible(true);
        //frame.add(label);
        frame.add(panel);
        panel.add(label);
        //frame.add(panel2);
        */

        return panel;

    }

}