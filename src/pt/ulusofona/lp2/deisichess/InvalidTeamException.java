package pt.ulusofona.lp2.deisichess;

public class InvalidTeamException extends Throwable {

    String nomePeca;
    String texto;


    public InvalidTeamException(String texto,String nomePeca) {
        this.texto = texto;
        this.nomePeca = nomePeca;

    }

    public String getInvalidPieceName(){
        return nomePeca;
    }

    public String getTexto(){
        return texto;
    }
}
