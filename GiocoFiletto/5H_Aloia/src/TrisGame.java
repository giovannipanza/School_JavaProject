import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrisGame {
    private Board board;
    private char currentPlayer;
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public TrisGame(Socket socket, char currentPlayer, Board sharedBoard) {
        this.board = sharedBoard;
        this.currentPlayer = currentPlayer;
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void play() {
        try {
            boolean gameWon = false;

            synchronized (board) { // board oggetto condiviso

                do {
                        while (board.getCurrPlayer() != currentPlayer) {
                            board.wait(); // wait until it's your turn
                        }

                        if(board.isDraw() || board.isWon())   break;
                        board.printBoard(out);
                        Move move = getPlayerMove();
                        if (board.isValidMove(move)) {
                            board.makeMove(move, currentPlayer); //
                            gameWon = board.checkWin(move, currentPlayer); // ha vinto il giocatore corrente
                            if (gameWon && board.isWon()) { // gioco vinto da un giocatore
                                board.setDraw(false);
                            }
                            else if (board.isBoardFull())    board.setDraw(true); // board full -> pareggio
                        } else {
                            out.println("Mossa non valida. Riprova.");
                        }

                        char i = (currentPlayer == 'O') ? 'X' : 'O'; // prossimo giocatore
                        board.setCurrPlayer(i);
                        out.println("attendi il tuo turno...");
                        board.notify(); // notify l'altro giocatore (thread) che e' il suo turno

                } while (!gameWon && !board.isBoardFull());

                board.printBoard(out);
                if(board.isDraw())      out.println("È un pareggio!");
                else if(gameWon)   out.println("Hai vinto!");
                else out.println("Hai perso!");
                notifyAll(); // notify all other threads
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close(); // chiudi socket
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private synchronized Move getPlayerMove() throws IOException {

        out.println("Giocatore " + currentPlayer + ", inserisci la riga (0, 1 o 2): ");
        int row = Integer.parseInt(in.readLine());

        out.println("Giocatore " + currentPlayer + ", inserisci la colonna (0, 1 o 2): ");
        int col = Integer.parseInt(in.readLine());
        return new Move(row, col);

    }
}