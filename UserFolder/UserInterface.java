import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface UserInterface {

    String getUsername();
    BufferedImage imageSave(BufferedImage image, String filepath, String formatName );
    void saveToFile(User user);
    String toString();
    void rewriteUsers();
    void loadUsers();
    void setUsername (String username);
    boolean usernameAvail(String username);
    String getPassword();
    void setPassword(String password);
    boolean validatePassword(String username, String password);
    String getBio();
    void setBio(String bio);
    void setImage(BufferedImage image);
    BufferedImage getImage ();
    User createProfile(String username, String password, String bio);
    User createProfile(String username, String password, String bio, BufferedImage image, String filepath, String formatName);
    void updateUsername(String newUsername);
    void updatePassword(String newPassword);
    void updateBio(String newBio);
    void removeProfile(String username);
    String findProfile(String username);
    void setFormatName(String formatName);
    void setFilepath(String filepath);
    String getFilepath ();
    String getFormatName();
    boolean userExists(String username);
    boolean passwordExists(String password);
    boolean bioExists(String bio);


}
