import java.io.*;
import java.net.*;

public class ClientBancomat implements Runnable {
    private Socket clientSocket;
    private ContoBancario conto;

    public ClientBancomat(Socket socket, ContoBancario conto) {
        this.clientSocket = socket;
        this.conto = conto;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Invia un messaggio di benvenuto
            out.println("Benvenuto al Bancomat. Inserisci il tuo PIN:");

            // Leggi il PIN dal client


            // Effettua l'autenticazione
            if (pin.equals("1234")) {
                out.println("Autenticazione riuscita. Saldo iniziale: " + conto.getSaldo() + " Euro");
            } else {
                out.println("Autenticazione fallita. Connessione chiusa.");
                clientSocket.close();
                return;
            }

            while (true) {
                out.println("Seleziona un'operazione (prelevamento/deposito/bilancio/uscita):");
                String scelta = in.readLine();

                if (scelta.equalsIgnoreCase("prelevamento")) {
                    out.println("Inserisci l'importo da prelevare:");
                    double importo = Double.parseDouble(in.readLine());
                    if (conto.preleva(importo)) {
                        out.println("Prelevamento effettuato. Saldo attuale: " + conto.getSaldo() + " Euro");
                    } else {
                        out.println("Fondi insufficienti.");
                    }
                } else if (scelta.equalsIgnoreCase("deposito")) {
                    out.println("Inserisci l'importo da depositare:");
                    double importo = Double.parseDouble(in.readLine());
                    conto.deposita(importo);
                    out.println("Deposito effettuato. Saldo attuale: " + conto.getSaldo() + " Euro");
                } else if (scelta.equalsIgnoreCase("bilancio")) {
                    out.println("Saldo attuale: " + conto.getSaldo() + " Euro");
                } else if (scelta.equalsIgnoreCase("uscita")) {
                    out.println("Grazie per aver usato il Bancomat. Arrivederci!");
                    clientSocket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
