package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameManager {

    @Test
    public boolean compilar(){
        return true;
    }
    @Test
    public void testMoveValidar_ValidMove_ReturnsTrue() {
        // Arrange
        GameManager gameManager = new GameManager();
        // Set up the initial state or dependencies, if needed.

        // Act
        boolean isValidMove = gameManager.moveValidar(0, 0, 1, 1);

        // Assert
        assertTrue(isValidMove);
    }
    /*
    @Test
    public void testMoveValidar_ValidMove_ReturnsTrue() {
        // Arrange
        GameManager gameManager = new GameManager();
        // Set up the initial state or dependencies, if needed.

        // Act
        boolean isValidMove = gameManager.moveValidar(0, 0, 1, 1);

        // Assert
        assertTrue(isValidMove);
    }

    @Test
    public void testMove_ValidMove_ReturnsTrue() {
        // Arrange
        GameManager gameManager = new GameManager();
        // Set up the initial state or dependencies, if needed.
        gameManager.loadGame("C:\Users\filip\IdeaProjects\DEISI CHESS PART1\ficheiros\8x8.txt");
        // Act
        boolean result = gameManager.move(0, 0, 1, 1);

        // Assert
        assertTrue(result);
        // Add more assertions to check the state after the move.
    }

    // Add more test methods for other functionalities...



        @Test
        public void testaGameOver() {
            GameManager gameManager = new GameManager();
            gameManager.getBoardSize();

            gameManager.gameBoard.pecasEmJogo = new HashMap<>();


            Peca pecaBranca1 = new Peca("1", 0, 1, "Peça Branca 1");
            Peca pecaBranca2 = new Peca("2", 0, 1, "Peça Branca 2");



            gameManager.gameBoard.pecasEmJogo.put("1", pecaBranca1);
            gameManager.gameBoard.pecasEmJogo.put("2", pecaBranca2);
            gameManager.gameBoard.numPecasBrancas = 2;


            boolean vitoriaBrancas = gameManager.gameOver();

            if (vitoriaBrancas) {
                System.out.println("Vitória das peças brancas: TESTE PASSOU");
            } else {
                System.out.println("Vitória das peças brancas: TESTE FALHOU");
            }
        }

        @Test
        public void testaPieceInfo(){
            GameManager gameManager = new GameManager();
            gameManager.getBoardSize();

            gameManager.gameBoard.pecasEmJogo = new HashMap<>();
            //gameManager.gameBoard.pecasCapturadas = new HashMap<>();

            Peca pecaBranca1 = new Peca("1", 0, 1, "Peça Branca 1");
            Peca pecaBranca2 = new Peca("2", 0, 1, "Peça Branca 2");

            gameManager.gameBoard.pecasEmJogo.put("1", pecaBranca1);
            gameManager.gameBoard.pecasEmJogo.put("2", pecaBranca2);

            String[] infoPeca = gameManager.getPieceInfo(1);
            if(infoPeca[0].equals(pecaBranca1.getIdPeca()) && infoPeca[1].equals(String.valueOf(pecaBranca1.getIdPeca())) &&
                    infoPeca[2].equals(String.valueOf(pecaBranca1.getEquipaPeca())) &&
                    infoPeca[3].equals(pecaBranca1.getNomePeca()) &&
                    infoPeca[4].equals(String.valueOf(pecaBranca1.getEstado())) &&
                    infoPeca[5].equals(String.valueOf(pecaBranca1.getPosX())) &&
                    infoPeca[6].equals(String.valueOf(pecaBranca1.getPosY()))) {
                System.out.println("TESTE PASSOU");
            } else {
                System.out.println("TESTE FALHOU");
            }
        }

        @Test
        public void testaMoverPeca() {
            GameManager gameManager = new GameManager();
            gameManager.boardSize = 4;

            gameManager.gameBoard.pecasEmJogo = new HashMap<>();

            Peca pecaPreta = new Peca("1", 0, 0, "Peça Preta");
            Peca pecaBranca = new Peca("2", 0, 1, "Peça Branca");

            gameManager.gameBoard.pecasEmJogo.put("1", pecaPreta);
            gameManager.gameBoard.pecasEmJogo.put("2", pecaBranca);

            gameManager.vezDeJogar = 0;

            boolean moveuPeca = gameManager.move(3, 3, 3, 2);

            if (moveuPeca) {
                System.out.println("TESTE PASSOU");
            } else {
                System.out.println("TESTE FALHOU");
            }

        }

        @Test
        public void testaMoverPecaFalha() {
            GameManager gameManager = new GameManager();
            gameManager.boardSize = 4;

            gameManager.gameBoard.pecasEmJogo = new HashMap<>();

            Peca pecaPreta = new Peca("1", 0, 0, "Peça Preta");
            Peca pecaPreta2 = new Peca("2", 0, 0, "Peça Preta2");
            Peca pecaBranca = new Peca("3", 0, 1, "Peça Branca");

            gameManager.gameBoard.pecasEmJogo.put("1", pecaPreta);
            gameManager.gameBoard.pecasEmJogo.put("3", pecaBranca);
            gameManager.gameBoard.pecasEmJogo.put("2",pecaPreta2);

            gameManager.vezDeJogar = 0;

            boolean moveuPeca = gameManager.move(3, 3, 3, 2);

            if (!moveuPeca) {
                System.out.println("TESTE PASSOU");
            } else {
                System.out.println("TESTE FALHOU");
            }
        }

        @Test
        public void testaGameBoardSize() {
            GameManager gameManager = new GameManager();
            gameManager.boardSize = 4;

            int tamanhoTabuleiro = gameManager.getBoardSize();

            if(tamanhoTabuleiro == gameManager.boardSize) {
                System.out.println("TESTE PASSOU");
            } else {
                System.out.println("TESTE FALHOU");
            }
        }


    @Test
    public void testMoveRei() throws IOException, InvalidGameInputException {

        GameManager gameManager = new GameManager();
        File file = new File("test-files/testesMove/moveRei");
        gameManager.loadGame(file);

        //preta rei
        assertTrue(gameManager.move(0, 0, 0, 1));
        String[] quas = gameManager.getSquareInfo(0, 1);
        Assertions.assertEquals(5, quas.length);
        //branca rei
        assertTrue(gameManager.move(0, 7, 0, 6));

        assertTrue(gameManager.move(0, 1, 0, 2));
        assertTrue(gameManager.move(0, 6, 0, 5));

        quas = gameManager.getSquareInfo(0, 1);
        Assertions.assertEquals(0, quas.length);
        quas = gameManager.getSquareInfo(0, 2);
        Assertions.assertEquals(5, quas.length);
        quas = gameManager.getSquareInfo(0, 6);
        Assertions.assertEquals(0, quas.length);

        Assertions.assertFalse(gameManager.move(0, 2, 2, 2));
        Assertions.assertFalse(gameManager.move(0, 5, 5, 5));

        Assertions.assertFalse(gameManager.move(0, 2, 1, 2));
        Assertions.assertFalse(gameManager.move(0, 5, 0, 5));


    }
    */
}
