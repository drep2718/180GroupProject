import java.util.ArrayList;

public class Friends implements FriendsInterface{
    public User user;
    ArrayList<String> friendsList;
    ArrayList<String> blockedList;

    public void removeFriend(User userToRemove) {
        if (friendsList.contains(userToRemove)) {
            friendsList.remove(userToRemove);
        } else {
            System.out.println("Friend not found");
        }
    }
    public boolean isFriend(User otherUser) {
        return friendsList.contains(otherUser);
    }
    
    public void blockUser (User UserToBlock) {
        if (!blockedList.contains(UserToBlock)) {
            blockedList.add(UserToBlock.getUsername());
        } else {
            System.out.println("User is already blocked");
        }
    }

    public void unblockUser (User userToUnblock) { // does unblocking mean adding to friendsLost
        if (blockedList.contains(userToUnblock)) {
            blockedList.remove(userToUnblock);
        }  else {
            System.out.println("Blocked User not found");
        }
    }

    // Adding friends & viewing
    public void addFriend(User newFriend) {
        if (!friendsList.contains(newFriend.getUsername())) {
            friendsList.add(newFriend.getUsername());
        }
    }

    public ArrayList<String> viewFriends(User user) {
        return friendsList;
    }
    
    public ArrayList<String> viewBlocked (User user) {
        return blockedList;
    }
    
    public boolean isBlocked (User otherUser) {
        if (blockedList.contains(otherUser)) {
            return true;
        } else {
            return false;
        }
    }

    // Viewing blocked users
    public ArrayList<String> viewBlockedUsers(User user) {
        return blockedList;
    }
    
    public User getUser (User user) {
        return user;
    }

}
