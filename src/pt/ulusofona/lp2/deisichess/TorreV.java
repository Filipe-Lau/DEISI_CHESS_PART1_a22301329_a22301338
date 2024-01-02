package pt.ulusofona.lp2.deisichess;

public class TorreV extends Peca {
    protected final int pontos = 3;
    protected String tipoPecaString = "TorreVert";
    protected final int tipoPeca = 5;
    protected int limiteCasas = 0; //0 quer dizer que nao tem limite

    protected TorreV() {

    }

    protected TorreV(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")) {
            return idPeca + " | TorreVert | " + pontos +  " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | TorreVert | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
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
        return (x1 == posX && y1 <= 7);
    }
}
