import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Gestore implements Runnable {

    private Socket socket;
    ArrayList<Socket> connessioni;
    public Gestore(Socket socket, ArrayList<Socket> connessioni) {

        this.socket = socket;
        this.connessioni=connessioni;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            PrintWriter out2;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = in.readLine();
            out.println("Ti sei connesso: a breve partirà l'asta ");
            System.out.println("Connessioni attive: "+connessioni.size());
            if (connessioni.size()==2) {
                for (int i = 0; i < connessioni.size(); i++) {
                    out2 = new PrintWriter(connessioni.get(i).getOutputStream(), true);
                    out2.println("Asta avviata: fai la tua offerta");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}