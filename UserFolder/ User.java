import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User implements UserInterface {
    private String username;
    private String password;
    private String bio;
    private BufferedImage image;
    private String filepath;
    private String formatName;
    private ArrayList<User> allUsers;
    private ArrayList<BufferedImage> images;
    private ArrayList<String> bios;
    private ArrayList<String> passwords;
    private ArrayList<String> usernames;

    public User(String username, String password, String bio, BufferedImage image) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.usernames = new ArrayList<>();
        this.passwords = new ArrayList<>();
        this.bios = new ArrayList<>();
        this.images = new ArrayList<>();
        this.allUsers = new ArrayList<>();

    }

    public User(String username, String password, String bio, BufferedImage image, String filepath, String formatName) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = imageSave(image, filepath, formatName);
        this.usernames = new ArrayList<>();
        this.passwords = new ArrayList<>();
        this.bios = new ArrayList<>();
        this.images = new ArrayList<>();
        this.allUsers = new ArrayList<>();
    }

    public BufferedImage imageSave(BufferedImage image, String filepath, String formatName ) {
        try {
            File pathToImage = new File(filepath);
            ImageIO.write(image, formatName, pathToImage);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createProfile(String username, String password, String bio, BufferedImage image) {
        User user = new User(username, password, bio, image);
        usernames.add(username);
        passwords.add(password);
        bios.add(bio);
        allUsers.add(user);


        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Users.txt", true))) {
            bfw.write(user.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return username + "," + password + "," + bio + "," + filepath;
    }

    public void removeProfile(String username) {
        int index = usernames.indexOf(username);
        if (index != -1) {
            usernames.remove(index);
            passwords.remove(index);
            bios.remove(index);
        } else {
            System.out.println("User not found!");
        }
    }

    public String findProfile(String username) { // finds profile and prints username. will have to change string format later.
        int index = usernames.indexOf(username);
        if (index != -1) {
            return "Name: " + usernames.get(index) + "\nBio: " + bios.get(index);
        } else {
            return "Profile not found";
        }
    }

    public boolean usernameAvail(String username){
        return usernames.contains(username);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage () {
        return image;
    }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public void updateUsername(String newUsername) {
        this.username = newUsername;
    }

    public void updateBio(String newBio) {
        this.bio = newBio;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;

    }

    // Password Methods

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public boolean validatePassword(String username, String password) {
        if ((password.equals(this.password) && (passwords.contains(password))
                && (username.equals(this.username)) && usernames.contains(username))) {
            return true;
        } else if ((!password.equals(this.password)) || (!passwords.contains(password))) {
            System.out.println("Wrong Password!");
            return false;
        } else if ((!usernames.contains(username)) || (!(username.equals(this.username)))) {
            System.out.println("Wrong Username!");
            return false;
        }
        return false;
    }



}
