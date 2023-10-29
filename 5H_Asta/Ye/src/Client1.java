import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client1 {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;


    public Client1(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
    public  static  void     main(String[] args) throws IOException{
        // Creo il client
        Client1 client = new Client1("localhost", 12345);
        String inputLine;
//while((inputLine = client.receiveMessage() )!=null){
//    if(inputLine.contains("fine")){
//        System.out.println("ciao");
//        break;
//    }
    // Invio un messaggio al server
    client.sendMessage("Ciao server!");

    // Ricevo un messaggio dal server
    String message = client.receiveMessage();
    System.out.println(message);
//}


        // Chiudo il client
        client.close();


    }

}