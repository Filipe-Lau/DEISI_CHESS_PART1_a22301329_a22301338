package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public class Joker extends Peca{

    protected int pontos = 4; // NÃO PODE SER FINAL NEM PRIVATE POIS É PRECISO ACEDER NA PARTE FUNCIONAL
    protected String tipoPecaString = "Joker/Rainha";
    protected int tipoPeca = 7; // FINAL PORQUE O TIPO PEÇA NUNCA MUDA ????
    protected Peca pecaEmUso = new Rainha();

    public Joker(){

    }

    public Joker(String idPeca, int equipaPeca, String nomePeca) {
        super(idPeca,equipaPeca, nomePeca);
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

    public Peca getPecaEmUso(int tipoPeca){

        if (!Objects.equals(estado, "capturado")) {
            switch (tipoPeca) {
                case 1 -> {
                    tipoPecaString = "Joker/Rainha";
                    return pecaEmUso = new Rainha();
                }
                case 2 -> {
                    tipoPecaString = "Joker/Ponei Mágico";
                    return pecaEmUso = new PoneiMagico();
                }
                case 3 -> {
                    tipoPecaString = "Joker/Padre da Vila";
                    return pecaEmUso = new PadreDaVila();
                }
                case 4 -> {
                    tipoPecaString = "Joker/TorreHor";
                    return pecaEmUso = new TorreH();
                }
                case 5 -> {
                    tipoPecaString = "Joker/TorreVert";
                    return pecaEmUso = new TorreV();
                }
                case 0 -> {
                    tipoPecaString = "Joker/Homer Simpson";
                    return pecaEmUso = new HomerSimpson();
                }
                default -> {
                    return pecaEmUso;
                }
            }
        }
        return pecaEmUso;
    }

    public String toString() { // NÃO ESTÁ A MUDAR O getTipoPecaString(), ESTÁ SEMPRE NA RAINHA
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPecaString + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return  idPeca + " | " + tipoPecaString + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public boolean movePeca(int x1,int y1){
        pecaEmUso.setPosX(getPosX());
        pecaEmUso.setPosY(getPosY());

        return pecaEmUso.movePeca(x1,y1);
    }


}
