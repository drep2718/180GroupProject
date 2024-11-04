import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest implements UserTestInterface{
    private User user = new User("Username", "Password", "Bio");
    BufferedImage image;





    @Test
    public void createBasicProfile() {

        User newUser = user.createProfile("Username", "Password" , "Bio");
        assertEquals("Username", newUser.getUsername());
        assertEquals("Password", newUser.getPassword());
        assertEquals("Bio", newUser.getBio());

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
            assertEquals("Username,Password,Bio", lastLine);

        } catch (IOException e) {
            e.getMessage();
        }
    }




    @Test
    public void testToStringBasicUser() {

        User newUser = user.createProfile("Username", "Password", "Bio");
        String test = newUser.toString();
        assertEquals("Username,Password,Bio", test);
    }

    @Test
    public void testToStringPfpUser () {  // fix buffered Image
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
        User newUser = user.createProfile("Username", "oldPassword", "Bio");
        assertTrue(newUser.userExists("Username"));
        assertTrue(newUser.passwordExists("oldPassword"));
        assertTrue(newUser.bioExists("Bio"));
        String newPassword = "Password";
        newUser.updatePassword(newPassword);
        assertFalse(newUser.passwordExists("oldPassword"));
        assertTrue(newUser.userExists("Username"));
        assertTrue(newUser.passwordExists("Password"));
        assertTrue(newUser.bioExists("Bio"));

    }

    @Test
    public void updateBio() {
        User newUser = user.createProfile("Username", "Password", "oldBio");
        assertTrue(newUser.userExists("Username"));
        assertTrue(newUser.passwordExists("Password"));
        assertTrue(newUser.bioExists("oldBio"));
        String newBio = "Bio";
        newUser.updateBio(newBio);
        assertFalse(newUser.bioExists("oldBio"));
        assertTrue(newUser.userExists("Username"));
        assertTrue(newUser.passwordExists("Password"));
        assertTrue(newUser.bioExists("Bio"));
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
        User user = new User ("John");
        boolean b = user.usernameAvail("Aiden");
        assertEquals(false, b);

    }

    @Test
    public void usernameAvail() {
        User newUser = user.createProfile("Aiden", "Password", "Bio");
        User user = new User ("John");
        boolean b = user.usernameAvail("Matt");
        assertEquals(true, b);

    }

    @Test
    public void setImage() {
        User user = new User ("Aiden");
        BufferedImage image1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        user.setImage(image1);
        assertEquals(image1, user.getImage());
    }

    @Test
    public void getImage() {
        User user = new User ("Aiden");
        BufferedImage image1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        user.setImage(image1);
        BufferedImage getImage = user.getImage();
        assertEquals(getImage, image1);
    }

    @Test
    public void getBio() {
        User user = new User ("Aiden","Password","Bio");
        String bio = "Bio";
        assertEquals(bio, user.getBio());
    }

    @Test
    public void setBio() {
        User user = new User ("Aiden","Password","Bio");
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
        User user = new User ("Aiden");
        String newUsername = "username";
        user.setUsername(newUsername);
        String usernameFromGet = user.getUsername();
        assertEquals(newUsername, usernameFromGet);
    }

    @Test
    public void getPassword() {
        User user = new User ("Aiden","Password","Bio");
        String password = "Password";
        assertEquals(password, user.getPassword());
    }

    @Test
   public void setPassword() {
        User user = new User ("Aiden","Password","Bio");
        String newPassword = "Password";
        user.setPassword(newPassword);
        String passwordAfterSet = user.getPassword();
        assertEquals(newPassword, passwordAfterSet);
    }

    @Test
    public void validatePassword() {

    User newUser = new User ("Aiden", "Password", "Bio");
    boolean b = newUser.validatePassword("Aiden","Password");
    assertEquals(true, b);

    }

    @Test
    public void validatePasswordWrongUsername() {

        User newUser = new User ("John", "Password", "Bio");
        boolean b = newUser.validatePassword("Aiden","Password");
        assertEquals(false, b);

    }

    @Test
    public void validatePasswordWrongPassword() {

        User newUser = new User ("Aiden", "password", "Bio");
        boolean b = newUser.validatePassword("Aiden","Password");
        assertEquals(false, b);

    }

    }
