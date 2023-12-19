package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca{
    int pontos = 1000;
    String tipoPecaString = "Rei";
    int tipoPeca = 0;
    int limiteCasas = 1;

    public Rei(){

    }

    @Override
    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Rei | (infinito) | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public Rei(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public int getPontos(){
        return pontos;
    }

    public int getTipoPeca(){
        return tipoPeca;
    }

    //@Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    @Override
    boolean movePeca(int x1, int y1) {
        if ((x1 - posX >= -1 && x1 - posX <= 1) && (y1 - posY >= -1 && y1 - posY <= 1)){
            setPosX(x1);
            setPosY(y1);
            return true;
        }
        return false;
    }
}

