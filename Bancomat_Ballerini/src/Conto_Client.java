import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Conto_Client {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Sistema_Banca sys = new Sistema_Banca();

            // CONNESSIONE AL SERVER SPECIFICO
            Socket socket = new Socket("localhost", 12345);

            // LETTURA E SCRITTURA DATI
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // RICEZIONE E INVIO MESSAGGI DA CLIENT A SERVER
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);

                if (serverMessage.equals("Inserire il PIN: ")) {
                    int PIN = sc.nextInt();
                    out.println(String.valueOf(PIN));
                } 
                else if(serverMessage.equals("Connesso")){
                    do{
                        sys.menu();
                        sys.setScelta(sc.nextInt());
                        switch(sys.getScelta()){
                            case 1: sys.Prelievo(); break;
                            case 2: sys.Versamento(); break;
                            case 3: sys.Saldo(); break;
                            default:
                                System.out.println("Arrivederci");
                                break;
                        }
                    }
                    while(sys.getScelta() != 4);
                }
                else {
                    String clientResponse = sc.nextLine();
                    out.println(clientResponse);
                }
            }

            sc.close();
            socket.close();
        } catch (Exception e) {
            System.err.print("Eccezione: " + e);
        }
    }
}
