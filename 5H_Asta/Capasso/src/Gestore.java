import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Gestore implements Runnable {

    private Socket socket;
    private PrintWriter out, out2;//
    private BufferedReader in, in2;//
    ArrayList<Socket> connessioni;
    ArrayList<Integer> offerte;
    
    
    public Gestore(Socket socket, ArrayList<Socket> connessioni, ArrayList<Integer> offerte) {

        this.socket = socket;
        this.connessioni=connessioni;
        this.offerte = offerte;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);//comunicazione con thread di connessione corrente
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message = in.readLine();//appena il client riceve il messaggio lo legge grazie a questa riga di codice
            out.println("<Gestore>Ti sei connesso: a breve partirà l'asta \n");
            System.out.println("<Gestore>Connessioni attive: "+connessioni.size());
            if (connessioni.size()==2) {//possono partecipare solo due persone, l'asta si avvia non appena le persone sono due
                for (int i = 0; i < connessioni.size(); i++) {
                    out2 = new PrintWriter(connessioni.get(i).getOutputStream(), true);//utilizzo out2 perchè out punta solo a una singola connessione mentre out2 punta sull'arraylist
                    out2.println("<Gestore>Asta avviata: fai la tua offerta");//comunicazione con tutte le comunicazioni, vedi dichiarazione nel for
                }
                for ( int i = 0; i < connessioni.size(); i++) {
                	in2 = new BufferedReader(new InputStreamReader(connessioni.get(i).getInputStream()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}