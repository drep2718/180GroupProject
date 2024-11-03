import java.util.ArrayList;

public class Messaging implements MessagingInterface {
    private Friends sender;
    private Friends receiver;
    private Messaging content;
    private String date;
    private Boolean isRead;
    private ArrayList<Messaging> messageHistory;



    public Messaging(Friends sender, Friends receiver, Messaging content, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = null;
        this.date = "";
        this.isRead = false;
    }

    public Friends getSender() {
        return sender;
    }

    public Friends getReceiver() {
        return receiver;
    }

    public Messaging getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void sendMessage(Friends sender, User receiver, Messaging content) {
        if (sender.isBlocked(receiver)) {
            System.out.println("Cannot send message because account you have been blocked.");
        }

        if (!sender.isFriend(receiver)) {
            System.out.println("Cannot send message because you are not friends.");
        }

        sender.sentMessage(content);
        System.out.println("Delivered");
    }

    public void deleteMessage(Friends sender, User receiver, Messaging content) {
        if (content.getSender().equals(sender)) {
            sender.removeSentMessage(content);
            System.out.println("Removed messages for both parties.");
        } else {
            System.out.println("Message removed for you only.");
        }
    }

    public void report(User sender, User receiver, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Report.txt", true))) {
            String reportEntry = "Reported User: " + sender.getUsername() + " | Message: \"" + content + "\"" +
                    " | Reported by: " + receiver.getUsername();
            bw.write(reportEntry);
            bw.newLine();
            System.out.println("Report has been filed successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteConversation(User userOne, User userTwo) {
        for (int i = 0; i < messageHistory.size(); i++) {
            Messaging message = messageHistory.get(i);

            Friends sender = message.getSender();
            Friends receiver = message.getReceiver();
            User senderUser = sender.getUser();
            User receiverUser = receiver.getUser();

            if ((senderUser.equals(userOne) && receiverUser.equals(userTwo)) ||
                    senderUser.equals(userTwo) && receiverUser.equals(userOne)) {
                messageHistory.remove(i);
                i--; // Fixes index after removing a message from messageHistory
            }
        }

        System.out.println("Conversation between " + userOne.getUsername() + " and " +
                userTwo.getUsername() + " has been deleted.");
    }
}
