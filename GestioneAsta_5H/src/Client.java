/* 
La classe Client crea una connessione con il server passandogli l'indirizzo IP e la porta del server. 
Dopo aver stabilito la connessione, il client può inviare e ricevere messaggi dal server.
*/

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static BufferedReader tastiera;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        tastiera = new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println("Risposta del Server: ");
        String app=client.receiveMessage();
        System.out.println(app);


        String inputLine;
        while ((inputLine = client.receiveMessage()) != null) {
            if (inputLine.contains("FINE")) {
                System.out.println("Asta conclusa!");
                break;
            }
            System.out.println(inputLine);
            client.sendMessage(tastiera.readLine());


        }
    }
}