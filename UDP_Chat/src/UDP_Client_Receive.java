// UDPClient.java

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Client_Receive extends Thread {
    private DatagramSocket clientSocket;
    private DatagramPacket receivePacket;
    private Scanner scanner = new Scanner(System.in);

    public UDP_Client_Receive() {
        try {
            clientSocket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void receiveMessages() {
        try {
            byte[] receiveData = new byte[1024];
            System.out.println("Sono nel metodo receiveMessages()");
            while (true) {
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Messaggio dal server: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        receiveMessages();
    }
}
