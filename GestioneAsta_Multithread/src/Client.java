import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        // Crea un Socket che si connette al server sulla porta 8080
        Socket socket = new Socket("localhost", 12345);

        // Crea un BufferedReader per leggere i dati dal server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Crea un PrintWriter per scrivere i dati al server
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);



        // Invia le varie offerte al server
        while (true)
        {
            // Leggi le informazioni sull'asta dal server
            String oggettoAllAsta = reader.readLine();
            String prezzoPartenza = reader.readLine();

            // Stampa le informazioni sull'asta
            System.out.println(oggettoAllAsta);
            System.out.println(prezzoPartenza);
            System.out.println("Inserisci la tua offerta: ");

            // Leggi l'offerta da tastiera
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            String offerta = tastiera.readLine();

            // Invia l'offerta al server
            writer.println(offerta);

            // Leggi la risposta del server
            String risposta = reader.readLine();

            // Stampa la risposta del server
            System.out.println(risposta);

            // Se arriva la stringa "fine" esci dal ciclo
            if (risposta.equals("fine")) {
                break;
            }
        }

        // Chiudi la connessione con il server
        socket.close();
    }
}

