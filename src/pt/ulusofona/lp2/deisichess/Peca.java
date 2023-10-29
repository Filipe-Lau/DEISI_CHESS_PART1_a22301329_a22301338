package pt.ulusofona.lp2.deisichess;

public class Peca {
    String idPeca;
    int tipoPeca;
    int equipaPeca;
    String nomePeca;
    int posX;
    int posY;
    String estado;
    //int jogadaValida;
    //int jogadaInvalida;

    public Peca() {
    }

    public Peca(String idPeca, int tipoPeca, int equipaPeca, String nomePeca, int posX, int posY, String estado) {
        this.idPeca = idPeca;
        this.tipoPeca = tipoPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
        this.posX = posX;
        this.posY = posY;
        this.estado = estado;
       // this.jogadaValida = jogadaValida;
       // this.jogadaInvalida = jogadasInvalidas;
    }

    public void notInJogo(){
        estado = "capturado";
    }
    @Override
    public String toString() {
        return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ " + "(" + posX + ", " + posY + ")";
    }
}
