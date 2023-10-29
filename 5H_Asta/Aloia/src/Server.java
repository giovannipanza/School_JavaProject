import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
/*
Descrizione dell'esercizio: Asta Giudiziaria con Socket in Java
Obiettivo: Creare un'applicazione client-server in Java che simuli un'asta giudiziaria, in cui il server comunica l'oggetto all'asta e il prezzo di partenza ai client connessi. I client devono fare offerte, e ogni offerta deve essere superiore all'offerta precedente per essere accettata.
Requisiti:
Creare un server che accetta connessioni da client tramite socket.
Il server deve iniziare un'asta con un oggetto e un prezzo di partenza.
I client devono poter connettersi al server e ricevere le informazioni sull'oggetto all'asta e il prezzo di partenza.
I client devono fare offerte all'asta inviando il loro importo al server.
Il server deve accettare offerte solo se sono superiori all'offerta corrente.
Il server deve comunicare l'offerta più alta attuale a tutti i client connessi.
L'asta deve terminare quando si verifica una delle seguenti condizioni:
a. Il server decide di terminare l'asta manualmente (es. dopo 5 offerte).
b. Un timer scade, ad esempio, dopo 3 minuti.
c. Nessun nuovo cliente si connette per un periodo di tempo prestabilito.
 */

// lista di socket che vengono passati al thread gestore dell'asta (manda a tutti l'importo totale)



public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server in ascolto sulla porta");
            Vector<Socket> sockets = new Vector<>();

            Handler handler = new Handler(sockets);

            Thread t = new Thread(handler);
            t.start();

            while(true) {
                Socket clientSocket = serverSocket.accept();
                synchronized (sockets) {
                    sockets.add(clientSocket);
                    sockets.notifyAll();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
