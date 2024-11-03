public interface MessagingInterface {

    void sendMessage (Friends sender, User receiver, Messaging content);
    void deleteMessage (Friends sender, User receiver, Messaging content);
    boolean isRead();
    void Report (User sender, User receiver);
    void deleteConversation (User sender, User receiver);
    String toString();
    void saveToFile(Messaging messages); // make sure each User has a separate .txt file for all their messages
    void rewriteMessages();

}
