/*
    Programma semplificato di gestione di un asta online NO MULTITHREAD (un solo client alla volta)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // Crea un ServerSocket che accetta connessioni sulla porta 8080
        ServerSocket serverSocket = new ServerSocket(8080);

        // Accetta una connessione da un client
        Socket socket = serverSocket.accept();

        // Crea un BufferedReader per leggere i dati dal client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Crea un PrintWriter per scrivere i dati al client
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        // Invia le informazioni sull'asta al client
        writer.println("Oggetto all'asta: Casa");
        writer.println("Prezzo di partenza: 100.000€");

        // Legge l'offerta dal client
        String offerta = reader.readLine();

        // Controlla che l'offerta sia valida
        double offertaDouble = Double.parseDouble(offerta);
        if (offertaDouble < 100.000) {
            writer.println("Offerta non valida.");
            return;
        }

        // Comunica l'offerta al client
        writer.println("Offerta accettata: " + offerta);

        // Termina l'asta
        serverSocket.close();
    }
}
