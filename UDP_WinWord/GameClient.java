import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GameClient {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public GameClient(String address, int port) throws IOException {
        socket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
        serverPort = port;
    }

    // Metodo per inviare le ipotesi al server
    public void sendGuess(String guess) throws IOException {
        byte[] buffer = guess.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
        socket.send(packet);
    }

    // Metodo per ricevere risposte dal server
    public void receiveServerResponse() throws IOException {
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Server response: " + received);
    }

    // Metodo principale per eseguire il client
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo indirizzo IP:");
        String address = scanner.nextLine();
        int port = 12345; // Sostituire con la porta del server
        GameClient client = new GameClient(address, port);

        while (true) {
            System.out.println("Inserisci un carattere della parola che vuoi indovinare:");
            String guess = scanner.nextLine();

            // Assicurati che l'utente inserisca un solo carattere
            if (guess.length() == 1) {
                client.sendGuess(guess);
                client.receiveServerResponse();
            } else {
                System.out.println("Per favore, inserisci solo un carattere.");
            }
        }
    }
}
