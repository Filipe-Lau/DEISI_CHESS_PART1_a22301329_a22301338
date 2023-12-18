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

    public TorreH(String idPeca, int tipoPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
        this.tipoPeca = tipoPeca;
    }

    public int getPontos(){
        return pontos;
    }

    public int getTipoPeca(){
        return tipoPeca;
    }
}
