import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessagingTest {
    private User sender;
    private Friends receiver;
    private User userSender;
    private User userReceiver;
    private String date;
    private String message;
    private ArrayList<Messaging> messageHistory;

    @Test
    void testSendMessage() {

//        public void sendMessage(User sender, Friends receiver, String content, String date, Boolean isRead) {
//            if (receiver.isBlocked(sender)) {
//                System.out.println("Cannot send message because account you have been blocked.");
//            }
//
//            if (!receiver.isFriend(sender)) {
//                System.out.println("Cannot send message because you are not friends.");
//            }
//
//            Messaging message = new Messaging(sender, receiver, content, date, isRead);
//            messageHistory.add(message);
//
//            try(BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + ".txt",true))) {
//                bfw.write(message.toString());
//                bfw.newLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("Delivered");
//        }


        Messaging messageToSend = new Messaging(sender, receiver,message, date, false);
        messageToSend.sendMessage(sender, receiver, message, date, false);

        assertTrue(messageHistory.contains(messageToSend));
    }

    @Test
    void testSendMessageToBlockedUser() {
        receiver.blockUser(userSender);
        Messaging messageToSend = new Messaging(sender, receiver, message, date, false);
        messageToSend.sendMessage(sender, receiver, message, date, false);

        assertFalse(messageHistory.contains(messageToSend));
    }

    @Test
    void testDeleteMessage() {
        Messaging messageToDelete = new Messaging(sender, receiver, message, date, false);
        messageHistory.add(messageToDelete);

        messageToDelete.deleteMessage(receiver, sender, message,date,false);
        assertFalse(messageHistory.contains(messageToDelete));
    }

    @Test
    void testReport() {
        String reportMessage = "test report message";
        Messaging messageToReport = new Messaging(sender, receiver, message, date, false);
        messageToReport.report(sender, message);

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
    void testSaveToFile() {
        Messaging messaging = new Messaging(sender, receiver, message, date, false);
        messaging.saveToFile();

        try (BufferedReader br = new BufferedReader(new FileReader("senderUsername.txt"))) {
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
