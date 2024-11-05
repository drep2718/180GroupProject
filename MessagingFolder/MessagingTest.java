import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessagingTest {
    private User sender = new User("Username");
    User user = new User("Username");
    Friends receiver = new Friends(user);
    private User userSender;
    private User userReceiver;
    private String date;
    private String message = "Hello";
    private ArrayList<Messaging> messageHistory = new ArrayList<>();

    @Test
    void testSendMessage() {

        receiver.addFriend(sender);
        Messaging messages = new Messaging(sender, receiver, message, date, false);
        messages.sendMessage(sender, receiver, message, date, false);
        messageHistory = Messaging.getMessageHistory();
        String answer = "Username:Hello:Username";
        assertEquals(answer, messageHistory.get(0).toString());

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
        Messaging messageToSend = new Messaging(sender, receiver, message, date, false);
        messageToSend.sendMessage(sender, receiver, message, date, false);
        messageHistory = Messaging.getMessageHistory();
        String answer = "Username:Hello:Username";
        assertEquals(answer, messageHistory.get(0).toString());

        messageToSend.deleteMessage(sender, receiver, message, date, false);
        messageHistory = Messaging.getMessageHistory();
        assertTrue(messageHistory.isEmpty());


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
        Messaging messageOne = new Messaging(sender, receiver, message, date, false);
        Messaging messageTwo = new Messaging(sender, receiver, message, date, false);
        messageHistory.add(messageOne);
        messageHistory.add(messageTwo);

        messageOne.deleteConversation(userSender, userReceiver);

        assertFalse(messageHistory.contains(messageOne));
        assertFalse(messageHistory.contains(messageTwo));
    }


    @Test
    void rewriteMessages() {
    }

    @Test
    void testToString() {
    }

    @Test
    void saveToFile() {
    }

    @Test
    void sendMessage() {
    }

    @Test
    void sendAllFriendsMessage() {
    }

    @Test
    void sendAllUsersMessage() {
    }

    @Test
    void deleteMessage() {
    }

    @Test
    void report() {
    }

    @Test
    void deleteConversation() {
    }

    @Test
    void deleteAllFriendsConversation() {
    }

    @Test
    void deleteAllUsersConversation() {
    }
}