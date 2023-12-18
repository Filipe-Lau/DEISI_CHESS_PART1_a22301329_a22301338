package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{
    int pontos = 4;
    int tipoPeca = 7;
    Peca pecaEmUso;

    public Joker(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Joker/" + pecaEmUso.getNomePeca() + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public int getPontos(){
        return pontos;
    }
    public int getTipoPeca(){
        return tipoPeca;
    }

    public boolean moverPeca(int x1,int y1){

    }



}
