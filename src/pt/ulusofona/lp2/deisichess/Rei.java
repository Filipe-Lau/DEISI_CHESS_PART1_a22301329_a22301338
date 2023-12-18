package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca{
    int pontos = 1000;
    int tipoPeca = 0;
    int limiteCasas = 1;

    public Rei(){

    }

    public Rei(String idPeca, int equipaPeca, String nomePeca, int tipoPeca) {
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
