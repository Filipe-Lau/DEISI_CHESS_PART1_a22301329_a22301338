package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca {
    int pontos = 2;
    String tipoPecaString = "Homer Simpson";
    int tipoPeca = 6;

    int nrDaJogada = 0;
    int limiteCasas = 1;

    /*
    boolean horizontal = false;
    boolean vertical = false;
    boolean diagonal = true;
     */
    boolean aDormir = true;

    public void setNrDaJogada(int nrDaJogada) {
        this.nrDaJogada = nrDaJogada;
    }

    public int getNrDaJogada() {
        return nrDaJogada;
    }

    public HomerSimpson() {

    }

    public HomerSimpson(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (getEstado().equals("capturado")) {
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }

        if (aDormir) {
            return "Doh! zzzzzz";
        }

        return idPeca + " | Homer Simpson | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";

    }

    public int getPontos() {
        return pontos;
    }

    public int getTipoPeca() {
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    public boolean movePeca(int x1, int y1) {
        int diffX = Math.abs(x1 - posX);
        int diffY = Math.abs(y1 - posY);
        return diffX == limiteCasas && diffY == limiteCasas;
    }

    public boolean getaDormir() {
        return aDormir;
    }

    public void setaDormir(Boolean sono) {
        this.aDormir = sono;
    }


}
