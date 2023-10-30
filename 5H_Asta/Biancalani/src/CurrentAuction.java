import java.util.UUID;

public class CurrentAuction {
    public static boolean auctionAlive = true;
    private static CurrentAuction instance = null;


//    Here is default first bid
    private double lastBid = 10.0;
    private UUID lastBidUUID = new UUID(0,0);
    private ObservableList<SocketAuction> socketAuctions = null;
    private CurrentAuction() {

    }

    public static CurrentAuction getInstance() {
        // Singleton
        if (instance == null) {
            instance = new CurrentAuction();
        }
        return instance;
    }
    public double getLastBid() {
        return lastBid;
    }

    public void setLastBid(double lastBid) {
        this.lastBid = lastBid;
    }

    public UUID getLastBidUUID() {
        return lastBidUUID;
    }

    public void setLastBidUUID(UUID lastBidUUID) {
        this.lastBidUUID = lastBidUUID;
    }

    public ObservableList<SocketAuction> getSocketAuctions() {
        return socketAuctions;
    }

    public void setSocketAuctions(ObservableList<SocketAuction> socketAuctions) {
        this.socketAuctions = socketAuctions;
    }
}
