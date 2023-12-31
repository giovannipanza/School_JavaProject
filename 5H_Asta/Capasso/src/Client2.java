/*
La classe Client crea una connessione con il server passandogli l'indirizzo IP e la porta del server. 
Dopo aver stabilito la connessione, il client può inviare e ricevere messaggi dal server.
*/

import java.io.*;
import java.net.Socket;

public class Client2 {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in, tastiera;

    public Client2(String host, int port) throws IOException {
        socket = new Socket(host, port);//l'host è l'ip del server
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        tastiera=in = new BufferedReader(new InputStreamReader(System.in));
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
        Client2 client = new Client2("localhost", 12345);

        // Invio un messaggio al server
        client.sendMessage("Ciao server!");
        System.out.println("Risposta del Server: " + client.receiveMessage());

        String inputLine;
        while ((inputLine = client.receiveMessage()) != null) {
            if (inputLine.contains("FINE")) {
                System.out.println("Asta conclusa!");
                break;
            }
            System.out.println(inputLine);
        }
    }
}