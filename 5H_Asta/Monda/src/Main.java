import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // Avvio il server
        Server server = new Server(8080);
        server.start();

        // Creo il client
        Client client = new Client("localhost", 8080);

        // Invio un messaggio al server
        client.sendMessage("Ciao server!");

        // Ricevo un messaggio dal server
        String message = client.receiveMessage();
        System.out.println(message);

        // Chiudo il client
        client.close();
    }

}