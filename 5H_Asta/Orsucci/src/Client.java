import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);
                String clientMessage = consoleInput.readLine();
                out.println(clientMessage);
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Errore di I/O: " + e.getMessage());
        }
    }
}
