public interface UserInterface {

     String getUsername();
     void setUsername (String username);
     Boolean checkUsernameAvailability(String username);
     String getPassword();
     void setPassword(String password);
     Boolean validatePassword(String username, String password);
     String getBio();
     void setBio(String bio);
     void createProfile(String username, String password, String bio);
     void updateUsername(String newUsername);
     void updatePassword(String newPassword);
     void updateBio(String newBio);
     String removeProfile(String username, String password);
     String findProfile(String username);

}
