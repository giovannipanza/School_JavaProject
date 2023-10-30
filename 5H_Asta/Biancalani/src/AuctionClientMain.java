import java.util.Scanner;

public class AuctionClientMain {

    public static void main(String[] args) {

        AuctionClient auctionClient = new AuctionClient("localhost", 11111);
        auctionClient.start();

        Scanner scanner = new Scanner(System.in);


        while (!auctionClient.isClosed()) {
            System.out.println("Insert a bid to offer something: ");
            double bid = scanner.nextDouble();
            auctionClient.sendBid(bid);
            System.out.println("Bid sent!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
