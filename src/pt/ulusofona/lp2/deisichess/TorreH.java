package pt.ulusofona.lp2.deisichess;

public class TorreH extends Peca {
    int pontos = 3;

    String tipoPecaString = "TorreHor";
    int tipoPeca = 4;
    int limiteCasas = 0; //0 quer dizer que nao tem limite
    boolean horizontal = true;
    boolean vertical = false;
    boolean diagonal = false;

    public TorreH(){

    }

    public TorreH(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | TorreHor | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
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

    @Override
    boolean movePeca(int x1, int y1) {
       // if (y1 == posY && x1 != posX && x1 >= -1 && x1 <= 7)/* de 0 a 7, 8 casas possiveis?*/ {
            if (y1 == posY && x1 <= 7){
            setPosX(x1);
            setPosY(y1);
            return true;
        }

        // Retorna falso se o movimento não for válido.
        return false;
    }
}
