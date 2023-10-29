import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Auction auction;
    private AuctionServer server;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, Auction auction, AuctionServer server) {
        this.clientSocket = socket;
        this.auction = auction;
        this.server = server;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Benvenuto all'asta. Oggetto: " + auction.getItem() + " - Prezzo di partenza: " + auction.getCurrentPrice());

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                int bid = Integer.parseInt(clientMessage);
                if (auction.makeBid(bid)) {
                    server.broadcast("Nuova offerta: " + bid);
                } else {
                    out.println("Offerta troppo bassa. Offerta attuale: " + auction.getCurrentPrice());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            server.removeClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
