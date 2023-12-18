package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {
    int pontos = 5;
    String tipoPecaString = "Ponei Magico";
    int tipoPeca = 2;

    public PoneiMagico() {

    }

    public PoneiMagico(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }

    @Override
    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Ponei Magico | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public int getPontos(){
        return pontos;
    }

    public int getTipoPeca(){
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    @Override
    boolean movePeca(int x1, int y1) {
        if ((x1 - posX == 2 && y1 - posY == 2) || (x1 - posX == -2 && y1 - posY == 2) || (x1 - posX == 2 && y1 - posY == -2) || (x1 - posX == -2 && y1 - posY == -2)){
            setPosX(x1);
            setPosY(y1);
            return true;
        }
        return false;
    }

}
