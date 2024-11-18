import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Server implements FlagInterface {

    private static List<User[]> userDatabase = new ArrayList<>();
    private static List<User[]> passwordsDatabase = new ArrayList<>();
    private static ArrayList<Messaging> messageHistoryFriends = new ArrayList<>();
    private ArrayList<Messaging> messageHistoryUsers = new ArrayList<>();

    private static ArrayList<String> usernames = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<User> friendsList = new ArrayList<>();
    private static ArrayList<User> friends = new ArrayList<>();
    private static ArrayList<Messaging> messageHistory = new ArrayList<>();
    private static final Object SERVER_LOCK = new Object();
    private int serverNum;


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

                            friend1.addFriend(friend);
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
                            String friendUsername = operation[1];
                            String content = operation[2];
                            Friends currentUserFriends = new Friends(currentUser);
                            currentUserFriends.loadFriends();
                            currentUserFriends.loadBlocked();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();

                            User friendUser = null;
                            for (User user : User.getAllUsers()) {
                                if (user.getUsername().equals(friendUsername)) {
                                    friendUser = user;
                                    break;
                                }
                            }

                            if (friendUser == null) {
                                writer.println(TEXT_SINGLE_FRIEND + ";false");
                                writer.flush();
                                return;
                            }

                            Friends friendFriends = new Friends(friendUser);
                            System.out.println(friendFriends);
                            System.out.println(friendsList);

                            Messaging messaging = new Messaging(currentUser, friendFriends, content, date, isRead);
                            messaging.sendMessage(currentUser, friendFriends, content, date, isRead);
                            writer.println(TEXT_SINGLE_FRIEND + ";true");
                            writer.flush();

                        } else if (secondMessage.contains(MESSAGE_ALL_FRIENDS)) {
                                try {
                                    InputStream inputStream = socket.getInputStream();
                                    BufferedImage imageContent = ImageIO.read(inputStream);

                                    String date = "TODAY";
                                    boolean isRead = false;

                                    Friends currentUserFriends = new Friends(currentUser);
                                    currentUserFriends.loadFriends();
                                    currentUserFriends.loadBlocked();
                                    allUsers = User.getAllUsers();
                                    friendsList = Friends.getFriendsList();

                                    PhotoMessaging photoMessage = new PhotoMessaging(currentUser, imageContent, friendsList, date, isRead, "AllFriends");
                                    photoMessage.sendAllFriendsPhotoMessage(currentUser, imageContent, date, isRead);

                                    boolean sent = true;
                                    writer.println(MESSAGE_ALL_FRIENDS + ";" + sent);
                                    writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        else if (secondMessage.contains(MESSAGE_ALL_USERS)) {
                            try {
                                InputStream inputStream = socket.getInputStream();
                                BufferedImage imageContent = ImageIO.read(inputStream);

                                String date = "TODAY";
                                boolean isRead = false;

                                Friends currentUserFriends = new Friends(currentUser);
                                currentUserFriends.loadFriends();
                                currentUserFriends.loadBlocked();
                                allUsers = User.getAllUsers();

                                PhotoMessaging photoMessage = new PhotoMessaging(currentUser, imageContent, allUsers, date, isRead, "AllUsers");
                                photoMessage.sendAllUsersPhotoMessage(currentUser, imageContent, date, isRead);

                                boolean sent = true;
                                writer.println(MESSAGE_ALL_USERS + ";" + sent);
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (secondMessage.contains(MESSAGE_SINGLE_FRIEND)) {
                            try {
                                String[] operation = secondMessage.split(";");
                                String date = "TODAY";
                                boolean isRead = false;
                                String friendUsername = operation[1];

                                InputStream inputStream = socket.getInputStream();
                                BufferedImage imageContent = ImageIO.read(inputStream);

                                Friends currentUserFriends = new Friends(currentUser);
                                currentUserFriends.loadFriends();
                                currentUserFriends.loadBlocked();
                                allUsers = User.getAllUsers();
                                friendsList = Friends.getFriendsList();

                                User friendUser = null;
                                for (User user : allUsers) {
                                    if (user.getUsername().equals(friendUsername)) {
                                        friendUser = user;
                                        break;
                                    }
                                }

                                if (friendUser == null) {
                                    writer.println(MESSAGE_SINGLE_FRIEND + ";false");
                                    writer.flush();
                                    return;
                                }

                                Friends friendFriends = new Friends(friendUser);

                                PhotoMessaging photoMessage = new PhotoMessaging(currentUser, friendFriends, imageContent, date, isRead);
                                photoMessage.sendPhotoMessage(currentUser, friendFriends, imageContent, date, isRead);

                                writer.println(MESSAGE_SINGLE_FRIEND + ";true");
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        } else if (secondMessage.contains(DELETE_SINGLE_FRIEND)) {


                            String[] operation = secondMessage.split(";");
                            String date = "TODAY";
                            boolean isRead = false;
                            String friendUsername = operation[1];
                            String content = operation[2];
                            Friends currentUserFriends = new Friends(currentUser);
                            currentUserFriends.loadFriends();
                            currentUserFriends.loadBlocked();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();
                            messageHistory = Messaging.getMessageHistory();

                            User friendUser = null;
                            for (User user : User.getAllUsers()) {
                                if (user.getUsername().equals(friendUsername)) {
                                    friendUser = user;
                                    break;
                                }
                            }

                            if (friendUser == null) {
                                writer.println(TEXT_SINGLE_FRIEND + ";false");
                                writer.flush();
                                return;
                            }

                            Friends friendFriends = new Friends(friendUser);
                            System.out.println(friendFriends);
                            System.out.println(friendsList);

                            Messaging messageTemp = new Messaging(currentUser, friendFriends, content, date, isRead);
                            messageTemp.loadMessages(currentUser);


                            System.out.println(messageHistory);


                            friendUser = null;
                            for (User user : User.getAllUsers()) {
                                if (user.getUsername().equals(friendUsername)) {
                                    friendUser = user;
                                    break;
                                }
                            }

                            if (friendUser == null) {
                                writer.println(DELETE_SINGLE_FRIEND + ";false");
                                writer.flush();
                                return;
                            }

                            friendFriends = new Friends(friendUser);


                            boolean messageDeleted = false;
                            for (Messaging message1 : messageHistory) {
                                if (message1.getSender().equals(currentUser) &&
                                        message1.getReceiver().equals(friendFriends) &&
                                        message1.getContent().equals(content)) {
                                    message1.deleteMessage(currentUser, friendFriends, content, date, isRead);
                                    messageDeleted = true;
                                    break;
                                }
                            }


                            if (messageDeleted) {
                                writer.println(DELETE_SINGLE_FRIEND + ";true");
                            } else {
                                writer.println(DELETE_SINGLE_FRIEND + ";false");
                            }
                            writer.flush();

                        } else if (secondMessage.contains(DELETE_ALL_FRIENDS)) {

                            String[] operation = secondMessage.split(";");
                            String date = "TODAY";
                            boolean isRead = false;
                            String content = operation[1];


                            Friends friend1 = new Friends(currentUser);
                            friend1.loadFriends();
                            friend1.loadBlocked();
                            currentUser.loadUsers();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();
                            messageHistory = Messaging.getMessageHistory();


                            Messaging messageTemp = new Messaging(currentUser, null, content, date, isRead);
                            messageTemp.loadMessages(currentUser);

                            System.out.println(messageHistory);


                            boolean messageDeleted = false;
                            for (Messaging message2 : messageHistory) {
                                if (message2.getSender().equals(currentUser) && message2.getContent().equals(content) &&
                                        message2.getMessageType().equals("AllFriends")) {
                                    message2.deleteFriendsMessage(currentUser, content, date, isRead);
                                    messageDeleted = true;
                                    break;
                                }
                            }

                            if (messageDeleted) {
                                writer.println(DELETE_ALL_FRIENDS + ";true");
                            } else {
                                writer.println(DELETE_ALL_FRIENDS + ";false");
                            }
                            writer.flush();


                        } else if (secondMessage.contains(DELETE_ALL_USERS)){

                            String[] operation = secondMessage.split(";");
                            String date = "TODAY";
                            boolean isRead = false;
                            String content = operation[1];

                            Friends currentUserFriends = new Friends(currentUser);
                            currentUserFriends.loadFriends();
                            currentUserFriends.loadBlocked();
                            allUsers = User.getAllUsers();
                            friendsList = Friends.getFriendsList();
                            messageHistory = Messaging.getMessageHistory();

                            Messaging messageTemp = new Messaging(currentUser, null, content, date, isRead);
                            messageTemp.loadMessages(currentUser);

                            System.out.println(messageHistory);

                            boolean messageDeleted = false;

                            for (Messaging message1 : messageHistory) {
                                if (message1.getSender().equals(currentUser) &&
                                        message1.getContent().equals(content) &&
                                        message1.getMessageType().equals("AllUsers")) {
                                    message1.deleteAllMessage(currentUser, content, date, isRead);
                                    messageDeleted = true;
                                }
                            }

                            if (messageDeleted) {
                                writer.println(DELETE_ALL_USERS + ";true");
                            } else {
                                writer.println(DELETE_ALL_USERS + ";false");
                            }
                            writer.flush();


                        }


                }
            } catch(IOException e){
                e.printStackTrace();
            } finally{
                socket.close();
            }
        }

    } finally

    {
        serverSocket.close();
    }
}

public void serverStart(int value) {
    synchronized (SERVER_LOCK) {
        serverNum += value;
    }
}
}

