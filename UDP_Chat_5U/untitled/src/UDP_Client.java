// UDPClient.java

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Client extends Thread {
    private DatagramSocket clientSocket;
    private Scanner scanner = new Scanner(System.in);

    public UDP_Client() {
        try {
            clientSocket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessages() {
        try {
            while (true) {
                System.out.print("Inserisci il messaggio: ");
                String message = scanner.nextLine();

                // Invia il messaggio al server
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
                clientSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveMessages() {
        try {
            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Messaggio dal server: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        sendMessages();
        receiveMessages();
    }
}
