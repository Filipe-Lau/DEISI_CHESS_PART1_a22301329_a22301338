package pt.ulusofona.lp2.deisichess;

public class Peca {
    String idPeca;
    int tipoPeca;
    int equipaPeca;
    String nomePeca;
    int posX;
    int posY;

    public Peca(String idPeca, int tipoPeca, int equipaPeca, String nomePeca,int posX,int posY) {
        this.idPeca = idPeca;
        this.tipoPeca = tipoPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
        this.posX = posX;
        this.posY = posY;
    }

    public String getIdPeca() {
        return idPeca;
    }

    @Override
    public String toString() {
        return "Peca{" +
                "idPeca=" + idPeca +
                ", tipoPeca=" + tipoPeca +
                ", equipaPeca=" + equipaPeca +
                ", nomePeca='" + nomePeca + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
