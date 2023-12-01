public class MainClient {
    public static void main(String[] args) {
        UDP_Client_Receive t1 = new UDP_Client_Receive();
        UDP_Client_Send t2 = new UDP_Client_Send();
        t1.start();
        t2.start();
    }
}
