/*
La classe Server crea un socket server sulla porta specificata. 
Quando un client si connette al server, il server crea un thread (Gestore - Handler) per gestire la connessione. 
Il thread legge il messaggio inviato dal client e invia un messaggio di risposta.
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
        	if (connessioni.size()<2) { 
	            Socket socket = serverSocket.accept();
	            connessioni.add(Socket);
	            Thread thread = new Thread(new Gestore(socket,connessioni));
	            thread.start();
        	}
        }
    }
    
    
    public static void main(String[] args) throws IOException {
    	// Avvio il server
        Server server = new Server(12345);
        server.start();
    }
}