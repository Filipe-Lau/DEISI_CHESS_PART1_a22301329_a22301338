package pt.ulusofona.lp2.deisichess;

public abstract class Peca {
    String idPeca;
    int pontos;
    String tipoPecaString = "";
    int equipaPeca;
    String nomePeca;
    int posX = -1;
    int posY = -1;
    String estado = "capturado";

    public Peca() {
    }

    public Peca(String idPeca,int equipaPeca, String nomePeca) {
        this.idPeca = idPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
    }

    abstract int getPontos();
    abstract int getTipoPeca();
    abstract String getTipoPecaString();
    abstract boolean movePeca(int x1,int y1);
    abstract public String toString();
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getIdPeca() {
        return idPeca;
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

    public void inJogo(){
        estado = "em jogo";
    }

}
