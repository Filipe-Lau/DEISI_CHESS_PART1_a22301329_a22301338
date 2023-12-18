package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {
    int pontos = 5;
    int tipoPeca = 2;

    public PoneiMagico() {

    }

    public PoneiMagico(String idPeca, int tipoPeca, int equipaPeca, String nomePeca) {
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
