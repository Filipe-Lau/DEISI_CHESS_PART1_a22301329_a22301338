package pt.ulusofona.lp2.deisichess;

public abstract class Peca {
    protected String idPeca;
    protected int pontosObtidos = 0;
    protected int numCapturas = 0;
    protected int nrJogadasValidas = 0;
    protected int nrJogadasInvalidas = 0;

    protected int pontos;
    protected String tipoPecaString = "";
    protected int equipaPeca;
    protected String nomePeca;
    protected int posX = -1;
    protected int posY = -1;
    protected String estado = "capturado";

    protected Peca() {
    }

    protected Peca(String idPeca,int equipaPeca, String nomePeca) {
        this.idPeca = idPeca;
        this.equipaPeca = equipaPeca;
        this.nomePeca = nomePeca;
    }
    abstract int getPontos();
    abstract int getTipoPeca();
    abstract String getTipoPecaString();
    abstract boolean movePeca(int x1,int y1);
    abstract public String toString();
    protected void setPosX(int posX) {
        this.posX = posX;
    }

    protected void setPosY(int posY) {
        this.posY = posY;
    }

    protected void setEstado(String estado) {
        this.estado = estado;
    }

    protected void setPontos(int pontos) {
        this.pontos = pontos;
    }

    protected int getNrJogadasValidas() {
        return nrJogadasValidas;
    }

    protected void incrementaNrJogadasValidas(){
        nrJogadasValidas++;
    }

    protected int getNrJogadasInvalidas() {
        return nrJogadasInvalidas;
    }

    protected void incrementaNrJogadasInvalidas(){
        nrJogadasInvalidas++;
    }

    protected void incrementaNumCapturasPeca(){
        numCapturas++;
    }

    protected int getNumCapturas(){
        return numCapturas;
    }

    protected void incrementaPontosObtidos(int pontosCapturados){
        this.pontosObtidos += pontosCapturados;
    }

    protected int getPontosObtidos() {
        return pontosObtidos;
    }

    protected void setPontosObtidos(int pontosObtidos) {
        this.pontosObtidos = pontosObtidos;
    }

    protected String getIdPeca() {
        return idPeca;
    }

    protected int getEquipaPeca() {
        return equipaPeca;
    }

    protected String getNomePeca() {
        return nomePeca;
    }

    protected int getPosX() {
        return posX;
    }

    protected int getPosY() {
        return posY;
    }

    protected String getEstado() {
        return estado;
    }

    protected void notInJogo(){
        estado = "capturado";
    }

    protected void inJogo(){
        estado = "em jogo";
    }

}