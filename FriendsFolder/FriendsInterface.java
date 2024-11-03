import java.util.ArrayList;

public interface FriendsInterface {
    void addFriend(User newFriend);
    void removeFriend(User userToRemove);
    void blockUser(User userToBlock);
    void unblockUser(User userToUnblock);
    boolean isFriend(User otherUser);
    boolean isBlocked(User otherUser);
    User getUser(User user);
    public ArrayList<User> viewFriends(User user);
    public ArrayList<User> viewBlocked(User user);
    public void rewriteFriends();
    public void rewriteBlocked();
    public void saveFriendsToFile(User friend);
    public void saveBlockedToFile(User blocked);
    String toString();


}
