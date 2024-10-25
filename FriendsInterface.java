import java.util.ArrayList;

public interface FriendsInterface {
    void addFriend(User newFriend);
    void removeFriend(User userToRemove);
    void blockUser(User userToBlock);
    void unblockUser(User userToUnblock);
    boolean isFriend(User otherUser);
    boolean isBlocked(User otherUser);
    User getUser(User user);
    public ArrayList<String> viewFriends(User user);
    public ArrayList<String> viewBlocked(User user);
    

}
