/*
  In questo esempio, il client apre una connessione Datagram Socket verso un server specificato (in questo caso localhost sulla porta 4445). Ci sono due thread:
- sendThread: Legge il testo dalla tastiera e invia i dati al server.
- receiveThread: Ascolta continuamente i messaggi in arrivo dal server e li stampa.
*/
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;
    private int port;

    public Client(String address, int port) throws IOException {
        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName(address);
        this.port = port;
    }

    public void start() {
        Thread sendThread = new Thread(new Runnable() {
            public void run() {
                sendData();
            }
        });

        Thread receiveThread = new Thread(new Runnable() {
            public void run() {
                receiveData();
            }
        });

        sendThread.start();
        receiveThread.start();
    }

    private void sendData() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            while (true) {
                System.out.print("Enter message: ");
                input = scanner.nextLine();
                buf = input.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);

                if (input.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private void receiveData() {
        try {
            while (true) {
                buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Server: " + received);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 4445);
            client.start();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
