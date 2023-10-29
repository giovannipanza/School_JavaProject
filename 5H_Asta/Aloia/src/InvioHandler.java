import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class InvioHandler extends Thread {
    public static double LastImporto;
    Vector<Socket> sockets;

    public static void setLastImporto(double lastImporto) {
        LastImporto = lastImporto;
    }

    public synchronized static double getLastImporto() {
        return LastImporto;
    }

    public void run() {

        System.out.println("starting invio handler");
        synchronized (this) {
            while(true) {

                try {
                    System.out.println("waiting");
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                // invio importo
                for (Socket s : sockets) {
                    PrintWriter out = null;
                    try {
                        out = new PrintWriter(s.getOutputStream(), true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println("get importo: " + getLastImporto());
                    out.println(getLastImporto());
                }
            }
        }
    }

    public InvioHandler(Vector<Socket> sockets) {
        this.sockets = sockets;
    }
}
