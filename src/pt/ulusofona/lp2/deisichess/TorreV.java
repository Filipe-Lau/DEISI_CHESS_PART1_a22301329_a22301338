package pt.ulusofona.lp2.deisichess;

public class TorreV extends Peca {
    int pontos = 3;

    String tipoPecaString = "TorreVert";
    int tipoPeca = 5;
    int limiteCasas = 0; //0 quer dizer que nao tem limite
    boolean horizontal = false;
    boolean vertical = true;
    boolean diagonal = false;

    public TorreV() {

    }

    public TorreV(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")) {
            return idPeca + " | TorreVert | " + pontos +  " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | TorreVert | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public int getPontos() {
        return pontos;
    }

    public int getTipoPeca() {
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    @Override
    boolean movePeca(int x1, int y1) {
        //if (x1 == posX && y1 != posY && y1 >= -1 && y1 <= 7)/* de 0 a 7, 8 casas possiveis?*/
        return ((x1 == posX) && (y1 <= 7));

        // Retorna falso se o movimento não for válido.
    }
}
