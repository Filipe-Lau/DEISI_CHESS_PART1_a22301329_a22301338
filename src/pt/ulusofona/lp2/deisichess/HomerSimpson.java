package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    int pontos = 2;
    String tipoPecaString = "Homer Simpson";
    int tipoPeca = 6;
    int limiteCasas = 1;
    boolean horizontal = false;
    boolean vertical = false;
    boolean diagonal = true;
    boolean aDormir = true;
    public HomerSimpson(){

    }

    public HomerSimpson(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Homer Simpson | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public int getPontos(){
        return pontos;
    }
    public int getTipoPeca(){
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    public boolean movePeca(int x1,int y1){
        if ((x1 - posX >= -1 && x1 - posX <= 1) && (y1 - posY >= -1 && y1 - posY <= 1)){
            setPosX(x1);
            setPosY(y1);
            return true;
        }
        return false;
    }


}
