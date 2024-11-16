import java.io.*;
import java.net.*;
import java.security.AllPermission;
import java.util.*;


public class Server implements FlagInterface {

    private static List<User[]> userDatabase = new ArrayList<>();
    private static List<User[]> passwordsDatabase = new ArrayList<>();
    private static ArrayList<Messaging> messageHistoryFriends = new ArrayList<>();
    private ArrayList<Messaging> messageHistoryUsers = new ArrayList<>();
    private ArrayList<Messaging> messageHistory = new ArrayList<>();


    public static void main(String[] args) throws UnknownHostException, IOException {
        User currentUser = null;

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
                                    writer.println(LOGIN + ";" + "Correct username");
                                    currentUser = new User(username);
                                    writer.flush();
                                    break;
                                }
                                if (!validUser) {
                                    writer.println(LOGIN + ";" + "User does not exist");
                                    writer.flush();
                                    return;
                                } else {
                                    writer.println(LOGIN + ";" + "No username provided");
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
                                    writer.println(LOGIN + ";" + "Login Successful");
                                    writer.flush();
                                    break;
                                }
                                if (!validPassword) {
                                    writer.println(LOGIN + ";" + "Login Failed");
                                    writer.flush();
                                    return;
                                } else {
                                    writer.println(LOGIN + ";" + "No password provided");
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
                    currentUser = new User(username);
                    User userNew = new User(username, password, bio);
                    userNew.createProfile(username, password, bio);
                    boolean validate = userNew.validatePassword(username, password);
                    writer.println(CREATE + ";" + validate);
                    writer.flush();
                }

                String operation = reader.readLine();

                if (operation.contains(FRIENDS_ADD)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(FRIENDS_ADD + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    friend1.addFriend(friend);
                    boolean added = friend1.isFriend(friend);
                    writer.println(FRIENDS_ADD + ";" + added);
                    writer.flush();


                } else if (operation.contains(FRIENDS_REMOVE)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(FRIENDS_REMOVE + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    friend1.removeFriend(friend);
                    boolean removed = friend1.isFriend(friend);
                    writer.println(FRIENDS_REMOVE + ";" + removed);
                    writer.flush();

                } else if (operation.contains(FRIENDS_BLOCK)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(FRIENDS_BLOCK + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    friend1.blockUser(friend);
                    boolean blocked = friend1.isBlocked(friend);
                    writer.println(FRIENDS_BLOCK + ";" + blocked);
                    writer.flush();

                } else if (operation.contains(FRIENDS_UNBLOCK)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(FRIENDS_UNBLOCK + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    friend1.unblockUser(friend);
                    boolean unblocked = friend1.isBlocked(friend);
                    writer.println(FRIENDS_UNBLOCK + ";" + unblocked);
                    writer.flush();


                } else if (operation.contains(TEXT_ALL_FRIENDS)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(TEXT_ALL_FRIENDS + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    String content = operationed[2];
                    Friends receiver = new Friends(friend);

                    Messaging messaging = new Messaging(currentUser, receiver, content, "Date", false); // WRONG CONSTRUCTOR LOOK AT METHOD

                    String date = null;
                    boolean isRead = false;
                    messaging.sendAllFriendsMessage(currentUser, content, "date", isRead);
                    boolean sent = true; //CHECK MORE THAN TRUE
                    writer.println(TEXT_ALL_FRIENDS + ";" + sent);
                    writer.flush();


                } else if (operation.contains(TEXT_ALL_USERS)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(TEXT_ALL_USERS + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    String content = operationed[2];
                    Friends reciver = new Friends(friend);

                    Messaging messaging = new Messaging(currentUser, reciver, content, "Date", false); // WRONG CONSTRUCTOR LOOK AT METHOD


                    String date = null;
                    boolean isRead = false;
                    messaging.sendAllUsersMessage(currentUser, content, "date", isRead);
                    boolean sent = true;
                    writer.println(TEXT_ALL_USERS + ";" + sent);
                    writer.flush();

                } else if (operation.contains(TEXT_SINGLE_FRIEND)) {

                    String[] operationed = operation.split(";");

                    String target = operationed[1];

                    User friend = null;
                    Friends friend1 = new Friends(currentUser);
                    for (User friends : Friends.getFriendsList()) {
                        if (friends.toString().contains(target)) {
                            friend = friends;
                        } else {

                            writer.write(TEXT_SINGLE_FRIEND + ";" + "false");
                            writer.println();
                            writer.flush();
                        }
                    }

                    String content = operationed[2];
                    Friends reciver = new Friends(friend);

                    Messaging messaging = new Messaging(currentUser, reciver, content, "Date", false);


                    String date = null;
                    boolean isRead = false;
                    messaging.sendMessage(currentUser, reciver, content, "date", isRead);
                    boolean sent = true;
                    writer.println(TEXT_SINGLE_FRIEND + ";" + sent);
                    writer.flush();

                } else if (operation.contains(MESSAGE_ALL_FRIENDS)) {

                    String date = null;
                    boolean isRead = false;


                } else if (operation.contains(MESSAGE_ALL_USERS)) {

                    String date = null;
                    boolean isRead = false;


                } else if (operation.contains(MESSAGE_SINGLE_FRIEND)) {

                    String date = null;
                    boolean isRead = false;
                    break;


                }

            }
            writer.close();
            reader.close();
        }
    }
}


