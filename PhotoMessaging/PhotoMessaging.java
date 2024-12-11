import java.util.ArrayList;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Team Project -- PhotoMessaging Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 *
 * @version Dec 11, 2024
 *
 */

public class PhotoMessaging implements PhotoMessagingInterface {
    private User sender;
    private Friends receiver;
    private BufferedImage imageContent;
    private String date;
    private Boolean isRead;
    private static ArrayList<PhotoMessaging> messageHistory = new ArrayList<>();
    private String messageType;
    private static final Object gatekeeper = new Object();
    private String imagePath;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public PhotoMessaging(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
        this.messageType = "Single";
        saveImageToFile();
    }

    public PhotoMessaging(User sender, BufferedImage imageContent, ArrayList<User> users, String date, Boolean isRead, String messageType) {
        this.sender = sender;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
        this.messageType = messageType;
        saveImageToFile();
    }

    private void saveImageToFile() {
        if (imageContent != null) {
            try {
                String fileName = "photos/" + ".png";
                File outputFile = new File(fileName);
                outputFile.getParentFile().mkdirs();
                ImageIO.write(imageContent, "png", outputFile);
                this.imagePath = fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<PhotoMessaging> getMessageHistory() {
        return messageHistory;
    }

    public static void setMessageHistory(ArrayList<PhotoMessaging> messageHistory) {
        PhotoMessaging.messageHistory = messageHistory;
    }

    public void loadMessages(User user) {
        synchronized (gatekeeper) {
            messageHistory.clear();
            String filename = user.getUsername() + "_photos.txt";

            try (BufferedReader bfr = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length >= 3) {
                        String senderUsername = parts[0];
                        String imagePath = parts[1];
                        BufferedImage image = ImageIO.read(new File(imagePath));

                        if (parts.length == 4) {
                            String receiverUsername = parts[2];
                            User sender = new User(senderUsername);
                            Friends receiver = new Friends(new User(receiverUsername));
                            PhotoMessaging message = new PhotoMessaging(sender, receiver, image, "datePlaceholder", false);
                            messageHistory.add(message);
                        } else {
                            User sender = new User(senderUsername);
                            PhotoMessaging message = new PhotoMessaging(sender, image, new ArrayList<>(), "datePlaceholder", false, "Single");
                            messageHistory.add(message);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading photo messages for user " + user.getUsername() + ": " + e.getMessage());
            }
        }
    }

    public void sendPhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        if (receiver.isBlocked(sender)) {
            System.out.println("Cannot send photo message because account you have been blocked.");
            return;
        }

        if (!sender.isFriend1(receiver)) {
            System.out.println("Cannot send photo message because you are not friends.");
            return;
        }

        PhotoMessaging message = new PhotoMessaging(sender, receiver, imageContent, date, isRead);
        synchronized (gatekeeper) {
            messageHistory.add(message);

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "_photos.txt", true))) {
                bfw.write(message.toString());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Photo Delivered");
    }

    public void sendAllFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<User> AllFriends;
        synchronized (gatekeeper) {
            AllFriends = Friends.getFriendsList();
        }
        ArrayList<User> friendsToUser = new ArrayList<>();

        for (User user : AllFriends) {
            String[] histSplit = user.toString().split(":");
            if (histSplit[0].equals(sender.getUsername())) {
                friendsToUser.add(user);
            }
        }

        PhotoMessaging friendsToMessage = new PhotoMessaging(sender, imageContent, friendsToUser, date, isRead, "AllFriends");
        synchronized (gatekeeper) {
            messageHistory.add(friendsToMessage);

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "_photos_AllFriends.txt", true))) {
                bfw.write(friendsToMessage.toString());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Photo Delivered to All Friends");
    }

    public void sendAllUsersPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<User> AllUsers;
        synchronized (gatekeeper) {
            AllUsers = User.getAllUsers();
        }

