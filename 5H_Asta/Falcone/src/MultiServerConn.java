import java.net.*;
import java.util.ArrayList;
public class MultiServerConn {
    //definiamo le variabili
    ServerSocket mioServer;
    Socket connessione;
    int porta=12345;
    
    public static ArrayList<Socket> connessioni = new ArrayList<Socket>();
    
    public static ArrayList<Socket> getConnessioni() {
    	return connessioni;
    }
    
    public void connessione() throws Exception{
    	mioServer=new ServerSocket(porta);
	   
    
        while(true){
             Socket socket=mioServer.accept();
             System.out.println("Connesione stabilita: "+socket);
             connessioni.add(socket);
             //generiamo un thread
             ServerThread sThread=new ServerThread(socket);
             sThread.start();
        }
   }
}
