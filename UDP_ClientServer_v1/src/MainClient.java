import java.io.IOException;

public class MainClient {
public static void main(String[] args) throws IOException {
        UDP_Client client = new UDP_Client();
        client.sendMessage("end");

        System.out.println(client.receiveMessage());

    }

}
