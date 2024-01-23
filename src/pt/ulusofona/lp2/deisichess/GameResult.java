package pt.ulusofona.lp2.deisichess;

public class GameResult {
    int jogadaPretaValida;
    int jogadaPretaInvalida;
    int jogadaBrancaValida;
    int jogadaBrancaInvalida;
    int jogadaAmarelaValida;
    int jogadaAmarelaInvalida;
    int jogadasSemComer;
    int numCaptura;

    public GameResult() {
    }

    public void setJogadaPretaValida(int numJogadaPretaValida) {
        this.jogadaPretaValida = numJogadaPretaValida;
    }

    public void setJogadaBrancaValida(int numJogadaBrancaValida){
        this.jogadaBrancaValida = numJogadaBrancaValida;
    }

    public void setJogadaPretaInvalida(int numJogadaPretaInvalida){
        this.jogadaPretaInvalida = numJogadaPretaInvalida;
    }

    public void setJogadaBrancaInvalida(int numJogadaBrancaInvalida){
        this.jogadaBrancaInvalida = numJogadaBrancaInvalida;
    }

    public void setJogadasSemComer(int numJogadasSemComer){
        this.jogadasSemComer = numJogadasSemComer;
    }

    public void setnumCaptura(int numCaptura) {
        this.numCaptura = numCaptura;
    }

    public void mudaNumCaptura(int num){
        this.numCaptura += num;
    }

    public void aumentaJogadaPretaValida() {
        jogadaPretaValida++;
    }
    public void aumentaJogadaBrancaValida(){
        jogadaBrancaValida++;
    }

    public void aumentaJogadaPretaInvalida(){
        jogadaPretaInvalida++;
    }

    public void aumentaJogadaBrancaInvalida(){
        jogadaBrancaInvalida++;
    }

    public void aumentaJogadasSemComer(){
        jogadasSemComer++;
    }

    public int getNumCaptura(){
        return numCaptura;
    }
    public int getJogadaPretaValida(){
        return jogadaPretaValida;
    }
    public int getJogadaPretaInvalida(){
        return jogadaPretaInvalida;
    }
    public int getJogadaBrancaValida(){
        return jogadaBrancaValida;
    }
    public int getJogadaBrancaInvalida(){
        return jogadaBrancaInvalida;
    }
    public int getJogadasSemComer(){
        return jogadasSemComer;
    }
    public void setJogadaAmarelaValida(int jogadaAmarelaValida){
        this.jogadaAmarelaValida = jogadaAmarelaValida;
    }
    public void setJogadaAmarelaInvalida(int jogadaAmarelaInvalida){this.jogadaAmarelaInvalida = jogadaAmarelaInvalida;}
    public int getJogadaAmarelaValida(){
        return jogadaAmarelaValida;
    }
    public int getJogadaAmarelaInvalida(){return jogadaAmarelaInvalida;}
    public void aumentaJogadaAmarelaInvalida(){jogadaAmarelaInvalida++;}
    public void aumentaJogadaAmarelaValida(){jogadaAmarelaValida++;}

}