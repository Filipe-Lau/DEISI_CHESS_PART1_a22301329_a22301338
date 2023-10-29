package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Tabuleiro {
    int numPecasBrancas;
    int numPecasPretas;
    //int boardSize;
    int capturadasPorPretas = 0;
    int capturadasPorBrancas = 0;
    HashMap<String,Peca> pecasEmJogo;

    HashMap<String,Peca> pecasCapturadas;

    public Tabuleiro() {
    }

    /*
    public Tabuleiro(int numPecasBrancas, int numPecasPretas, int boardSize, HashMap<String, Peca> pecasEmJogo) {
        this.numPecasBrancas = numPecasBrancas;
        this.numPecasPretas = numPecasPretas;
        this.boardSize = boardSize;
        this.pecasEmJogo = pecasEmJogo;
    }
     */

    void pecaBrancaComida() {
        numPecasBrancas--;
    }

    void pecaPretaComida() {
        numPecasPretas--;
    }
}
