import java.lang.reflect.Array;
import java.net.Socket;
import  java.net.ServerSocket;
import java.io.*;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
ArrayList<Socket>  connessioni=new ArrayList<>();
ArrayList<Integer> offerta=new ArrayList<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {

            if (connessioni.size()<2){
                System.out.println("attendo una nuovo conesione");
            Socket socket = serverSocket.accept();
            connessioni.add(socket);
            Thread thread = new Thread(new Gestore(socket,connessioni,offerta));
            thread.start();
            System.out.println("Client Conesso");
            }
        }

    }
public  static  void     main(String[] args) throws IOException{
    // Avvio il server
    Server server = new Server(12345);
    server.start();

}


}