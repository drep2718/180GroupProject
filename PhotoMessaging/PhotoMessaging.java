import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class PhotoMessaging implements PhotoMessagingInterface {

    private User sender;
    private Friends receiver;
    private BufferedImage imageContent;
    private String date;
    private Boolean isRead;
    private String messageType;
    private static ArrayList<PhotoMessaging> photoMessageHistory = new ArrayList<>();

    public PhotoMessaging(User sender, Friends receiver,
                          BufferedImage imageContent, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
    }

    public PhotoMessaging(User sender, BufferedImage imageContent, ArrayList<User> users, String date, Boolean isRead, String messageType) {
        this.sender = sender;
        this.imageContent = imageContent;
        this.date = "";
        this.isRead = false;
        this.messageType = messageType;
    }


    public ArrayList<PhotoMessaging> getPhotoMessageHistory() {
        return photoMessageHistory;
    }


    public void setPhotoMessageHistory(ArrayList<PhotoMessaging> photoMessageHistory) {
        PhotoMessaging.photoMessageHistory = photoMessageHistory;
    }


    public User getSender() {
        return sender;
    }


    public Friends getReceiver() {
        return receiver;
    }


    public BufferedImage getImageContent() {
        return imageContent;
    }


    public String getDate() {
        return date;
    }


    public Boolean getIsRead() {
        return isRead;
    }


    public String getMessageType() {
        return messageType;
    }


    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


    public void rewritePhotoMessages() {
        String senderFile;

        for (PhotoMessaging pm : photoMessageHistory) {
            if (pm.getMessageType().equals("Single")) {
                senderFile = pm.getSender().getUsername() + ".txt";
            } else if (pm.getMessageType().equals("AllFriends")) {
                senderFile = pm.getSender().getUsername() + "AllFriends.txt";
            } else if (pm.getMessageType().equals("AllUsers")) {
                senderFile = pm.getSender().getUsername() + "AllUsers.txt";
            } else {
                continue;
            }

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(senderFile, false))) {
                for (PhotoMessaging photoMessage : photoMessageHistory) {
                    bfw.write(photoMessage.toString());
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String toString() {
        if (receiver != null) {
            return sender.getUsername() + ":" + imageContent + ":" + receiver.getUser().getUsername();
        } else {
            return sender.getUsername() + ":" + imageContent;
        }
    }


    public void saveToFile() {
        String senderFile;

        for (PhotoMessaging m : photoMessageHistory) {
            if (m.getMessageType().equals("Single")) {
                senderFile = m.getSender().getUsername() + ".txt";
            } else if (m.getMessageType().equals("AllFriends")) {
                senderFile = m.getSender().getUsername() + "AllFriends.txt";
            } else if (m.getMessageType().equals("AllUsers")) {
                senderFile = m.getSender().getUsername() + "AllUsers.txt";
            } else {
                continue;
            }

            try {
                ImageIO.write(m.getImageContent(), "png", new File(senderFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendPhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        boolean isBlocked = false;
        boolean isFriend = false;
        if (receiver.isBlocked(sender)) {
            System.out.println("Cannot send photo message because you have been blocked.");
            isBlocked = true;
        }

        if (!receiver.isFriend(sender) && !receiver.isBlocked(sender)) {
            System.out.println("Cannot send photo message because you are not friends.");
            isFriend = true;
        }

        if (receiver.isFriend(sender)) {
            PhotoMessaging photoMessage = new PhotoMessaging(sender, receiver, imageContent, date, isRead);
            photoMessageHistory.add(photoMessage);

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + ".txt", true))) {
                bfw.write(photoMessage.toString());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Delivered");
        }

    }


    public void sendAllFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<User> allFriends = Friends.getFriendsList();
        ArrayList<User> friendsToUser = new ArrayList<>();


        for (User user : allFriends) {
            String[] histSplit = user.toString().split(":");
            String senderFile = histSplit[0] + ".txt";
            if (histSplit[0].equals(sender.getUsername())) {
                friendsToUser.add(user);
            } else {
                System.out.println("Cannot send message because you have no friends");
            }
        }

        PhotoMessaging friendsPhotoMessage = new PhotoMessaging(sender, imageContent, friendsToUser, date, isRead, "AllFriends");
        photoMessageHistory.add(friendsPhotoMessage);

        String senderFile = sender.getUsername() + "AllFriends.txt";
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(senderFile, true))) {
            bfw.write(friendsPhotoMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }


    public void sendAllUsersPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<User> allUsers = User.getAllUsers();

        PhotoMessaging usersPhotoMessage = new PhotoMessaging(sender, imageContent, allUsers, date, isRead, "AllUsers");
        photoMessageHistory.add(usersPhotoMessage);

        String senderFile = sender.getUsername() + "AllUsers.txt";
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(senderFile, true))) {
            bfw.write(usersPhotoMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }


    public void deletePhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> photoMessagesToDelete = new ArrayList<>();

        for (PhotoMessaging photoMessage : photoMessageHistory) {
            if (photoMessage.getSender().equals(sender) && photoMessage.getReceiver().equals(receiver) &&
                    photoMessage.getImageContent().equals(imageContent)) {
                photoMessagesToDelete.add(photoMessage);
                break;
            } else {
                System.out.println("You are not the sender of the message");
            }
        }
        photoMessageHistory.removeAll(photoMessagesToDelete);
        rewritePhotoMessages();
    }


    public void deleteFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> deletedFriendsMessages = new ArrayList<>();

        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getSender().equals(sender)
                    && message.getImageContent().equals(imageContent)
                    && message.getMessageType().equals("AllFriends")) {
                deletedFriendsMessages.add(message);
                break;
            } else {
                System.out.println("You are not the sender of the message or no matching message found.");
            }
        }

        photoMessageHistory.removeAll(deletedFriendsMessages);
        rewritePhotoMessages();
    }


    public void deleteAllPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> deletedAllMessages = new ArrayList<>();

        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getSender().equals(sender)
                    && message.getImageContent().equals(imageContent)
                    && message.getMessageType().equals("AllUsers")) {
                deletedAllMessages.add(message);
                break;
            } else {
                System.out.println("You are not the sender of the message or no matching message found.");
            }
        }

        photoMessageHistory.removeAll(deletedAllMessages);
        rewritePhotoMessages();
    }


    public void report(User sender, BufferedImage imageContent) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Report.txt", true))) {
            String reportEntry = "Reported User: " + sender.getUsername() + " | Message: \"" + imageContent + "\"";
            bw.write(reportEntry);
            bw.newLine();
            System.out.println("Report has been filed successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteConversation(User userOne, User userTwo) {
        ArrayList<PhotoMessaging> tempArray = new ArrayList<>();

        for (PhotoMessaging photoMessage : photoMessageHistory) {
            User sender = photoMessage.getSender();
            Friends receiver = photoMessage.getReceiver();
            User receiverUser = receiver.getUser();

            if ((sender.equals(userOne) && receiverUser.equals(userTwo)) ||
                    (sender.equals(userTwo) && receiverUser.equals(userOne))) {
                tempArray.add(photoMessage);
            }
        }
        photoMessageHistory.removeAll(tempArray);

        if (!tempArray.isEmpty()) {
            System.out.println("Conversation between " + userOne.getUsername() + " and " + userTwo.getUsername() + " has been deleted.");
            rewritePhotoMessages();
        } else {
            System.out.println("No conversation found between " + userOne.getUsername() + " and " + userTwo.getUsername() + ".");
        }
    }


    public void deleteAllFriendsConversation(User userOne) {
        ArrayList<PhotoMessaging> tempArray = new ArrayList<>();

        for (PhotoMessaging photoMessage : photoMessageHistory) {
            User sender = photoMessage.getSender();

            if (photoMessage.getMessageType().equals("AllFriends") && sender.equals(userOne)) {
                tempArray.add(photoMessage);
            }
        }
        photoMessageHistory.removeAll(tempArray);

        if (!tempArray.isEmpty()) {
            System.out.println("Conversations involving " + userOne.getUsername() + " have been deleted.");
            rewritePhotoMessages();
        } else {
            System.out.println("No conversation found involving " + userOne.getUsername() + ".");
        }
    }


    public void deleteAllUsersConversation(User userOne) {
        ArrayList<PhotoMessaging> tempArray = new ArrayList<>();

        for (PhotoMessaging photoMessage : photoMessageHistory) {
            User sender = photoMessage.getSender();

            if (photoMessage.getMessageType().equals("AllUsers") && sender.equals(userOne)) {
                tempArray.add(photoMessage);
            }
        }
        photoMessageHistory.removeAll(tempArray);

        if (!tempArray.isEmpty()) {
            System.out.println("Conversations involving " + userOne.getUsername() + " have been deleted.");
            rewritePhotoMessages();
        } else {
            System.out.println("No conversation found involving " + userOne.getUsername() + ".");
        }
    }

}
