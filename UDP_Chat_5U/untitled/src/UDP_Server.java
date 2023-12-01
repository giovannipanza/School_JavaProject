// UDPServer.java

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;

public class UDP_Server {
    private DatagramSocket serverSocket;
    private ArrayList<DatagramPacket> clients = new ArrayList<>();
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

                clients.add(receivePacket);
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Messaggio da " + address + ":" + port + ": " + message);

                broadcastMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message) {
        try {
            byte[] sendData = message.getBytes();

            // Invia il messaggio a tutti gli indirizzi IP sulla rete
            for (DatagramPacket clientAddress : clients) {
                InetAddress address = clientAddress.getAddress();
                int port = clientAddress.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                serverSocket.send(sendPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

