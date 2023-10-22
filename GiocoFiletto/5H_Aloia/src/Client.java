import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",12345);
            Scanner client_in = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line = "", client_line;

            do {

                line = printBoard(in);

                if(line != null) {
                    client_line = client_in.nextLine(); // invia al server riga/colonna
                    out.println(client_line);
                }


            } while(line != null);


            socket.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static String printBoard(BufferedReader in) {
        String line;
        do {
            try {
                line = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(line != null)    System.out.println(line);
        } while (line != null && !(line.contains("inserisci")));
        return line;
    }


}
