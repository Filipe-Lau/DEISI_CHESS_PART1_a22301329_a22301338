package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {

    protected final int pontos = 5;
    protected final String tipoPecaString = "Ponei Mágico";
    protected final int tipoPeca = 2;
    protected final int limiteCasas = 2;

    protected PoneiMagico() {

    }

    protected PoneiMagico(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    @Override
    public String toString() {
        if (estado.equals("capturado")) {
            return idPeca + " | Ponei Mágico | " + pontos +  " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Ponei Mágico | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
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

    @Override
    protected boolean movePeca(int x1, int y1) {
        int diffX = Math.abs(x1 - posX);
        int diffY = Math.abs(y1 - posY);

        return diffX == limiteCasas && diffY == limiteCasas;
    }

}
