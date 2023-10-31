package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Tabuleiro {
    int numPecasBrancas;
    int numPecasPretas;
    //int boardSize;
    int capturadasPorPretas;
    int capturadasPorBrancas;

    String resultadoJogo;
    HashMap<String,Peca> pecasEmJogo;
    //HashMap<String,Peca> pecasCapturadas;

    public Tabuleiro() {
    }

    public void setNumPecasPretas(int numPecasPretas) {
        this.numPecasPretas = numPecasPretas;
    }

    public void setNumPecasBrancas(int numPecasBrancas) {
        this.numPecasBrancas = numPecasBrancas;
    }

    public void setResultadoJogo(String resultadoJogo) {
        this.resultadoJogo = resultadoJogo;
    }

    public void setCapturadasPorPretas(int capturadasPorPretas) {
        this.capturadasPorPretas = capturadasPorPretas;
    }

    public void setCapturadasPorBrancas(int capturadasPorBrancas) {
        this.capturadasPorBrancas = capturadasPorBrancas;
    }

    public int getNumPecasPretas() {
        return numPecasPretas;
    }

    public int getNumPecasBrancas() {
        return numPecasBrancas;
    }

    public String getResultadoJogo() {
        return resultadoJogo;
    }

    public int getCapturadasPorPretas() {
        return capturadasPorPretas;
    }

    public int getCapturadasPorBrancas() {
        return capturadasPorBrancas;
    }

    public HashMap<String, Peca> getPecasEmJogo() {
        return pecasEmJogo;
    }
/*
    public HashMap<String, Peca> getPecasCapturadas() {
        return pecasCapturadas;
    }
 */
    void aumentaNumPecasPretas(){
        numPecasPretas++;
    }

    void aumentaNumPecasBrancas(){
        numPecasBrancas++;
    }

    void capturaPorBrancas(){
        capturadasPorBrancas++;
    }

    void capturaPorPretas(){
        capturadasPorPretas++;
    }

    void pecaBrancaComida() {
        numPecasBrancas--;
    }

    void pecaPretaComida() {
        numPecasPretas--;
    }
}
