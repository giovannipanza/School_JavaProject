import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Gestore implements Runnable {

    private Socket socket;
    private int id;
    private int capienza;

    private ArrayList<Socket> connessioni;
    private ArrayList<Integer> offerte = new ArrayList<>();
    public Gestore(Socket socket) {
        this.socket = socket;
    }
    public Gestore(Socket socket,int id){
        this.socket = socket;
        this.id = id;
    }
    public Gestore(Socket socket,ArrayList<Socket> connessioni) {
        this.socket = socket;
        this.connessioni = connessioni;
    }
    public Gestore(Socket socket,ArrayList<Socket> connessioni,int capienza) {
        this.socket = socket;
        this.connessioni = connessioni;
        this.capienza = capienza;
    }
    @Override
    public  void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            PrintWriter out2 ;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ArrayList<BufferedReader> in2 = new ArrayList<>();
           String message = in.readLine();
           out.println("ti sei connesso, a breve partira' l'asta");
                if(connessioni.size() == 2) {
                    for(Socket conn : connessioni) {
                        out2 = new PrintWriter(conn.getOutputStream(),true);
                        out2.println("Asta avviata: fai la tua offerta");
                        in2.add(new BufferedReader(new InputStreamReader(conn.getInputStream())));
                    }
                    for (BufferedReader inConn : in2) {
                        String msg = inConn.readLine(); //offerte inviate dai client
                        offerte.add(Integer.parseInt(msg));
                        for (int i = 0; i < offerte.size(); i++) {
                            System.out.println("offerta di : " + offerte.get(i));
                        }


                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}