/*
La classe Client crea una connessione con il server passandogli l'indirizzo IP e la porta del server. 
Dopo aver stabilito la connessione, il client può inviare e ricevere messaggi dal server.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }

    public static void main(String[] args) throws IOException {
    	Client client = new Client("localhost", 12345);
    	
    	// Invio un messaggio al server
    	client.sendMessage("Ciao server!");
    	System.out.println("Risposta del server: "+client.receiveMessage());
    }
    
}