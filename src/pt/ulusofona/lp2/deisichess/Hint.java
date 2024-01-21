package pt.ulusofona.lp2.deisichess;

public class Hint implements Comparable <Hint> {
    String coords;
    int pontuacao;

    @Override
    public int compareTo(Hint pista) {
        //System.out.println("RESULTADO DA COMPARAÇÃO: " + Integer.compare(pista.getPontuacao(), this.pontuacao) + "\n" + " PISTA PONTOS: " + pista.getPontuacao() + " THIS.PONTOS: " + this.pontuacao);
        return Integer.compare(pista.getPontuacao(), this.pontuacao);
    }
    public Hint() {
    }
    public Hint(String coords){
        this.coords = coords;
    }

    public Hint(String coords, int pontuacao) {
        this.coords = coords;
        this.pontuacao = pontuacao;

    }

    public int getPontuacao(){
        return pontuacao;
    }

    @Override
    public String toString() {
        return "(" + coords + ")" + " -> " + pontuacao;
    }
}