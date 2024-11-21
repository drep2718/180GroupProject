import java.util.ArrayList;

/**
 * Team Project -- Friends interface
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 *
 * @version November 17, 2024
 *
 */

public interface FriendsInterface {
    void addFriend(User newFriend);

    void removeFriend(Friends main, User userToRemove);

    void blockUser(User userToBlock);

    void unblockUser(Friends main, User userToUnblock);

    boolean isBlocked(User otherUser);

    public ArrayList<User> viewFriends(User user);

    public ArrayList<User> viewBlocked(User user);

    public void rewriteFriends();

    public void rewriteBlocked();

    public void saveFriendsToFile(User friend);

    public void saveBlockedToFile(User blocked);

    String toString();

    public void loadFriends();

    public void loadBlocked();

    User getUser();

    static ArrayList<User> getFriendsList() {
        return new ArrayList<>();
    }

    public static ArrayList<User> getBlockedList() {
        return new ArrayList<>();
    }

    static void setBlockedList(ArrayList<User> blockedList) {
        Friends.setBlockedList(blockedList);
    }

    public static void setFriendsList(ArrayList<User> friendsList) {
        Friends.setFriendsList(friendsList);
    }
}
