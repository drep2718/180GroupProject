import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class PhotoMessaging {

    private Friends sender;
    private Friends receiver;
    private BufferedImage imageContent;
    private String date;
    private Boolean isRead;
    private ArrayList<PhotoMessaging> photoMessageHistory = new ArrayList<>();

    public PhotoMessaging(Friends sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
    }

    public ArrayList<PhotoMessaging> getMessageHistory() {
        return photoMessageHistory;
    }

    public void setMessageHistory(ArrayList<PhotoMessaging> photoMessageHistory) {
        this.photoMessageHistory = photoMessageHistory;
    }

    public Friends getSender() {
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

    public void saveToFile() {
        for (PhotoMessaging m : photoMessageHistory) {
            String senderFile = m.getSender().getUsername(); 
            try {
                ImageIO.write(m.getImageContent(), "png", new File(senderFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


  


}
