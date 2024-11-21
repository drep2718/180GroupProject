import java.io.*;
import java.util.ArrayList;
/**
 * Team Project -- Friends Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 *
 * @version November 17, 2024
 *
 */

public class Friends implements FriendsInterface {
    private User waypointUser;
    private static ArrayList<User> friendsList = new ArrayList<>();
    private static ArrayList<User> blockedList = new ArrayList<>();
    private static final Object gatekeeper = new Object();

    public Friends() {
    }

    public static ArrayList<User> getFriendsList() {
        return friendsList;
    }

    public static ArrayList<User> getBlockedList() {
        return blockedList;
    }

    public static void setFriendsList(ArrayList<User> friendsList) {
        Friends.friendsList = friendsList;
    }

    public static void setBlockedList(ArrayList<User> blockedList) {
        Friends.blockedList = blockedList;
    }

    public Friends(User waypointUser) {
        this.waypointUser = waypointUser;
    }

    public String toString() {
        return waypointUser.getUsername();
    }


    public void removeFriend(Friends main, User userToRemove) {

        ArrayList<String> friendsText = new ArrayList<>();

        synchronized (gatekeeper) {
            try (BufferedReader bfr = new BufferedReader(new FileReader("Friends.txt"))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    friendsText.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error loading friends: " + e.getMessage());
                e.printStackTrace();
            }
        }

        ArrayList<String> friendsText1 = new ArrayList<>();
        for (String name : friendsText) {
            String[] parts = name.split(":");
            String mainFriend = main.getUser().getUsername();
            String toRemove = userToRemove.getUsername();

            if (parts[0].equals(mainFriend) && parts[1].equals(toRemove)) {
                friendsText1.add(name);
                synchronized (gatekeeper) {
                    friendsList.remove(userToRemove);
                }
            }
        }
        synchronized (gatekeeper) {
            friendsText.removeAll(friendsText1);
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Friends.txt", false))) {

                for (String name : friendsText) {
                    bfw.write(name);
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void rewriteFriends() {
        synchronized (gatekeeper) {

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Friends.txt", false))) {
                for (int i = 0; i < friendsList.size(); i++) {
                    User friend = friendsList.get(i);
                    bfw.write(waypointUser.getUsername() + ":" + friend.getUsername());
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rewriteBlocked() {
        synchronized (gatekeeper) {

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", false))) {
                for (int i = 0; i < blockedList.size(); i++) {
                    User blocked = blockedList.get(i);
                    bfw.write(waypointUser.getUsername() + ":" + blocked.getUsername());
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFriendsToFile(User friend) {
        synchronized (gatekeeper) {

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Friends.txt", true))) {
                bfw.write(waypointUser.getUsername() + ":" + friend.getUsername());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveBlockedToFile(User blocked) {
        synchronized (gatekeeper) {

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", true))) {
                bfw.write(waypointUser.getUsername() + ":" + blocked.getUsername());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isFriend(User user) {
        return friendsList.contains(user);
    }

    public void blockUser(User userToBlock) {
        synchronized (gatekeeper) {
            ;
            loadBlocked();
            if (!blockedList.contains(userToBlock)) {
                blockedList.add(userToBlock);
                friendsList.remove(userToBlock);
                saveBlockedToFile(userToBlock);
            } else {
                System.out.println("User is already blocked");
            }
        }
    }

    public void unblockUser(Friends main, User userToUnblock) {
        ArrayList<String> blockedText = new ArrayList<>();
        synchronized (gatekeeper) {

            try (BufferedReader bfr = new BufferedReader(new FileReader("Blocked.txt"))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    blockedText.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error loading friends: " + e.getMessage());
                e.printStackTrace();
            }
        }

        ArrayList<String> blockedText1 = new ArrayList<>();
        for (String name : blockedText) {
            String[] parts = name.split(":");
            String mainFriend = main.getUser().getUsername();
            String toRemove = userToUnblock.getUsername();

            if (parts[0].equals(mainFriend) && parts[1].equals(toRemove)) {
                blockedText1.add(name);
                synchronized (gatekeeper) {
                    blockedList.remove(userToUnblock);
                }
            }
        }
        synchronized (gatekeeper) {

            blockedText.removeAll(blockedText1);

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", false))) {

                for (String name : blockedText) {
                    bfw.write(name);
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // Adding friends & viewing
    public void addFriend(User newFriend) {
        synchronized (gatekeeper) {

            loadFriends();
            if (!friendsList.contains(newFriend)) {
                friendsList.add(newFriend);
                saveFriendsToFile(newFriend);
            } else {
                System.out.println("Already Friend");
            }
        }
    }

    public ArrayList<User> viewFriends(User user) {
        return friendsList;
    }

    public ArrayList<User> viewBlocked(User user) {
        return blockedList;
    }

    public boolean isBlocked(User otherUser) {
        if (blockedList.contains(otherUser)) {
            return true;
        } else {
            return false;
        }
    }

    public User getUser() {
        return waypointUser;
    }

    public void loadFriends() {
        synchronized (gatekeeper) {

            friendsList.clear();

            try (BufferedReader bfr = new BufferedReader(new FileReader("Friends.txt"))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 2) {
                        String waypoint = parts[0];
                        String friendUsername = parts[1];

                        if (waypoint.equals(waypointUser.getUsername())) {
                            User friend = new User(friendUsername);
                            friendsList.add(friend);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading friends: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public void loadBlocked() {
        synchronized (gatekeeper) {

            blockedList.clear();

            try (BufferedReader bfr = new BufferedReader(new FileReader("Blocked.txt"))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 2) {
                        String waypoint = parts[0];
                        String blockedUsername = parts[1];

                        if (waypoint.equals(waypointUser.getUsername())) {
                            User blocked = new User(blockedUsername);
                            blockedList.add(blocked);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading blocked users: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


}
