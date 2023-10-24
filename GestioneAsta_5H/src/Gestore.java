import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Gestore implements Runnable {

    private Socket socket;
    PrintWriter out, out2;
    BufferedReader in, in2;
    ArrayList<Socket> connessioni;
    ArrayList <Integer> offerte;
    public Gestore(Socket socket, ArrayList<Socket> connessioni, ArrayList <Integer> offerte ) {

        this.socket = socket;
        this.connessioni=connessioni;
        this.offerte=offerte;
    }

    @Override
    public void run() {
        try {
           out = new PrintWriter(socket.getOutputStream(),true);
           in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = in.readLine();
            System.out.println(message);

            out.println("Ti sei connesso: a breve partirà l'asta \n");

            System.out.println("Connessioni attive: "+connessioni.size());

            //gestisto una comunicazione in BROADCAST
            if (connessioni.size()==2) {
                for (int i = 0; i < connessioni.size(); i++) {
                    out2 = new PrintWriter(connessioni.get(i).getOutputStream(), true);
                    out2.println("Asta avviata: fai la tua offerta");
                }

                for (int i = 0; i < connessioni.size(); i++) {
                    in2 = new BufferedReader(new InputStreamReader(connessioni.get(i).getInputStream()));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}