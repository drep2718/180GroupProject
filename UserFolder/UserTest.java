import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Team Project -- UserTest Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */
class UserTest implements UserTestInterface {
    User user = new User("UsernameQ", "PasswordQ", "BioQ");
    BufferedImage image;


    @Test
    public void createBasicProfile() {
        User newUser = new User("UsernameP", "PasswordP", "BioP");
        User newerUser = newUser.createProfile("UsernameP", "PasswordP", "BioP");
        assertEquals("UsernameP", newerUser.getUsername());
        assertEquals("PasswordP", newerUser.getPassword());
        assertEquals("BioP", newerUser.getBio());
    }

    @Test
    public void testCreateProfile() {  // fix how this method works and reads in the filename and formatName
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        User newUser = user.createProfile("Username", "Password", "Bio", image, "FilePath", "FormatName");
        assertEquals("Username", newUser.getUsername());
        assertEquals("Password", newUser.getPassword());
        assertEquals("Bio", newUser.getBio());
        assertEquals(image, newUser.getImage());
        assertEquals("FilePath", newUser.getFilepath());
        assertEquals("FormatName", newUser.getFormatName());
    }

    @Test
    public void saveToFile() {

        User newUser = user.createProfile("Username", "Password", "Bio");

        try (BufferedReader bfr = new BufferedReader(new FileReader("Users.txt"))) {
            String lastLine = "";
            String line = bfr.readLine();
            while (line != null) {
                lastLine = line;
                line = bfr.readLine();
            }
            assertEquals("Username22,Password22,Bio22", lastLine);

        } catch (IOException e) {
            e.getMessage();
        }
    }


    @Test
    public void testToStringBasicUser() {

        User newUser = new User("UsernamePP", "PasswordPP", "BioPP");
        User newerUser = newUser.createProfile("UsernamePP", "PasswordPP", "BioPP");
        String test = newUser.toString();
        assertEquals("UsernamePP,PasswordPP,BioPP", test);
    }

    @Test
    public void testToStringPfpUser() {  // fix buffered Image
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        User newUser = user.createProfile("Username", "Password", "Bio", image, "FilePath", "FormatName");
        String test = newUser.toString();
        assertEquals("Username,Password,Bio,FilePath", test);

    }

    @Test
    public void removeProfile() {
        User newUser = user.createProfile("Username22", "Password22", "Bio22");
        String Username = "Username22";
        assertTrue(newUser.userExists("Username22"));
        assertTrue(newUser.passwordExists("Password22"));
        assertTrue(newUser.bioExists("Bio22"));
        newUser.removeProfile(Username);
        assertFalse(newUser.userExists(newUser.getUsername()));
        assertFalse(newUser.passwordExists(newUser.getPassword()));
        assertFalse(newUser.bioExists(newUser.getBio()));
    }

    @Test
    public void rewriteUsers() {

        User.setAllUsers(new ArrayList<>());
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Users.txt", false))) {
            bfw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }


        User newUser = user.createProfile("Username11", "Password11", "Bio11");
        User newUser2 = user.createProfile("Username33", "Password33", "Bio33");
        newUser2.rewriteUsers();

        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader("Users.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }

        } catch (IOException e) {
            e.getMessage();
        }


