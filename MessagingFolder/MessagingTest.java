import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessagingTest {
    private Friends sender;
    private Friends receiver;
    private User userSender;
    private User userReceiver;
    private String date;
    private Messaging message;
    private ArrayList<Messaging> messageHistory;


    @BeforeEach
    public void setUpMessages() {
        userSender = new User("senderUsername", "password123", "senderBio");
        userReceiver = new User("receiverUsername", "password456", "receiverBio");
        sender = new Friends(userSender);
        receiver = new Friends(userReceiver);
        date = "11-03-2024";

        message = new Messaging(sender, receiver, null, date, true);

        messageHistory = new ArrayList<>();
        messageHistory.add(message);
    }

    @Test
    void testGetSender() {
        assertEquals(sender, message.getSender());
    }

    @Test
    void testGetReceiver() {
        assertEquals(receiver, message.getReceiver());
    }

    @Test
    void testGetContent() {
        assertNull(message.getContent());
    }

    @Test
    void testGetDate() {
        assertEquals(date, message.getDate());
    }

    @Test
    void testGetIsRead() {
        assertTrue(message.isRead());
    }

    @Test
    void testSendMessage() {
        Messaging messageToSend = new Messaging(sender, receiver, message, date, false);
        message.sendMessage(sender, userReceiver, messageToSend);

        assertTrue(messageHistory.contains(messageToSend));
    }

    @Test
    void testSendMessageToBlockedUser() {
        receiver.blockUser(userSender);
        Messaging messageToSend = new Messaging(sender, receiver, message, date, false);
        message.sendMessage(sender, userReceiver, messageToSend);

        assertFalse(messageHistory.contains(messageToSend));
    }

    @Test
    void testDeleteMessage() {
        Messaging messageToDelete = new Messaging(sender, receiver, message, date, false);
        messageHistory.add(messageToDelete);

        message.deleteMessage(sender, userReceiver, messageToDelete);
        assertFalse(messageHistory.contains(messageToDelete));
    }
    
    @Test
    void testReport() {
        String reportMessage = "test report message";
        message.report(userSender, userReceiver, reportMessage);

        try (BufferedReader br = new BufferedReader(new FileReader("Report.txt"))) {
            String line;
            boolean reportFound = false;

            while ((line = br.readLine()) != null) {
                if (line.contains(reportMessage) && line.contains(userSender.getUsername()) && line.contains(userReceiver.getUsername())) {
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
    void testDeleteConversation() {
        Messaging messageOne = new Messaging(sender, receiver, message, date, false);
        Messaging messageTwo = new Messaging(receiver, sender, message, date, false);
        messageHistory.add(messageOne);
        messageHistory.add(messageTwo);
        
        message.deleteConversation(userSender, userReceiver);
        
        assertFalse(messageHistory.contains(messageOne));
        assertFalse(messageHistory.contains(messageTwo));
    }
}
