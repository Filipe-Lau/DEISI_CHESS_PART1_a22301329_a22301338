package pt.ulusofona.lp2.deisichess;

public class Peca {
    String idPeca;
    int tipoPeca;
    int equipaPeca;
    String nomePeca;
    int posX;
    int posY;
    boolean emJogo;
    int jogadaValida;
    int jogadaInvalida;

    public Peca() {
    }

    public Peca(String idPeca, int tipoPeca, int equipaPeca, String nomePeca, int posX, int posY, boolean emJogo,int jogadaValida,int jogadasInvalidas) {
        this.idPeca = idPeca;
        this.tipoPeca = tipoPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
        this.posX = posX;
        this.posY = posY;
        this.emJogo = emJogo;
        this.jogadaValida = jogadaValida;
        this.jogadaInvalida = jogadasInvalidas;
    }

    public boolean notInJogo(){
        emJogo = false;
        return false;
    }

    @Override
    public String toString() {
        return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ " + "(" + posX + "," + posY + ")" + emJogo;
    }
}
