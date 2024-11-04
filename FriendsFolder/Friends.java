import java.io.*;
import java.util.ArrayList;

public class Friends implements FriendsInterface {
    private User waypointUser;
    private ArrayList<User> friendsList;
    private ArrayList<User> blockedList;

    public Friends(User waypointUser) {
        this.waypointUser = waypointUser;
        this.friendsList = new ArrayList<>();
        this.blockedList = new ArrayList<>();
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
        try (BufferedReader bfr = new BufferedReader(new FileReader("Friends.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] parts = line.split(":");
                String waypoint = parts[0];
                String friendUsername = parts[1];

                if (waypoint.equals(waypointUser.getUsername())) {
                    User friend = new User(friendUsername);
                    friendsList.add(friend);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadBlocked() {
        try (BufferedReader bfr = new BufferedReader(new FileReader("Blocked.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] parts = line.split(" ");
                String waypoint = parts[0];
                String blockedUsername = parts[1];

                if (waypoint.equals(waypointUser.getUsername())) {
                    User friend = new User(blockedUsername);
                    friendsList.add(friend);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sentMessage(Messaging message) {
        messageHistory.add(message);
    }

    public void removeSentMessage(Messaging message) {
        messageHistory.remove(message);
    }

}
