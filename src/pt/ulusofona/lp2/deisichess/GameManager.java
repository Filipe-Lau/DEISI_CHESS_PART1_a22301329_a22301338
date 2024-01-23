package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.*;
import java.util.List;

// 10 pretas
// 20 brancas

public class GameManager {
    protected int boardSize;
    protected int vezDeJogar = 10;
    Tabuleiro gameBoard = new Tabuleiro();
    GameResult gameResult = new GameResult();
    HistoricoJogada historico = new HistoricoJogada();
    int nrDaJogada = 0;

    public GameManager() {
    }

    public Map<String, String> customizeBoard() {
        return new HashMap<>();
    }

    int equipa;
    String nome;
    String id;
    int tipo;
    public void loadGame(File file) throws InvalidGameInputException, IOException, InvalidTeamException {
        // System.out.println("LOADGAME\n");
        if (file == null) {
            throw new IOException();
            //throw new InvalidGameInputException(0, "Ficheiro não existente");
        }
        Boolean savegame = false;
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
        gameResult.setJogadaAmarelaValida(0);
        gameResult.setGetJogadaAmarelaInvalida(0);
        gameResult.setJogadasSemComer(0);
        setVezDeJogar(10);
        gameBoard.pecasEmJogo = new HashMap<>();
        historico.jogadasFeitas = new HashMap<>();
        gameBoard.setNumPecasBrancas(0);
        gameBoard.setNumPecasPretas(0);
        gameBoard.setNumPecasAmarelas(0);
        gameBoard.setCapturadasPorPretas(0);
        gameBoard.setCapturadasPorBrancas(0);
        gameBoard.setCapturadasPorAmarelas(0);
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
                        id = partes[0];
                        tipo = Integer.parseInt(partes[1]);
                        nome = partes[3].trim();
                        equipa = Integer.parseInt(partes[2].trim());

                    if(equipa != 10 && equipa != 20 && equipa != 30){
                        throw new InvalidTeamException("Nome da peca com a equipa errada : ", nome);
                    }

                    switch (tipo) {
                        case 0:
                            Peca rei = new Rei(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, rei);
                            break;
                        case 1:
                            Peca rainha = new Rainha(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, rainha);
                            break;
                        case 2:
                            Peca poneiMagico = new PoneiMagico(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, poneiMagico);
                            break;
                        case 3:
                            Peca padre = new PadreDaVila(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, padre);
                            break;
                        case 4:
                            Peca torreH = new TorreH(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, torreH);
                            break;
                        case 5:
                            Peca torreV = new TorreV(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, torreV);
                            break;
                        case 6:
                            HomerSimpson homer = new HomerSimpson(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, homer);
                            break;
                        case 7:
                            Joker joker = new Joker(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, joker);
                            break;
                        case 10:
                            JohnMcClane johnMcClane = new JohnMcClane(id, equipa, nome);
                            gameBoard.getPecasEmJogo().put(id, johnMcClane);
                    }
                    count++;
                 if(partes.length < 4){
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
                        } else if (gameBoard.getPecasEmJogo().get(id).getEquipaPeca() == 20) {
                            gameBoard.aumentaNumPecasBrancas();
                        } else {
                            gameBoard.aumentaNumPecasAmarelas();
                        }
                    }
                    x++;
                }
                y++;

                // LEITURA DE UM FICHEIRO GUARDADO
                if (numlinha == boardSize + numpecas + 2) {
                    String turnoAtual = reader.readLine();
                    if (turnoAtual != null && !turnoAtual.isEmpty()) {
                        setVezDeJogar(Integer.parseInt(turnoAtual));
                        gameResult.setJogadasSemComer(Integer.parseInt(reader.readLine()));
                        gameResult.setnumCaptura(Integer.parseInt(reader.readLine()));

                        if (haPretas()) {
                            gameBoard.setCapturadasPorPretas(Integer.parseInt(reader.readLine()));
                            gameResult.setJogadaPretaValida(Integer.parseInt(reader.readLine()));
                            gameResult.setJogadaPretaInvalida(Integer.parseInt(reader.readLine()));
                        }
                        if (haAmarelas()) {
                            gameBoard.setCapturadasPorAmarelas(Integer.parseInt(reader.readLine()));
                            gameResult.setJogadaAmarelaValida(Integer.parseInt(reader.readLine()));
                            gameResult.setGetJogadaAmarelaInvalida(Integer.parseInt(reader.readLine()));
                        }
                        if (haBrancas()) {
                            gameBoard.setCapturadasPorBrancas(Integer.parseInt(reader.readLine()));
                            gameResult.setJogadaBrancaValida(Integer.parseInt(reader.readLine()));
                            gameResult.setJogadaBrancaInvalida(Integer.parseInt(reader.readLine()));
                        }
                    }
                }
            }

            line = reader.readLine();
        }
        if (!haPretas()) {
            setVezDeJogar(30);
        }
        reader.close();
    }

    public boolean haPretas() {
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getEquipaPeca() == 10) {
                return true;
            }
        }
        return false;
    }

    public boolean haBrancas() {
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getEquipaPeca() == 20) {
                return true;
            }
        }
        return false;
    }

    public boolean haAmarelas() {
        for (Peca peca : gameBoard.getPecasEmJogo().values()) {
            if (peca.getEquipaPeca() == 30) {
                return true;
            }
        }
        return false;
    }

    public void setVezDeJogar(int turnoAtual) {
        vezDeJogar = turnoAtual;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNrDaJogada() {
        return nrDaJogada;
    }

    public int getCurrentTeamID() {
        return vezDeJogar;
    }

    public boolean moveValidar(int x0, int y0, int x1, int y1) { // VALIDA A JOGADA
        Peca peca = obterPecaCoor(x0, y0);
        Peca peca1 = obterPecaCoor(x1, y1);

        if (peca == null) { // SELECIONAR UMA CASA VAZIA
            return false;
        }

        if (x0 == x1 && y0 == y1) { // ANDAR PARA A MESMA CASA
            return false;
        }

        if (x0 < 0 || y0 < 0 || x1 < 0 || y1 < 0) { // ABAIXO DOS LIMITES DO TABULEIRO (NUMEROS NEGATIVOS)
            return false;
        }

        if (x0 > boardSize - 1 || y0 > boardSize - 1 || x1 > boardSize - 1 || y1 > boardSize - 1) { // ACIMA DOS LIMITES DO TABULEIRO (NUMEROS POSITIVOS)
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

        if (!peca.movePeca(x1, y1)) { // VALIDAR O MOVIMENTO DA PEÇA
            return false;
        }

        int tipoPeca = peca.getTipoPeca();

        if (tipoPeca == 7) {
            Joker joker = (Joker) peca;
            tipoPeca = joker.pecaEmUso.getTipoPeca();
        }
        if(tipoPeca == 10) {
            return false;
        }

        if (!caminhoLivre(tipoPeca, x0, y0, x1, y1)) { // VALIDAR SE A PEÇA NÃO PASSA POR CIMA DE OUTRAS PEÇAS
            return false;
        }

        if (peca1 != null) { // ATAQUE
            int tipoPeca1 = peca1.getTipoPeca();
            if (tipoPeca1 == 7) {
                Joker joker = (Joker) peca1;
                tipoPeca1 = joker.pecaEmUso.getTipoPeca();
            }
            if(tipoPeca1 == 10){
                JohnMcClane johnMcClane = (JohnMcClane) peca1;
                if(johnMcClane.getTentativasComer() < 4){
                    johnMcClane.aumentaTentativasComer();
                }
            }
            // VALIDA SE NÃO ESTOU A ATACAR UMA PEÇA DA MESMA EQUIPA E SE NÃO ESTOU A ATACAR UMA RAINHA COM UMA RAINHA
            return (peca.getEquipaPeca() != peca1.getEquipaPeca()) && (tipoPeca != 1 || tipoPeca1 != 1);
        }
        return true;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        Peca peca = obterPecaCoor(x0, y0);
        Peca peca1 = obterPecaCoor(x1, y1);

        if (!moveValidar(x0, y0, x1, y1)) {// JOGADA INVALIDA
            if (peca != null) {
                peca.incrementaNrJogadasInvalidas();
            }
            contadorJogadaInvalida();
            return false;
        }

        nrDaJogada++;
        gameResult.aumentaJogadasSemComer();

        if (peca1 != null) {// ATAQUE

            if(peca1.getTipoPeca() == 10){
                JohnMcClane johnMcClane = (JohnMcClane) peca1;
                if(johnMcClane.getTentativasComer() < 4) {
                    int xTemp = peca.getPosX();
                    int yTemp = peca.getPosY();

                    peca.setPosX(peca1.getPosX());
                    peca.setPosY(peca1.getPosY());

                    peca1.setPosX(xTemp);
                    peca1.setPosY(yTemp);

                    HistoricoJogada pecaAJogar = new HistoricoJogada(peca, x0, y0, x1, y1);
                    historico.getJogadasFeitas().put(nrDaJogada, pecaAJogar);

                    peca.incrementaNrJogadasValidas(); // INCREMENTA O NUMERO DE JOGADAS VALIDAS DA PECA
                    atualizarJogadasValidas(); // INCREMENTA AS JOGADAS VALIDAS DAS EQUIPAS
                    atualizarVezDeJogar(); // PRETA JOGA, A VEZ DE JOGAR MUDA PARA AS BRANCAS E VICE VERSA
                    atualizarHomer(); // ATUALIZAR O HOMER DAS RONDAS
                    atualizarJoker(); // ATUALIZAR O JOKER DAS RONDAS

                    return true;

                }
            }

            atualizarCapturas(peca);
            HistoricoJogada pecaCapturada = new HistoricoJogada(peca1, peca1.getPosX(), peca1.getPosY(), -1, -1);
            historico.getJogadasFeitas().put(-nrDaJogada, pecaCapturada);

            peca.incrementaPontosObtidos(peca1.getPontos()); // A PEÇA FICA COM OS PONTOS DA PEÇA QUE CAPTUROU
            peca.incrementaNumCapturasPeca();

            peca1.setPosX(-1); // TIRAR A PEÇA CAPTURADA DO TABULEIRO
            peca1.setPosY(-1);
            peca1.notInJogo();
        }

        peca.setPosX(x1);
        peca.setPosY(y1);

        HistoricoJogada pecaAJogar = new HistoricoJogada(peca, x0, y0, x1, y1);
        historico.getJogadasFeitas().put(nrDaJogada, pecaAJogar);

        peca.incrementaNrJogadasValidas(); // INCREMENTA O NUMERO DE JOGADAS VALIDAS DA PECA
        atualizarJogadasValidas(); // INCREMENTA AS JOGADAS VALIDAS DAS EQUIPAS
        atualizarVezDeJogar(); // PRETA JOGA, A VEZ DE JOGAR MUDA PARA AS BRANCAS E VICE VERSA
        atualizarHomer(); // ATUALIZAR O HOMER DAS RONDAS
        atualizarJoker(); // ATUALIZAR O JOKER DAS RONDAS
        return true;
    }

    public void atualizarHomer() {
        HomerSimpson homerPreto = (HomerSimpson) obterPecaTipo(6, 10);
        if (homerPreto != null) {
            homerPreto.setaDormir(nrDaJogada % 3 == 0); // DEPOIS DE CADA JOGADA ATUALIZAMOS O HOMER
        }
        HomerSimpson homerBranco = (HomerSimpson) obterPecaTipo(6, 20);
        if (homerBranco != null) {
            homerBranco.setaDormir(nrDaJogada % 3 == 0); // DEPOIS DE CADA JOGADA ATUALIZAMOS O HOMER
        }
        HomerSimpson homerAmarelo = (HomerSimpson) obterPecaTipo(6,30);
        if(homerAmarelo != null)  {
            homerAmarelo.setaDormir(nrDaJogada % 3 == 0);
        }
    }

    public void atualizarJoker() {
        Joker jokerPreto = (Joker) obterPecaTipo(7, 10);
        if (jokerPreto != null) {
            jokerPreto.getPecaEmUso((nrDaJogada + 1) % 6); // DEPOIS DE CADA JOGADA ATUALIZAMOS O JOKER
        }
        Joker jokerBranco = (Joker) obterPecaTipo(7, 20);
        if (jokerBranco != null) {
            jokerBranco.getPecaEmUso((nrDaJogada + 1) % 6); // DEPOIS DE CADA JOGADA ATUALIZAMOS O JOKER
        }
        Joker jokerAmarelo = (Joker) obterPecaTipo(7,30);
        if(jokerAmarelo != null) {
            jokerAmarelo.getPecaEmUso((nrDaJogada+1) % 6);
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
        if(haPretas() && haBrancas()) {
            if (peca.getEquipaPeca() == 10) { // PECA PRETA COME
                gameBoard.capturaPorPretas();
                gameBoard.pecaBrancaComida();
            } else {
                gameBoard.capturaPorBrancas();
                gameBoard.pecaPretaComida();
            }
        } else if(haPretas() && haAmarelas()){
            if(peca.getEquipaPeca() == 10){
                gameBoard.capturaPorPretas();
                gameBoard.pecaAmarelaComida();
            } else {
                gameBoard.capturaPorAmarelas();
                gameBoard.pecaPretaComida();
            }
        } else {
            if(peca.getEquipaPeca() == 30){
                gameBoard.capturaPorAmarelas();
                gameBoard.pecaBrancaComida();
            } else {
                gameBoard.capturaPorBrancas();
                gameBoard.pecaAmarelaComida();
            }
        }

        gameResult.mudaNumCaptura(1); // CONTADOR DE CAPTURAS
        gameResult.setJogadasSemComer(0); // RESET DAS JOGADSAS SEM COMER
    }

    public void atualizarJogadasValidas() {
        if (getCurrentTeamID() == 10) {
            gameResult.aumentaJogadaPretaValida();
        } else if (getCurrentTeamID() == 20){
            gameResult.aumentaJogadaBrancaValida();
        } else {
            gameResult.aumentaJogadaAmarelaValida();
        }
    }

    public void atualizarVezDeJogar() {
        if(haPretas() && haBrancas()) {
            if (getCurrentTeamID() == 10) {
                setVezDeJogar(20);
            } else {
                setVezDeJogar(10);
            }
        } else if (haPretas() && haAmarelas()){
            if (getCurrentTeamID() == 10) {
                setVezDeJogar(30);
            } else {
                setVezDeJogar(10);
            }
        } else {
            if(getCurrentTeamID() == 20){
                setVezDeJogar(30);
            } else {
                setVezDeJogar(20);
            }
        }
    }

    public void contadorJogadaInvalida() {
        if (getCurrentTeamID() == 20) {
            gameResult.aumentaJogadaBrancaInvalida();
        } else if (getCurrentTeamID() == 10){
            gameResult.aumentaJogadaPretaInvalida();
        } else {
            gameResult.aumentaJogadaAmarelaInvalida();
        }
    }

    public String[] getSquareInfo(int x, int y) { //falta as imagens
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
                        } else if (peca.getEquipaPeca() == 10) {
                            squareInfo[4] = "rei_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                        break;
                    case 1: //RAINHA
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "rainha_white.png";
                        } else if (peca.getEquipaPeca() == 10){
                            squareInfo[4] = "rainha_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                        break;
                    case 2:// PONEI MAGICO
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "ponei_magico_white.png";
                        } else if (peca.getEquipaPeca() == 10){
                            squareInfo[4] = "ponei_magico_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                        break;
                    case 3:// PADRE DA VILA
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "padre_vila_white.png";
                        } else if(peca.getEquipaPeca() == 10) {
                            squareInfo[4] = "padre_vila_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                        break;
                    case 4:// TORRE HORIZONTAL
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "torre_h_white.png";
                        } else if(peca.getEquipaPeca() == 10){
                            squareInfo[4] = "torre_h_black.png";
                        } else {
                        squareInfo[4] = "amarelo.png";
                    }
                        break;
                    case 5:// TORRE VERTICAL
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "torre_v_white.png";
                        } else if(peca.getEquipaPeca() == 10){
                            squareInfo[4] = "torre_v_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                        break;
                    case 6:// HOMER SIMPSON
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "homer_white.png";
                        } else if (peca.getEquipaPeca() == 10){
                            squareInfo[4] = "homer_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                        break;
                    case 7:// JOKER
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "joker_white.png";
                        } else if (peca.getEquipaPeca() == 10){
                            squareInfo[4] = "joker_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
                        }
                    case 10:
                        if (peca.getEquipaPeca() == 20) {
                            squareInfo[4] = "joker_white.png";
                        } else if (peca.getEquipaPeca() == 10){
                            squareInfo[4] = "joker_black.png";
                        } else {
                            squareInfo[4] = "amarelo.png";
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

    public boolean gameOver() {

        if(haPretas() && haBrancas()) {

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
        } else if(haPretas() && haAmarelas()){
            Peca reiPreto = obterPecaTipo(0, 10);
            Peca reiAmarelo = obterPecaTipo(0, 30);

            // CASO DE EMPATE
            if (gameBoard.getNumPecasAmarelas() == 1 && gameBoard.getNumPecasPretas() == 1) {
                gameBoard.setResultadoJogo("EMPATE");
                return true;
            }
            // VITÓRIA DAS BRANCAS (REI PRETO CAPTURADO)
            if (reiPreto.getEstado().equals("capturado")) {
                gameBoard.setResultadoJogo("VENCERAM AS AMARELAS");
                return true;
            }
            // VITÓRIA DAS PRETAS (REI BRANCO CAPTURADO)
            if (reiAmarelo.getEstado().equals("capturado")) {
                gameBoard.setResultadoJogo("VENCERAM AS PRETAS");
                return true;
            }
            // VITÓRIA PARA AS BRANCAS (COMERAM AS PRETAS TODAS)
            else if (gameBoard.getNumPecasAmarelas() > 0 && gameBoard.getNumPecasPretas() == 0) {
                gameBoard.setResultadoJogo("VENCERAM AS AMARELAS");

                return true;
            }
            // VITÓRIA PARA AS PRETAS (COMERAM AS BRANCAS TODAS)
            else if (gameBoard.getNumPecasAmarelas() == 0 && gameBoard.getNumPecasPretas() > 0) {
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
        } else if (haAmarelas() && haBrancas()){
            Peca reiAmarelo = obterPecaTipo(0, 30);
            Peca reiBranco = obterPecaTipo(0, 20);

            // CASO DE EMPATE
            if (gameBoard.getNumPecasBrancas() == 1 && gameBoard.getNumPecasAmarelas() == 1) {
                gameBoard.setResultadoJogo("EMPATE");
                return true;
            }
            // VITÓRIA DAS BRANCAS (REI PRETO CAPTURADO)
            if (reiAmarelo.getEstado().equals("capturado")) {
                gameBoard.setResultadoJogo("VENCERAM AS BRANCAS");
                return true;
            }
            // VITÓRIA DAS PRETAS (REI BRANCO CAPTURADO)
            if (reiBranco.getEstado().equals("capturado")) {
                gameBoard.setResultadoJogo("VENCERAM AS AMARELAS");
                return true;
            }
            // VITÓRIA PARA AS BRANCAS (COMERAM AS PRETAS TODAS)
            else if (gameBoard.getNumPecasBrancas() > 0 && gameBoard.getNumPecasAmarelas() == 0) {
                gameBoard.setResultadoJogo("VENCERAM AS BRANCAS");

                return true;
            }
            // VITÓRIA PARA AS PRETAS (COMERAM AS BRANCAS TODAS)
            else if (gameBoard.getNumPecasBrancas() == 0 && gameBoard.getNumPecasAmarelas() > 0) {
                gameBoard.setResultadoJogo("VENCERAM AS AMARELAS");
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
        return false;
    }

    public ArrayList<String> getGameResults() {
        // System.out.println("GETGAMERESULTS\n");
        ArrayList<String> resultadosJogo = new ArrayList<>();
        resultadosJogo.add("JOGO DE CRAZY CHESS");
        resultadosJogo.add("Resultado: " + gameBoard.getResultadoJogo());
        resultadosJogo.add("---");
        if(haPretas()) {
            resultadosJogo.add("Equipa das Pretas");
            resultadosJogo.add(gameBoard.getCapturadasPorPretas() + "");
            resultadosJogo.add(gameResult.getJogadaPretaValida() + ""); // NUMERO DE JOGADAS
            resultadosJogo.add(gameResult.getJogadaPretaInvalida() + ""); // NUMERO DE TENTATIVAS INVALIDAS
        }
        if(haAmarelas()){
            resultadosJogo.add("Equipa das Amarelas");
            resultadosJogo.add(gameBoard.getCapturadasPorAmarelas()+ "");
            resultadosJogo.add(gameResult.getJogadaAmarelaValida()+ "");
            resultadosJogo.add(gameResult.getJogadaAmarelaInvalida()+ "");
        }
        if(haBrancas()) {
            resultadosJogo.add("Equipa das Brancas");
            resultadosJogo.add(gameBoard.getCapturadasPorBrancas() + "");
            resultadosJogo.add(gameResult.getJogadaBrancaValida() + ""); // NUMERO DE JOGADAS
            resultadosJogo.add(gameResult.getJogadaBrancaInvalida() + ""); // NUMERO DE TENTATIVAS INVALIDAS
        }
        return resultadosJogo;
    }

    public void undo() {

        if (nrDaJogada == 0) { // NÃO DÁ PARA FAZER MAIS UNDO
            return;
        }

        HistoricoJogada jogada = historico.getJogadasFeitas().get(nrDaJogada); // JOGADA SEM CAPTURA / PEÇA ATACANTE
        HistoricoJogada jogada1 = historico.getJogadasFeitas().get(-nrDaJogada); // JOGADA COM CAPTURA / PEÇA ATACADA

        if (jogada1 != null) { // HOUVE CAPTURA
            jogada1.getPeca().setPosX(jogada1.getX0());
            jogada1.getPeca().setPosY(jogada1.getY0());
            jogada1.getPeca().inJogo();
            gameResult.mudaNumCaptura(-1); // DECREMENTA CONTADOR DE CAPTURAS
        }

        if (jogada != null) { // HOUVE JOGADA SEM CAPTURA
            jogada.getPeca().setPosX(jogada.getX0());
            jogada.getPeca().setPosY(jogada.getY0());
        }

        nrDaJogada--;

        atualizarHomer(); // ATUALIZAR O HOMER DAS RONDAS
        atualizarJoker(); // ATUALIZAR O JOKER DAS RONDAS

        if(haPretas() && haBrancas()){
            if (getCurrentTeamID() == 10) {
                setVezDeJogar(20);
            } else {
                setVezDeJogar(10);
            }
        } else if(haPretas() && haAmarelas()){
            if(getCurrentTeamID() == 10){
                setVezDeJogar(30);
            } else {
                setVezDeJogar(10);
            }
        } else if(haAmarelas() && haBrancas()){
            if(getCurrentTeamID() == 30){
                setVezDeJogar(20);
            } else {
                setVezDeJogar(30);
            }
        }

    }

    public void saveGame(File file) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(getBoardSize() + "\n" + gameBoard.getPecasEmJogo().size() + "\n");

        Map<String, Peca> pecas = new TreeMap<>(gameBoard.getPecasEmJogo());
        for (String idPeca : pecas.keySet()) {
            Peca peca = pecas.get(idPeca);
            writer.write(peca.getIdPeca() + ":" + peca.getTipoPeca() + ":" + peca.getEquipaPeca() +
                    ":" + peca.getNomePeca() + "\n");
        }

        for (int line = 0; line < getBoardSize(); line++) {
            for (int column = 0; column < getBoardSize(); column++) {
                Peca peca = obterPecaCoor(column, line);
                if (peca != null) {
                    if (column == getBoardSize() - 1) {
                        writer.write(peca.getIdPeca() + "\n");
                    } else {
                        writer.write(peca.getIdPeca() + ":");
                    }

                } else {
                    if (column == getBoardSize() - 1) {
                        writer.write("0\n");
                    } else {
                        writer.write("0:");
                    }
                }
            }
        }
        // JOGO
        writer.write(getCurrentTeamID() + "\n");
        writer.write(gameResult.getJogadasSemComer() + "\n");
        writer.write(gameResult.getNumCaptura() + "\n");
        if (haPretas()) {
            // PECAS PRETAS
            writer.write(gameBoard.getCapturadasPorPretas() + "\n");
            writer.write(gameResult.getJogadaPretaValida() + "\n");
            writer.write(gameResult.getJogadaPretaInvalida() + "\n");
        }
        if (haAmarelas()) {
            writer.write(gameBoard.getCapturadasPorAmarelas() + "\n");
            writer.write(gameResult.getJogadaAmarelaValida() + "\n");
            writer.write(gameResult.getJogadaAmarelaInvalida() + "\n");
            writer.close();
        }
        if (haBrancas()) {
            // PECAS BRANCAS
            writer.write(gameBoard.getCapturadasPorBrancas() + "\n");
            writer.write(gameResult.getJogadaBrancaValida() + "\n");
            writer.write(gameResult.getJogadaBrancaInvalida() + "\n");
            writer.close();
        }
    }


    public List<Comparable> getHints(int x, int y) {

        List<Comparable> pistas = new ArrayList<>();

        Peca peca = obterPecaCoor(x, y);

        if (peca == null) {
            return null;
        }

        if (peca.getEquipaPeca() != getCurrentTeamID()) {
            return null;
        }

        if (peca.getTipoPeca() == 10) {
            pistas.add("Sou o JohnMcClane. Yippee ki yay. Sou duro de roer, mas não me sei mover");
        }
        for (int column = 0; column < getBoardSize(); column++) { // x
            for (int line = 0; line < getBoardSize(); line++) { // y

                Peca peca1 = obterPecaCoor(column, line); // PROCURARA PEÇA1 PELAS COORDENADAS

                int pontos = 0;

                if (peca1 != null) {
                    pontos = peca1.getPontos(); // OBTER OS PONTOS DAS PEÇAS QUE ESTEJAM NO CAMINHO DA PEÇA
                }
                if (moveValidar(x, y, column, line)) { // SE JOGADA FOR VALIDA DÁ A PISTA
                    pistas.add(new Hint(column + "," + line, pontos));
                }
            }
        }
        Collections.sort(pistas);
        return pistas;
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