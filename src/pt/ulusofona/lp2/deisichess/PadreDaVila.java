package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca {

    protected final int pontos = 3;
    protected final String tipoPecaString = "Padre da Vila";
    protected final int tipoPeca = 3;
    protected int limiteCasas = 3;

    protected PadreDaVila() {

    }

    protected PadreDaVila(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")) {
            return idPeca + " | Padre da Vila | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Padre da Vila | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
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

        return diffX == diffY && diffX <= limiteCasas;
    }
}
