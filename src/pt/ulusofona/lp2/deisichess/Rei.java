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
            return idPeca + " | Rei | (infinito) | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
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
        int diffX = Math.abs(x1 - posX);
        int diffY = Math.abs(y1 - posY);

        return (diffX >= -1 && diffX <= 1) && (diffY >= -1 && diffY <= 1);
    }
}

