import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

class Gestore implements Runnable {
	
	
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
            out.println("Messaggio ricevuto: " + message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}