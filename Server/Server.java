import java.io.*;
import java.net.*;


/**
 * Team Project -- Server Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */
public class Server {


    private static final int PORT = 2727;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);


        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client");
                ThreadSafe threadSafe = new ThreadSafe(socket);
                threadSafe.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
