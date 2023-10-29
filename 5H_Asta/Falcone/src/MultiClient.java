import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MultiClient {
	//dichiarzione variabili
	Socket mioClient;
	int porta=12345;
	//stresm per gestire flusso in input
	InputStream in;
	InputStreamReader input;
	BufferedReader sIn;
	//gest flusso in output
	OutputStream out;
	PrintWriter sOut;
	String mexServer;

	BufferedReader tastiera;

	//metodo connessione
	public Socket connessione() throws Exception{
		mioClient=new Socket(InetAddress.getLocalHost(), porta);
		System.out.println("3_Apertura connessione con server: "+InetAddress.getLocalHost()+ "su porta: "+porta);
		tastiera=new BufferedReader(new InputStreamReader(System.in));
		//inizializzazione flusso output
		out=mioClient.getOutputStream();
		sOut=new PrintWriter(out);
		//inizializzazione flusso input
		in=mioClient.getInputStream();
		input=new InputStreamReader(in);
		sIn=new BufferedReader(input);

		return mioClient;
	}
	public void comunica() throws Exception{
		//leggo offerta di base
		mexServer=sIn.readLine();
		System.out.println(mexServer);
		
		do {
			mexServer=sIn.readLine();
			System.out.println(mexServer);
			
			//propongo offerta
			String offerta = tastiera.readLine();
			sOut.println(offerta);
			sOut.flush();
			
			
			//leggo l'ultima offerta effettuata
			mexServer=sIn.readLine();
			System.out.println(mexServer);	
		} while (!mexServer.contains("Asta finita"));
		
		mioClient.close();
	}
	public static void main(String[] args) {
		MultiClient c=new MultiClient();
		try {
			c.connessione();
			c.comunica();
		} catch (Exception ex) {
			Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
		}



	}

}


