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

    public PhotoMessaging(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
        this.messageType = "Single";
    }

    public PhotoMessaging(User sender, BufferedImage imageContent, ArrayList<User> users, String date, Boolean isRead, String messageType) {
        this.sender = sender;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
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
        if (receiver.isBlocked(sender)) {
            System.out.println("Cannot send photo message because you have been blocked.");
            return;
        }

        if (!receiver.isFriend(sender)) {
            System.out.println("Cannot send photo message because you are not friends.");
            return;
        }

        if (receiver.isFriend(sender)) {
            PhotoMessaging photoMessage = new PhotoMessaging(sender, receiver, imageContent, date, isRead);
            photoMessageHistory.add(photoMessage);
            saveToFile();
            System.out.println("Photo message delivered.");
        }
    }

    
    public void sendAllFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<User> friendsList = Friends.getFriendsList();
        ArrayList<User> friendsToUser = new ArrayList<>();

        for (User user : friendsList) {
            String[] histSplit = user.toString().split(":");
            String senderFile = histSplit[0] + ".txt";
            if (histSplit[0].equals(sender.getUsername())) {
                friendsToUser.add(user);
            } else {
                System.out.println("Cannot send message because you have no friends");
            }
        }

        PhotoMessaging allFriendsPhotoMessage = new PhotoMessaging(sender, imageContent, friendsList, date, isRead, "AllFriends");
        photoMessageHistory.add(allFriendsPhotoMessage);

        String senderFile = sender.getUsername() + "AllFriends.txt";
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(senderFile, true))) {
            bfw.write(allFriendsPhotoMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }

    
    public void sendAllUsersPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<User> allUsers = User.getAllUsers();
        PhotoMessaging allUsersPhotoMessage = new PhotoMessaging(sender, imageContent, allUsers, date, isRead, "AllUsers");
        photoMessageHistory.add(allUsersPhotoMessage);

        String senderFile = sender.getUsername() + "AllUsers.txt";
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(senderFile, true))) {
            bfw.write(allUsersPhotoMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }

    
    public void deletePhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> messagesToDelete = new ArrayList<>();
        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getSender().equals(sender) && message.getReceiver().equals(receiver) &&
                    message.getImageContent().equals(imageContent)) {
                messagesToDelete.add(message);
                break;
            } else {
                System.out.println("You are not the sender of the message");
            }
        }
        photoMessageHistory.removeAll(messagesToDelete);
        rewritePhotoMessages();
    }

    
    public void deleteFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> deletedMessages = new ArrayList<>();
        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getSender().equals(sender) && message.getImageContent().equals(imageContent) &&
                    message.getMessageType().equals("AllFriends")) {
                deletedMessages.add(message);
                break;
            } else {
                System.out.println("You are not the sender of the message or no matching message found.");
            }
        }
        photoMessageHistory.removeAll(deletedMessages);
        rewritePhotoMessages();
    }

    
    public void deleteAllPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> deletedMessages = new ArrayList<>();
        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getSender().equals(sender) && message.getImageContent().equals(imageContent) &&
                    message.getMessageType().equals("AllUsers")) {
                deletedMessages.add(message);
                break;
            } else {
                System.out.println("You are not the sender of the message or no matching message found.");
            }
        }
        photoMessageHistory.removeAll(deletedMessages);
        rewritePhotoMessages();
    }

    
    public void report(User sender, BufferedImage imageContent) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PhotoReport.txt", true))) {
            String reportEntry = "Reported User: " + sender.getUsername();
            bw.write(reportEntry);
            bw.newLine();
            System.out.println("Photo report has been filed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteConversation(User userOne, User userTwo) {
        ArrayList<PhotoMessaging> tempArray = new ArrayList<>();
        for (PhotoMessaging message : photoMessageHistory) {
            if ((message.getSender().equals(userOne) && message.getReceiver().getUser().equals(userTwo)) ||
                    (message.getSender().equals(userTwo) && message.getReceiver().getUser().equals(userOne))) {
                tempArray.add(message);
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

        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getMessageType().equals("AllFriends") && message.getSender().equals(userOne)) {
                tempArray.add(message);
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
        for (PhotoMessaging message : photoMessageHistory) {
            if (message.getMessageType().equals("AllUsers") && message.getSender().equals(userOne)) {
                tempArray.add(message);
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
