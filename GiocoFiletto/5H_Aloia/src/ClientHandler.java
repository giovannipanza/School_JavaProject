import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler implements Runnable {
    private Socket socket;
    private char playerId;

    private Board sharedBoard;
    ExecutorService executor = Executors.newFixedThreadPool(2);

    public ClientHandler(Socket socket, char playerId, Board sharedBoard) {
        this.socket = socket;
        this.playerId = playerId;
        this.sharedBoard = sharedBoard;
    }
    public void run() {
        TrisGame game = new TrisGame(socket, playerId, sharedBoard);
        executor.submit(game::play);
    }

}
