import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;



public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {


        int portNumber = 2727;
        Socket socket;
        String host = "localhost";

        try {
            socket = new Socket(host, portNumber);
        } catch (IOException e) {
            return;
        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        while (true) {
            System.out.println("1 - Login");

            ArrayList<String> title = new ArrayList<>();
            String message;

            while (true) {
                message = reader.readLine();
                break;
            }

            writer.close();
            reader.close();


        }
    }
}
