import java.io.IOException;
import java.net.*;

public class ClientUDP {
    private DatagramSocket socket;
    private InetAddress address;
    private DatagramPacket packet;

    private byte[] buf;

    public ClientUDP() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public void sendMessage(String msg) throws IOException {
        buf = msg.getBytes();
        packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
    }
    public String receveMessage() throws IOException {
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }

    public static  void main(String[] args) throws IOException {
        ClientUDP c1=new ClientUDP();
        c1.sendMessage("Ciao sono il client");
        System.out.println("Risposta dal SERVER:");
        System.out.println(c1.receveMessage());
    }
}