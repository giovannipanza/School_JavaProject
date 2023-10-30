import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class AuctionClient extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private UUID userUUID;

    public AuctionClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws Exception {
        if (socket.isClosed()) {
            throw new Exception("Closed Socked! Bid Ended");
        }
        else {
            out.println(message);
        }
    }

    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            String message = receiveMessage();
            System.out.println("Received " + message);
            String[] parsedMessage = AuctionProtocol.readCommand(message);

            if (parsedMessage[0].equals("HELLO")) {
                userUUID = UUID.fromString(parsedMessage[1]);
                System.out.println("You are connected. Your UUID: " + parsedMessage[1]);
            } else if (parsedMessage[0].equals("BID")) {
                System.out.println("New bid of " + parsedMessage[2] + " made by " + parsedMessage[1]);
            } else if (parsedMessage[0].equals("ENDAUCTION")) {
                System.out.println("Auction Ended.");
                if (parsedMessage[1].equals("true")) {
                    System.out.println("You Won!");
                }
                else {
                    System.out.println("You Lost!");
                }
            }
//            System.out.flush();
        }
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public void sendBid(double bid) {
        try {
            sendMessage(AuctionProtocol.bid(userUUID, bid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}