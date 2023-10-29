import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

public class Gestore implements Runnable {

    private Socket socket;
    private ArrayList<Socket>connessioni;
private ArrayList<Integer> offerta;

    public Gestore(Socket socket, ArrayList<Socket> connessioni,ArrayList<Integer> offerta) {
        this.socket = socket;
         this.connessioni=connessioni;
         this.offerta=offerta;

    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out2;
            BufferedReader in2;
            String message = in.readLine();
         //   out.println("ciao client messagio ricevuto");
            //out.println("Messaggio ricevuto: " + message);
            //gestiscie un comunicazione brodcast
            if(connessioni.size()==2){
                for(int i=0;i<connessioni.size();i++){
                    out2=new PrintWriter(connessioni.get(i).getOutputStream(),true) ;
                    out2.println("asta partito");
                }
                for(int i=0;i<connessioni.size();i++){
                    in2=new BufferedReader(new InputStreamReader( connessioni.get(i).getInputStream())) ;

                }
            }


//}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}