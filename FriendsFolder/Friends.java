import java.io.*;
import java.util.ArrayList;

/**
 * Team Project -- Friends Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

public class Friends implements FriendsInterface {
    private User waypointUser;
    private static ArrayList<User> friendsList = new ArrayList<>();
    private static ArrayList<User> blockedList = new ArrayList<>();

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


    public void removeFriend(User userToRemove) {

        if (friendsList.contains(userToRemove)) {
            friendsList.remove(userToRemove);
            rewriteFriends();
        } else {
            System.out.println("Friend not found");
        }
    }

    public void rewriteFriends() {
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

    public void rewriteBlocked() {
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

    public void saveFriendsToFile(User friend) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Friends.txt", true))) {
            bfw.write(waypointUser.getUsername() + ":" + friend.getUsername());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBlockedToFile(User blocked) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", true))) {
            bfw.write(waypointUser.getUsername() + ":" + blocked.getUsername());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isFriend(User otherUser) {
        return friendsList.contains(otherUser);
    }


    public void blockUser(User userToBlock) {
        if (!blockedList.contains(userToBlock)) {
            blockedList.add(userToBlock);
            friendsList.remove(userToBlock);
            saveBlockedToFile(userToBlock);
        } else {
            System.out.println("User is already blocked");
        }
    }

    public void unblockUser(User userToUnblock) {
        if (blockedList.contains(userToUnblock)) {
            blockedList.remove(userToUnblock);
            rewriteBlocked();
        } else {
            System.out.println("Blocked User not found");
        }
    }

    // Adding friends & viewing
    public void addFriend(User newFriend) {
        if (!friendsList.contains(newFriend)) {
            friendsList.add(newFriend);
            saveFriendsToFile(newFriend);
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


    public void loadBlocked() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Friends otherFriends = (Friends) obj;

        if (this.waypointUser == null) {
            return otherFriends.waypointUser == null;
        }

        return this.waypointUser.equals(otherFriends.waypointUser);
    }


}
