public class MainServer {

    public static void main(String[] args) {
        UDP_Server server = new UDP_Server(9876);
        server.startServer();
    }
}
