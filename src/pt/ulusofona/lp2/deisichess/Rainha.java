package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca { //ATENÇÃO NO MOVE, RAINHA NÃO PODE COMER RAINHA!!!

    int pontos = 8;
    String tipoPecaString = "Rainha";
    int tipoPeca = 1;
    int limiteCasas = 5;
    boolean diagonal = true;
    boolean vertical = true;
    boolean horizontal = true;

    public Rainha(){

    }

    @Override
     public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Rainha | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public Rainha(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
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
        return false;
    }
}