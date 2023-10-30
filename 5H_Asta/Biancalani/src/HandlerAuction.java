import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HandlerAuction extends Thread{
    ObservableList<SocketAuction> sockets;
    public HandlerAuction(ObservableList<SocketAuction> sockets) {
        CurrentAuction.getInstance().setSocketAuctions(sockets);
        this.sockets = sockets;
        this.sockets.setListener(new ListListener<SocketAuction>() {
            @Override
            public void onAdd(List<SocketAuction> list, SocketAuction socketAuction) {
                socketAuction.start();
            }

            @Override
            public void onDelete(List<SocketAuction> list, SocketAuction socketAuction) {
                socketAuction.interrupt();
            }
        });
    }

    public void run() {
        HandlerAuction cm = this;
        System.out.println("Handler Auction started!");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Auction End!");
                CurrentAuction.auctionAlive = false;
                cm.interrupt();
//                100 = Bid Ended
                System.exit(100);
            }
        }, 2*60*1000);
//        Delay of 2 minutes

    }

    public void interrupt() {
        for (SocketAuction socket : sockets) {
            socket.interrupt();
        }
//        super.interrupt();
    }
}
