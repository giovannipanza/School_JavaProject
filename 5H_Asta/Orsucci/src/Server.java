import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        AuctionServer auctionServer = new AuctionServer();
        auctionServer.start();
    }
}
