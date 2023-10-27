import java.io.*;
import java.net.*;

public class Bank_Server {
    public static void main(String[] args) {
        try {
            // CREAZIONE DEL SERVER SOCKET IN ASCOLTO SULLA PORTA 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server in ascolto sulla porta 12345...");

            // ACCETTA UNA CONNESSIONE DAL CLIENT
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());

            // STREAM PER LA LETTURA E SCRITTURA DEI DATI
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // INVIA MESSAGGIO DI BENVENUTO AL CLIENT
            out.println("Inserire PIN: ");

            // LEGGE RISPOSTA DAL CLIENT E INTERAGISCE
            String clientResponse;
            while ((clientResponse = in.readLine()) != null) {
                if (clientResponse.equals("123")) {
                    out.println("Connesso");
                } else {
                    out.println("Comando non riconosciuto. Riprova.");
                }
            }

            // CHIUSURA DELLA CONNESSIONE
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Errore nel server: " + e);
        }
    }
}

