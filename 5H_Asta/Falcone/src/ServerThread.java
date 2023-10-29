import java.io.*;
import java.net.*;


public class ServerThread extends Thread {
    Socket connessione;
    //creiamo i canali di stream
    OutputStream out;
    PrintWriter sOut;
    InputStream in;
    InputStreamReader input;
    BufferedReader sIn;
    String risposta;
    String mexRicevuto;
    
    double offerta;
    double prezzoBase;
    double maxOfferta;
    final int OFFERTE_MAX = 10;
    
    
    //definiamo il costruttore
    public ServerThread(Socket socket){
        this.connessione=socket;
    }
    //definiamo il metodo run
    @Override
    public void run(){
        try {
            comunica();
        } catch (Exception ex) {System.out.println(ex.getMessage());}
    }
    public void comunica() throws Exception{
        //inizializzazione flusso output
        out=connessione.getOutputStream();
        sOut=new PrintWriter(out);
        //inizializzazione flusso input
        in=connessione.getInputStream();
        input=new InputStreamReader(in);
        sIn=new BufferedReader(input);
        
        //codice
        Oggetto o = new Oggetto("Quadro", 120.00);
        prezzoBase = o.getPrezzo();
        maxOfferta = prezzoBase;
        int i = 0;
        
        sOut.println("Offerta di base: "+prezzoBase);
        sOut.flush();
        
        do{
        	sOut.println("Fai un'offerta: ");
            sOut.flush();
        	offerta = Double.parseDouble(sIn.readLine());
            
            if(offerta>prezzoBase) {
            	//true
            	maxOfferta = offerta;
            	for (int j=0; j<MultiServerConn.getConnessioni().size(); j++) {
            		OutputStream outAll=MultiServerConn.getConnessioni().get(i).getOutputStream();
            		PrintWriter sOutAll=new PrintWriter(outAll);
                    
            		sOutAll.println("Nuova offerta massima: "+maxOfferta);
                    sOutAll.flush();
            	}
            }

            sOut.println("Offerta attuale: "+maxOfferta);
            sOut.flush();
        	
        	i++;
        }while (i<OFFERTE_MAX);
        
        sOut.println("Asta finita, oggetto venduto! Prezzo: "+maxOfferta);
        sOut.flush();
        o.setVenduto(true);
        
        
        //chiudiamo i canali di stram
        sOut.close();
        sIn.close();
        System.out.println("Chiusura socket: "+connessione);
        connessione.close();
        
    }
}
