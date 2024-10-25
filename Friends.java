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

     public void unblockUser (User userToUnblock) { // does unblocking mean adding to friendsLost
        if (blockedList.contains(userToUnblock)) {
            blockedList.remove(userToUnblock);
        }  else {
            System.out.println("Blocked User not found");
        }
    }
    
}

