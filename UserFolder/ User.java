import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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

    public User(String username) {
        this.username = username;
        this.usernames = new ArrayList<>();
        this.passwords = new ArrayList<>();
        this.bios = new ArrayList<>();
        this.allUsers = new ArrayList<>();

    }

    public User(String username, String password, String bio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.usernames = new ArrayList<>();
        this.passwords = new ArrayList<>();
        this.bios = new ArrayList<>();
        this.allUsers = new ArrayList<>();

    }

    public User(String username, String password, String bio, BufferedImage image, String filepath, String formatName) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.filepath = filepath;
        this.formatName = formatName;
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



    public User createProfile(String username, String password, String bio) {
        User user = new User(username, password, bio);
        usernames.add(username);
        passwords.add(password);
        bios.add(bio);
        allUsers.add(user);

        saveToFile(user);
        return user;
    }

    public User createProfile(String username, String password, String bio, BufferedImage image, String filepath, String formatName) {
        try {
            formatName = filepath.substring(filepath.lastIndexOf(".") + 1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        User user = new User(username, password, bio, image, filepath, formatName);
        usernames.add(username);
        passwords.add(password);
        bios.add(bio);
        allUsers.add(user);

        saveToFile(user);
        return user;
    }

    public void saveToFile(User user) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Users.txt", true))) {
            bfw.write(user.toString());
            bfw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String path = "";
        if (filepath != null) {
            path = "," + filepath;
        }
        return username + "," + password + "," + bio + "," + path;
    }



    public void removeProfile(String username) {
        int index = usernames.indexOf(username);
        if (index != -1) {
            usernames.remove(index);
            passwords.remove(index);
            bios.remove(index);

            for (User user : allUsers) {
                if (user.toString().contains(this.username)) {
                    allUsers.remove(user);
                }
            }

            rewriteUsers();


        } else {
            System.out.println("User not found!");
        }
    }

    public void rewriteUsers() {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Users.txt", false))) {
            for (int i = 0; i < allUsers.size(); i++) {
                User user = allUsers.get(i);
                bfw.write(user.toString());
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsers() { 
        try (BufferedReader bfr = new BufferedReader(new FileReader("Users.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                String bio = parts[2];

                    usernames.add(username);
                    passwords.add(password);
                    bios.add(bio);

                  if (parts.length > 3) {
                      String filepath = parts[3];
                      BufferedImage image = ImageIO.read(new File(filepath));

                      User user = new User(username, password, bio, image, filepath, filepath.substring(filepath.lastIndexOf('.') + 1));
                      allUsers.add(user);
                } else {
                      User user = new User(username, password, bio);
                      allUsers.add(user);
                  }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateUsername(String newUsername) {
        int index = usernames.indexOf(this.username);
        if (index != -1) {
            usernames.set(index, newUsername);
            for (User user : allUsers) {
                if (user.toString().contains(this.username)) {
                    allUsers.remove(user);
                }
            }

            User updatedUser = new User(newUsername, this.password, this.bio);
            allUsers.add(updatedUser);
            this.username = newUsername;
            rewriteUsers();

        } else {
            System.out.println("No Username Found");
        }
    }

    public void updateBio(String newBio) {
        int index = bios.indexOf(this.bio);
        if (index != -1) {
            bios.set(index, newBio);
            for (User user : allUsers) {
                if (user.toString().contains(this.bio)) {
                    allUsers.remove(user);
                }
            }

            User updatedUser = new User(this.username, this.password, newBio);
            allUsers.add(updatedUser);
            this.bio = newBio;
            rewriteUsers();

        } else {
            System.out.println("No Bio Found");
        }
    }

    public void updatePassword(String newPassword) {
        int index = passwords.indexOf(this.password);
        if (index != -1) {
            passwords.set(index, newPassword);
            for (User user : allUsers) {
                if (user.toString().contains(this.password)) {
                    allUsers.remove(user);
                }
            }

            User updatedUser = new User(this.username, newPassword, this.bio);
            allUsers.add(updatedUser);
            this.password = newPassword;
            rewriteUsers();

        } else {
            System.out.println("No Password Found");
        }
    }


    public String findProfile(String username) { 
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
