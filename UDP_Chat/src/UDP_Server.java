// UDPServer.java

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class UDP_Server {
    private DatagramSocket serverSocket;
    private ArrayList<InetAddress> clients = new ArrayList<>();
    private DatagramPacket receivePacket;
    private DatagramPacket sendPacket;
    private byte[] receiveData;
    public UDP_Server(int port) {
        try {
            serverSocket = new DatagramSocket(port);
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Server avviato...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while (true) {
                serverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress clientAddress = receivePacket.getAddress();
                clients.add(clientAddress);

                System.out.println("Messaggio da " + clientAddress + ":" + clientAddress.getHostAddress()+ ": " + message);

                broadcastMessage(message, clients);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message,ArrayList<InetAddress> clients) {
        try {
            byte[] sendData = message.getBytes();

            // Invia il messaggio a tutti gli indirizzi IP sulla rete
            for (InetAddress clientAddress : clients) {
                int port = new InetSocketAddress(clientAddress, 0).getPort();
                //ho il problema di come prelevare la porta del client
                sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, port);
                serverSocket.send(sendPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

