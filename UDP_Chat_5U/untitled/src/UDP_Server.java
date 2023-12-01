// UDPServer.java

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Server {
    private DatagramSocket serverSocket;

    public UDP_Server(int port) {
        try {
            serverSocket = new DatagramSocket(port);
            System.out.println("Server avviato...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Messaggio da " + clientAddress + ":" + clientPort + ": " + message);

                broadcastMessage(message, clientAddress, clientPort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message, InetAddress clientAddress, int clientPort) {
        try {
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

