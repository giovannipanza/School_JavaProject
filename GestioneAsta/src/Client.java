import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {

    public static void main(String[] args) throws IOException {
        // Crea un Socket che si connette al server sulla porta 8080
        Socket socket = new Socket("localhost", 8080);

        // Crea un BufferedReader per leggere i dati dal server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Crea un PrintWriter per scrivere i dati al server
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        // Leggi le informazioni sull'asta dal server
        String oggettoAllAsta = reader.readLine();
        String prezzoPartenza = reader.readLine();

        // Invia un'offerta al server
        writer.println("150.000");

        // Leggi la risposta del server
        String risposta = reader.readLine();

        // Stampa la risposta del server
        System.out.println(risposta);

        // Chiudi la connessione con il server
        socket.close();
    }
}

