package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Tabuleiro {
    int numPecasBrancas;
    int numPecasPretas;
    int boardSize;
    HashMap<String,Peca> pecaHashMap;

    public Tabuleiro() {
    }

    public Tabuleiro(int numPecasBrancas, int numPecasPretas, int boardSize, HashMap<String, Peca> pecaHashMap) {
        this.numPecasBrancas = numPecasBrancas;
        this.numPecasPretas = numPecasPretas;
        this.boardSize = boardSize;
        this.pecaHashMap = pecaHashMap;
    }

    void pecaBrancaComida() {
        numPecasBrancas--;
    }

    void pecaPretaComida() {
        numPecasPretas--;
    }
}
