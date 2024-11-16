import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server implements FlagInterface{
    public static void main(String[] args) throws UnknownHostException, IOException {

        ServerSocket serverSocket = new ServerSocket(2727);

        while (true) {
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            while (true) {
                String message = reader.readLine();
                if (message.contains(LOGIN))
                break;
            }
            writer.close();
            reader.close();
        }


    }
}

