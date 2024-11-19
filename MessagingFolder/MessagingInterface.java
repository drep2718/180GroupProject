import java.util.ArrayList;

public interface MessagingInterface {

    User getSender();

    Friends getReceiver();

    String getContent();

    String getDate();

    Boolean getIsRead();

    String getMessageType();

    static ArrayList<Messaging> getMessageHistory() {
        return Messaging.getMessageHistory();
    }

    static void setMessageHistory(ArrayList<Messaging> messageHistory) {
        Messaging.setMessageHistory(messageHistory);
    }

    void setMessageType(String messageType);

    void rewriteMessages();

    void saveToFile();

    void sendMessage(User sender, Friends receiver, String content, String date, Boolean isRead);

    void sendAllFriendsMessage(User sender, String content, String date, Boolean isRead);

    void sendAllUsersMessage(User sender, String content, String date, Boolean isRead);

    void deleteMessage(User receiver, Friends sender, String content, String date, Boolean isRead);

    void report(User sender, String content);

    void deleteConversation(User userOne, User userTwo);

    void deleteAllFriendsConversation(User userOne);

    void deleteAllUsersConversation(User userOne);

    public void deleteFriendsMessage(User sender, String content, String date, Boolean isRead);

    public void deleteAllMessage(User sender, String content, String date, Boolean isRead);

}
