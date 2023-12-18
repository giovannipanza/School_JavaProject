import java.net.*;
import java.io.*;
import java.util.*;

public class GameServer {
    private DatagramSocket socket;
    private List<InetAddress> clientAddresses; //per tenere traccia degli IP di tutti i clients connessi
    private List<Integer> clientPorts;         //e delle rispettive porte logiche
    private ArrayList<String> usedWords;
    private String wordToGuess;
    private StringBuilder currentWordState;
    private int maxAttempts;

    public GameServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
        clientAddresses = new ArrayList<>();
        clientPorts = new ArrayList<>();
        usedWords = new ArrayList<>();
    }

    // Metodo per avviare il server
    public void startGame() {
        // Logica per avviare il gioco...non importa il Mutithread....basta un while true.....
    }

    // Metodo per processare i messaggi dei client
    private void processClientMessage(String message, InetAddress address, int port) {
        // Logica per processare le lettere inviate dai client
    }

    // Metodo per inviare messaggi ai client
    private void sendMessage(String message, InetAddress address, int port) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

    // Metodo principale per eseguire il server
    public static void main(String[] args) throws SocketException {
        int port = 12345; // Sostituire con la porta desiderata
        GameServer server = new GameServer(port);
        server.startGame();
    }
}
