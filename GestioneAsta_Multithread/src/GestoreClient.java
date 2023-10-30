import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class GestoreClient implements Runnable {

    private Socket socket;
    OggettoAsta o1;

    public GestoreClient(Socket socket, OggettoAsta o1){

        this.socket = socket;
        this.o1 = o1;
    }

    @Override
    public void run() {
        // Crea un BufferedReader per leggere i dati dal client
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea un PrintWriter per scrivere i dati al client
        PrintWriter writer = null;
        PrintWriter writer2 = null;

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ciclo infinito per gestire le offerte
        while (o1.getNumeroOfferta()<o1.getNumeroOfferteMax()) {
            writer.println("Oggetto all'asta:"+o1.getOggettoAllAsta());
            writer.println("Prezzo di partenza:"+o1.getPrezzoMassimo()+"€");

            // Legge l'offerta dal client
            String offerta = null;
            try {
                offerta = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            o1.setNumeroOfferta();
            double offertaDouble = Double.parseDouble(offerta);

            // Controlla che l'offerta sia valida
            if (offertaDouble <= o1.getPrezzoMassimo()) {
                writer.println("Offerta non valida. Inserire un valore maggiore di " + o1.getPrezzoMassimo() + "€");
            }
            else {
                writer.println("Offerta accettata: " + offerta);
                o1.setPrezzoMassimo(offertaDouble);

            }


            // Crea un PrintWriter per scrivere i dati ai vari  client connessi (per comunicare l'offerta più alta attuale)


            // Comunica l'offerta più alta attuale a tutti i client connessi
            /*
            for (int i=0; i<Server.connessioni.size(); i++) {
                Socket socket_tmp = Server.connessioni.get(i);
                try {
                    writer2= new PrintWriter(socket_tmp.getOutputStream(), true);
                    writer2.println("Offerta più alta attuale: " + prezzoMassimo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } */

        } //fine while

    }
}