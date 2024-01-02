package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca { //ATENÇÃO NO MOVE, RAINHA NÃO PODE COMER RAINHA!!!

    protected final int pontos = 8;
    protected final String tipoPecaString = "Rainha";
    protected final int tipoPeca = 1;
    protected final int limiteCasas = 5;

    protected Rainha(){

    }

    @Override
     public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | Rainha | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Rainha | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    protected Rainha(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }

    protected int getPontos(){
        return pontos;
    }

    protected int getTipoPeca(){
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

        if (diffX == 0) { // MOVIMENTO NA VERTICAL
            return diffY <= limiteCasas;
        }

        if (diffY == 0) { // MOVIMENTO NA HORIZONTAL
            return diffX <= limiteCasas;
        }

        return diffX == diffY && diffX <= limiteCasas; // MOVIMENTO NA DIAGONAL
    }
}
