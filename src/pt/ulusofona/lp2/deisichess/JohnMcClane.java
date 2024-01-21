package pt.ulusofona.lp2.deisichess;

public class JohnMcClane extends Peca{

    protected final int pontos = 100; //mandar mail ao stor
    protected final String tipoPecaString = "John McClane";
    int tentativasComer = 0;
    protected final int tipoPeca = 1;


    public JohnMcClane() {

    }

    protected JohnMcClane(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
    }


    protected int getTentativasComer(){
        return tentativasComer;
    }

    protected int getPontos(){
        return pontos;
    }

    protected int getTipoPeca(){
        return tipoPeca;
    }

    @Override
    String getTipoPecaString() {
        return tipoPecaString;
    }

    @Override
    protected boolean movePeca(int x1, int y1) {
        return false;
    }

    @Override
    public String toString() {
        return idPeca + " | John McClane | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }
}
