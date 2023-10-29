import javax.accessibility.AccessibleState;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler implements Runnable{
    Vector<Socket> sockets;
    Vector<Asta> aste = new Vector<>();
    ExecutorService executor = Executors.newFixedThreadPool(10);
    InvioHandler inviohandler;

    public Handler(Vector<Socket> sockets) {
        this.sockets = sockets;
        this.inviohandler = new InvioHandler(sockets);
    }

    @Override
    public void run() {
        System.out.println("starting handler");

        // timer

        // start handler thread
        inviohandler.start();


        synchronized (sockets) {
            try {
                System.out.println("waiting");
                sockets.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("notified");

            List<Socket> socketsCopy = new ArrayList<>(sockets);
            for (Socket s : socketsCopy) {
                System.out.println(sockets.size());
                Asta a = new Asta(s, inviohandler);
//                aste.add(a);
                System.out.println("starting asta");
                executor.execute(a::start_asta);

                try {
                    System.out.println("waiting");
                    sockets.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("notified");
            }
//            inviohandler.start();

        }
    }

    public synchronized void sendList() {

    }
}
