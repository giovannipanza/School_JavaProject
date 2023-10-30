import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

        private ServerSocket serverSocket;
        public static ArrayList<Socket> connessioni;



        public static void main(String[] args) throws IOException {
            // Crea un ServerSocket che accetta connessioni sulla porta 8080
            ServerSocket serverSocket = new ServerSocket(12345);

            OggettoAsta o1 = new OggettoAsta("Macchina", 1000);

            // Crea una lista di thread
            connessioni = new ArrayList<Socket>();

            // Accetta connessioni da client
            while (true) {
                // Accetta una connessione da un client
                System.out.println("In attesa di connessioni...");
                Socket socket = serverSocket.accept();
                System.out.println("Si è connesso un client!");

                // Crea un thread per gestire il client e l'oggetto dell'asta
                Thread thread = new Thread(new GestoreClient(socket, o1));
                thread.start();
                connessioni.add(socket );
                System.out.println("Connessione attive!"+connessioni.size());
                System.out.println("------------------------------------");

            }
        }
}
