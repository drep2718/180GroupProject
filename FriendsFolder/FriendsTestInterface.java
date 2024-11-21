
/**
 * Team Project -- FriendsTest Interface
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

public interface FriendsTestInterface {

    void addFriend(User user);

    void addFriend();

    void removeFriend();

    void blockUser();

    void unblockUser();

    void isFriend();

    void isBlocked();

    void saveFriendsToFile();

    void saveBlockedToFile();

    void rewriteFriends();

    void rewriteBlocked();

}
