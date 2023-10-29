/*
La classe Server crea un socket server sulla porta specificata. 
Quando un client si connette al server, il server crea un thread (Gestore - Handler) per gestire la connessione. 
Il thread legge il messaggio inviato dal client e invia un messaggio di risposta.
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    ArrayList<Socket> connessioni = new ArrayList<Socket>(); // Create an ArrayList object
    ArrayList<Integer> offerte = new ArrayList<Integer>();
    
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
        	System.out.println("Attendo una nuova connessione");
                Socket socket = serverSocket.accept();
                connessioni.add(socket);
                Thread thread = new Thread(new Gestore(socket, connessioni, offerte));
                thread.start();
                System.out.println("Client connesso!");

        }
    }

    public static void main(String[] args) throws IOException {
        // Avvio il server
        Server server = new Server(12345);
        server.start();


    }

}