// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ServerMain {

    private static final int port = 11111;

    public static void main(String[] args) {
        System.out.println("Starting server.");
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Server started and listening on " + serverSocket.getInetAddress() + " " + port);

        ObservableList<SocketAuction> socketAuctions = new ObservableList<>(Collections.synchronizedList(new ArrayList<>()));

//        Starting Auction
        HandlerAuction handlerAuction = new HandlerAuction(socketAuctions);
        handlerAuction.start();

//        Set Interrupt action
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            handlerAuction.interrupt();
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        while (CurrentAuction.auctionAlive) {
            try {
                Socket clientSocket = serverSocket.accept();
                SocketAuction socketAuction = new SocketAuction(clientSocket);
                socketAuctions.add(socketAuction);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}