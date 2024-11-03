import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Friends implements FriendsInterface {
    private User user;
    private ArrayList<User> friendsList;
    private ArrayList<User> blockedList;


    public Friends (User user ) {
        this.user = user;
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
                User user = friendsList.get(i);
                bfw.write(user.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteBlocked() {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", false))) {
            for (int i = 0; i < blockedList.size(); i++) {
                User user = blockedList.get(i);
                bfw.write(user.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFriendsToFile(User friend) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Friends.txt", true))) {
                bfw.write(friend.toString());
                bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBlockedToFile(User blocked) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", true))) {
            bfw.write(blocked.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return user.getUsername() + " ";
    }


    public boolean isFriend(User otherUser) {
        return friendsList.contains(otherUser);
    }

    public void blockUser (User userToBlock) {
        if (!blockedList.contains(userToBlock)) {
            blockedList.add(userToBlock);
            friendsList.remove(userToBlock);
            saveBlockedToFile(userToBlock);
        } else {
            System.out.println("User is already blocked");
        }
    }

    public void unblockUser (User userToUnblock) {
        if (blockedList.contains(userToUnblock)) {
            blockedList.remove(userToUnblock);
            rewriteBlocked();
        }  else {
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

    public ArrayList<User> viewBlocked (User user) {
        return blockedList;
    }

    public boolean isBlocked (User otherUser) {
        if (blockedList.contains(otherUser)) {
            return true;
        } else {
            return false;
        }
    }

    public User getUser (User user) {
        return user;
    }

}
