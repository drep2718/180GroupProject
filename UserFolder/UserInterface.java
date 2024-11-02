public interface UserInterface {

     String getUsername();
     void setUsername (String username);
     boolean usernameAvail(String username);
     String getPassword();
     void setPassword(String password);
     boolean validatePassword(String username, String password);
     String getBio();
     void setBio(String bio);
     void createProfile(String username, String password, String bio);
     void updateUsername(String newUsername);
     void updatePassword(String newPassword);
     void updateBio(String newBio);
     void removeProfile(String username);
     String findProfile(String username);

}
