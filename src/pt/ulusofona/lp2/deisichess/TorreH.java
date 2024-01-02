package pt.ulusofona.lp2.deisichess;

public class TorreH extends Peca {
    protected final int pontos = 3;
    protected final String tipoPecaString = "TorreHor";
    protected final int tipoPeca = 4;

    protected TorreH() {

    }

    protected TorreH(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")) {
            return idPeca + " | TorreHor | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | TorreHor | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    protected int getPontos() {
        return pontos;
    }

    protected int getTipoPeca() {
        return tipoPeca;
    }

    @Override
    protected String getTipoPecaString() {
        return tipoPecaString;
    }

    @Override
    protected boolean movePeca(int x1, int y1) {
        return y1 == posY && x1 <= 7;
    }
}
