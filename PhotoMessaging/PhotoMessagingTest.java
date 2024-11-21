import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Team Project -- Photomessaging Test
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

class PhotoMessagingTest implements PhotoMessagingTestInterface{
    private User sender;
    private User receiverUser;
    private Friends receiver;
    private BufferedImage imageContent;
    private String date;
    private PhotoMessaging photoMessaging;
    private ArrayList<PhotoMessaging> photoMessageHistory;

    @BeforeEach
    public void setUp() {
        sender = new User("testSender", "password", "Test User");
        receiverUser = new User("testReceiver", "password", "Receiver User");
        receiver = new Friends(sender);
        receiver.addFriend(receiverUser);
        imageContent = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        date = "2024-11-17";
        photoMessaging = new PhotoMessaging(sender, receiver, imageContent, date, false);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        photoMessageHistory.clear();
    }


    @Test
    public void testSendAllFriendsPhotoMessage() {
        photoMessaging.sendAllFriendsPhotoMessage(sender, imageContent, date, false);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertEquals(1, photoMessageHistory.size());
        assertTrue(photoMessageHistory.get(0).getMessageType().equals("AllFriends"));
    }

    @Test
    public void testSendAllUsersPhotoMessage() {
        photoMessaging.sendAllUsersPhotoMessage(sender, imageContent, date, false);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertEquals(1, photoMessageHistory.size());
        assertTrue(photoMessageHistory.get(0).getMessageType().equals("AllUsers"));
    }

    @Test
    public void testDeletePhotoMessage() {
        photoMessaging.sendPhotoMessage(sender, receiver, imageContent, date, false);
        photoMessaging.deletePhotoMessage(sender, receiver, imageContent, date, false);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertTrue(photoMessageHistory.isEmpty());
    }

    @Test
    public void testDeleteFriendsPhotoMessage() {
        photoMessaging.sendAllFriendsPhotoMessage(sender, imageContent, date, false);
        photoMessaging.deleteFriendsPhotoMessage(sender, imageContent, date, false);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertTrue(photoMessageHistory.isEmpty());
    }

    @Test
    public void testDeleteAllPhotoMessage() {
        photoMessaging.sendAllUsersPhotoMessage(sender, imageContent, date, false);
        photoMessaging.deleteAllPhotoMessage(sender, imageContent, date, false);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertTrue(photoMessageHistory.isEmpty());
    }

    @Test
    public void testReportPhotoMessage() {
        File reportFile = new File("PhotoReport.txt");

        User sender = new User("testUser");
        BufferedImage imageContent = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        PhotoMessaging message = new PhotoMessaging(sender, null, imageContent, "2024-11-17", false);
        message.report(sender, imageContent);

        assertTrue(reportFile.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line = reader.readLine();
            assertEquals("Reported User: testUser", line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    @Test
    public void testDeleteConversation() {
        photoMessaging.sendPhotoMessage(sender, receiver, imageContent, date, false);
        photoMessaging.deleteConversation(sender, receiverUser);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertTrue(photoMessageHistory.isEmpty());
    }

    @Test
    public void testDeleteAllFriendsConversation() {
        photoMessaging.sendAllFriendsPhotoMessage(sender, imageContent, date, false);
        photoMessaging.deleteAllFriendsConversation(sender);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertTrue(photoMessageHistory.isEmpty());
    }

    @Test
    public void testDeleteAllUsersConversation() {
        photoMessaging.sendAllUsersPhotoMessage(sender, imageContent, date, false);
        photoMessaging.deleteAllUsersConversation(sender);
        photoMessageHistory = photoMessaging.getPhotoMessageHistory();
        assertTrue(photoMessageHistory.isEmpty());
    }
}
