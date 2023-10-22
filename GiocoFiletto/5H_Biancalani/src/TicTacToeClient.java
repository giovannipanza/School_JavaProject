import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicTacToeClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String serverResponse;
            while ((serverResponse = in.readLine()) != null) {
                if (serverResponse.contains("ENDL")) {
                    String tabellone[] = serverResponse.split("ENDL");
                    for (String s : tabellone) {
                        System.out.println(s);
                    }
                    continue;
                }
                else {
                    System.out.println(serverResponse);
                }

                if (serverResponse.equals("Game over!") || serverResponse.equals("You win!") ||
                        serverResponse.equals("It's a draw!")) {
                    break;
                }

                String userMove = userInput.readLine();
                out.println(userMove);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}