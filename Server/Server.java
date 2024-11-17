import java.io.*;
import java.net.*;
import java.util.*;


public class Server implements FlagInterface {

    private static List<User[]> userDatabase = new ArrayList<>();
    private static List<User[]> passwordsDatabase = new ArrayList<>();
    private static ArrayList<Messaging> messageHistoryFriends = new ArrayList<>();
    private ArrayList<Messaging> messageHistoryUsers = new ArrayList<>();
    private ArrayList<Messaging> messageHistory = new ArrayList<>();

    private static ArrayList<String> usernames = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<User> friendsList = new ArrayList<>();
    private static ArrayList<User> friends = new ArrayList<>();




    public static void main(String[] args) throws UnknownHostException, IOException {
        ServerSocket serverSocket = new ServerSocket(2727);

        try {
            while (true) {
                Socket socket = serverSocket.accept();

                try {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    User currentUser = null;

                    while (true) {
                        String message = reader.readLine();

                        if (message == null) {
                            break;
                        }
                        if (message.equals("LOOP")) {
                            writer.println("CONTINUE");
                            writer.flush();
                            continue;
                        }
                        if (message.contains(LOGIN)) {
                            String[] index = message.split(";");
                            if (index.length > 2) {
                                String username = index[1];
                                String password = index[2];
                                boolean validUser = false;
                                currentUser = new User(username);
                                currentUser.loadUsers();
                                usernames = User.getUsernames();


                                for (String existingUsername : usernames) {
                                    if (existingUsername.equals(username)) {
                                        validUser = true;
                                        break;
                                    }
                                }


                                boolean validPassword = false;
                                for (String existingPassword : User.getPasswords()) {
                                    if (existingPassword.equals(password)) {
                                        validPassword = true;
                                        break;
                                    }
                                }

                                if (validPassword && validUser) {
                                    writer.println(LOGIN + ";" + "Login Successful");
                                } else {
                                    writer.println(LOGIN + ";" + "Login Failed");
                                }
                            } else {
                                writer.println(LOGIN + ";" + "Missing credentials");
                            }
                            writer.flush();
                            continue;
                        }


                        if (message.contains(CREATE)) {
                            String username = message.split(";")[1];
                            String password = message.split(";")[2];
                            String bio = message.split(";")[3];
                            currentUser = new User(username);
                            usernames = User.getUsernames();
                            boolean validUser = false;
                            User newUser = currentUser.createProfile(username, password, bio);
                            String check = newUser.getUsername();
                            String check1 = newUser.getPassword();
                            System.out.println(check + " " + check1);


                            for (String existingUsername : User.getUsernames()) {
                                if (existingUsername.equals(username)) {
                                    System.out.println(existingUsername);
                                    validUser = true;
                                    break;
                                }
                            }
                            writer.println(CREATE + ";" + validUser);
                            writer.flush();
                            continue;
                        }

                        String secondMessage = reader.readLine();
                        if (secondMessage == null || secondMessage.equals("LOOP")) {
                            writer.println("CONTINUE");
                            writer.flush();
                            continue;
                        }
                        if (secondMessage.contains(FRIENDS_ADD)) {

                            String[] operation = secondMessage.split(";");
                            String target = operation[1];
                            User friend = null;

                            Friends friend1 = new Friends(currentUser);
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friend1.loadFriends();
                            for (User current : allUsers) {
                                if (current.getUsername().equals(target)) {
                                    friend = new User(current.getUsername());
                                    break;
                                }
                            }


                            if (currentUser == null) {
                                writer.write(FRIENDS_ADD + ";" + "false");
                                writer.println();
                                writer.flush();
                            }

                            friend1.addFriend(friend); // KJ,null,null
                            boolean added = friend1.isFriend(friend);
                            writer.println(FRIENDS_ADD + ";" + added);
                            writer.flush();


                        } else if (secondMessage.contains(FRIENDS_REMOVE)) {

                            String[] operation = secondMessage.split(";");
                            String target = operation[1];
                            User friend = null;
                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();


                            for (User current : allUsers) {
                                if (current.getUsername().equals(target)) {
                                    friend = new User(current.getUsername());
                                    break;
                                }
                            }


                            if (currentUser == null) {
                                writer.write(FRIENDS_ADD + ";" + "false");
                                writer.println();
                                writer.flush();
                            }


                            friend1.removeFriend(friend);
                            boolean added = !friend1.isFriend(friend);
                            writer.println(FRIENDS_REMOVE + ";" + added);
                            writer.flush();


                        } else if (secondMessage.contains(FRIENDS_BLOCK)) {


                            String[] operation = secondMessage.split(";");
                            String target = operation[1];
                            User friend = null;
                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();


                            for (User current : allUsers) {
                                if (current.getUsername().equals(target)) {
                                    friend = new User(current.getUsername());
                                    break;
                                }
                            }


                            if (currentUser == null) {
                                writer.write(FRIENDS_ADD + ";" + "false");
                                writer.println();
                                writer.flush();
                            }


                            friend1.removeFriend(friend);
                            friend1.blockUser(friend);
                            boolean blocked = friend1.isBlocked(friend);
                            writer.println(FRIENDS_BLOCK + ";" + blocked);
                            writer.flush();

                        } else if (secondMessage.contains(FRIENDS_UNBLOCK)) {

                            String[] operation = secondMessage.split(";");
                            String target = operation[1];
                            User friend = null;
                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            friend1.loadBlocked();
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();


                            for (User current : allUsers) {
                                if (current.getUsername().equals(target)) {
                                    friend = new User(current.getUsername());
                                    break;
                                }
                            }


                            if (currentUser == null) {
                                writer.write(FRIENDS_ADD + ";" + "false");
                                writer.println();
                                writer.flush();
                            }


                            friend1.addFriend(friend);
                            friend1.unblockUser(friend);
                            boolean unblocked = !friend1.isBlocked(friend);
                            writer.println(FRIENDS_UNBLOCK + ";" + unblocked);
                            writer.flush();


                        } else if (secondMessage.contains(TEXT_ALL_FRIENDS)) {

                            String[] operation = secondMessage.split(";");
                            String date = "TODAY";
                            boolean isRead = false;
                            User friend = null;
                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            friend1.loadBlocked();
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();
                            String content = operation[1];



                            Messaging messages = new Messaging(currentUser, content, friends, date, isRead, "AllFriends");
                            messages.sendAllFriendsMessage(currentUser, content, date, isRead);
                            boolean sent = true;
                            writer.println(TEXT_ALL_FRIENDS + ";" + sent);
                            writer.flush();


                        } else if (secondMessage.contains(TEXT_ALL_USERS)) {

                            String[] operation = secondMessage.split(";");
                            String date = "TODAY";
                            boolean isRead = false;
                            User friend = null;
                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            friend1.loadBlocked();
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();
                            String content = operation[1];



                            Messaging messages = new Messaging(currentUser, content, friends, date, isRead, "AllFriends");
                            messages.sendAllUsersMessage(currentUser, content, date, isRead);
                            boolean sent = true;
                            writer.println(TEXT_ALL_FRIENDS + ";" + sent);
                            writer.flush();



                        } else if (secondMessage.contains(TEXT_SINGLE_FRIEND)) {
                            String[] operation = secondMessage.split(";");
                            String date = "TODAY";
                            boolean isRead = false;

                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            friend1.loadBlocked();
                            currentUser.loadUsers();
                            friendsList = Friends.getFriendsList();
                            allUsers = User.getAllUsers();
                            String friendUsername = operation[1];
                            String content = operation[2];

                            for (User user: friendsList) {
                                System.out.println(user);
                            }

                            Friends receiver = null;
                            for (User user : allUsers) {
                                if (user.getUsername().equals(friendUsername)) {
                                    System.out.println(user);
                                    receiver = new Friends(user);
                                    System.out.println(receiver);
                                    break;
                                }
                            }

                            if (receiver.isFriend(currentUser)) {
                                System.out.println("FRIENDS");
                            }

                            if (receiver != null) {
                                Messaging messaging = new Messaging(currentUser, receiver, content, date, isRead);
                                messaging.sendMessage(currentUser, receiver, content, date, isRead);
                                boolean sent = true;
                                writer.println(TEXT_SINGLE_FRIEND + ";" + sent);
                                writer.flush();
                            } else {
                                writer.println(TEXT_SINGLE_FRIEND + ";false");
                                writer.flush();
                            }
                        }else if (secondMessage.contains(MESSAGE_ALL_FRIENDS)) {

                            String date = null;
                            boolean isRead = false;


                        } else if (secondMessage.contains(MESSAGE_ALL_USERS)) {

                            String date = null;
                            boolean isRead = false;


                        } else if (secondMessage.contains(MESSAGE_SINGLE_FRIEND)) {

                            String date = null;
                            boolean isRead = false;
                            break;


                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    socket.close();
                }
            }

        } finally {
            serverSocket.close();
        }
    }
}


