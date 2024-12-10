import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.Socket;
import java.util.*;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Team Project -- ThreadSafe Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

public class ThreadSafe extends Thread implements FlagInterface {
    private static List<User[]> userDatabase = new ArrayList<>();
    private static List<User[]> passwordsDatabase = new ArrayList<>();
    private static ArrayList<Messaging> messageHistoryFriends = new ArrayList<>();
    private ArrayList<Messaging> messageHistoryUsers = new ArrayList<>();


    private static ArrayList<String> usernames = new ArrayList<>();
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<User> friendsList = new ArrayList<>();
    private static ArrayList<User> friends = new ArrayList<>();
    private static ArrayList<Messaging> messageHistory = new ArrayList<>();
    private static final Object gatekeeper = new Object();
    private int serverNum;
    private Socket socket;


    public ThreadSafe(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {


        while (true) {

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
                        synchronized (gatekeeper) {
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
                    }


                    if (message.contains(CREATE)) {
                        synchronized (gatekeeper) {
                            String[] parts = message.split(";");
                            String username = parts[1];
                            String password = parts[2];
                            String bio = parts[3];

                            User temp = new User("temp");
                            temp.loadUsers();
                            ArrayList<User> allUsers = User.getAllUsers();

                            boolean isUsernameTaken = false;
                            for (User user : allUsers) {
                                if (user.getUsername().equals(username)) {
                                    isUsernameTaken = true;
                                    break;
                                }
                            }

                            if (isUsernameTaken) {
                                writer.println(CREATE + ";Taken");
                                writer.flush();
                            } else {
                                User newUser = new User(username);
                                newUser.createProfile(username, password, bio);
                                writer.println(CREATE + ";true");
                                writer.flush();
                            }
                        }
                    } if (message.equals("LOGOUT")) {
                        System.out.println("User has logged out");
                        break;
                    }



                    String secondMessage = reader.readLine();
                    if (secondMessage == null || secondMessage.equals("LOOP")) {
                        writer.println("CONTINUE");
                        writer.flush();
                        continue;
                    }
                    if (secondMessage.contains(FRIENDS_ADD)) {
                        synchronized (gatekeeper) {

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


                            if (currentUser == null || Friends.getFriendsList().contains(friend)) {
                                writer.write(FRIENDS_ADD + ";" + "false");
                                writer.println();
                                writer.flush();
                            }

                            friend1.addFriend(friend);
                            boolean added = friend1.isFriend(friend);
                            writer.println(FRIENDS_ADD + ";" + added);
                            writer.flush();

                        }
                    } else if (secondMessage.contains(FRIENDS_REMOVE)) {
                        synchronized (gatekeeper) {

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


                            if (currentUser == null || !Friends.getFriendsList().contains(friend)) {
                                writer.write(FRIENDS_REMOVE + ";" + "false");
                                writer.println();
                                writer.flush();
                            }


                            friend1.removeFriend(friend1, friend);
                            boolean added = !friend1.isFriend(friend);
                            writer.println(FRIENDS_REMOVE + ";" + added);
                            writer.flush();
                        }


                    } else if (secondMessage.contains(FRIENDS_BLOCK)) {

                        synchronized (gatekeeper) {


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


                            friend1.loadBlocked();
                            if (currentUser == null || Friends.getBlockedList().contains(friend)) {
                                writer.write(FRIENDS_BLOCK + ";" + "false");
                                writer.println();
                                writer.flush();
                            }


                            friend1.removeFriend(friend1, friend);
                            friend1.blockUser(friend);
                            boolean blocked = friend1.isBlocked(friend);
                            writer.println(FRIENDS_BLOCK + ";" + blocked);
                            writer.flush();
                        }

                    } else if (secondMessage.contains(FRIENDS_UNBLOCK)) {

                        synchronized (gatekeeper) {
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


                            friend1.loadBlocked();
                            if (currentUser == null || !Friends.getBlockedList().contains(friend)) {
                                writer.write(FRIENDS_UNBLOCK + ";" + "false");
                                writer.println();
                                writer.flush();
                            }


                            friend1.addFriend(friend);
                            friend1.unblockUser(friend1, friend);
                            boolean unblocked = !friend1.isBlocked(friend);
                            writer.println(FRIENDS_UNBLOCK + ";" + "true");
                            writer.flush();
                        }

                    } else if (secondMessage.contains(TEXT_ALL_FRIENDS)) {

                        synchronized (gatekeeper) {
                            System.out.println("ALL FRIENDS");
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
                            writer.println(TEXT_ALL_FRIENDS + ";" + "true");
                            writer.flush();
                        }


                    } else if (secondMessage.contains(TEXT_ALL_USERS)) {

                        synchronized (gatekeeper) {
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
                            writer.println(TEXT_ALL_USERS + ";" + sent);
                            writer.flush();
                        }


                    } else if (secondMessage.contains(TEXT_SINGLE_FRIEND)) {
                        synchronized (gatekeeper) {
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


                            Messaging messaging = new Messaging(currentUser, friendFriends, content, date, isRead);
                            messaging.sendMessage(currentUser, friendFriends, content, date, isRead);
                            writer.println(TEXT_SINGLE_FRIEND + ";" + "true");
                            writer.flush();
                        }

                    } else if (secondMessage.contains(MESSAGE_ALL_FRIENDS)) {
                        synchronized (gatekeeper) {
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

                                writer.println(MESSAGE_ALL_FRIENDS + ";" + "sent");
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (secondMessage.contains(MESSAGE_ALL_USERS)) {
                        synchronized (gatekeeper) {
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
                    } else if (secondMessage.contains(MESSAGE_SINGLE_FRIEND)) {
                        synchronized (gatekeeper) {
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

                        synchronized (gatekeeper) {
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
                                writer.println(DELETE_SINGLE_FRIEND + ";false");
                                writer.flush();
                                return;
                            }

                            Friends friendFriends = new Friends(friendUser);

                            Messaging messageTemp = new Messaging(currentUser, friendFriends, content, date, isRead);
                            messageTemp.loadMessages(currentUser);


                            boolean messageDeleted = true;
                            messageTemp.deleteMessage(currentUser, friendFriends, content, date, isRead);


                            if (messageDeleted) {
                                writer.println(DELETE_SINGLE_FRIEND + ";true");
                            } else {
                                writer.println(DELETE_SINGLE_FRIEND + ";false");
                            }
                            writer.flush();
                        }


                    } else if (secondMessage.contains(DELETE_ALL_USERS)) {

                        synchronized (gatekeeper) {
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
                            messageTemp.loadAllUsersMessages(currentUser);


                            boolean messageDeleted = true;
                            messageTemp.deleteUsersMessage(currentUser, content, date, isRead);


                            if (messageDeleted) {
                                writer.println(DELETE_ALL_USERS + ";true");
                            } else {
                                writer.println(DELETE_ALL_USERS + ";false");
                            }
                            writer.flush();

                        }

                    } else if (secondMessage.contains(DELETE_ALL_FRIENDS)) {

                        synchronized (gatekeeper) {
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
                            messageTemp.loadAllFriendMessages(currentUser);

                            boolean messageDeleted = true;
                            messageTemp.deleteFriendsMessage(currentUser, content, date, isRead);


                            if (messageDeleted) {
                                writer.println(DELETE_ALL_FRIENDS + ";true");
                            } else {
                                writer.println(DELETE_ALL_FRIENDS + ";false");
                            }
                            writer.flush();
                        }

                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
