import java.net.SocketException;

public class MainServer {
    public static void main(String[] args) throws SocketException {
        UDP_Server server = new UDP_Server();

        server.start();

    }
}
