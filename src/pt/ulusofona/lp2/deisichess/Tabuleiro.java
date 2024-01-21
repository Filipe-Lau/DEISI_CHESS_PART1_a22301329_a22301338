package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Tabuleiro {
    int numPecasAmarelas;
    int numPecasBrancas;
    int numPecasPretas;
    //int boardSize;
    int capturadasPorPretas;
    int capturadasPorBrancas;
    int capturadasPorAmarelas;

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
    public void setNumPecasAmarelas(int numPecasAmarelas){
        this.numPecasAmarelas = numPecasAmarelas;
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
    public void setCapturadasPorAmarelas(int capturadasPorAmarelas) {
        this.capturadasPorAmarelas = capturadasPorAmarelas;
    }

    public int getNumPecasPretas() {
        return numPecasPretas;
    }

    public int getNumPecasBrancas() {
        return numPecasBrancas;
    }
    public int getNumPecasAmarelas(){return numPecasAmarelas;}
    public String getResultadoJogo() {
        return resultadoJogo;
    }

    public int getCapturadasPorAmarelas(){
        return capturadasPorAmarelas;
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
    void aumentaNumPecasAmarelas(){numPecasAmarelas++;}

    void capturaPorBrancas(){
        capturadasPorBrancas++;
    }
    void capturaPorAmarelas(){capturadasPorAmarelas++;}

    void capturaPorPretas(){
        capturadasPorPretas++;
    }

    void pecaBrancaComida() {
        numPecasBrancas--;
    }
    void pecaAmarelaComida(){numPecasAmarelas--;}

    void pecaPretaComida() {
        numPecasPretas--;
    }
}
