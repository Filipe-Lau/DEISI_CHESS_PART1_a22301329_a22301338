package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{
    int pontos = 4;
    String tipoPecaString = "Joker";
    int tipoPeca = 7;
    Peca pecaEmUso;

    public Joker(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Joker/" + pecaEmUso.getTipoPecaString() + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public int getPontos(){
        return pontos;
    }
    public int getTipoPeca(){
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return null;
    }

    public boolean movePeca(int x1,int y1){


        return pecaEmUso.movePeca(x1,y1);

        switch (pecaEmUso.getTipoPecaString()){
            case "Rainha":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                     return pecaEmUso.movePeca(x1, y1);
                }
            case "Ponei Magico":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                    return pecaEmUso.movePeca(x1, y1);
                }
            case "Padre da Vila":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                    return pecaEmUso.movePeca(x1, y1);
                }
            case "TorreHor":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                    return pecaEmUso.movePeca(x1, y1);
                }
            case "TorreVert":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                    return pecaEmUso.movePeca(x1, y1);
                }
            case "Homer Simpson":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                    return pecaEmUso.movePeca(x1, y1);
                }
            case "Joker":
                if (pecaEmUso.getEstado().equals("emJogo")) {
                    return pecaEmUso.movePeca(x1, y1);
                }
        }
        return false;
    }

    public Peca getPecaEmUso(){
        int count = 1;

        while (count < 7){




            count++;
        }
    }


}
