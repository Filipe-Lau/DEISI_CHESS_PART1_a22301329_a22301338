package pt.ulusofona.lp2.deisichess;

public class Peca {
    String idPeca;
    int tipoPeca;
    int equipaPeca;
    String nomePeca;
    int posX;
    int posY;
    boolean emJogo;

    public Peca(String idPeca, int tipoPeca, int equipaPeca, String nomePeca,int posX,int posY,boolean emJogo) {
        this.idPeca = idPeca;
        this.tipoPeca = tipoPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
        this.posX = posX;
        this.posY = posY;
        this.emJogo = emJogo;
    }

    public boolean notInJogo(){
        emJogo = false;
        return false;
    }
    public boolean isEmJogo() {
        return emJogo;
    }

    @Override
    public String toString() {
        return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ " + "(" + posX + "," + posY + ")";
    }
}
