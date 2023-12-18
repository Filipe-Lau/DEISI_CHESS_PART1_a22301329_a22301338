package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca {

    int pontos = 3;
    String tipoPecaString = "Padre da Vila";
    int tipoPeca = 3;
    int limiteCasas = 3;
    boolean diagonal = true;
    boolean horizontal = false;
    boolean vertical = false;

    public PadreDaVila(){

    }

    public PadreDaVila(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca, equipaPeca, nomePeca);
    }

    public String toString() {
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Padre da Vila | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";    }

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
        return false;
    }
}
