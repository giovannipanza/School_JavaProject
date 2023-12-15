import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDP_Client {
    //attributi globali della classe
    DatagramSocket socket;
    DatagramPacket packet;
    String msg;
    InetAddress address;
    int port;
    Scanner tastiera;

    byte messaggio[]=new byte[256];

    public UDP_Client() throws SocketException {
        socket =new DatagramSocket();
        tastiera=new Scanner(System.in);
    }

    public void inviaPacchetto() throws IOException {
        String msg=tastiera.nextLine();
        messaggio=msg.getBytes();

        //prendo l'indirizzo ip locale dellla macchina dove gira il server
        address=InetAddress.getLocalHost();
        //address=InetAddress.getByName("");
        //costruisco l'oggetto datagramma per la trasmissione
        packet=new DatagramPacket(messaggio,messaggio.length,address,12345);
        socket.send(packet);
    }

    public void ricezionePacchetto() throws IOException {
        //preparo il datagramma per inserirci i dati che ricevo
        packet=new DatagramPacket(messaggio,messaggio.length);
        socket.receive(packet);
        msg=new String(packet.getData(),0, packet.getLength());
        /*estrapolo IP e PORTA del client che mi ha mandato il msg
        address = packet.getAddress();
        port = packet.getPort(); */

        System.out.println("Il client "+address.getHostName()+" " +
                "sulla porta: "+port+" ha inviato il msg \n"+msg);
    }

    //metodo main del Client
   public static void main(String[] args) throws IOException {

        UDP_Client c=new UDP_Client();
        //while(true){
            c.inviaPacchetto();
            c.ricezionePacchetto();
            //}

   }
}
