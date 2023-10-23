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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = in.readLine();
            out.println("Ti sei connesso! A breve partirà l'asta. ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}