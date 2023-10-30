import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class SocketAuction extends Thread {
    private Socket socket;
    private UUID userUUID = UUID.randomUUID();

    private PrintWriter out;
    private BufferedReader in;


    public SocketAuction(Socket socket) {
        this.socket = socket;
//        Creating stream
        try {
            this.out =  new PrintWriter(socket.getOutputStream(), true);
            this.in =   new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendBid() {
        out.println(AuctionProtocol.bid());
    }

    private void sendCloseBid() {
        out.println(AuctionProtocol.endAuction(userUUID));
    }


    public void run() {
        out.println(AuctionProtocol.startAuction(userUUID));
        sendBid();
        while (CurrentAuction.auctionAlive) {
            String clientCommand = null;
            try {
                clientCommand = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] parsedCommand = AuctionProtocol.readCommand(clientCommand);
            if (parsedCommand[0].equals("BID")) {
                CurrentAuction.getInstance().setLastBid(Double.parseDouble(parsedCommand[2]));
                CurrentAuction.getInstance().setLastBidUUID(UUID.fromString(parsedCommand[1]));
                CurrentAuction.getInstance().getSocketAuctions().forEach(
                        socketAuction -> {
                            System.out.println("Sending " + parsedCommand[2] + " bid to " + socketAuction.getUserUUID().toString());
                            if (!CurrentAuction.getInstance().getLastBidUUID().equals(userUUID))
                                out.println(AuctionProtocol.bid());
                        }
                );
            }
        }
    }

    public void interrupt() {
//        Send here close signal on protocol
        sendCloseBid();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.interrupt();
    }

    public UUID getUserUUID() {
        return userUUID;
    }
}
