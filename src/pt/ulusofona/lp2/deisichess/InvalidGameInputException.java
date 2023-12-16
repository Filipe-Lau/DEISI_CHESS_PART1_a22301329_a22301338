package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Throwable {

    int numLine;

    String problemDescription;

    public InvalidGameInputException(int numLine, String problemDescription) {
        this.numLine = numLine;
        this.problemDescription = problemDescription;
    }

    public int getLineWithError() {
        return numLine;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

}
