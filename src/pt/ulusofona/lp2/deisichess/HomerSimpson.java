package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    int pontos = 2;
    int tipoPeca = 6;
    int limiteCasas = 1;
    boolean horizontal = false;
    boolean vertical = false;
    boolean diagonal = true;
    boolean aDormir = true;
    public HomerSimpson(){

    }

    public HomerSimpson(String idPeca, int tipoPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
        this.tipoPeca = tipoPeca;
    }

    public int getPontos(){
        return pontos;
    }
    public int getTipoPeca(){
        return tipoPeca;
    }
}
