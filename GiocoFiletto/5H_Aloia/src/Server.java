import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {


        try {
            int port = 12345;
            char playerId = 'X';
            while(true) {
                ExecutorService executor = Executors.newFixedThreadPool(10);
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Server listening on port: " + port);
                try {
                    Board sharedBoard = new Board(); // creo una board condivisa per i 2 threads (giocatori)


                    // player 1
                    Socket playerX = serverSocket.accept();
                    ClientHandler clientHandlerX = new ClientHandler(playerX, playerId, sharedBoard);
                    executor.submit(clientHandlerX::run);

                    // player 2
                    playerId = 'O';
                    Socket playerO = serverSocket.accept();
                    ClientHandler clientHandlerO = new ClientHandler(playerO, playerId, sharedBoard);
                    executor.submit(clientHandlerO::run);

                } catch(Exception e) {
                    e.printStackTrace();
                }
                port++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
