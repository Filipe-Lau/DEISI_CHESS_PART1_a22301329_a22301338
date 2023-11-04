package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class GameResult {
    int jogadaPretaValida;
    int jogadaPretaInvalida;
    int jogadaBrancaValida;
    int jogadaBrancaInvalida;
    int jogadasSemComer;
    boolean houveCaptura;

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

    public void setHouveCaptura(boolean valorCaptura) {
        this.houveCaptura = valorCaptura;
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

    public boolean getHouveCaptura(){
        return houveCaptura;
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
}