        assertEquals(2, lines.size());
        assertEquals(newUser.toString(), lines.get(0));
        assertEquals(newUser2.toString(), lines.get(1));
    }


    @Test
    public void updateUsername() {
        User newUser = user.createProfile("oldUsername", "Password", "Bio");
        assertTrue(newUser.userExists("oldUsername"));
        assertTrue(newUser.passwordExists("Password"));
        assertTrue(newUser.bioExists("Bio"));
        String newUsername = "Username";
        newUser.updateUsername(newUsername);
        assertFalse(newUser.userExists("oldUsername"));
        assertTrue(newUser.userExists("Username"));
        assertTrue(newUser.passwordExists("Password"));
        assertTrue(newUser.bioExists("Bio"));

    }

    @Test
    public void updatePassword() {
        User user = new User("UsernameW", "oldPasswordW", "BioW");
        User newUser = user.createProfile("UsernameW", "oldPasswordW", "BioW");
        assertTrue(newUser.userExists("UsernameW"));
        assertTrue(newUser.passwordExists("oldPasswordW"));
        assertTrue(newUser.bioExists("BioW"));
        String newPassword = "PasswordW";
        newUser.updatePassword(newPassword);
        assertFalse(newUser.passwordExists("oldPasswordW"));
        assertTrue(newUser.userExists("UsernameW"));
        assertTrue(newUser.passwordExists("PasswordW"));
        assertTrue(newUser.bioExists("BioW"));

    }

    @Test
    public void updateBio() {
        User user = new User("UsernameL", "PasswordL", "oldBioL");
        User newUser = user.createProfile("UsernameL", "PasswordL", "oldBioL");
        assertTrue(newUser.userExists("UsernameL"));
        assertTrue(newUser.passwordExists("PasswordL"));
        assertTrue(newUser.bioExists("oldBioL"));
        String newBio = "BioL";
        newUser.updateBio(newBio);
        assertFalse(newUser.bioExists("oldBioL"));
        assertTrue(newUser.userExists("UsernameL"));
        assertTrue(newUser.passwordExists("PasswordL"));
        assertTrue(newUser.bioExists("BioL"));
    }

    @Test
    public void findProfile() {

        User newUser = user.createProfile("Aiden", "Password", "Bio");
        User user = new User("John");
        String result = user.findProfile("Aiden");
        assertEquals("Name: Aiden\nBio: Bio", result);

    }

    @Test
    public void usernameNotAvail() {
        User newUser = user.createProfile("Aiden", "Password", "Bio");
        User user = new User("John");
        boolean b = user.usernameAvail("Aiden");
        assertEquals(false, b);

    }

    @Test
    public void usernameAvail() {
        User newUser = user.createProfile("Aiden", "Password", "Bio");
        User user = new User("John");
        boolean b = user.usernameAvail("Matt");
        assertEquals(true, b);

    }

    @Test
    public void setImage() {
        User user = new User("Aiden");
        BufferedImage image1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        user.setImage(image1);
        assertEquals(image1, user.getImage());
    }

    @Test
    public void getImage() {
        User user = new User("Aiden");
        BufferedImage image1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        user.setImage(image1);
        BufferedImage getImage = user.getImage();
        assertEquals(getImage, image1);
    }

    @Test
    public void getBio() {
        User user = new User("Aiden", "Password", "Bio");
        String bio = "Bio";
        assertEquals(bio, user.getBio());
    }

    @Test
    public void setBio() {
        User user = new User("Aiden", "Password", "Bio");
        String newBio = "Bio";
        user.setBio(newBio);
        String bioFromGet = user.getBio();
        assertEquals(newBio, bioFromGet);
    }

    @Test
    public void getUsername() {
        User user = new User("Aiden");
        String username = "Aiden";
        assertEquals(username, user.getUsername());
    }

    @Test
    public void setUsername() {
        User user = new User("Aiden");
        String newUsername = "username";
        user.setUsername(newUsername);
        String usernameFromGet = user.getUsername();
        assertEquals(newUsername, usernameFromGet);
    }

    @Test
    public void getPassword() {
        User user = new User("Aiden", "Password", "Bio");
        String password = "Password";
        assertEquals(password, user.getPassword());
    }

    @Test
    public void setPassword() {
        User user = new User("Aiden", "Password", "Bio");
        String newPassword = "Password";
        user.setPassword(newPassword);
        String passwordAfterSet = user.getPassword();
        assertEquals(newPassword, passwordAfterSet);
    }

    @Test
    public void validatePassword() {

        User newUser = new User("Aiden", "Password", "Bio");
        boolean b = newUser.validatePassword("Aiden", "Password");
        assertEquals(true, b);

    }

    @Test
    public void validatePasswordWrongUsername() {

        User newUser = new User("John", "Password", "Bio");
        boolean b = newUser.validatePassword("Aiden", "Password");
        assertEquals(false, b);

    }

    @Test
    public void validatePasswordWrongPassword() {

        User newUser = new User("Aiden", "password", "Bio");
        boolean b = newUser.validatePassword("Aiden", "Password");
        assertEquals(false, b);

    }

}