        PhotoMessaging usersToMessage = new PhotoMessaging(sender, imageContent, AllUsers, date, isRead, "AllUsers");
        synchronized (gatekeeper) {
            messageHistory.add(usersToMessage);

            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(sender.getUsername() + "_photos_AllUsers.txt", true))) {
                bfw.write(usersToMessage.toString());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Photo Delivered to All Users");
    }

    public String toString() {
        if (receiver != null) {
            return sender.getUsername() + ":" + imagePath + ":" + receiver.getUser().getUsername();
        } else {
            return sender.getUsername() + ":" + imagePath;
        }
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

    public boolean isRead() {
        return false;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void saveToFile() {
        synchronized (gatekeeper) {
            String senderFile;
            for (PhotoMessaging m : messageHistory) {
                if (m.getMessageType().equals("Single")) {
                    senderFile = m.getSender().getUsername() + "_photos.txt";
                } else if (m.getMessageType().equals("AllFriends")) {
                    senderFile = m.getSender().getUsername() + "_photos_AllFriends.txt";
                } else if (m.getMessageType().equals("AllUsers")) {
                    senderFile = m.getSender().getUsername() + "_photos_AllUsers.txt";
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
    }

    public void deletePhotoMessage(User sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> messagesToDelete = new ArrayList<>();
        synchronized (gatekeeper) {
            boolean messageDeleted = false;

            if (!sender.isFriend1(receiver)) {
                System.out.println("Cannot delete photo message because you are not friends.");
                return;
            }

            for (PhotoMessaging message : messageHistory) {
                if (message.getReceiver().toString().equals(receiver.getUser().getUsername()) &&
                        message.getImagePath().equals(this.imagePath)) {
                    messagesToDelete.add(message);
                    System.out.println("TRUE");
                    messageDeleted = true;
                }
            }

            if (messageDeleted) {
                System.out.println("History" + messageHistory);
                messageHistory.removeAll(messagesToDelete);
                System.out.println("post" + messageHistory);
                rewritePhotoMessages(sender);
                System.out.println("Photo message deleted.");
            } else {
                System.out.println("No matching photo message found to delete.");
            }
        }
    }

    public void deleteFriendsPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> deletedFriendsMessages = new ArrayList<>();
        synchronized (gatekeeper) {
            boolean messageFound = false;

            for (PhotoMessaging messages : messageHistory) {
                if (messages.getImagePath().equals(this.imagePath)) {
                    deletedFriendsMessages.add(messages);
                    messageFound = true;
                }
            }

            if (messageFound) {
                messageHistory.removeAll(deletedFriendsMessages);
                rewritePhotoMessages(sender);
                System.out.println("Photo message deleted successfully.");
            } else {
                System.out.println("Photo message not found for deletion.");
            }
        }
    }

    public void deleteAllPhotoMessage(User sender, BufferedImage imageContent, String date, Boolean isRead) {
        ArrayList<PhotoMessaging> deletedAllMessages = new ArrayList<>();
        synchronized (gatekeeper) {
            for (PhotoMessaging messages : messageHistory) {
                if (messages.getSender().equals(sender) &&
                        messages.getImagePath().equals(this.imagePath) &&
                        (messageType.equals("AllUsers"))) {
                    deletedAllMessages.add(messages);
                    break;
                }
            }
            messageHistory.removeAll(deletedAllMessages);
            rewritePhotoMessages(sender);
        }
    }

    public void report(User sender, BufferedImage imageContent) {
        synchronized (gatekeeper) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("PhotoReport.txt", true))) {
                String reportEntry = "Reported User: " + sender.getUsername() + " | Photo: \"" + this.imagePath + "\"";
                bw.write(reportEntry);
                bw.newLine();
                System.out.println("Photo report has been filed successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteConversation(User userOne, User userTwo) {
        synchronized (gatekeeper) {
            boolean conversationDeleted = false;
            ArrayList<PhotoMessaging> tempArray = new ArrayList<>();
            for (int i = 0; i < messageHistory.size(); i++) {
                PhotoMessaging message = messageHistory.get(i);
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
                System.out.println("Photo conversation between " + userOne.getUsername() +
                        " and " + userTwo.getUsername() + " has been deleted.");
                rewritePhotoMessages(sender);
            } else {
                System.out.println("No photo conversation found between " + userOne.getUsername() +
                        " and " + userTwo.getUsername() + ".");
            }
        }
    }

    public void deleteAllFriendsConversation(User userOne) {
        synchronized (gatekeeper) {
            boolean conversationDeleted = false;
            ArrayList<PhotoMessaging> tempArray = new ArrayList<>();
            for (int i = 0; i < messageHistory.size(); i++) {
                PhotoMessaging message = messageHistory.get(i);
                User sender = message.getSender();

                if (message.getMessageType().equals("AllFriends") && sender.equals(userOne)) {
                    tempArray.add(message);
                }
            }

            messageHistory.remove(tempArray);
            conversationDeleted = true;

            if (conversationDeleted) {
                System.out.println("Photo conversations involving " + userOne.getUsername() + " has been deleted.");
                rewritePhotoMessages(sender);
            } else {
                System.out.println("No photo conversation found for " + userOne.getUsername() + ".");
            }
        }
    }

    public void deleteAllUsersConversation(User userOne) {
        synchronized (gatekeeper) {
            boolean conversationDeleted = false;
            ArrayList<PhotoMessaging> tempArray = new ArrayList<>();
            for (int i = 0; i < messageHistory.size(); i++) {
                PhotoMessaging message = messageHistory.get(i);
                User sender = message.getSender();

                if (message.getMessageType().equals("AllUsers") && sender.equals(userOne)) {
                    tempArray.add(message);
                }
            }

            messageHistory.remove(tempArray);
            conversationDeleted = true;

            if (conversationDeleted) {
                System.out.println("Photo conversations involving " + userOne.getUsername() + " has been deleted.");
                rewritePhotoMessages(sender);
            } else {
                System.out.println("No photo conversation found for " + userOne.getUsername() + ".");
            }
        }
    }

    public ArrayList<PhotoMessaging> getPhotoMessageHistory() {
        return messageHistory;
    }

    public void setPhotoMessageHistory(ArrayList<PhotoMessaging> photoMessageHistory) {
        PhotoMessaging.messageHistory = photoMessageHistory;
    }

    public void rewritePhotoMessages(User user) {
        synchronized (gatekeeper) {
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(user.getUsername() + "_photos.txt", false))) {
                for (int i = 0; i < messageHistory.size(); i++) {
                    PhotoMessaging message = messageHistory.get(i);
                    bfw.write(message.toString());
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getImagePath() {
        return imagePath;
    }
}
