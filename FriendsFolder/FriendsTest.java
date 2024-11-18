import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Team Project -- Friends Test
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

class FriendsTest {

    @Test
    void removeFriend() {
        User user = new User("Username");
        Friends friend = new Friends(user);
        User newUser = new User("UsernameNew");
        friend.addFriend(newUser);

        assertTrue(friend.isFriend(newUser));

        friend.removeFriend(newUser);

        assertFalse(friend.isFriend(newUser));

    }

    @Test
    void rewriteFriends() {
        Friends.setFriendsList(new ArrayList<>());
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Friends.txt", false))) {
            bfw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new User("Aiden");
        Friends friend = new Friends(user);
        friend.addFriend(user);
        User user1 = new User("Anthony");
        Friends friend1 = new Friends(user1);
        friend1.rewriteFriends();


        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("Friends.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }

        } catch (IOException e) {
            e.getMessage();
        }


        assertEquals(1, lines.size());
        assertEquals(friend1.toString() + ":" + friend.toString(), lines.get(0));


    }

    @Test
    void rewriteBlocked() {
        Friends.setBlockedList(new ArrayList<>());
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Blocked.txt", false))) {
            bfw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new User("Aiden");
        Friends friend = new Friends(user);
        friend.blockUser(user);
        User user1 = new User("Anthony");
        Friends friend1 = new Friends(user1);
        friend1.rewriteBlocked();


        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("Blocked.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }

        } catch (IOException e) {
            e.getMessage();
        }


        assertEquals(1, lines.size());
        assertEquals(friend1.toString() + ":" + friend.toString(), lines.get(0));

    }

    @Test
    void saveFriendsToFile() {

        User user = new User("Aiden");
        Friends friend = new Friends(user);
        User friendToAdd = new User("Friend");
        friend.saveFriendsToFile(friendToAdd);

        try (BufferedReader bfr = new BufferedReader(new FileReader("Friends.txt"))) {
            String lastLine = "";
            String line = bfr.readLine();
            while (line != null) {
                lastLine = line;
                line = bfr.readLine();
            }
            assertEquals("Aiden:Friend", lastLine);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Test
    void saveBlockedToFile() {

        User user = new User("Aiden");
        Friends blocked = new Friends(user);
        User friendToBlock = new User("Blocked");
        blocked.saveBlockedToFile(friendToBlock);

        try (BufferedReader bfr = new BufferedReader(new FileReader("Blocked.txt"))) {
            String lastLine = "";
            String line = bfr.readLine();
            while (line != null) {
                lastLine = line;
                line = bfr.readLine();
            }
            assertEquals("Aiden:Blocked", lastLine);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Test
    void isFriend() {
        User user = new User("Username");
        Friends friend = new Friends(user);
        User newUser = new User("UsernameNew");
        friend.addFriend(newUser);

        assertTrue(friend.isFriend(newUser));

    }

    @Test
    void blockUser() {

        User user = new User("Username");
        Friends blocked = new Friends(user);
        User newUserToBlock = new User("UsernameBlocked");
        blocked.blockUser(newUserToBlock);

        assertTrue(blocked.isBlocked(newUserToBlock));

    }

    @Test
    void unblockUser() {
        User user = new User("Username");
        Friends blocked = new Friends(user);
        User newUserToBlock = new User("UsernameBlocked");
        blocked.blockUser(newUserToBlock);
        assertTrue(blocked.isBlocked(newUserToBlock));
        blocked.unblockUser(newUserToBlock);
        assertFalse(blocked.isBlocked(newUserToBlock));

    }

    @Test
    void addFriend() {
        User user = new User("Username");
        Friends friend = new Friends(user);
        User newUser = new User("UsernameNew");
        friend.addFriend(newUser);
        assertTrue(friend.isFriend(newUser));
    }


    @Test
    void isBlocked() {
        User user = new User("Username");
        Friends blocked = new Friends(user);
        User newUserToBlock = new User("UsernameBlocked");
        blocked.blockUser(newUserToBlock);

        assertTrue(blocked.isBlocked(newUserToBlock));

    }

    @Test
    void sentMessage() {
    }

    @Test
    void removeSentMessage() {
    }
}
