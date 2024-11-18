public interface FlagInterface {

    /**
 * Team Project -- Flag Interface
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 *
 * @version November 17, 2024
 *
 */

    // Login;username;password
    String LOGIN = "Login";

    // create;username;password
    String CREATE = "Create";

    //FriendsAdd; friendName
    String FRIENDS_ADD = "friendsAdd";

    // FriendsRemove; friend name
    String FRIENDS_REMOVE = "friendsRemove";

    //Block; friend name
    String FRIENDS_BLOCK = "friendsBlocked";

    // Unblock; friend name
    String FRIENDS_UNBLOCK = "friendsUnblocked";

    // TextAllFriends; text to send
    String TEXT_ALL_FRIENDS = "TextAllFriends";

    // TextAllUsers; text to send
    String TEXT_ALL_USERS = "TextAllUsers";

    // TextSingleFriend; friend username; text to send
    String TEXT_SINGLE_FRIEND = "TextSingleFriend";

    // MessageAllFriends; text to send
    String MESSAGE_ALL_FRIENDS = "MessageAllFriends";

    // MessageAllUsers; text to send
    String MESSAGE_ALL_USERS = "MessageAllUsers";

    // MessageSingleFriend; friend username; text to send
    String MESSAGE_SINGLE_FRIEND = "MessageSingleFriend";
    
    // friend username:content
    String DELETE_ALL_FRIENDS = "DeleteMessageToAllFriends";
    
    // friend username: content
    String DELETE_ALL_USERS = "DeleteMessageToAllUsers";

    String DELETE_SINGLE_FRIEND = "DeleteSingleFriendMessage";

}
