import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessagingTest {
    private User sender = new User("Username");
    private User sender1 = new User("Username1");
    private User sender2 = new User("Username2");
    User user = new User("Username");
    Friends receiver = new Friends(user);
    private User userSender;
    private User userReceiver;
    private String date;
    private String message = "Hello";
    private ArrayList<Messaging> messageHistory = new ArrayList<>();
    private ArrayList<Messaging> messageHistoryFriends = new ArrayList<>();
    private ArrayList<Messaging> messageHistoryUsers = new ArrayList<>();
    ArrayList<User> friends = new ArrayList<>();
    ArrayList<User> allUsers = new ArrayList<>();

    @Test
    void testSendMessage() {


        receiver.addFriend(sender);
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + ".txt", false))) {
            bfw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Messaging messages = new Messaging(sender, receiver, message, date, false);
        messages.sendMessage(sender, receiver, message, date, false);
        messageHistory = Messaging.getMessageHistory();
        String answer = "Username:Hello:Username";
        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + ".txt"))) {
            String line = br.readLine();
            while (line != null) {
                assertEquals(answer, line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    void sendAllFriendsMessage() {

        receiver.addFriend(sender);
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "AllFriends.txt", false))) {
            bfw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Messaging messages = new Messaging(sender, message, friends, date, false, "AllFriends");
        messages.sendAllFriendsMessage(sender1, message, date, false);
        messageHistoryFriends = Messaging.getMessageHistory();
        String answer = "Username1:Hello";
        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + "AllFriends.txt"))) {
            String line = br.readLine();
            while (line != null) {
                assertEquals(answer, line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    @Test
    void sendAllUsersMessage() {

        receiver.addFriend(sender);
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "AllUsers.txt", false))) {
            bfw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Messaging messages = new Messaging(sender, message, friends, date, false, "AllUsers");
        messages.sendAllUsersMessage(sender2, message, date, false);
        messageHistoryUsers = Messaging.getMessageHistory();
        String answer = "Username2:Hello";
        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + "AllUsers.txt"))) {
            String line = br.readLine();
            while (line != null) {
                assertEquals(answer, line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();


        }


    }

    @Test
    void testSendMessageToBlockedUser() {

        receiver.blockUser(sender);
        Messaging messages = new Messaging(sender, receiver, message, date, false);
        messages.sendMessage(sender, receiver, message, date, false);
    }

    @Test
    void testDeleteMessage() {
        receiver.addFriend(sender);


        Messaging messageSender = new Messaging(sender, receiver, message, date, false);
        messageSender.sendMessage(sender, receiver, message, date, false);
        messageHistory = Messaging.getMessageHistory();
        String answer = "Username:Hello:Username";
        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + ".txt"))) {
            String lastLine = "";
            String line = br.readLine();
            while (line != null) {
                lastLine = line;
                line = br.readLine();
            }
            assertEquals(answer, lastLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageHistory = Messaging.getMessageHistory();
        for (Messaging messaging : messageHistory) {
            if (messaging == messageSender) {
                messageSender.deleteMessage(sender, receiver, message, date, false);
                assertTrue(messageHistory.isEmpty());

            }
        }
    }

    @Test
    void deleteFriendsMessage() {

        receiver.addFriend(sender);


        Messaging messages = new Messaging(sender, message, friends, date, false, "AllFriends");
        messages.sendAllFriendsMessage(sender1, message, date, false);
        messageHistoryFriends = Messaging.getMessageHistory();
        String answer = "Username1:Hello";
        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + "AllFriends.txt"))) {
            String line = br.readLine();
            while (line != null) {
                assertEquals(answer, line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();


        }

        messageHistoryFriends = Messaging.getMessageHistory();
        for (Messaging messaging : messageHistoryFriends) {
            if (messaging == messages) {
                messages.deleteFriendsMessage(sender1, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
        }

    }

    @Test
    void deleteAllMessage() {
        receiver.addFriend(sender);


        Messaging messages = new Messaging(sender, message, friends, date, false, "AllUsers");
        messages.sendAllUsersMessage(sender2, message, date, false);
        messageHistoryUsers = Messaging.getMessageHistory();
        String answer = "Username2:Hello";
        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + "AllUsers.txt"))) {
            String line = br.readLine();
            while (line != null) {
                assertEquals(answer, line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();


        }


        messages.deleteAllMessage(sender2, message, date, false);
        messageHistoryUsers = Messaging.getMessageHistory();
        for (Messaging messaging : messageHistoryUsers) {
            if (messaging == messages) {
                messages.deleteAllMessage(sender2, message, date, false);
                assertTrue(messageHistoryUsers.isEmpty());
            }
        }
    }

    @Test
    void testReport() {

        String reportMessage = "test report message";
        userSender = new User("Username");
        userReceiver = new User("Username");
        Messaging messageToReport = new Messaging(sender, receiver, message, date, false);
        messageToReport.report(sender, message);

        try (BufferedReader br = new BufferedReader(new FileReader("Report.txt"))) {
            String line;
            boolean reportFound = false;

            while ((line = br.readLine()) != null) {
                if (line.contains(messageToReport.getContent()) && line.contains(userSender.getUsername()) && line.contains(userReceiver.getUsername())) {
                    reportFound = true;
                    break;
                }
            }
            assertTrue(reportFound);
        } catch (IOException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }


    @Test
    void testSaveToFile() {
        Messaging messaging = new Messaging(sender, receiver, message, date, false);
        messaging.saveToFile();

        try (BufferedReader br = new BufferedReader(new FileReader(sender.getUsername() + ".txt"))) {
            String line;
            boolean convoFound = false;

            while ((line = br.readLine()) != null) {
                if (line.contains(message.toString())) {
                    convoFound = true;
                    break;
                }
            }
            assertTrue(convoFound);
        } catch (IOException e) {
            e.printStackTrace();
            assertFalse(true);
        }
    }

    @Test
    void testDeleteConversation() {
        userSender = new User("Username1");
        userReceiver = new User("Username2");

        Messaging messageOne = new Messaging(sender, receiver, message, date, false);
        Messaging messageTwo = new Messaging(sender, receiver, message, date, false);

        messageHistory.add(messageOne);
        messageHistory.add(messageTwo);

        messageHistory = Messaging.getMessageHistory();
        for (Messaging messaging : messageHistory) {
            if (messaging == messageOne) {
                messageOne.deleteFriendsMessage(sender, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
            if (messaging == messageTwo) {
                messageTwo.deleteFriendsMessage(sender, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
        }
    }

    @Test
    void testDeleteAllFriendsConversation() {
        userSender = new User("Username1");
        userReceiver = new User("Username2");

        Messaging messageOne = new Messaging(sender, receiver, message, date, false);
        Messaging messageTwo = new Messaging(sender, receiver, message, date, false);

        messageHistory.add(messageOne);
        messageHistory.add(messageTwo);

        messageHistory = Messaging.getMessageHistory();
        for (Messaging messaging : messageHistory) {
            if (messaging == messageOne) {
                messageOne.deleteFriendsMessage(sender, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
            if (messaging == messageTwo) {
                messageTwo.deleteFriendsMessage(sender, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
        }


    }

    @Test
    void testDeleteAllUsersConversation() {
        userSender = new User("Username1");
        userReceiver = new User("Username2");

        Messaging messageOne = new Messaging(sender, receiver, message, date, false);
        Messaging messageTwo = new Messaging(sender, receiver, message, date, false);

        messageHistory.add(messageOne);
        messageHistory.add(messageTwo);


        messageHistory = Messaging.getMessageHistory();
        for (Messaging messaging : messageHistory) {
            if (messaging == messageOne) {
                messageOne.deleteFriendsMessage(sender, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
            if (messaging == messageTwo) {
                messageTwo.deleteFriendsMessage(sender, message, date, false);
                assertTrue(messageHistoryFriends.isEmpty());
            }
        }
    }


    @Test
    void rewriteMessages() {
    }

    @Test
    void testToString() {
    }


}
