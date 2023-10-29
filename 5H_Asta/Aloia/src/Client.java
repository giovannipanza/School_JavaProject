import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",12345);
            Scanner client_in = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            String serverMess = in.readLine();
            System.out.println(serverMess);
            String offer = client_in.nextLine();
            out.println(offer);
            serverMess = in.readLine();
            System.out.println(serverMess);
            String importo = client_in.nextLine();
            out.println(importo);

//            socket.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}