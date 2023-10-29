import java.util.concurrent.locks.ReentrantLock;

public class Auction {
    private String item = "Oggetto all'asta: Bicicletta";
    private int startingPrice = 100;
    private int currentPrice = startingPrice;
    private ReentrantLock lock = new ReentrantLock();

    public String getItem() {
        return item;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public boolean makeBid(int amount) {
        lock.lock();
        try {
            if (amount > currentPrice) {
                currentPrice = amount;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
