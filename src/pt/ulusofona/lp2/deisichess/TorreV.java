package pt.ulusofona.lp2.deisichess;

public class TorreV extends Peca{
    int pontos = 3;
    int tipoPeca = 5;
    int limiteCasas = 0; //0 quer dizer que nao tem limite
    boolean horizontal = false;
    boolean vertical = true;
    boolean diagonal = false;

    public TorreV(){

    }

    public TorreV(String idPeca, int tipoPeca, int equipaPeca, String nomePeca) {
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
