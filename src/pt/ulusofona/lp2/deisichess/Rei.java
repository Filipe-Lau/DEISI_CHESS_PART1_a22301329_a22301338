package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca{
    int pontos = 1000;
    int tipoPeca = 0;
    int limiteCasas = 1;

    public Rei(){

    }

    @Override
    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Rei | (infinito) | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public Rei(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public int getPontos(){
        return pontos;
    }

    public int getTipoPeca(){
        return tipoPeca;
    }
}

