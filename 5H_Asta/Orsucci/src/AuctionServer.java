import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuctionServer {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Auction auction = new Auction();
    private Timer timer = new Timer();
    private boolean auctionEnded = false;

    public AuctionServer() {
        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Auction server started...");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endAuction();
            }
        }, 30000); // Terminate the auction after 30 seconds

        while (!auctionEnded) {
            try {
                ClientHandler client = new ClientHandler(serverSocket.accept(), auction, this);
                clients.add(client);
                executor.execute(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void endAuction() {
        auctionEnded = true;
        broadcast("Asta terminata. Offerta finale: " + auction.getCurrentPrice());
    }
}
