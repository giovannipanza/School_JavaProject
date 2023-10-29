import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public  void sendMessage(String message) throws IOException {
        out.println(message);

    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client c1 = new Client("localhost",12345);

        Scanner s = new Scanner(System.in);


        c1.sendMessage("ciao server");
        String inizio = c1.receiveMessage();
        System.out.println(inizio);




        String inputLine;
        while((inputLine = c1.receiveMessage()) != null) {
            if(inputLine.equalsIgnoreCase("FINE")) {
                System.out.println("asta finita!");
                break;
            } else {
                System.out.println(inputLine);
                c1.sendMessage(s.nextLine());
            }
        }
        c1.close();

    }
}