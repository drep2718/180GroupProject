import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Team Project -- User
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */


public class User implements UserInterface {
    private String username;
    private String password;
    private String bio;
    private BufferedImage image;
    private String filepath;
    private String formatName;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<BufferedImage> images = new ArrayList<>();
    private static ArrayList<String> bios = new ArrayList<>();
    private static ArrayList<String> passwords = new ArrayList<>();
    private static ArrayList<String> usernames = new ArrayList<>();
    private static final Object gatekeeper = new Object();
    public static boolean usernameTaken = false;

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<String> getUsernames() {
        return usernames;
    }

    public static ArrayList<String> getPasswords() {
        return passwords;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getFormatName() {
        return formatName;
    }

    public User(String username) {
        this.username = username;

    }

    public User(String username, String password, String bio) {
        this.username = username;
        this.password = password;
        this.bio = bio;


    }

    public User(String username, String password, String bio,
                BufferedImage image, String filepath, String formatName) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.filepath = filepath;
        this.formatName = formatName;
        this.image = imageSave(image, filepath, formatName);


    }

    public BufferedImage imageSave(BufferedImage image, String filepath, String formatName) {
        synchronized (gatekeeper) {
            try {
                File pathToImage = new File(filepath);
                ImageIO.write(image, formatName, pathToImage);
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    public User createProfile(String username, String password, String bio) {
        synchronized (gatekeeper) {
            loadUsers();
            for (User user : allUsers) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Username already taken");
                    usernameTaken = true;
                    return null;
                }
            }
            User user = new User(username, password, bio);
            usernames.add(username);
            passwords.add(password);
            bios.add(bio);
            allUsers.add(user);

            saveToFile(user);
            return user;
        }
    }

    public User createProfile(String username, String password, String bio,
                              BufferedImage image, String filepath, String formatName) {

        User user = new User(username, password, bio, image, filepath, formatName);
        synchronized (gatekeeper) {
            usernames.add(username);
            passwords.add(password);
            bios.add(bio);
            allUsers.add(user);
        }

        saveToFile(user);
        return user;
    }

    public void saveToFile(User user) {
        synchronized (gatekeeper) {
            try (BufferedWriter bfw =
                         new BufferedWriter(new FileWriter("Users.txt", true))) {
                bfw.write(user.toString());
                bfw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        String path = "";
        if (filepath != null) {
            path = "," + filepath;
        }
        return username + "," + password + "," + bio + path;
    }


    public void removeProfile(String username) {
        synchronized (gatekeeper) {
            int index = usernames.indexOf(username);
            if (index != -1) {
                usernames.remove(index);
                passwords.remove(index);
                bios.remove(index);

                ArrayList<User> deleted = new ArrayList<>();
                for (User user : allUsers) {
                    if (user.toString().contains(this.username)) {
                        deleted.add(user);
                    }
                }

                allUsers.remove(deleted);
                rewriteUsers();


            } else {
                System.out.println("User not found!");
            }
        }
    }


    public void loadUsers() {
        synchronized (gatekeeper) {
            usernames.clear();
            passwords.clear();
            bios.clear();
            allUsers.clear();

            try (BufferedReader bfr = new BufferedReader(new FileReader("Users.txt"))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String username = parts[0];
                        String password = parts[1];
                        String bio = parts[2];

                        usernames.add(username);
                        passwords.add(password);
                        bios.add(bio);

                        User user;
                        if (parts.length > 3) {
                            String filepath = parts[3];
                            try {
                                BufferedImage image = ImageIO.read(new File(filepath));
                                String formatName = filepath.substring(filepath.lastIndexOf('.') + 1);
                                user = new User(username, password, bio, image, filepath, formatName);
                            } catch (IOException e) {
                                user = new User(username, password, bio);
                                System.err.println("Warning: Could not load image for user " + username);
                            }
                        } else {
                            user = new User(username, password, bio);
                        }
                        allUsers.add(user);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error loading users: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void updateUsername(String newUsername) {
        int index = usernames.indexOf(this.username);
        if (index != -1) {
            usernames.set(index, newUsername);
            ArrayList<User> deleted = new ArrayList<>();
            for (User user : allUsers) {
                if (user.toString().contains(this.username)) {
                    deleted.add(user);
                }
            }

            allUsers.remove(deleted);

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
            ArrayList<User> deleted = new ArrayList<>();
            for (User user : allUsers) {
                if (user.toString().contains(this.bio)) {
                    deleted.add(user);
                }
            }

            allUsers.remove(deleted);


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
            ArrayList<User> deleted = new ArrayList<>();
            for (User user : allUsers) {
                if (user.toString().contains(this.password)) {
                    deleted.add(user);
                }
            }

            allUsers.remove(deleted);

            User updatedUser = new User(this.username, newPassword, this.bio);
            allUsers.add(updatedUser);
            this.password = newPassword;
            rewriteUsers();

        } else {
            System.out.println("No Password Found");
        }
    }

    // -------------------------------------------------------------

    public String findProfile(String username) { // finds profile and prints username. will have to change string format later.
        int index = usernames.indexOf(username);
        if (index != -1) {
            return "Name: " + usernames.get(index) + "\nBio: " + bios.get(index);
        } else {
            return "Profile not found";
        }
    }

    public boolean userExists(String username) {
        return usernames.contains(username);
    }

    public boolean passwordExists(String password) {
        return passwords.contains(password);
    }

    public boolean bioExists(String bio) {
        return bios.contains(bio);
    }

    public boolean usernameAvail(String username) {
        return !usernames.contains(username);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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
        if ((password.equals(this.password) && (username.equals(this.username)))) {
            return true;
        } else if ((!password.equals(this.password))) {
            System.out.println("Wrong Password!");
            return false;
        } else if ((!(username.equals(this.username)))) {
            System.out.println("Wrong Username!");
            return false;
        }
        return false;
    }

    public void rewriteUsers() {

        synchronized (gatekeeper) {
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("Users.txt", false))) {
                for (User user : allUsers) {
                    bfw.write(user.toString());
                    bfw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        User otherUser = (User) obj;
        if (this.username == null) {
            return otherUser.username == null;
        }
        return this.username.equals(otherUser.username);
    }

    public boolean isFriend1(Friends friend) {
        for (User user : Friends.getFriendsList()) {
            String username = user.getUsername();
            if (username.equals(friend.getUser().getUsername())) {
                return true;
            }
        }
        return false;
    }

}


