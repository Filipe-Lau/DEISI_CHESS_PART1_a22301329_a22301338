package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca{
    int pontos = 4;
    String tipoPecaString = "Joker";
    int tipoPeca = 7;
    Peca pecaEmUso = new Rainha();

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
        return switch (tipoPeca) {
            case 1 -> pecaEmUso = new Rainha();
            case 2 -> pecaEmUso = new PoneiMagico();
            case 3 -> pecaEmUso = new PadreDaVila();
            case 4 -> pecaEmUso = new TorreH();
            case 5 -> pecaEmUso = new TorreV();
            case 0 -> pecaEmUso = new HomerSimpson();
            default -> pecaEmUso;
        };
    }

    public String toString() { // NÃO ESTÁ A MUDAR O getTipoPecaString(), ESTÁ SEMPRE NA RAINHA
        if (estado.equals("capturado")){
            return idPeca + " | Joker/" + pecaEmUso.getTipoPecaString() + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Joker/" + pecaEmUso.getTipoPecaString() + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public boolean movePeca(int x1,int y1){

        pecaEmUso.setPosX(getPosX());
        pecaEmUso.setPosY(getPosY());
        System.out.println(pecaEmUso.getTipoPeca());
        return pecaEmUso.movePeca(x1,y1);
        /*
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

         */
    }


}
