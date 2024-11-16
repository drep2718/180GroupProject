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
                String message = reader.readLine();
                for (User[] user : userDatabase) {
                    if (message.contains(LOGIN)) {
                        String[] index = message.split(";");
                        if (index.length > 1) {
                            String username = index[1];
                            boolean validUser = false;

                            for (String users : User.getUsernames()) {
                                if (users.contains(username)) {
                                    validUser = true;
                                    writer.println(LOGIN + "Correct username");
                                    writer.flush();
                                    break;
                                }
                                if (!validUser) {
                                    writer.println(LOGIN + "User does not exist");
                                    writer.flush();
                                    return;
                                } else {
                                    writer.println(LOGIN + "No username provided");
                                    writer.flush();
                                    return;
                                }
                            }
                        }
                    }
                }

                for (User[] passwordArray : passwordsDatabase) {
                    if (message.contains(LOGIN)) {
                        String[] index = message.split(";");
                        if (index.length > 1) {
                            String password = index[2];
                            boolean validPassword = false;

                            for (String passwords : User.getPasswords()) {
                                if (passwords.contains(password)) {
                                    validPassword = true;
                                    writer.println(LOGIN + "Login Successful");
                                    writer.flush();
                                    break;
                                }
                                if (!validPassword) {
                                    writer.println(LOGIN + "Login Failed");
                                    writer.flush();
                                    return;
                                } else {
                                    writer.println(LOGIN + "No password provided");
                                    writer.flush();
                                    return;
                                }
                            }
                        }
                    }
                }

                if (message.contains(CREATE)) {
                    String username = message.split(";")[1];
                    String password = message.split(";")[2];
                    String bio = message.split(";")[3];
                    User userNew = new User(username, password, bio);
                    userNew.createProfile(username, password, bio);
                    userNew.validatePassword(username, password);
                    writer.println(CREATE + "User created");
                    writer.flush();
                }

                for (User[] passwordArray : passwordsDatabase) {
                    String message = reader.readLine();
                    if (message.contains(LOGIN)) {
                        String[] index = message.split(";");
                        if (index.length > 1) {
                            String password = index[2];
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

                    String operation = reader.readLine();
                    String[] operationed = operation.split(";");

                    String target = operationed[1];


                    User friend = null;
                    for (User user : User.getAllUsers()) {

                        if (user.toString().contains(target)) {
                            friend = user;
                        } else {

                            writer.write("false");
                            writer.println();
                            writer.flush();
                        }
                    }
                    if (operation.contains(FRIENDS_ADD)) {
                        currentUser.addFriend(friend);
                        boolean added = currentUser.isFriend(friend);
                        writer.write(String.valueOf(added));
                        writer.println();
                        writer.flush();


                    } else if (operation.contains(FRIENDS_REMOVE)) {
                        currentUser.removeFriend(friend);
                        boolean removed = currentUser.isFriend(friend);
                        writer.write(String.valueOf(!removed));
                        writer.println();
                        writer.flush();

                    } else if (operation.contains(FRIENDS_BLOCK)) {
                        currentUser.blockFriend(friend);
                        boolean blocked = currentUser.isBlocked(friend);
                        writer.write(String.valueOf(blocked));
                        writer.println();
                        writer.flush();

                    } else if (operation.contains(FRIENDS_UNBLOCK)) {
                        currentUser.unblockFriend(friend);
                        boolean unblocked = currentUser.isBlocked(friend);
                        writer.write(String.valueOf(!unblocked));
                        writer.println();
                        writer.flush();

                    } else if (operation.contains(TEXT_ALL_FRIENDS)) {
                        String content = operationed[2];

                        String date = null;
                        boolean isRead = false;
                        currentUser.sendAllFriendsMessage(currentUser, content, date, isRead);
                        boolean sent = true;
                        writer.write(String.valueOf(sent));
                        writer.println();
                        writer.flush();


                    } else if (operation.contains(TEXT_ALL_USERS)) {
                        String content = operationed[2];
                        String date = null;
                        boolean isRead = false;
                        currentUser.sendAllUsersMessage(currentUser, content, date, isRead);
                        boolean sent = true;
                        writer.write(String.valueOf(sent));
                        writer.println();
                        writer.flush();

                    } else if (operation.contains(TEXT_SINGLE_FRIEND)) {
                        String content = operationed[2];
                        String date = null;
                        boolean isRead = false;
                        currentUser.sendMessage(currentUser, friend, content, date, isRead);
                        boolean sent = true;
                        writer.write(String.valueOf(sent));
                        writer.println();
                        writer.flush();

                    } else if (operation.contains(MESSAGE_ALL_FRIENDS)) {
                        String content = operationed[2];
                        String date = null;
                        boolean isRead = false;


                    } else if (operation.contains(MESSAGE_ALL_USERS)) {
                        String content = operationed[2];
                        String date = null;
                        boolean isRead = false;


                    } else if (operation.contains(MESSAGE_SINGLE_FRIEND)) {
                        String content = operationed[2];
                        String date = null;
                        boolean isRead = false;


                    }


                }
                writer.close();
                reader.close();
            }
        }
    }
}


