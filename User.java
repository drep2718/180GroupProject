import java.util.ArrayList;

public class User implements UserInterface {
    private ArrayList<String> usernames;
    String username;
    private ArrayList<String> passwords;
    String password;
    private ArrayList<String> bios;
    String bio;

    public User(String username, String password, String bio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        usernames = new ArrayList<>();
        passwords = new ArrayList<>();
        bios = new ArrayList<>();
    }

    public void createProfile(String username, String password, String bio) {
        usernames.add(username);
        passwords.add(password);
        bios.add(bio);
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
// I dont know if you guys want me to change it so that we check password validity before removing the profile so ill wait on adding it.

    public String findProfile(String username) { // finds profile and prints username. will have to change string format later.
        int index = usernames.indexOf(username);
        if (index != -1) {
            return usernames.get(index); // can return bio too once it finds profile.
        } else {
            return "Profile not found";
        }
    }
    
    public String getBio(String bio) {
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



}

