import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


/**
 * Team Project -- Client Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */

public class Client extends Thread implements FlagInterface {
    private final Server server;
    public static String loop;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
        Scanner scan = new Scanner(System.in);
        boolean loggedIn = false;


        int portNumber = 2727;
        Socket socket;
        String host = "localhost";

        try {
            socket = new Socket(host, portNumber);
        } catch (IOException e) {
            return;
        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        try {

            while (true) {


                while (!loggedIn) {
                    System.out.println("1- Login to account");
                    System.out.println("2- Create account");
                    System.out.println("3- Exit");

                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                    while ((ComplexGUI.firstMenuItemGUI == null || ComplexGUI.firstMenuItemGUI.isEmpty())) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while waiting for input.", "Error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                            return;
                        }
                    }

                    String firstMenuItem = ComplexGUI.firstMenuItemGUI;

                    if (!firstMenuItem.equals("1") && !firstMenuItem.equals("2") && !firstMenuItem.equals("3")) {
                        writer.println("LOOP");
                        writer.flush();
                        String response = reader.readLine();
                        if (response.equals("CONTINUE")) {
                            continue;
                        }
                    } else if (firstMenuItem.equals("1")) {

                        while ((ComplexGUI.usernameGUI == null || ComplexGUI.usernameGUI.isEmpty()) ||
                                (ComplexGUI.passwordGUI == null || ComplexGUI.passwordGUI.isEmpty())) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                JOptionPane.showMessageDialog(null, "An error occurred while waiting for input.", "Error", JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();
                                return;
                            }
                        }

                        System.out.println("Enter your username");
                        String username = ComplexGUI.usernameGUI;


                        System.out.println("Enter your password");
                        String password = ComplexGUI.passwordGUI;

                        System.out.println(password);
                        if (password.equals("back")) {
                            ComplexGUI.passwordGUI = null;
                            ComplexGUI.usernameGUI = null;
                            ComplexGUI.firstMenuItemGUI = null;
                            writer.println("LOOP");
                            writer.flush();
                            String response = reader.readLine();
                            if (response.equals("CONTINUE")) {
                                continue;
                            }
                        }

                        String login = LOGIN + ";" + username + ";" + password;
                        writer.write(login);
                        writer.println();
                        writer.flush();
                        String message = reader.readLine();

                        if (message.contains(LOGIN)) {
                            String[] index = message.split(";");
                            String passwordMessage = index[1];
                            if (passwordMessage.equals("Login Successful")) {
                                System.out.println("Login Successful");
                            } else if (passwordMessage.equals("Login Failed")) {
                                loop = "no";
                                JOptionPane.showMessageDialog(null, "Error: Try again", null, JOptionPane.ERROR_MESSAGE);
                                System.out.println("Login Failed Try Again");
                                SwingUtilities.invokeLater(() -> new loginMenu().setVisible(true));
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.passwordGUI = null;
                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response.equals("CONTINUE")) {
                                    continue;
                                }
                            } else if (passwordMessage.equals("Missing credentials")) {
                                loop = "no";
                                JOptionPane.showMessageDialog(null, "Error: Missing credentials", null, JOptionPane.ERROR_MESSAGE);
                                System.out.println("Missing credentials");
                                SwingUtilities.invokeLater(() -> new loginMenu().setVisible(true));
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.passwordGUI = null;
                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response == null || response.equals("CONTINUE")) {
                                    continue;
                                }
                            } else {
                                loop = "no";
                                JOptionPane.showMessageDialog(null, "Error: Try again", null, JOptionPane.ERROR_MESSAGE);
                                System.out.println("Failure try again");
                                SwingUtilities.invokeLater(() -> new loginMenu().setVisible(true));
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.passwordGUI = null;
                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response == null || response.equals("CONTINUE")) {
                                    continue;
                                }
                            }

                        }
                        loggedIn = true;
                        JOptionPane.showMessageDialog(null, "Successful Login", null, JOptionPane.INFORMATION_MESSAGE);
                        SwingUtilities.invokeLater(() -> new mainMenu1().setVisible(true));


                    } else if (firstMenuItem.equals("2")) {

                        while ((ComplexGUI.usernameGUI == null || ComplexGUI.usernameGUI.isEmpty()) ||
                                (ComplexGUI.passwordGUI == null || ComplexGUI.passwordGUI.isEmpty()) ||
                                (ComplexGUI.bioGUI == null || ComplexGUI.bioGUI.isEmpty())) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                JOptionPane.showMessageDialog(null, "An error occurred while waiting for input.", "Error", JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();
                                return;
                            }
                        }
                        System.out.println("Enter you new username");
                        String newUsername = ComplexGUI.usernameGUI;


                        System.out.println("Enter your new password");
                        String newPassword = ComplexGUI.passwordGUI;

                        System.out.println(newPassword);
                        if (newPassword.equals("back")) {
                            ComplexGUI.passwordGUI = null;
                            ComplexGUI.usernameGUI = null;
                            ComplexGUI.firstMenuItemGUI = null;
                            writer.println("LOOP");
                            writer.flush();
                            String response = reader.readLine();
                            if (response.equals("CONTINUE")) {
                                continue;
                            }
                        }


                        System.out.println("Enter your bio");
                        String newBio = ComplexGUI.bioGUI;


                        String create = CREATE + ";" + newUsername + ";" + newPassword + ";" + newBio;
                        writer.write(create);
                        writer.println();
                        writer.flush();

                        String message = reader.readLine();

                        if (message.contains(CREATE)) {
                            String[] index = message.split(";");
                            String successMessage = index[1];
                            System.out.println("message h" + successMessage);
                            if (successMessage.equals("true")) {
                                System.out.println("User Successfully Created");
                            } else if (successMessage.equals("Taken")) {
                                JOptionPane.showMessageDialog(null, "Username, Already Taken", null, JOptionPane.ERROR_MESSAGE);
                                SwingUtilities.invokeLater(() -> new createMenu().setVisible(true));
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.passwordGUI = null;
                                ComplexGUI.bioGUI = null;
                                System.out.println("User does not exist. You may create User");
                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response.equals("CONTINUE")) {
                                    continue;
                                }
                            } else if (successMessage.equals("false")) {
                                JOptionPane.showMessageDialog(null, "Error: Try again", null, JOptionPane.ERROR_MESSAGE);
                                SwingUtilities.invokeLater(() -> new createMenu().setVisible(true));
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.passwordGUI = null;
                                ComplexGUI.bioGUI = null;
                                System.out.println("User does not exist. You may create User");
                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response.equals("CONTINUE")) {
                                    continue;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error: Try again", null, JOptionPane.ERROR_MESSAGE);
                                SwingUtilities.invokeLater(() -> new createMenu().setVisible(true));
                                firstMenuItem = "2";
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.passwordGUI = null;
                                ComplexGUI.bioGUI = null;
                                System.out.println("Failure try again");
                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response == null || response.equals("CONTINUE")) {
                                    continue;
                                }
                            }

                        }
                        loggedIn = true;
                        JOptionPane.showMessageDialog(null, "You successfully created an account", null, JOptionPane.INFORMATION_MESSAGE);
                        SwingUtilities.invokeLater(() -> new mainMenu1().setVisible(true));

                    } else if (firstMenuItem.equals("3")) {
                        return;
                    }

                    while (ComplexGUI.back == null || ComplexGUI.back.isEmpty()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            JOptionPane.showMessageDialog(null, "An error occurred while waiting for input.", "Error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                            return;
                        }
                    }

                    String back = ComplexGUI.back;
                    if (back.equals("true")) {
                        writer.println("LOOP");
                        writer.flush();
                        String response = reader.readLine();
                        if (response == null || response.equals("CONTINUE")) {
                            continue;
                        }
                    }

                    while (loggedIn) {
                        System.out.println("1- Add or remove friends");
                        System.out.println("2- block or unblock users");
                        System.out.println("3- Message");

                        while ((ComplexGUI.secondMenuItem == null || ComplexGUI.secondMenuItem.isEmpty())) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                JOptionPane.showMessageDialog(null, "An error occurred while waiting for input.", "Error", JOptionPane.ERROR_MESSAGE);
                                e.printStackTrace();
                                return;
                            }
                        }

                        String secondMenuItem = ComplexGUI.secondMenuItem;
                        System.out.println("second item" + secondMenuItem);

                        if (!secondMenuItem.equals("1") && !secondMenuItem.equals("2") && !secondMenuItem.equals("3") && !secondMenuItem.equals("4")) {
                            System.out.println("Not a valid choice");
                            writer.println("LOOP");
                            writer.flush();
                            String response = reader.readLine();
                            if (response.equals("CONTINUE")) {
                                continue;
                            }
                        } else if (secondMenuItem.equals("1")) {
                            writer.println(secondMenuItem);
                            writer.flush();
                            System.out.println("1- Add Friend");
                            System.out.println("2- Remove Friend");
                            String addOrRemove = scan.nextLine();
                            if (addOrRemove.equals("1")) {
                                System.out.println("What is the name of the user you want to add as a friend");
                                String friendName = scan.nextLine();
                                String friendToAdd = FRIENDS_ADD + ";" + friendName;
                                writer.println(friendToAdd);
                                writer.flush();
                            } else if (addOrRemove.equals("2")) {
                                System.out.println("What is the name of the user you want to remove as a friend");
                                String friendName = scan.nextLine();
                                String friendToBlock = FRIENDS_REMOVE + ";" + friendName;
                                writer.write(friendToBlock);
                                writer.println();
                                writer.flush();
                            }

                            String message = reader.readLine();
                            if (message == null || message.isEmpty()) {
                                System.out.println("No response from server. Please try again.");
                                continue;
                            }
                            String[] index = message.split(";");
                            String trueOrFalse = index[1];
                            if (message.contains(FRIENDS_ADD)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("Friend added Successfully");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You, are already friends");
                                    writer.println("LOOP");
                                    writer.flush();
                                    String response = reader.readLine();
                                    if (response.equals("CONTINUE")) {
                                        continue;
                                    }
                                } else {
                                    System.out.println("Friend does not exist, try again");
                                    writer.println("LOOP");
                                    writer.flush();
                                    String response = reader.readLine();
                                    if (response.equals("CONTINUE")) {
                                        continue;
                                    }
                                }
                            } else if (message.contains(FRIENDS_REMOVE)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("Friend removed Successfully");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("Cannot remove friend because he is not your friend");
                                    writer.println("LOOP");
                                    writer.flush();
                                    String response = reader.readLine();
                                    if (response.equals("CONTINUE")) {
                                        continue;
                                    }
                                } else {
                                    System.out.println("Friend does not exist, try again");
                                    writer.println("LOOP");
                                    writer.flush();
                                    String response = reader.readLine();
                                    if (response.equals("CONTINUE")) {
                                        continue;
                                    }
                                }
                            }


                        } else if (secondMenuItem.equals("2")) {
                            writer.println(secondMenuItem);
                            writer.flush();
                            System.out.println("1- Block User");
                            System.out.println("2- Unblock User");
                            String blockOrUnblock = scan.nextLine();
                            if (blockOrUnblock.equals("1")) {
                                System.out.println("What is the username of the user you want to block");
                                String whoToBlock = scan.nextLine();
                                String blocked = FRIENDS_BLOCK + ";" + whoToBlock;
                                writer.write(blocked);
                                writer.println();
                                writer.flush();
                            } else if (blockOrUnblock.equals("2")) {
                                System.out.println("What is the username of the user you want to unblock");
                                String whoToUnblock = scan.nextLine();
                                String unblock = FRIENDS_UNBLOCK + ";" + whoToUnblock;
                                writer.write(unblock);
                                writer.println();
                                writer.flush();
                            }

                            String message1 = reader.readLine();
                            String[] index = message1.split(";");
                            String trueOrFalse = index[1];
                            if (message1.contains(FRIENDS_BLOCK)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("Friend blocked Successfully");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("User is Already Blocked");
                                    writer.println("LOOP");
                                    writer.flush();
                                    continue;
                                } else {
                                    System.out.println("Friend does not exist, try again");
                                    writer.println("LOOP");
                                    writer.flush();
                                    continue;
                                }
                            } else if (message1.contains(FRIENDS_UNBLOCK)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("Friend unblocked Successfully");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("Cannot unblock friend, try again");
                                    writer.println("LOOP");
                                    writer.flush();
                                    continue;
                                } else {
                                    System.out.println("Friend does not exist, try again");
                                    writer.println("LOOP");
                                    writer.flush();
                                    continue;
                                }
                            }

                        } else if (secondMenuItem.equals("3")) {
                            writer.println(secondMenuItem);
                            writer.flush();
                            System.out.println("1- Send a text message");
                            System.out.println("2- Send a photo message");
                            System.out.println("3- Delete text message");
                            String whichMessage = ComplexGUI.whichMessage;
                            if (whichMessage.equals("1")) {
                                System.out.println("1- Send text message to all friends");
                                System.out.println("2- Send text message to all users");
                                System.out.println("3- send text message to single friend");
                                String textType = scan.nextLine();
                                if (textType.equals("1")) {
                                    System.out.println("What would you like to text to all your friends");
                                    String text = ComplexGUI.message;
                                    String textALlFriends = TEXT_ALL_FRIENDS + ";" + text;
                                    writer.write(textALlFriends);
                                    writer.println();
                                    writer.flush();
                                } else if (textType.equals("2")) {
                                    System.out.println("What would you like to text to all your users");
                                    String text = ComplexGUI.message;
                                    String textAllUsers = TEXT_ALL_USERS + ";" + text;
                                    writer.write(textAllUsers);
                                    writer.println();
                                    writer.flush();
                                } else if (textType.equals("3")) {
                                    System.out.println("Who is the friend you want to text");
                                    String friend = ComplexGUI.friend;
                                    System.out.println("What would you like to text " + friend);
                                    String text = ComplexGUI.message;
                                    String textSingleFriend = TEXT_SINGLE_FRIEND + ";" + friend + ";" + text;
                                    writer.write(textSingleFriend);
                                    writer.println();
                                    writer.flush();
                                }


                            } else if (whichMessage.equals("2")) { 
                                SwingUtilities.invokeLater(() -> new photoMenu().setVisible(true));

                                while (ComplexGUI.photoMessageTypeGUI == null || ComplexGUI.photoPathGUI == null) {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                String photoType = ComplexGUI.photoMessageTypeGUI;
                                File photoFile = new File(ComplexGUI.photoPathGUI);

                                try {
                                    BufferedImage image = ImageIO.read(photoFile);

                                    if (photoType.equals("1")) {
                                        String friendUsername = ComplexGUI.photoRecipientGUI;
                                        String photoMessage = PHOTO_SINGLE_FRIEND + ";" + friendUsername;
                                        writer.println(photoMessage);
                                        writer.flush();

                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        ImageIO.write(image, "png", baos);
                                        byte[] imageBytes = baos.toByteArray();
                                        writer.println(imageBytes.length);
                                        writer.flush();

                                        socket.getOutputStream().write(imageBytes);
                                        socket.getOutputStream().flush();

                                        String response = reader.readLine();
                                        if (response.contains(PHOTO_SINGLE_FRIEND)) {
                                            String[] parts = response.split(";");
                                            if (parts[1].equals("true")) {
                                                System.out.println("Photo sent to friend successfully");
                                            } else {
                                                System.out.println("Failed to send photo to friend");
                                                writer.println("LOOP");
                                                writer.flush();
                                                String loopResponse = reader.readLine();
                                                if (loopResponse.equals("CONTINUE")) {
                                                    continue;
                                                }
                                            }
                                        }

                                    } else if (photoType.equals("2")) { 
                                        String photoMessage = PHOTO_ALL_FRIENDS;
                                        writer.println(photoMessage);
                                        writer.flush();

                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        ImageIO.write(image, "png", baos);
                                        byte[] imageBytes = baos.toByteArray();
                                        writer.println(imageBytes.length);
                                        writer.flush();

                                        socket.getOutputStream().write(imageBytes);
                                        socket.getOutputStream().flush();

                                        String response = reader.readLine();
                                        if (response.contains(PHOTO_ALL_FRIENDS)) {
                                            String[] parts = response.split(";");
                                            if (parts[1].equals("true")) {
                                                System.out.println("Photo sent to all friends successfully");
                                            } else {
                                                System.out.println("Failed to send photo to all friends");
                                                writer.println("LOOP");
                                                writer.flush();
                                                String loopResponse = reader.readLine();
                                                if (loopResponse.equals("CONTINUE")) {
                                                    continue;
                                                }
                                            }
                                        }

                                    } else if (photoType.equals("3")) { 
                                        String photoMessage = PHOTO_ALL_USERS;
                                        writer.println(photoMessage);
                                        writer.flush();

                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        ImageIO.write(image, "png", baos);
                                        byte[] imageBytes = baos.toByteArray();
                                        writer.println(imageBytes.length);
                                        writer.flush();

                                        socket.getOutputStream().write(imageBytes);
                                        socket.getOutputStream().flush();

                                        String response = reader.readLine();
                                        if (response.contains(PHOTO_ALL_USERS)) {
                                            String[] parts = response.split(";");
                                            if (parts[1].equals("true")) {
                                                System.out.println("Photo sent to all users successfully");
                                            } else {
                                                System.out.println("Failed to send photo to all users");
                                                writer.println("LOOP");
                                                writer.flush();
                                                String loopResponse = reader.readLine();
                                                if (loopResponse.equals("CONTINUE")) {
                                                    continue;
                                                }
                                            }
                                        }
                                    }

                                    ComplexGUI.photoPathGUI = null;
                                    ComplexGUI.photoMessageTypeGUI = null;
                                    ComplexGUI.photoRecipientGUI = null;
                                    ComplexGUI.whichMessage = null;
                                    
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println("Error processing photo");
                                    writer.println("LOOP");
                                    writer.flush();
                                    String response = reader.readLine();
                                    if (response.equals("CONTINUE")) {
                                        continue;
                                    }
                                }

                                writer.println("LOOP");
                                writer.flush();
                                String response = reader.readLine();
                                if (response.equals("CONTINUE")) {
                                    continue;
                                }

                            } else if (whichMessage.equals("3")) {
                                System.out.println("1- Delete message to all friends");
                                System.out.println("2- Delete message to all users");
                                System.out.println("3- Delete message to single friend");

                                String textType = scan.nextLine();

                                if (textType.equals("1")) {
                                    System.out.println("What message to all your friends would you like to delete");
                                    String text = ComplexGUI.message;
                                    String messageALlFriends = DELETE_ALL_FRIENDS + ";" + text;
                                    writer.write(messageALlFriends);
                                    writer.println();
                                    writer.flush();
                                } else if (textType.equals("2")) {
                                    System.out.println("What message to all your users would you like to delete");
                                    String text = ComplexGUI.message;
                                    String messageAllUsers = DELETE_ALL_USERS + ";" + text;
                                    writer.write(messageAllUsers);
                                    writer.println();
                                    writer.flush();
                                } else if (textType.equals("3")) {
                                    System.out.println("Who would you like to delete the message from");
                                    String friend = ComplexGUI.friend;
                                    System.out.println("What did the message say");
                                    String text = ComplexGUI.message;
                                    String textSingleFriend = DELETE_SINGLE_FRIEND + ";" + friend + ";" + text;
                                    writer.write(textSingleFriend);
                                    writer.println();
                                    writer.flush();
                                }

                                String message2 = reader.readLine();
                                System.out.println(message2);
                                String[] index = message2.split(";");
                                String trueOrFalse = index[1];
                                if (message2.contains(TEXT_ALL_FRIENDS)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully texted all friends");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot send a message to all friends, try again");
                                        continue;
                                    } else {
                                        System.out.println("No friends exist");
                                        continue;
                                    }
                                } else if (message2.contains(TEXT_ALL_USERS)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully texted all users");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot send a message to all users, try again");
                                        continue;
                                    } else {
                                        System.out.println("Failure");
                                        continue;
                                    }
                                } else if (message2.contains(TEXT_SINGLE_FRIEND)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully texted a friend");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot send a message to this friend, try again");
                                        continue;
                                    } else {
                                        System.out.println("Not your friend");
                                        continue;
                                    }
                                } else if (message2.contains(MESSAGE_ALL_FRIENDS)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully messaged all friends");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot send a messages to all friends");
                                        continue;
                                    } else {
                                        System.out.println("No friends");
                                        continue;
                                    }
                                } else if (message2.contains(MESSAGE_ALL_USERS)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully messaged all users");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot send a messages to all users");
                                        continue;
                                    } else {
                                        System.out.println("Failure");
                                        continue;
                                    }
                                } else if (message2.contains(MESSAGE_SINGLE_FRIEND)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully messaged a friend");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot message this friend");
                                        continue;
                                    } else {
                                        System.out.println("Failure");
                                        continue;
                                    }
                                } else if (message2.contains(DELETE_ALL_FRIENDS)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully deleted all friends message");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot message this friend");
                                        continue;
                                    } else {
                                        System.out.println("Failure");
                                        continue;
                                    }
                                } else if (message2.contains(DELETE_ALL_USERS)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully deleted all users message");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot message this friend");
                                        continue;
                                    } else {
                                        System.out.println("Failure");
                                        continue;
                                    }
                                } else if (message2.contains(DELETE_SINGLE_FRIEND)) {
                                    if (trueOrFalse.equals("true")) {
                                        System.out.println("You successfully deleted a message");
                                    } else if (trueOrFalse.equals("false")) {
                                        System.out.println("You cannot delete this message");
                                        continue;
                                    } else {
                                        System.out.println("Failure");
                                        continue;
                                    }
                                }


                            }
                            if (ComplexGUI.secondMenuItem.equals("4") || ComplexGUI.logout != null || ComplexGUI.logout.equals("logout")) {
                                System.out.println("Logging out...");
                                writer.println("LOOP");
                                writer.flush();

                                ComplexGUI.logout = null;
                                ComplexGUI.passwordGUI = null;
                                ComplexGUI.usernameGUI = null;
                                ComplexGUI.firstMenuItemGUI = null;
                                ComplexGUI.secondMenuItem = null;

                                JOptionPane.showMessageDialog(null, "You have been logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);

                                loggedIn = false;
                                continue;
                            }
                        }


                    }
                }
            }
        } finally {
            writer.close();
            reader.close();
            socket.close();
        }
    }

    public Client(Server server) {
        this.server = server;
    }


}
