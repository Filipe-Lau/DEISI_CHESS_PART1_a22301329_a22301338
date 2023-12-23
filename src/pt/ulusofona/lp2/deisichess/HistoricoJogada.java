package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class HistoricoJogada {

    Peca peca;
    int x0;
    int y0;
    int x1;
    int y1;


    HashMap<Integer,HistoricoJogada> jogadasFeitas = new HashMap<>();

    public HashMap<Integer, HistoricoJogada> getJogadasFeitas() {
        return jogadasFeitas;
    }

    public HistoricoJogada() {

    }
    public HistoricoJogada(Peca peca, int x0, int y0, int x1, int y1) {
        this.peca = peca;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public Peca getPeca() {
        return peca;
    }

    public int getX0() {
        return x0;
    }

    public int getY0() {
        return y0;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    @Override
    public String toString() {
        return "HistoricoJogada{" +
                "peca=" + peca +
                ", x0=" + x0 +
                ", y0=" + y0 +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", jogadasFeitas=" + jogadasFeitas +
                '}';
    }
}
