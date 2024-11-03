public interface MessagingInterface {

    void sendMessage (User sender, User receiver, String content);
    void deleteMessage (User sender, User receiver, String content);
    boolean isRead();
    void Report (User sender, User receiver);
    void deleteConversation (User sender, User receiver);
    String toString();
    void saveToFile(Messaging messages); // make sure each User has a separate .txt file for all their messages
    void rewriteMessages();



}

