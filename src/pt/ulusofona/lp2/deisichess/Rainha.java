package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca { //ATENÇÃO NO MOVE, RAINHA NÃO PODE COMER RAINHA!!!

    int pontos = 8;
    int tipoPeca = 1;
    int limiteCasas = 5;
    boolean diagonal = true;
    boolean vertical = true;
    boolean horizontal = true;

    public Rainha(){

    }
    public Rainha(String idPeca, int tipoPeca, int equipaPeca, String nomePeca) {
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
