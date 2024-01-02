package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {
    protected final int pontos = 1000;
    protected String tipoPecaString = "Rei";
    protected final int tipoPeca = 0;
    protected int limiteCasas = 1;

    protected Rei(){

    }

    @Override
    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | Rei | (infinito) | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Rei | (infinito) | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    protected Rei(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    protected int getPontos(){
        return pontos;
    }

    protected int getTipoPeca(){
        return tipoPeca;
    }

    String getTipoPecaString() {
        return tipoPecaString;
    }

    @Override
    protected boolean movePeca(int x1, int y1) {
        int diffX = Math.abs(x1 - posX);
        int diffY = Math.abs(y1 - posY);

        return (diffX >= -1 && diffX <= 1) && (diffY >= -1 && diffY <= 1);
    }

}

