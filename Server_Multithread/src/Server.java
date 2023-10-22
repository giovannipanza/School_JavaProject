/*
La classe Server crea un socket server sulla porta specificata. 
Quando un client si connette al server, il server crea un thread (Gestore - Handler) per gestire la connessione. 
Il thread legge il messaggio inviato dal client e invia un messaggio di risposta.
*/

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new Gestore(socket));
            thread.start();
        }
    }

}