import java.util.ArrayList;
import java.io.*;

public class Messaging implements MessagingInterface {
    private User sender;
    private Friends receiver;
    private String content;
    private String date;
    private Boolean isRead;
    private ArrayList<Messaging> messageHistory = new ArrayList<>();
    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Messaging(User sender, Friends receiver, String content, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = "";
        this.isRead = false;
        this.messageType = "Single";
    }

    public Messaging(User sender, String content, ArrayList<User> users, String date, Boolean isRead, String messageType) {
        this.sender = sender;
        this.content = content;
        this.date = "";
        this.isRead = false;
        this.messageType = messageType;
    }


    @Override
    public ArrayList<Messaging> getMessageHistory() {
        return messageHistory;
    }

    @Override
    public void setMessageHistory(ArrayList<Messaging> messageHistory) {
        this.messageHistory = messageHistory;
    }

    public void rewriteMessages() {
        String senderFile;
        for (Messaging m : messageHistory) {
            if (m.getMessageType().equals("Single")) {
                senderFile = m.getSender().getUsername() + ".txt";
            } else if (m.getMessageType().equals("AllFriends")) {
                senderFile = m.getSender().getUsername() + "AllFriends.txt";
            } else if (m.getMessageType().equals("AllUsers")) {
                senderFile = m.getSender().getUsername() + "AllUsers.txt";
            } else {
                continue;
            }

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(senderFile, false))) {
                for (int i = 0; i < messageHistory.size(); i++) {
                    Messaging message = messageHistory.get(i);
                    bfw.write(message.toString());
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        if (receiver != null) {
            return sender.getUsername() + ":" + content + ":" + receiver.getUser().getUsername();
        } else {
            return sender.getUsername() + ":" + content;
        }
    }


    public User getSender() {
        return sender;
    }

    public Friends getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public boolean isRead() {
        return false;
    }

    public Boolean getIsRead() {
        return isRead;
    }



    public void saveToFile() {
            String senderFile;
            for (Messaging m : messageHistory) {
                if (m.getMessageType().equals("Single")) {
                    senderFile = m.getSender().getUsername() + ".txt";
                } else if (m.getMessageType().equals("AllFriends")) {
                    senderFile = m.getSender().getUsername() + "AllFriends.txt";
                } else if (m.getMessageType().equals("AllUsers")) {
                    senderFile = m.getSender().getUsername() + "AllUsers.txt";
                } else {
                    continue;
                }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(senderFile, true))) {
                writer.write(m.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMessage(User sender, Friends receiver, String content, String date, Boolean isRead) {
        if (receiver.isBlocked(sender)) {
            System.out.println("Cannot send message because account you have been blocked.");
        }

        if (!receiver.isFriend(sender)) {
            System.out.println("Cannot send message because you are not friends.");
        }

        Messaging message = new Messaging(sender, receiver, content, date, isRead);
        messageHistory.add(message);

        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + ".txt",true))) {
            bfw.write(message.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }

    public void sendAllFriendsMessage(User sender, String content, String date, Boolean isRead) {
        ArrayList<User> AllFriends = Friends.getFriendsList();
        ArrayList<User> friendsToUser = new ArrayList<>();

        for (User user : AllFriends) {
            String[] histSplit = user.toString().split(":");
            String senderFile = histSplit[0] + ".txt";
            if (histSplit[0].equals(sender.getUsername())) {
                friendsToUser.add(user);
            } else {
                System.out.println("Cannot send message because you have no friends");
            }
        }

        Messaging friendsToMessage = new Messaging(sender, content, friendsToUser, date, isRead, "AllFriends");
        messageHistory.add(friendsToMessage);

        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "AllFriends.txt",true))) {
            bfw.write(friendsToMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }

    public void sendAllUsersMessage(User sender, String content, String date, Boolean isRead) {
        ArrayList<User> AllUsers = User.getAllUsers();

        Messaging usersToMessage = new Messaging(sender, content, AllUsers, date, isRead, "AllUsers");
        messageHistory.add(usersToMessage);

        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "AllUsers.txt",true))) {
            bfw.write(usersToMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }



    public void deleteMessage(Friends sender, User receiver, String content, String date, Boolean isRead) {
        Messaging message = new Messaging(receiver, sender, content, date, isRead);
        boolean messageRemoved = false;
        for (int i = 0; i < messageHistory.size(); i++) {
            Messaging messages = messageHistory.get(i);

            if (messages.getSender().equals(sender) && messages.getReceiver().equals(receiver)
            && messages.getContent().equals(content)) {
                messageHistory.remove(i);
                messageRemoved = true;
                break;
            }


            if (messageRemoved) {
                System.out.println("Removed messages for both parties.");
            } else {
                System.out.println("Message not removed.");
            }
        }
    }

    public void report(User sender, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Report.txt", true))) {
            String reportEntry = "Reported User: " + sender.getUsername() + " | Message: \"" + content + "\"";
            bw.write(reportEntry);
            bw.newLine();
            System.out.println("Report has been filed successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteConversation(User userOne, User userTwo) {
        boolean conversationDeleted = false;
        ArrayList<Messaging> tempArray = new ArrayList<>();
        for (int i = 0; i < messageHistory.size(); i++) {
            Messaging message = messageHistory.get(i);

            User sender = message.getSender();
            Friends receiver = message.getReceiver();
            Friends receiverUser = receiver;

            if ((sender.equals(userOne) && receiverUser.equals(userTwo)) ||
                    sender.equals(userTwo) && receiverUser.equals(userOne)) {
                tempArray.add(message);
            }
        }
        messageHistory.remove(tempArray);
        conversationDeleted = true;

        if (conversationDeleted) {
            System.out.println("Conversation between " + userOne.getUsername() + " and " + userTwo.getUsername() + " has been deleted.");
            rewriteMessages();
        } else {
            System.out.println("No conversation found between " + userOne.getUsername() + " and " + userTwo.getUsername() + ".");
        }
    }

    public void deleteAllFriendsConversation(User userOne) {
        boolean conversationDeleted = false;
        ArrayList<Messaging> tempArray = new ArrayList<>();
        for (int i = 0; i < messageHistory.size(); i++) {
            Messaging message = messageHistory.get(i);
            User sender = message.getSender();

            if (message.getMessageType().equals("AllFriends") && sender.equals(userOne)) {
                tempArray.add(message);
            }

        }

        messageHistory.remove(tempArray);
        conversationDeleted = true;

        if (conversationDeleted) {
            System.out.println("Conversations involving " + userOne.getUsername() +  " has been deleted.");
            rewriteMessages();
        } else {
            System.out.println("No conversation found between " + userOne.getUsername() + ".");
        }
    }

    public void deleteAllUsersConversation(User userOne) {
        boolean conversationDeleted = false;
        ArrayList<Messaging> tempArray = new ArrayList<>();
        for (int i = 0; i < messageHistory.size(); i++) {
            Messaging message = messageHistory.get(i);
            User sender = message.getSender();

            if (message.getMessageType().equals("AllUsers") && sender.equals(userOne)) {
                tempArray.add(message);
            }

        }

        messageHistory.remove(tempArray);
        conversationDeleted = true;

        if (conversationDeleted) {
            System.out.println("Conversations involving " + userOne.getUsername() +  " has been deleted.");
            rewriteMessages();
        } else {
            System.out.println("No conversation found between " + userOne.getUsername() + ".");
        }
    }
}
