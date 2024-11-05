import java.util.ArrayList;

public interface MessagingInterface {
    String getMessageType();

    void setMessageType(String messageType);

    ArrayList<Messaging> getMessageHistory();

    void setMessageHistory(ArrayList<Messaging> messageHistory);

    void rewriteMessages();

    String toString();

    User getSender();

    Friends getReceiver();

    String getContent();

    String getDate();

    boolean isRead();

    Boolean getIsRead();

    void saveToFile();

    void sendMessage(User sender, Friends receiver, String content, String date, Boolean isRead);

    void sendAllFriendsMessage(User sender, String content, String date, Boolean isRead);

    void sendAllUsersMessage(User sender, String content, String date, Boolean isRead);

    void deleteMessage(Friends sender, User receiver, String content, String date, Boolean isRead);

    void deleteFriendsPhotoMessage(User sender, String content, String date, Boolean isRead);

    void deleteAllPhotoMessage(User sender, String content, String date, Boolean isRead);

    void report(User sender, String content);

    void deleteConversation(User userOne, User userTwo);

    void deleteAllFriendsConversation(User userOne);

    void deleteAllUsersConversation(User userOne);
}
