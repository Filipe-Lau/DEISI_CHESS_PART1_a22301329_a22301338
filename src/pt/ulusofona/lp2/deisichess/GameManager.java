package pt.ulusofona.lp2.deisichess;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

// 10 pretas
// 20 brancas

public class GameManager {
    int boardSize;
    int vezDeJogar = 10;
    Tabuleiro gameBoard = new Tabuleiro();
    GameResult gameResult = new GameResult();
    HistoricoJogada historico = new HistoricoJogada();
    Peca pecaCapturada;
    int nrDaJogada = 0;

    public GameManager() {
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }

    public void loadGame(File file) throws InvalidGameInputException, IOException {
        // System.out.println("LOADGAME\n");

        if (file == null) {
            throw new IOException();
            //throw new InvalidGameInputException(0, "Ficheiro não existente");
        }

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
        historico.jogadasFeitas = new HashMap<>();
        gameBoard.setNumPecasBrancas(0);
        gameBoard.setNumPecasPretas(0);
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

                    String nome = partes[3].trim();

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
                                HomerSimpson homerBranco = new HomerSimpson(id, equipa, nome);
                                gameBoard.getPecasEmJogo().put(id, homerBranco);
                            } else {
                                HomerSimpson homerPreto = new HomerSimpson(id, equipa, nome);
                                gameBoard.getPecasEmJogo().put(id, homerPreto);
                            }
                            break;
                        case 7:
                            if (equipa == 20) {
                                Joker jokerBranco = new Joker(id, equipa, nome);
                                gameBoard.getPecasEmJogo().put(id, jokerBranco);
                            } else {
                                Joker jokerPreto = new Joker(id, equipa, nome);
                                gameBoard.getPecasEmJogo().put(id, jokerPreto);
                            }
                    }
                    count++;

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidGameInputException(numlinha, "DADOS A MENOS (Esperava: " + 4 + " ; Obtive: " + partes.length + ")");
                }

                if (partes.length > 4) {
                    throw new InvalidGameInputException(numlinha, "DADOS A MAIS (Esperava: " + 4 + " ; Obtive: " + partes.length + ")");
                }
            }
            if (numlinha > numpecas + 2) {

                partes = line.split(":", boardSize);

                x = 0;

                for (String id : partes) {
                    if (!Objects.equals(id, "0")) {
                        gameBoard.getPecasEmJogo().get(id).setPosX(x);
                        gameBoard.getPecasEmJogo().get(id).setPosY(y);
                        gameBoard.getPecasEmJogo().get(id).inJogo();
                        if (gameBoard.getPecasEmJogo().get(id).getEquipaPeca() == 10) {
                            gameBoard.aumentaNumPecasPretas();
                        } else {
                            gameBoard.aumentaNumPecasBrancas();
                        }
                    }
                    x++;
                }
                y++;
            }
            line = reader.readLine();
        }
        reader.close();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean moveValidar(int x0, int y0, int x1, int y1) { // OS ERROS DEVEM SER DO SAVE GAME POIS ESTAMOS A DAR RESET AO MOVIMENTOS INVALIDOS QUANDO FAZEMOS LOAD GAME

        Peca peca = obterPecaCoor(x0, y0);
        Peca peca1 = obterPecaCoor(x1, y1);

        if (x0 < 0 || y0 < 0 || x1 < 0 || y1 < 0) {
            return false;
        }

        if (x0 > boardSize - 1 || y0 > boardSize - 1 || x1 > boardSize - 1 || y1 > boardSize - 1) {
            return false;
        }

        if (!validaVezDeJogar(peca)) { // VALIDAR QUEM ESTÁ A JOGAR
            return false;
        }

        if (peca.getTipoPeca() == 6) { // VALIDAR SE O HOMER NÃO JOGA ENQUANTO DORME
            HomerSimpson homer = (HomerSimpson) peca;
            if (homer.getaDormir()) {
                return false;
            }
        }

        if (!peca.movePeca(x1, y1)) { // SE O PECA ANDAR MAIS QUE O LIMITE DE CASAS QUE PODE ANDAR
            return false;
        }

        int tipoPeca = peca.getTipoPeca();

        if (tipoPeca == 7) {
            Joker joker = (Joker) peca;
            tipoPeca = joker.pecaEmUso.getTipoPeca();
        }

        if (!caminhoLivre(tipoPeca, x0, y0, x1, y1)) { // Valida se nenhuma peça passa por cima de outra
            return false;
        }

        if (peca1 != null) { // ATAQUE

            int tipoPeca1 = peca1.getTipoPeca();

            if (tipoPeca1 == 7) {
                Joker joker = (Joker) peca1;
                tipoPeca1 = joker.pecaEmUso.getTipoPeca();
            }

            return (peca.getEquipaPeca() != peca1.getEquipaPeca()) && (tipoPeca != 1 || tipoPeca1 != 1);
        }
        return true;
    }

    public boolean move(int x0, int y0, int x1, int y1) { // OS ERROS DEVEM SER DO SAVE GAME POIS ESTAMOS A DAR RESET AO MOVIMENTOS INVALIDOS QUANDO FAZEMOS LOAD GAME

        Peca peca = obterPecaCoor(x0, y0);
        Peca peca1 = obterPecaCoor(x1, y1);

        if (peca == null) { // CASO TENTE ANDAR COM NENHUMA PEÇA
            return false;
        }

        if (!moveValidar(x0, y0, x1, y1)) {
            contadorJogadaInvalida();
            return false;
        }

        nrDaJogada++;
        gameResult.aumentaJogadasSemComer();

        if (peca1 != null) { // HISTÓRICO DE JOGADA DA PEÇA CAPTURADA

            atualizarCapturas(peca);

            HistoricoJogada pecaCapturada = new HistoricoJogada(peca1, peca1.getPosX(), peca1.getPosY(), -1, -1);
            historico.getJogadasFeitas().put(-nrDaJogada, pecaCapturada);

            peca1.setPosX(-1); // TIRAR A PEÇA CAPTURADA DO TABULEIRO
            peca1.setPosY(-1);
            peca1.notInJogo();
        }

        peca.setPosX(x1);
        peca.setPosY(y1);

        HistoricoJogada pecaAJogar = new HistoricoJogada(peca, x0, y0, x1, y1);
        historico.getJogadasFeitas().put(nrDaJogada, pecaAJogar);

        atualizarJogadasValidas(); // INCREMENTA AS JOGADAS VALIDAS DAS EQUIPAS
        atualizarVezDeJogar(); // PRETA JOGA, A VEZ DE JOGAR MUDA PARA AS BRANCAS E VICE VERSA

        atualizarHomer(); // ATUALIZAR O HOMER DAS RONDAS
        atualizarJoker(); // ATUALIZAR O JOKER DAS RONDAS

        return true;
    }

    public void atualizarHomer() {
        HomerSimpson homerPreto = (HomerSimpson) obterPecaTipo(6, 10);
        if (homerPreto != null) {
            homerPreto.setaDormir(nrDaJogada % 3 == 0);
        }
        HomerSimpson homerBranco = (HomerSimpson) obterPecaTipo(6, 20);
        if (homerBranco != null) {
            homerBranco.setaDormir(nrDaJogada % 3 == 0);
        }
    }

    public void atualizarJoker() {
        Joker jokerPreto = (Joker) obterPecaTipo(7, 10);
        if (jokerPreto != null) {
            jokerPreto.getPecaEmUso((nrDaJogada + 1) % 6);
        }
        Joker jokerBranco = (Joker) obterPecaTipo(7, 20);
        if (jokerBranco != null) {
            jokerBranco.getPecaEmUso((nrDaJogada + 1) % 6);
        }
    }

    public Boolean caminhoLivre(int tipoPeca, int x0, int y0, int x1, int y1) {

        if (tipoPeca == 2) {
            boolean v0, v1, v2, v3, h0, h1, h2, h3;

            /*
                       v0        v1        v3
              2 |____|____|____|____|____|____|____| 1
                |____|____|____|_v1_|____|____|____|
             h0 |____|_XX_|_h0_|_h0_|_h0_|_XX_|____|
                |____|_v0_|____|____|____|_v3_|____|
             h1 |_h1_|_v0_|____|_PM_|____|_v3_|_h2_|  h2
                |____|_v0_|____|____|____|_v3_|____|
             h3 |____|_XX_|_h3_|_h3_|_h3_|_XX_|____|
                |____|____|____|_v2_|____|____|____|
              3 |____|____|____|____|____|____|____| 4
                                 v2
                                                      */

            v0 = caminhoLivre(-1, x0 - 2, y0, x1, y1);
            v1 = caminhoLivre(-1, x0, y0, x0, y0 - 3);
            v2 = caminhoLivre(-1, x0, y0, x0, y0 + 3);
            v3 = caminhoLivre(-1, x0 + 2, y0, x1, y1);
            h0 = caminhoLivre(-1, x0, y0 - 2, x1, y1);
            h1 = caminhoLivre(-1, x0, y0, x0 - 3, y0);
            h2 = caminhoLivre(-1, x0, y0, x0 + 3, y0);
            h3 = caminhoLivre(-1, x0, y0 + 2, x1, y1);

            if (x1 > x0 && y0 > y1) { // 1º QUADRANTE
                return (v1 && h0) || (h2 && v3);
            }

            if (x1 > x0 && y0 < y1) { // 4º QUADRANTE
                return (v2 && h3) || (h2 && v3);
            }

            if (x0 > x1 && y0 > y1) { // 2 QUADRANTE
                return (v1 && h0) || (h1 && v0);
            }

            if (x0 > x1 && y0 < y1) { // 3º QUADRANTE
                return (v2 && h3) || (h1 && v0);
            }
        }

        int y = y0;

        for (int i = x0 + 1; i < x1; i++) { // 2 E 4 QUADRANTE
            if (y + 1 < y1) { // CASO DE DIAGONAL PARA BAIXO DIREITA
                y++;
            }
            if (y - 1 > y1) { // CASO DE DIAGONAL PARA CIMA DIREITO
                y--;
            }
            if (obterPecaCoor(i, y) != null) {
                return false;
            }
        }

        for (int i = x0 - 1; i > x1; i--) {
            if (y + 1 < y1) { // CASO DE DIAGONAL PARA BAIXO DIREITA
                y++;
            }
            if (y - 1 > y1) { // CASO DE DIAGONAL PARA CIMA DIREITO
                y--;
            }
            if (obterPecaCoor(i, y) != null) {
                return false;
            }
        }

        if (x0 != x1) {
            return true;
        }

        for (int i = y0 + 1; i < y1; i++) {
            if (obterPecaCoor(x0, i) != null) {
                return false;
            }
        }

        for (int i = y0 - 1; i > y1; i--) {
            if (obterPecaCoor(x0, i) != null) {
                return false;
            }
        }

        return true;
    }

    public Peca obterPecaTipo(int tipoPeca, int equipa) {
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getTipoPeca() == tipoPeca && peca.getEquipaPeca() == equipa) {
                return peca;
            }
        }
        return null;
    }

    public Peca obterPecaCoor(int x1, int y1) {
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getPosX() == x1 && peca.getPosY() == y1) {
                return peca;
            }
        }
        return null;
    }

    public boolean validaVezDeJogar(Peca peca) {
        return getCurrentTeamID() == peca.getEquipaPeca();
    }

    public void atualizarCapturas(Peca peca) {
        if (peca.getEquipaPeca() == 10) { // PECA PRETA COME
            gameBoard.capturaPorPretas();
            gameBoard.pecaBrancaComida();
        } else {
            gameBoard.capturaPorBrancas();
            gameBoard.pecaPretaComida();
        }

        gameResult.mudaNumCaptura(1);
        gameResult.setJogadasSemComer(0);

    }

    public void atualizarJogadasValidas() {

        if (getCurrentTeamID() == 10) {
            gameResult.aumentaJogadaPretaValida();
        } else {
            gameResult.aumentaJogadaBrancaValida();
        }
    }

    public void atualizarVezDeJogar() {

        if (getCurrentTeamID() == 10) {
            vezDeJogar = 20;
        } else {
            vezDeJogar = 10;
        }
    }

    /*
    public void atacar(Peca peca, Peca peca1, int x1, int y1) {
        pecaCapturada = peca1;
        //  gameBoard.getPecasEmJogo().get(peca1.getIdPeca()).notInJogo();

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

        if (gameResult.getNumCaptura()) {
            gameResult.setJogadasSemComer(0);
        } else {
            gameResult.setHouveCaptura(true);
            gameResult.setJogadasSemComer(0);
        }
    }

     */
    public void contadorJogadaInvalida() {
        if (vezDeJogar == 20) {
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

        Peca reiPreto = obterPecaTipo(0, 10);
        Peca reiBranco = obterPecaTipo(0, 20);

        // CASO DE EMPATE
        if (gameBoard.getNumPecasBrancas() == 1 && gameBoard.getNumPecasPretas() == 1) {
            gameBoard.setResultadoJogo("EMPATE");
            return true;
        }
        // VITÓRIA DAS BRANCAS (REI PRETO CAPTURADO)
        if (reiPreto.getEstado().equals("capturado")) {
            gameBoard.setResultadoJogo("VENCERAM AS BRANCAS");
            return true;
        }
        // VITÓRIA DAS PRETAS (REI BRANCO CAPTURADO)
        if (reiBranco.getEstado().equals("capturado")) {
            gameBoard.setResultadoJogo("VENCERAM AS PRETAS");
            return true;
        }
        // VITÓRIA PARA AS BRANCAS (COMERAM AS PRETAS TODAS)
        else if (gameBoard.getNumPecasBrancas() > 0 && gameBoard.getNumPecasPretas() == 0) {
            gameBoard.setResultadoJogo("VENCERAM AS BRANCAS");
            return true;
        }
        // VITÓRIA PARA AS PRETAS (COMERAM AS BRANCAS TODAS)
        else if (gameBoard.getNumPecasBrancas() == 0 && gameBoard.getNumPecasPretas() > 0) {
            gameBoard.setResultadoJogo("VENCERAM AS PRETAS");
            return true;
        }
        // EMPATE POR EXAUSTÃO
        else if (gameResult.getNumCaptura() > 0 && gameResult.getJogadasSemComer() >= 10) {
            gameBoard.setResultadoJogo("EMPATE");
            return true;
        }
        // JOGO A DECORRER
        else {
            return false;
        }
    }

    public void undo() {

        if (nrDaJogada == 0) {
            return;
        }

        HistoricoJogada jogada = historico.getJogadasFeitas().get(nrDaJogada);

        HistoricoJogada jogada1 = historico.getJogadasFeitas().get(-nrDaJogada);

        if (jogada1 != null) {
            jogada1.getPeca().setPosX(jogada1.getX0());
            jogada1.getPeca().setPosY(jogada1.getY0());
            jogada1.getPeca().inJogo();
            gameResult.mudaNumCaptura(-1);
        }

        if (jogada != null) {
            jogada.getPeca().setPosX(jogada.getX0());
            jogada.getPeca().setPosY(jogada.getY0());
        }

        nrDaJogada--;

        atualizarHomer(); // ATUALIZAR O HOMER DAS RONDAS
        atualizarJoker(); // ATUALIZAR O JOKER DAS RONDAS

        if (vezDeJogar == 10) {
            vezDeJogar = 20;
        } else {
            vezDeJogar = 10;
        }
    }

    public void saveGame(File file) throws IOException {

        Scanner leitor = new Scanner(new FileReader(file));
        BufferedWriter escritor = new BufferedWriter(new FileWriter(file));

        String linha = leitor.nextLine();
        int count = 0;
        int pecas = gameBoard.getNumPecasBrancas() +
                gameBoard.getNumPecasPretas();
        while (linha != null) {
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
                */
        }
    }

    public List<Hint> getHints(int x, int y) { // FALTA O COMPARABLE

        List<Hint> pistas = new ArrayList<>();

        Peca peca = obterPecaCoor(x, y);

        if (peca == null) {
            return null;
        }

        if (peca.getEquipaPeca() != vezDeJogar){
            return null;
        }

        for (int column = 0; column < getBoardSize(); column++) {
            for (int line = 0; line < getBoardSize(); line++) {

                Peca peca1 = obterPecaCoor(column, line); // PROCURARA PEÇA1 PELAS COORDENADAS

                int pontos = 0;

                if (peca1 != null) {
                    pontos = peca1.getPontos(); // OBTER OS PONTOS DAS PEÇAS QUE ESTEJAM NO CAMINHO DA PEÇA
                }

                if (moveValidar(x, y, column, line)) {
                    pistas.add(new Hint(column + "," + line , pontos));
                }
            }
        }
        Collections.sort(pistas);
        return pistas;
    }

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

}