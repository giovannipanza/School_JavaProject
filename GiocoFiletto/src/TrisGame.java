import java.util.Scanner;

public class TrisGame {
    private Board board;
    private char currentPlayer;

    public TrisGame() {
        this.board = new Board();
        this.currentPlayer = 'X';
    }

    public void play() {
        boolean gameWon = false;
        char winner = ' ';

        do {
            board.printBoard();
            Move move = getPlayerMove();
            if (board.isValidMove(move)) {
                board.makeMove(move, currentPlayer);
                gameWon = board.checkWin(move, currentPlayer);
                if (gameWon) {
                    winner = currentPlayer;
                } else {
                    //currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Mossa non valida. Riprova.");
            }
        } while (!gameWon && !board.isBoardFull());

        board.printBoard();
        if (winner != ' ') {
            System.out.println("Giocatore " + winner + " ha vinto!");
        } else {
            System.out.println("È un pareggio!");
        }
    }

    private Move getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Giocatore " + currentPlayer + ", inserisci la riga (0, 1 o 2): ");
        int row = scanner.nextInt();
        System.out.print("Giocatore " + currentPlayer + ", inserisci la colonna (0, 1 o 2): ");
        int col = scanner.nextInt();
        return new Move(row, col);
    }
}