import java.io.*;
import java.net.*;
import java.security.AllPermission;
import java.util.*;


public class Server implements FlagInterface {

    private static List<User[]> userDatabase = new ArrayList<>();
    private static List<User[]> passwordsDatabase = new ArrayList<>();

    public static void main(String[] args) throws UnknownHostException, IOException {

        ServerSocket serverSocket = new ServerSocket(2727);


        while (true) {
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            while (true) {

                for (User[] user : userDatabase) {
                    String message = reader.readLine();
                    if (message.contains(LOGIN)) {
                        String[] index = message.split(";");
                        if (index.length > 1) {
                            String username = index[0];
                            boolean validUser = false;

                            for (String users : User.getUsernames()) {
                                if (users.contains(username)) {
                                    validUser = true;
                                    writer.println("Correct username");
                                    break;
                                }
                                if (!validUser) {
                                    writer.println("User does not exist. Create user?");
                                    String password = "";
                                    String bio = "";
                                    User userNew = new User(username, password, bio);
                                    userNew.createProfile(username, password, bio);
                                    userNew.validatePassword(username, password);

                                    return;
                                } else {
                                    writer.println("No username provided");
                                    return;
                                }
                            }
                        }
                    }
                }

                for (User[] passwordArray : passwordsDatabase) {
                    String message = reader.readLine();
                    if (message.contains(LOGIN)) {
                        String[] index = message.split(";");
                        if (index.length > 1) {
                            String password = index[1];
                            boolean validPassword = false;

                            for (String passwords : User.getPasswords()) {
                                if (passwords.contains(password)) {
                                    validPassword = true;
                                    writer.println("Login Successful");
                                    break;
                                }
                                if (!validPassword) {
                                    writer.println("Login Failed");
                                    return;
                                } else {
                                    writer.println("No password provided");
                                    return;
                                }
                            }
                        }
                    }
                }
                writer.close();
                reader.close();
            }
        }
    }
}



