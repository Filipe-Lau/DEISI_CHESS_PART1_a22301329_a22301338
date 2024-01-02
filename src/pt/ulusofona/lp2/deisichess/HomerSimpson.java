package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca {

    protected final int pontos = 2;
    protected String tipoPecaString = "Homer Simpson";
    protected final int tipoPeca = 6;
    protected int nrDaJogada = 0;
    protected int limiteCasas = 1;
    protected boolean aDormir = true;

    protected void setNrDaJogada(int nrDaJogada) {
        this.nrDaJogada = nrDaJogada;
    }

    protected int getNrDaJogada() {
        return nrDaJogada;
    }

    protected HomerSimpson() {
    }

    protected HomerSimpson(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (getEstado().equals("capturado")) {
            return idPeca + " | Homer Simpson | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }

        if (aDormir) {
            return "Doh! zzzzzz";
        }

        return idPeca + " | Homer Simpson | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";

    }

    protected int getPontos() {
        return pontos;
    }

    protected int getTipoPeca() {
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    protected boolean movePeca(int x1, int y1) {
        int diffX = Math.abs(x1 - posX);
        int diffY = Math.abs(y1 - posY);
        return diffX == limiteCasas && diffY == limiteCasas;
    }

    protected boolean getaDormir() {
        return aDormir;
    }

    protected void setaDormir(Boolean sono) {
        this.aDormir = sono;
    }

}
