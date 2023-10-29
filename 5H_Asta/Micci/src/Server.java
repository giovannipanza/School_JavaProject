import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    private ServerSocket serverSocket;
    public ArrayList<Socket> connessioni = new ArrayList<>();
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        int i = 0;
        System.out.println("inserisci massima capienza");
        Scanner s = new Scanner(System.in);
        int capienza = Integer.parseInt(s.nextLine());

        while (true) {
            if(connessioni.size() < capienza){
                System.out.println("in attesa di connessione");
                Socket socket = serverSocket.accept();
                i++;
                connessioni.add(socket);

                System.out.println("conn attive " + i);
                Thread thread = new Thread(new Gestore(socket,connessioni,capienza));
                thread.start();
                System.out.println("client connesso");
            }


        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(12345);
        server.start();


    }

}