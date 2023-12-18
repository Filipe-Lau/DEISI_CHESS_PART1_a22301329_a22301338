package pt.ulusofona.lp2.deisichess;

public abstract class Peca {
    String idPeca;
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
        //this.pontos = pontos;
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

    @Override
    public String toString() {

        String tipoPecaString = "";

        if (estado.equals("capturado")){
            return idPeca + " | " + /*tipoPeca +*/ " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        tipoPecaString = switch (equipaPeca /* esta mal devia ser tipoPeca*/) {
            case 0 -> // REI
                    "Rei";
            case 1 -> //RAINHA
                    "Rainha";
            case 2 ->// PONEI MAGICO
                    "Ponei Mágico";
            case 3 ->// PADRE DA VILA
                    "Padre da Vila";
            case 4 ->// TORRE HORIZONTAL
                    "Torre Horizontal";
            case 5 ->// TORRE VERTICAL
                    "Torre Vertical";
            case 6 ->// HOMER SIMPSON
                    "Homer Simpson";
            case 7 ->// JOKER
                    "Joker";
            default -> tipoPecaString;
        };

        if (getPontos() == 1000){
            return idPeca + " | " + tipoPecaString + " | (infinito) | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
        }
        //FALTA MUDAR O TIPODEPECA STRING NO JOKER QUANDO MUDA DE PEÇA EXEMPLO: JOKER/RAINHA -> JOKER/PONEI MÁGICO
        //return idPeca + " | " + tipoPecaString + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    return "";
    }
}
