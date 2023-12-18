import java.net.*;
import java.io.*;
import java.util.*;

public class GameServer {
    private DatagramSocket socket;
    private List<InetAddress> clientAddresses; //per tenere traccia degli IP di tutti i clients connessi
    private List<Integer> clientPorts;         //e delle rispettive porte logiche
    private ArrayList<String> usedWords;
    private String wordToGuess;                //qui memorizzate la parola da indovinare
    private StringBuilder currentWordState;
    private int maxAttempts;                    //numero massimo di tentativi consentiti

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
        // Assicurati che il messaggio sia un singolo carattere
        if (message.length() == 1) {
            char guessedChar = message.charAt(0);
            // Logica per verificare la lettera e aggiornare lo stato della parola
            // ...

            // Invia l'aggiornamento a tutti i client
            String updateMessage = "Aggiornamento messaggio"; // Personalizza questo messaggio
            broadcastMessage(updateMessage);
        } else {
            sendMessage("Inserisci solo un carattere!", address, port);
        }
         // ... (resto del codice)
        
    }

    // Metodo per inviare messaggi al singolo client
    private void sendMessage(String message, InetAddress address, int port) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

     // Metodo per inviare un messaggio a tutti i client che hanno contattato il Server
    private void broadcastMessage(String message) throws IOException {
        for (int i = 0; i < clientAddresses.size(); i++) {
            sendMessage(message, clientAddresses.get(i), clientPorts.get(i));
        }
    }

    // Metodo principale per eseguire il server
    public static void main(String[] args) throws SocketException {
        int port = 12345; // Sostituire con la porta desiderata
        GameServer server = new GameServer(port);
        server.startGame();
    }
}
