package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {
    int pontos = 5;
    String tipoPecaString = "Ponei Mágico";
    int tipoPeca = 2;

    public PoneiMagico() {

    }

    public PoneiMagico(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca,"Ponei Mágico");
    }

    @Override
    public String toString() {
        if (estado.equals("capturado")) {
            return idPeca + " | Ponei Mágico | " + pontos +  " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Ponei Mágico | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
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

    @Override
    boolean movePeca(int x1, int y1) {
        int diffX = Math.abs(x1 - posX);
        int diffY = Math.abs(y1 - posY);

        return diffX == 2 && diffY == 2;
    }

}
