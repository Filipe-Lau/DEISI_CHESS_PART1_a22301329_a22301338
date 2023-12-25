package pt.ulusofona.lp2.deisichess;

public class Hint implements Comparable <Hint> {
    String coords;
    int pontuacao;


    public Hint() {
    }

    public Hint(String coords, int pontuacao) {
        this.coords = coords;
        this.pontuacao = pontuacao;

    }

    @Override
    public int compareTo(Hint pista) {
        return Integer.compare(pista.getPontuacao(), this.pontuacao);
    }

    public int getPontuacao(){
        return pontuacao;
    }

    @Override
    public String toString() {
        return "(" + coords + ")" + " -> " + pontuacao;
    }
}