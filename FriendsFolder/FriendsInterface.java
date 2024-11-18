import java.util.ArrayList;

/**
 * Team Project -- Friends Interface
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

public interface FriendsInterface {
    void addFriend(User newFriend);

    void removeFriend(User userToRemove);

    void blockUser(User userToBlock);

    void unblockUser(User userToUnblock);

    boolean isFriend(User otherUser);

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

    public void sentMessage(Messaging message);

    public void removeSentMessage(Messaging message);

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
