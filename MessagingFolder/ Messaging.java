import java.util.ArrayList;
import java.io.*;

class Messaging implements MessagingInterface {
    private User sender;
    private Friends receiver;
    private String content;
    private String date;
    private Boolean isRead;
    private static ArrayList<Messaging> messageHistory = new ArrayList<>();
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
        this.date = date;
        this.isRead = isRead;
        this.messageType = "Single";
    }

    public Messaging(User sender, String content, ArrayList<User> users, String date, Boolean isRead, String messageType) {
        this.sender = sender;
        this.content = content;
        this.date = date;
        this.isRead = isRead;
        this.messageType = messageType;
    }


    public static ArrayList<Messaging> getMessageHistory() {
        return messageHistory;
    }


    public static void setMessageHistory(ArrayList<Messaging> messageHistory) {
        Messaging.messageHistory = messageHistory;
    }

    public void loadMessages(User user) {
        messageHistory.clear();
        String filename = user.getUsername() + ".txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String senderUsername = parts[0];
                    String content = parts[1];

                    if (parts.length == 3) {
                        String receiverUsername = parts[2];
                        User sender = new User(senderUsername);
                        Friends receiver = new Friends(new User(receiverUsername));
                        Messaging message = new Messaging(sender, receiver, content, "datePlaceholder", false);
                        messageHistory.add(message);
                    } else {
                        User sender = new User(senderUsername);
                        Messaging message = new Messaging(sender, content, new ArrayList<>(), "datePlaceholder", false, "Single");
                        messageHistory.add(message);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading messages for user " + user.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadAllFriendMessages(User user) {
        messageHistory.clear();
        String filename = user.getUsername() + "AllFriends.txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String senderUsername = parts[0];
                    String content = parts[1];

                    if (parts.length == 3) {
                        String receiverUsername = parts[2];
                        User sender = new User(senderUsername);
                        Friends receiver = new Friends(new User(receiverUsername));
                        Messaging message = new Messaging(sender, receiver, content, "datePlaceholder", false);
                        messageHistory.add(message);
                    } else {
                        User sender = new User(senderUsername);
                        Messaging message = new Messaging(sender, content, new ArrayList<>(), "datePlaceholder", false, "Single");
                        messageHistory.add(message);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading messages for user " + user.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadAllUsersMessages(User user) {
        messageHistory.clear();
        String filename = user.getUsername() + "AllUsers.txt";

        try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2) {
                    String senderUsername = parts[0];
                    String content = parts[1];

                    if (parts.length == 3) {
                        String receiverUsername = parts[2];
                        User sender = new User(senderUsername);
                        Friends receiver = new Friends(new User(receiverUsername));
                        Messaging message = new Messaging(sender, receiver, content, "datePlaceholder", false);
                        messageHistory.add(message);
                    } else {
                        User sender = new User(senderUsername);
                        Messaging message = new Messaging(sender, content, new ArrayList<>(), "datePlaceholder", false, "Single");
                        messageHistory.add(message);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading messages for user " + user.getUsername() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void rewriteMessages(User user) {

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(user.getUsername()+ ".txt", false))) {
            for (int i = 0; i < messageHistory.size(); i++) {
                Messaging message = messageHistory.get(i);
                bfw.write(message.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void rewriteFriendsMessages(User user) {

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(user.getUsername()+ "AllFriends.txt", false))) {
            for (int i = 0; i < messageHistory.size(); i++) {
                Messaging message = messageHistory.get(i);
                bfw.write(message.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void rewriteUsersMessages(User user) {

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(user.getUsername()+ "AllUsers.txt", false))) {
            for (int i = 0; i < messageHistory.size(); i++) {
                Messaging message = messageHistory.get(i);
                bfw.write(message.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            return;
        }

        if (!sender.isFriend1(receiver)) {
            System.out.println("Cannot send message because you are not friends.");
            return;
        }


        Messaging message = new Messaging(sender, receiver, content, date, isRead);
        messageHistory.add(message);

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + ".txt", true))) {
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
            if (histSplit[0].equals(sender.getUsername())) {
                friendsToUser.add(user);
            }
        }

        Messaging friendsToMessage = new Messaging(sender, content, friendsToUser, date, isRead, "AllFriends");
        messageHistory.add(friendsToMessage);

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "AllFriends.txt", true))) {
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

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "AllUsers.txt", true))) {
            bfw.write(usersToMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }


    public void deleteMessage(User sender, Friends receiver, String content, String date, Boolean isRead) {
        ArrayList<Messaging> messagesToDelete = new ArrayList<>();
        boolean messageDeleted = false;

        if (!sender.isFriend1(receiver)) {
            System.out.println("Cannot send message because you are not friends.");
            return;
        }

        for (Messaging message : messageHistory) {
            if (message.getReceiver().toString().equals(receiver.getUser().getUsername()) && message.getContent().equals(content)) {
                messagesToDelete.add(message);
                System.out.println("TRUE");
                messageDeleted = true;
            }
        }

        if (messageDeleted) {
            System.out.println("Histroy" + messageHistory);
            messageHistory.removeAll(messagesToDelete);
            System.out.println("post" + messageHistory);
            rewriteMessages(sender);
            System.out.println("Message deleted.");
        } else {
            System.out.println("No matching message found to delete.");
        }
    }


    public void deleteFriendsMessage(User sender, String content, String date, Boolean isRead) {
        ArrayList<Messaging> deletedFriendsMessages = new ArrayList<>();
        boolean messageFound = false;


        for (Messaging messages : messageHistory) {
            if (messages.getContent().equals(content)) {
                deletedFriendsMessages.add(messages);
                messageFound = true;
            }
        }

        if (messageFound) {
            messageHistory.removeAll(deletedFriendsMessages);
            rewriteFriendsMessages(sender);
            System.out.println("Message deleted successfully.");
        } else {
            System.out.println("Message not found for deletion.");
        }
    }

    public void deleteUsersMessage(User sender, String content, String date, Boolean isRead) {
        ArrayList<Messaging> deletedUsersMessages = new ArrayList<>();
        boolean messageFound = false;


        for (Messaging messages : messageHistory) {
            if (messages.getContent().equals(content)) {
                deletedUsersMessages.add(messages);
                messageFound = true;
            }
        }

        if (messageFound) {
            System.out.println(messageHistory);
            messageHistory.removeAll(deletedUsersMessages);
            System.out.println(messageHistory);
            rewriteUsersMessages(sender);
            System.out.println("Message deleted successfully.");
        } else {
            System.out.println("Message not found for deletion.");
        }
    }


    public void deleteAllMessage(User sender, String content, String date, Boolean isRead) {
        ArrayList<Messaging> deletedAllMessages = new ArrayList<>();
        for (Messaging messages : messageHistory) {
            if (messages.getSender().equals(sender)
                    && messages.getContent().equals(content) && (messageType.equals("AllUsers"))) {
                deletedAllMessages.add(messages);
                break;
            }
        }
        messageHistory.removeAll(deletedAllMessages);
        rewriteMessages(sender);
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
            rewriteMessages(sender);
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
            System.out.println("Conversations involving " + userOne.getUsername() + " has been deleted.");
            rewriteMessages(sender);
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
            System.out.println("Conversations involving " + userOne.getUsername() + " has been deleted.");
            rewriteMessages(sender);
        } else {
            System.out.println("No conversation found between " + userOne.getUsername() + ".");
        }
    }







}
