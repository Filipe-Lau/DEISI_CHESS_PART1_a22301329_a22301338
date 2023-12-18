package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{
    int pontos = 4;
    int tipoPeca = 7;
    Peca pecaEmUso;



    public int getPontos(){
        return pontos;
    }
    public int getTipoPeca(){
        return tipoPeca;
    }
}
