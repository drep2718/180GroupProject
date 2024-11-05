import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class PhotoMessaging {

    private User sender;
    private Friends receiver;
    private BufferedImage imageContent;
    private String date;
    private Boolean isRead;
    private String messageType;
    private ArrayList<PhotoMessaging> photoMessageHistory = new ArrayList<>();

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

    public ArrayList<PhotoMessaging> getMessageHistory() {
        return photoMessageHistory;
    }

    public void setMessageHistory(ArrayList<PhotoMessaging> photoMessageHistory) {
        this.photoMessageHistory = photoMessageHistory;
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

        PhotoMessaging photoMessage = new PhotoMessaging(sender, receiver, imageContent, date, isRead);
        photoMessageHistory.add(photoMessage);

        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + ".txt",true))) {
            bfw.write(photoMessage.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Delivered");
    }
    
    public void deletePhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> photoMessagesToDelete = new ArrayList<>();
        
        for (PhotoMessaging photoMessage : photoMessageHistory) {
            if (photoMessage.getSender().equals(sender) && photoMessage.getDate().equals(date) && 
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
}
