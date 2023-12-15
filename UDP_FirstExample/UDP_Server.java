import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_Server {
   //attributi globali della classe
    DatagramSocket socket;
    DatagramPacket packet;
    String msg;
    InetAddress address;
    int port;

    byte messaggio[]=new byte[256];

    public UDP_Server() throws SocketException {
        socket =new DatagramSocket(12345);
    }

    public void ricezionePacchetto() throws IOException {
        //preparo il datagramma per inserirci i dati che ricevo
        packet=new DatagramPacket(messaggio,messaggio.length);
        socket.receive(packet);
        msg=new String(packet.getData(),0, packet.getLength());
        //estrapolo IP e PORTA del client che mi ha mandato il msg
        address = packet.getAddress();
        port = packet.getPort();

        System.out.println("Il client "+address.getHostName()+" " +
                "sulla porta: "+port+" ha inviato il msg \n"+msg);
    }

    public void inviaPacchetto() throws IOException {
        String msgUpper=msg.toUpperCase();
        messaggio=msgUpper.getBytes();
        //costruisco l'oggetto datagramma per la trasmissione
        packet=new DatagramPacket(messaggio,messaggio.length,address,port);
        socket.send(packet);

    }
    //metodo main del Server
    public static void main(String[] args) throws IOException {
        UDP_Server s=new UDP_Server();
       // while(true){
                s.ricezionePacchetto();
                s.inviaPacchetto();
       // }

    }
}
