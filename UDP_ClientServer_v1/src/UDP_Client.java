import java.io.IOException;
import java.net.*;

public class UDP_Client {
    private DatagramSocket socket;
    private DatagramPacket packet;
    private InetAddress address;

    private byte[] buf;

    public UDP_Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public void sendMessage(String msg) throws IOException {
        buf = msg.getBytes();
        packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
    }

    public String receiveMessage() throws IOException {
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }
}