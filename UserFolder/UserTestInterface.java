import java.util.ArrayList;

/**
 * Team Project -- UserTestInterface Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 *
 * @version November 17, 2024
 *
 */

public interface UserTestInterface {

    void createBasicProfile();

    void testCreateProfile();

    void saveToFile();

    void testToStringBasicUser();

    void testToStringPfpUser();

    void removeProfile();

    void rewriteUsers();

    void updateUsername();

    void updatePassword();

    void updateBio();

    void findProfile();

    void usernameNotAvail();

    void usernameAvail();

    void setImage();

    void getImage();

    void getBio();

    void setBio();

    void getUsername();

    void setUsername();

    void getPassword();

    void setPassword();

    void validatePassword();

    void validatePasswordWrongUsername();

    void validatePasswordWrongPassword();

}
