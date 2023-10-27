import java.io.*;
import java.net.*;


public class ServerBancomat {
    public static void main(String[] args) {
        final int PORT = 12345;
        ContoBancario conto = new ContoBancario();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server Bancomat in ascolto sulla porta " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuova connessione da " + clientSocket.getInetAddress());

                // Gestisci la connessione in un thread separato
                Thread clientThread = new Thread(new ClientBancomat(clientSocket, conto));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}