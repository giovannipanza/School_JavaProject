import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Asta {

    private Socket socket ;


    private InvioHandler invioHandler; // thread per l'invio dell'importo
    public Asta(Socket socket, InvioHandler invioHandler) {
        this.socket = socket;
        this.invioHandler = invioHandler;
    }

    public void start_asta() {

        try {
            BufferedReader in  =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            while(true) {
                out.println("per offrire inserisci 0: ");
                String choice = in.readLine();
                System.out.println("inizio offerta");
                out.println("inserisci l'importo: ");
                String importo = in.readLine();
                System.out.println("importo ricevuto: " + importo);

                if(Double.parseDouble(importo) > InvioHandler.getLastImporto()) { // set if there is a bigger offer
                    InvioHandler.setLastImporto(Double.parseDouble(importo));
                }

                // maybe put this inside previous if
                synchronized (invioHandler) {
                    invioHandler.notify();
                }

                //when finished
                // break and call close
                break; // delete later



            }
//            close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void close() {
        //chiudi asta
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
