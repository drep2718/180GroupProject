import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface PhotoMessagingInterface {
    ArrayList<PhotoMessaging> getMessageHistory();

    void setMessageHistory(ArrayList<PhotoMessaging> photoMessageHistory);

    User getSender();

    Friends getReceiver();

    BufferedImage getImageContent();

    String getDate();

    Boolean getIsRead();

    String getMessageType();

    void setMessageType(String messageType);

    void rewritePhotoMessages();

    String toString();

    void saveToFile();

    void sendPhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead);

    void sendAllFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead);

    void sendAllUsersPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead);

    void deletePhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead);

    void deleteFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead);

    void deleteAllPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead);

    void report(User sender, BufferedImage imageContent);

    void deleteConversation(User userOne, User userTwo);

    void deleteAllFriendsConversation(User userOne);

    void deleteAllUsersConversation(User userOne);
}
