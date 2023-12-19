package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

// 10 pretas
// 20 brancas

public class GameManager {
    int boardSize;
    int vezDeJogar = 10;
    Tabuleiro gameBoard = new Tabuleiro();
    GameResult gameResult = new GameResult();
    ArrayList<Integer> undo = new ArrayList<>();
    Peca pecaCapturada;

    public GameManager() {
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }

    public void loadGame(File file) throws InvalidGameInputException, IOException {
        // HASHMAP PARA GUARDAR AS PEÇAS
        // System.out.println("LOADGAME\n");
        try {
            int count = 2;
            int numlinha = 0;
            int x;
            int y = 0;
            int numpecas = 0;
            String[] partes;
            gameResult.setJogadaPretaValida(0);
            gameResult.setJogadaPretaInvalida(0);
            gameResult.setJogadaBrancaValida(0);
            gameResult.setJogadaBrancaInvalida(0);
            gameResult.setJogadasSemComer(0);
            vezDeJogar = 10;
            gameBoard.pecasEmJogo = new HashMap<>();
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
                numlinha++;
                if (numlinha == 1) {
                    this.boardSize = Integer.parseInt(line); // ESTÁ COMENTADO PARA TESTAR A FUNÇÃO
                } else if (numlinha == 2) {
                    numpecas = Integer.parseInt(line);
                }
                if (numlinha >= 3 && count < numpecas + 2) {

                    partes = line.split(":");

                    try {

                        String id = partes[0];

                        int tipo = Integer.parseInt(partes[1]);

                        int equipa = Integer.parseInt(partes[2].trim());

                        if (equipa == 10) {
                            gameBoard.aumentaNumPecasPretas();
                        } else {
                            gameBoard.aumentaNumPecasBrancas();
                        }

                        String nome = partes[3].trim();

                        //Peca peca = new Peca(id, tipo, equipa, nome);

                        switch (tipo) {
                            case 0:
                                if (equipa == 20) {
                                    Peca reiBranco = new Rei(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, reiBranco);
                                } else {
                                    Peca reiPreto = new Rei(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, reiPreto);
                                }
                                //Peca rei = new Rei(id, equipa, nome);
                                break;
                            case 1:
                                if (equipa == 20) {
                                    Peca rainhaBranco = new Rainha(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, rainhaBranco);
                                } else {
                                    Peca rainhaPreto = new Rainha(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, rainhaPreto);
                                }
//                                Peca rainha = new Rainha(id, equipa, nome);
                                break;
                            case 2:
                                if (equipa == 20) {
                                    Peca poneiMagicoBranco = new PoneiMagico(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, poneiMagicoBranco);
                                } else {
                                    Peca poneiMagicoPreto = new PoneiMagico(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, poneiMagicoPreto);
                                }
//                                Peca poneiMagico = new PoneiMagico(id, equipa, nome);
                                break;
                            case 3:
                                if (equipa == 20) {
                                    Peca padreBranco = new PadreDaVila(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, padreBranco);
                                } else {
                                    Peca padrePreto = new PadreDaVila(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, padrePreto);
                                }// padre da vila
//                                Peca padre = new PadreDaVila(id, equipa, nome);
                                break;
                            case 4:
                                if (equipa == 20) {
                                    Peca torreHBranco = new TorreH(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, torreHBranco);
                                } else {
                                    Peca torreHPreto = new TorreH(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, torreHPreto);
                                }// th
//                                Peca torreH = new TorreH(id, equipa, nome);
                                break;
                            case 5:
                                if (equipa == 20) {
                                    Peca torreVBranco = new TorreV(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, torreVBranco);
                                } else {
                                    Peca torreVPreto = new TorreV(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, torreVPreto);
                                }//tv
//                                Peca torreV = new TorreV(id, equipa, nome);
                                break;
                            case 6:
                                if (equipa == 20) {
                                    Peca homerBranco = new HomerSimpson(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, homerBranco);
                                } else {
                                    Peca homerPreto = new HomerSimpson(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, homerPreto);
                                }
//                                Peca homer = new HomerSimpson(id, equipa, nome);
                                break;
                            case 7:
                                if (equipa == 20) {
                                    Peca jokerBranco = new Joker(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, jokerBranco);
                                } else {
                                    Peca jokerPreto = new Joker(id, equipa, nome);
                                    gameBoard.getPecasEmJogo().put(id, jokerPreto);
                                }
                                //                     break;
//                                Peca joker = new Joker(id, equipa, nome);
                        }
                        //gameBoard.getPecasEmJogo().put(id, peca);
                        count++;

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new InvalidGameInputException(numlinha, "DADOS A MENOS (Esperava: " + 4 + " Obtive: " + partes.length + ")");
                    }

                    if (partes.length > 4) {
                        throw new InvalidGameInputException(numlinha, "DADOS A MAIS (Esperava: " + 4 + " Obtive: " + partes.length + ")");
                    }
                }
                if (numlinha > numpecas + 2) {
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
                    if (peca.getEquipaPeca() == 20) {
                        gameBoard.pecaBrancaComida();
                    } else {
                        gameBoard.pecaPretaComida();
                    }
                }
            }
            //  return true;
        } catch (IOException e) {
            //    return false;
        }
    }

    public int getBoardSize() {
        //System.out.println("BOARDSIZE\n");
        return boardSize;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        boolean ataque = false;
        Joker joker = new Joker();

        if (x0 >= -1 && x0 <= boardSize - 1 && y0 >= -1 && y0 <= boardSize - 1 && x1 >= -1 && x1 <= boardSize - 1 && y1 >= -1 && y1 <= boardSize - 1) { // VER SE AS COORDENADAS ESTÃO DENTRO DO TABULEIRO
            for (Peca peca : gameBoard.getPecasEmJogo().values()) {
                if (peca.getPosX() == x0 && peca.getPosY() == y0) {
                    if (validaVezDeJogar(peca)) { // VALIDAR QUEM ESTÁ A JOGAR
                        Peca peca1 = obterPeca(x1, y1);
                        if (peca1 != null) { // EXISTE UMA PEÇA NA CASA ONDE QUERO IR, LOGO VOU COMER
                            if ((peca1.getEquipaPeca() == peca.getEquipaPeca()) || (peca.getTipoPeca() == 1 && peca1.getTipoPeca() == 1)) { // SE FOREM DA MESMA EQUIPA OU AMBAS SÃO RAINHAS
                                contadorJogadaInvalida(peca);
                                return false;
                            } else {
                                ataque = true;
                                /*
                                undo.add(peca1.getPosX());
                                undo.add(peca1.getPosY());
                                undo.add(-1);
                                undo.add(-1);
                                 */
                                peca1.setPosX(-1);
                                peca1.setPosY(-1);
                                pecaCapturada = peca1;
                                gameBoard.getPecasEmJogo().get(peca1.getIdPeca()).notInJogo();
                            }
                        }

                        if (!peca.movePeca(x1, y1)) {
                            contadorJogadaInvalida(peca); // SE O PECA ANDAR MAIS QUE O LIMITE DE CASAS QUE PODE ANDAR
                            return false;
                        }
/*
                        undo.add(x0);
                        undo.add(y0);
                        undo.add(x1);
                        undo.add(y1);
 */
                        //LINHAS DE TESTE PARA VER COMO O JOKER ESTÁ A FUNCIONAR
                        System.out.println("ANTES DO GET PECA EM USO: " + joker.pecaEmUso.getTipoPecaString());
                        peca = joker.getPecaEmUso(2); // PONEI MAGICO
                        joker.pecaEmUso = peca;
                        System.out.println("DEPOIS DO GET PECA EM USO: " + joker.pecaEmUso.getTipoPecaString());
                        System.out.println("O JOKER ESTÁ A IMITAR: " + peca.getTipoPecaString());

                        if (ataque) {
                            atualizarCapturas(peca);
                        }

                        //joker.getPecaEmUso(count); // NÃO POSSO USAR UM COUNT VOU TER DE USAR UM ARRAY / OU UM HASMMAP(INTEGER,PECA)

                        atualizarJogadasValidas(); // INCREMENTA AS JOGADAS VALIDAS DAS EQUIPAS
                        atualizarVezDeJogar(); // PRETA JOGA, A VEZ DE JOGAR MUDA PARA AS BRANCAS
                        return true;

                    /*
                    switch (peca.getTipoPeca()) {
                        case 0:

                            //peca.movePeca(x1,y1);
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;


                                    if (peca1 == null) {// CASO DE ANDAR PARA UMA CASA VAZIA
                                        moverParaPosicaoVazia(peca, x1, y1);

                                        undo.add(x0);
                                        undo.add(y0);
                                        undo.add(x1);
                                        undo.add(y1);
                                        return true;
                                    } else {
                                        if (peca.getEquipaPeca() != peca1.getEquipaPeca()) {
                                            atacar(peca, peca1, x1, y1);
                                            return true;
                                        } else {
                                            contadorJogadaInvalida(peca); // SE O REI TENTAR COMER DA MESMA EQUIPA
                                        }
                                        return false;
                                    }


                                }

                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;

                        case 1:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;

                        case 2:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;
                        case 3:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;
                        case 4:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;
                        case 5:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;
                        case 6:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;
                        case 7:
                            if (peca.movePeca(x1, y1) == false) {
                                contadorJogadaInvalida(peca); // SE O REI ANDAR MAIS QUE UMA CASA
                                return false;
                            }
                            //undo();
                            //return true;
                            break;

                    }
                 */

                    } else {
                        return false;
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

    public boolean validaVezDeJogar(Peca peca) {
        if (getCurrentTeamID() == 20 && peca.getEquipaPeca() == 10) {
            System.out.println("VEZ DE JOGAR BRANCO MAS JOGOU PRETO");
            gameResult.aumentaJogadaBrancaInvalida();
            return false;
        }

        if (getCurrentTeamID() == 10 && peca.getEquipaPeca() == 20) {
            System.out.println("VEZ DE JOGAR PRETO MAS JOGOU BRANCO");
            gameResult.aumentaJogadaPretaInvalida();
            return false;
        }
        return true;
    }

    public void atualizarCapturas(Peca peca) {
        if (peca.getEquipaPeca() == 10) { // PECA PRETA COME
            //gameResult.aumentaJogadaPretaValida();
            gameBoard.capturaPorPretas();
            gameBoard.pecaBrancaComida();
        } else {
            //gameResult.aumentaJogadaBrancaValida();
            gameBoard.capturaPorBrancas();
            gameBoard.pecaPretaComida();
        }

        if (gameResult.getHouveCaptura()) {
            gameResult.setJogadasSemComer(0);
        } else {
            gameResult.setHouveCaptura(true);
            gameResult.setJogadasSemComer(0);
        }
    }

    public void atualizarJogadasValidas() {
        if (gameResult.getHouveCaptura()) {
            gameResult.aumentaJogadasSemComer();
        }
        if (getCurrentTeamID() == 10) {
            gameResult.aumentaJogadaPretaValida();
        } else {
            gameResult.aumentaJogadaBrancaValida();
        }
    }

    public void atualizarVezDeJogar() {

        if (getCurrentTeamID() == 10) {
            //gameResult.aumentaJogadaPretaValida();
            vezDeJogar = 20;
        } else {
            //gameResult.aumentaJogadaBrancaValida();
            vezDeJogar = 10;
        }
    }

    public void atacar(Peca peca, Peca peca1, int x1, int y1) {
        pecaCapturada = peca1;
        gameBoard.getPecasEmJogo().get(peca1.getIdPeca()).notInJogo();

        undo.add(peca1.posX);
        undo.add(peca1.posY);
        peca1.setPosX(-1); // METEMOS A -1 PARA FICAR FORA DO TABULEIRO, PARA O SQUAREINFO FUNCIONAR
        peca1.setPosY(-1);
        undo.add(peca1.posX);
        undo.add(peca1.posY);

        undo.add(peca.getPosX());
        undo.add(peca.getPosY());
        undo.add(x1);
        undo.add(y1);

        peca.setPosX(x1);
        peca.setPosY(y1);

        if (peca.getEquipaPeca() == 10) { // PECA PRETA COME
            gameResult.aumentaJogadaPretaValida();
            gameBoard.capturaPorPretas();
            gameBoard.pecaBrancaComida();
            vezDeJogar = 20;
        } else {
            gameResult.aumentaJogadaBrancaValida();
            gameBoard.capturaPorBrancas();
            gameBoard.pecaPretaComida();
            vezDeJogar = 10;
        }

        if (gameResult.getHouveCaptura()) {
            gameResult.setJogadasSemComer(0);
        } else {
            gameResult.setHouveCaptura(true);
            gameResult.setJogadasSemComer(0);
        }
    }

    public void contadorJogadaInvalida(Peca peca) {
        if (peca.getEquipaPeca() == 20) {
            gameResult.aumentaJogadaBrancaInvalida();
        } else {
            gameResult.aumentaJogadaPretaInvalida();
        }
    }

    public String[] getSquareInfo(int x, int y) {
        //  System.out.println("SQUAREINFO\n");
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

                switch (peca.getTipoPeca()) {

                    case 0: // REI
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "rei_white.png";
                        } else {
                            squareInfo[4] = "rei_black.png";
                        }
                        break;
                    case 1: //RAINHA
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "rainha_white.png";
                        } else {
                            squareInfo[4] = "rainha_black.png";
                        }
                        break;
                    case 2:// PONEI MAGICO
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "ponei_magico_white.png";
                        } else {
                            squareInfo[4] = "ponei_magico_black.png";
                        }
                        break;
                    case 3:// PADRE DA VILA
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "padre_vila_white.png";
                        } else {
                            squareInfo[4] = "padre_vila_black.png";
                        }
                        break;
                    case 4:// TORRE HORIZONTAL
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "torre_h_white.png";
                        } else {
                            squareInfo[4] = "torre_h_black.png";
                        }
                        break;
                    case 5:// TORRE VERTICAL
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "torre_v_white.png";
                        } else {
                            squareInfo[4] = "torre_v_black.png";
                        }
                        break;
                    case 6:// HOMER SIMPSON
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "homer_white.png";
                        } else {
                            squareInfo[4] = "homer_black.png";
                        }
                        break;
                    case 7:// JOKER
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "joker_white.png";
                        } else {
                            squareInfo[4] = "joker_black.png";
                        }
                        break;
                }
                return squareInfo; // squareinfo preenchido
            }
        }
        return new String[0]; // squareinfo vazio
    }

    public String[] getPieceInfo(int ID) {
        // System.out.println("PIECEINFO\n");
        String[] pieceInfo = new String[7];
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (Integer.parseInt(peca.getIdPeca()) == ID) {
                pieceInfo[0] = peca.getIdPeca();
                pieceInfo[1] = String.valueOf(peca.getTipoPeca());
                pieceInfo[2] = String.valueOf(peca.getEquipaPeca());
                pieceInfo[3] = peca.getNomePeca();
                pieceInfo[4] = String.valueOf(peca.getEstado());
                if (peca.getEstado().equals("capturado")) {
                    pieceInfo[5] = "";
                    pieceInfo[6] = "";
                } else {
                    pieceInfo[5] = String.valueOf(peca.getPosX());
                    pieceInfo[6] = String.valueOf(peca.getPosY());
                }
            }
        }
        return pieceInfo;
    }

    public String getPieceInfoAsString(int ID) {
        // System.out.println("PIECEINFOASSTRING\n");
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
        else if (gameResult.getHouveCaptura() && gameResult.getJogadasSemComer() >= 10) {
            gameBoard.setResultadoJogo("EMPATE");
            return true;
        }
        // JOGO A DECORRER
        else {
            return false;
        }
    }

    void saveGame(File file) throws IOException{
        Scanner leitor = new Scanner(new FileReader(file));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(file));

            String linha = leitor.nextLine();
            int count = 0;
            int pecas = gameBoard.getNumPecasBrancas()+
                    gameBoard.getNumPecasPretas();
            while(linha != null) {
                switch (count) {

                    case 0:
                        escritor.write(boardSize);
                        count++;
                        linha = leitor.nextLine();
                        break;

                    case 1:
                        escritor.write(gameBoard.getNumPecasBrancas() +
                                gameBoard.getNumPecasPretas());
                        count++;
                        linha = leitor.nextLine();
                        break;
                    default:
                        break;
                }

                if (count >= 2 && count <= 2 + pecas) { //descricao das pecas
                    //escreve logo as peças
                    Map<String, Peca> pecasJogaveis = new TreeMap<>(gameBoard.getPecasEmJogo());
                    for (String id : pecasJogaveis.keySet()) {
                        escritor.write(pecasJogaveis.get(id).getIdPeca() + ";"
                                + pecasJogaveis.get(id).getTipoPeca() + ";" +
                                pecasJogaveis.get(id).getEquipaPeca() + ";" +
                                pecasJogaveis.get(id).getNomePeca());
                        linha = leitor.nextLine();
                        count++;
                    }
                } else {
                    for (int line = 0; line < 8; line++) {
                        for (int coluna = 0; coluna < 8; coluna++) {
                            String[] id = getSquareInfo(line, coluna);
                            if (id.length == 0) {

                            } else {
                                escritor.write(id[0] + ";" + id[1] + ";" +
                                        id[2] + ";" + id[3]);
                            }
                        }
                        linha = leitor.nextLine();
                        count++;
                    }
                }
                /*estatisticas
                //if(numJogadas % 2 != 0){
                //escritor.write("brancas");
                //} else {
                //escritor.write("pretas");
                */}
            }

    //preta
    //20,a,b,c,d,e;10,a,b,c,d,e

    public ArrayList<String> getGameResults() {
        // System.out.println("GETGAMERESULTS\n");

        ArrayList<String> resultadosJogo = new ArrayList<>();

        resultadosJogo.add("JOGO DE CRAZY CHESS");
        resultadosJogo.add("Resultado: " + gameBoard.getResultadoJogo());
        resultadosJogo.add("---");
        resultadosJogo.add("Equipa das Pretas");
        resultadosJogo.add(gameBoard.getCapturadasPorPretas() + "");
        resultadosJogo.add(gameResult.getJogadaPretaValida() + ""); // NUMERO DE JOGADAS
        resultadosJogo.add(gameResult.getJogadaPretaInvalida() + ""); // NUMERO DE TENTATIVAS INVALIDAS
        resultadosJogo.add("Equipa das Brancas");
        resultadosJogo.add(gameBoard.getCapturadasPorBrancas() + "");
        resultadosJogo.add(gameResult.getJogadaBrancaValida() + ""); // NUMERO DE JOGADAS
        resultadosJogo.add(gameResult.getJogadaBrancaInvalida() + ""); // NUMERO DE TENTATIVAS INVALIDAS

        return resultadosJogo;
    }

    public JPanel getAuthorsPanel() {

        ImageIcon image = new ImageIcon("pecaPreta2.png");

        JLabel label = new JLabel();
        label.setIcon(image);
        label.setText("I did it, but at what cost?");
        label.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 250, 250);
        panel.add(label);

        return panel;

    }

    public void undo() {
        // gameBoard.getPecasEmJogo().get(pecaCapturada.getIdPeca()).inJogo();
        //String idPeca = pecaCapturada.getIdPeca();

        if (vezDeJogar == 10) {
            vezDeJogar = 20; // brancas
        } else {
            vezDeJogar = 10; // pretas
        }

        for (Integer i : undo) {
            System.out.println(i.toString());
        }

        if (undo.size() > 4) {
            if (undo.get(undo.size() - 5) == -1) {
                gameBoard.getPecasEmJogo().get(pecaCapturada.getIdPeca()).inJogo();
                pecaCapturada.setPosX(undo.get(undo.size() - 2));
                pecaCapturada.setPosY(undo.get(undo.size() - 1));
                getPieceInfo(Integer.parseInt(pecaCapturada.getIdPeca()));
                getSquareInfo(pecaCapturada.posX, pecaCapturada.posY);
            }
        }

        move(undo.get(undo.size() - 2), undo.get(undo.size() - 1), undo.get(undo.size() - 4), undo.get(undo.size() - 3));

        if (vezDeJogar == 10) {
            vezDeJogar = 20;
        } else {
            vezDeJogar = 10;
        }

        /*
        for (int i = 8; i > 0; i--) {
            System.out.println("apagado: " + undo.get(undo.size() - i));
            undo.remove(undo.size() - i);
        }

         */

        /*
        for (Integer i : undo) {
            System.out.println(undo.size());
        }

         */

    } // FALTA DAR UNDO DE UMA PEÇA CAPTURADA

}