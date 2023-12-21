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
         switch (tipoPeca) {
            case 1:
               return new Rainha(idPeca, equipaPeca, nomePeca);

                //pecaEmUso = new Rainha(idPeca, equipaPeca, nomePeca);
            //break;
            case 2:
              return new PoneiMagico(idPeca, equipaPeca, nomePeca);
                //pecaEmUso = new PoneiMagico(idPeca, equipaPeca, nomePeca);
            //break;
            case 3:
              return new PadreDaVila(idPeca, equipaPeca, nomePeca);
                //pecaEmUso = new PadreDaVila(idPeca, equipaPeca, nomePeca);
            //break;
            case 4:
              return pecaEmUso = new TorreH(idPeca, equipaPeca, nomePeca);
                 //pecaEmUso = new TorreH(idPeca, equipaPeca, nomePeca);
            //break;
            case 5:
                 pecaEmUso = new TorreV(idPeca, equipaPeca, nomePeca);
                 //pecaEmUso = new TorreV(idPeca, equipaPeca, nomePeca);
            break;
            case 6:
                pecaEmUso = new HomerSimpson(idPeca, equipaPeca, nomePeca);
               // pecaEmUso = new HomerSimpson(idPeca, equipaPeca, nomePeca);

        }
        return pecaEmUso;
    }

    public String toString() { // NÃO ESTÁ A MUDAR O getTipoPecaString(), ESTÁ SEMPRE NA RAINHA
        if (estado.equals("capturado")){
            return idPeca + " | " + tipoPeca + " | " + equipaPeca + " | " + nomePeca + " @ (n/a)";
        }
        return idPeca + " | Joker/" + pecaEmUso.getTipoPecaString() + " | " + pontos + " | " + equipaPeca + " | " + nomePeca + " @ (" + posX + ", " + posY + ")";
    }

    public boolean movePeca(int x1,int y1){

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
