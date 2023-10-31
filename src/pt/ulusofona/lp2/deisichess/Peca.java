package pt.ulusofona.lp2.deisichess;

public class Peca {
    String idPeca;
    int tipoPeca;
    int equipaPeca;
    String nomePeca;
    int posX = -1;
    int posY = -1;
    String estado = "capturado";

    public Peca() {
    }

    public Peca(String idPeca, int tipoPeca, int equipaPeca, String nomePeca) {
        this.idPeca = idPeca;
        this.tipoPeca = tipoPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdPeca() {
        return idPeca;
    }

    public int getTipoPeca() {
        return tipoPeca;
    }

    public int getEquipaPeca() {
        return equipaPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getEstado() {
        return estado;
    }

    public void notInJogo(){
        estado = "capturado";
    }

    @Override
    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ " + "(n/a)";
        }
        return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ " + "(" + posX + ", " + posY + ")";
    }
}
