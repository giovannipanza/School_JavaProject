import java.net.Socket;
import  java.io.*;


public class Client {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private  BufferedReader tastiera;


    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        tastiera = new BufferedReader(new InputStreamReader(System.in));
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

        Client client = new Client("localhost", 12345);

        String inputLine;
//while((inputLine = client.receiveMessage() )!=null){
//    if(inputLine.contains("fine")){
//        System.out.println("ciao");
//       break;
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