import java.util.ArrayList;

public class User implements UserInterface {
    ArrayList<String> usernames;
    private String username;
    ArrayList<String> passwords;
    private String password;
    ArrayList<String> bios;
    private String bio;
    
    public User(String username, String password, String bio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
    }

    public static void createProfile(String username, String password, String bio) {
        User username = new User(username, password, bio);
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

