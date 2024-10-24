import java.util.ArrayList;

public class User implements UserInterface {
    private ArrayList<String> usernames;
    String username;
    private ArrayList<String> passwords;
    String password;
    private ArrayList<String> bios;
    String bio;

    public User(ArrayList usernames, String username, ArrayList passwords,
                String password, ArrayList bios, String bio) {
        this.username = username;
        this.usernames = usernames;
        this.passwords = passwords;
        this.password = password;
        this.bio = bio;
        this.bios = bios;
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

