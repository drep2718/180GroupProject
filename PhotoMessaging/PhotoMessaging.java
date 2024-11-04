import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class PhotoMessaging {

    private Friends sender;
    private Friends receiver;
    private BufferedImage imageContent;
    private String date;
    private Boolean isRead;
    private ArrayList<PhotoMessaging> messageHistory = new ArrayList<>();

    public PhotoMessaging(Friends sender, Friends receiver, BufferedImage imageContent, String date, Boolean isRead) {
        this.sender = sender;
        this.receiver = receiver;
        this.imageContent = imageContent;
        this.date = date;
        this.isRead = isRead;
    }

  public ArrayList<PhotoMessaging> getMessageHistory() {
        return messageHistory;
    }


    public void setMessageHistory(ArrayList<PhotoMessaging> messageHistory) {
        this.messageHistory = messageHistory;
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
        for (PhotoMessaging m : messageHistory) {
            String senderFile = 
            try {
                ImageIO.write(m.getImageContent(), "png", new File(senderFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


  


}
