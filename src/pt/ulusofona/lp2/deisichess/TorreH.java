package pt.ulusofona.lp2.deisichess;

public class TorreH extends Peca {
    int pontos = 3;
    int tipoPeca = 4;
    int limiteCasas = 0; //0 quer dizer que nao tem limite
    boolean horizontal = true;
    boolean vertical = false;
    boolean diagonal = false;

    public TorreH(){

    }

    public TorreH(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | TorreHor | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }
    public int getPontos(){
        return pontos;
    }

    public int getTipoPeca(){
        return tipoPeca;
    }
}
