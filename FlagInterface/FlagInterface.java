public interface FlagInterface {

    // Login;username;password
    String LOGIN = "Login";

    // create;username;password
    String CREATE = "Create";

    String LOGOUT = "Login";

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

    String PHOTO_SINGLE_FRIEND = "PhotoSingleFriend";

    String PHOTO_ALL_FRIENDS = "PhotoAllFriends";

    String PHOTO_ALL_USERS = "PhotoAllUsers";

    // friend username:content
    String DELETE_ALL_FRIENDS = "DeleteMessageToAllFriends";

    // friend username: content
    String DELETE_ALL_USERS = "DeleteMessageToAllUsers";

    String DELETE_SINGLE_FRIEND = "DeleteSingleFriendMessage";

}
