import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServer {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started and listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private TicTacToeGame game;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                game = new TicTacToeGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                out.println("Welcome to Tic-Tac-Toe!");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] coordinates = inputLine.split(",");
                    int row = Integer.parseInt(coordinates[0]);
                    int col = Integer.parseInt(coordinates[1]);

                    if (game.isGameOver()) {
                        out.println("Game over!");
                        break;
                    }

                    if (game.isValidMove(row, col)) {
                        game.makeMove(row, col);
                        out.println(game.getBoardAsString());

                        if (game.checkForWin()) {
                            out.println("You win!");
                            break;
                        }

                        if (game.isBoardFull()) {
                            out.println("It's a draw!");
                            break;
                        }

                        out.println("Your turn");
                    } else {
                        out.println("Invalid move. Try again.");
                    }
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}