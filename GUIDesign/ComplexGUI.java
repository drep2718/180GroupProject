import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComplexGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
    }
}

class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        setTitle("Welcome");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon("/Users/aidendrep/Downloads/PurduePete1.png");
        Image scaledImage = logo.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledLogo, SwingConstants.CENTER);


        JLabel welcomeLabel = new JLabel("Welcome to Boiler Chat!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(logoLabel, BorderLayout.NORTH);
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setLayout(new BorderLayout());
        bottomBar.setPreferredSize(new Dimension(getWidth(), 100));

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.PLAIN, 16));
        continueButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new mainMenu().setVisible(true));
            dispose();
        });
        bottomBar.add(continueButton, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }
}


class mainMenu extends JFrame {
    public mainMenu() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Home Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 400));
        bottomBar.setLayout(new GridLayout(3, 1, 10, 10));


        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 30));
        loginButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new loginMenu().setVisible(true));
            dispose();
        });

        JButton createButton = new JButton("Create User");
        createButton.setFont(new Font("Arial", Font.PLAIN, 30));
        createButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new createMenu().setVisible(true));
            dispose();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        exitButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            dispose();
            return;
        });


        bottomBar.add(loginButton);
        bottomBar.add(createButton);
        bottomBar.add(exitButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}

class loginMenu extends JFrame {
    public loginMenu() {
        setTitle("Login Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Login Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 100));
        bottomBar.setLayout(new BorderLayout());

        JPanel central = new JPanel(new GridLayout(2, 2));
        central.setBackground(new Color(229, 194, 31));

        JLabel usernameLabel = new JLabel("Username:", SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        central.add(usernameLabel);

        JTextField username = new JTextField();
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        central.add(username);

        JLabel passwordLabel = new JLabel("Password:", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 30));
        central.add(passwordLabel);

        JTextField password = new JTextField();
        password.setFont(new Font("Arial", Font.PLAIN, 30));
        central.add(password);

        centerPanel.add(central, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.PLAIN, 30));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameText = username.getText();
                String passwordText = password.getText();
                System.out.println(usernameText);
                System.out.println(passwordText);
                SwingUtilities.invokeLater(() -> new mainMenu1().setVisible(true));
            }
        });

        bottomBar.add(enterButton, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }
}

class createMenu extends JFrame {
    public createMenu() {
        setTitle("Login Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Create User Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 100));
        bottomBar.setLayout(new BorderLayout());

        JPanel central = new JPanel(new GridLayout(3, 2));
        central.setBackground(new Color(229, 194, 31));

        JLabel usernameLabel = new JLabel("Username:", SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        central.add(usernameLabel);

        JTextField username = new JTextField();
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        central.add(username);

        JLabel passwordLabel = new JLabel("Password:", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 30));
        central.add(passwordLabel);

        JTextField password = new JTextField();
        password.setFont(new Font("Arial", Font.PLAIN, 30));
        central.add(password);

        JLabel bioLabel = new JLabel("Bio:", SwingConstants.CENTER);
        bioLabel.setFont(new Font("Arial", Font.BOLD, 30));
        central.add(bioLabel);

        JTextField bio = new JTextField();
        bio.setFont(new Font("Arial", Font.PLAIN, 30));
        central.add(bio);

        centerPanel.add(central, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.PLAIN, 30));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameText = username.getText();
                String passwordText = password.getText();
                String bioText = bio.getText();
                System.out.println(usernameText);
                System.out.println(passwordText);
                System.out.println(bioText);
                SwingUtilities.invokeLater(() -> new mainMenu1().setVisible(true));
            }
        });

        bottomBar.add(enterButton, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }
}

class mainMenu1 extends JFrame {
    public mainMenu1() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Main Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 400));
        bottomBar.setLayout(new GridLayout(3, 1, 10, 10));


        JButton friendButton = new JButton("Add or Remove Friends");
        friendButton.setFont(new Font("Arial", Font.PLAIN, 30));
        friendButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new friendsScreen1().setVisible(true));
            dispose();
        });

        JButton blockButton = new JButton("Block or Unblock Users");
        blockButton.setFont(new Font("Arial", Font.PLAIN, 30));
        blockButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new blockedScreen().setVisible(true));
            dispose();
        });

        JButton messageButton = new JButton("Message");
        messageButton.setFont(new Font("Arial", Font.PLAIN, 30));
        messageButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new messageMenu1().setVisible(true));
            dispose();
            return;
        });


        bottomBar.add(friendButton);
        bottomBar.add(blockButton);
        bottomBar.add(messageButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}

class friendsScreen1 extends JFrame {
    public friendsScreen1() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Friends Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 300));
        bottomBar.setLayout(new GridLayout(2, 1, 10, 10));


        JButton addFriendsButton = new JButton("ADD FRIENDS");
        addFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 40));
        addFriendsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String friendsName = JOptionPane.showInputDialog(friendsScreen1.this,
                        "Who do you want to add?");
                if (friendsName != null) {
                    JOptionPane.showMessageDialog(friendsScreen1.this,
                            "Please enter a name.");
                    return;
                }

//                    String doubleCheckFriend = FRIENDS_ADD + ";" + friendName;
//                    System.out.println(doubleCheckFriend);

                boolean friendExists = true;
                boolean alreadyFriends = false;

                if (friendExists) {
                    if (alreadyFriends) {
                        JOptionPane.showMessageDialog(friendsScreen1.this,
                                "You are already friends");
                    } else {
                        JOptionPane.showMessageDialog(friendsScreen1.this,
                                "Friend added successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(friendsScreen1.this,
                            "User does not exist.");
                }

                dispose();
            }
        });


        JButton removeFriendsButton = new JButton("REMOVE FRIENDS");
        removeFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 40));
        removeFriendsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String friendsName = JOptionPane.showInputDialog(friendsScreen1.this,
                        "Who do you want to remove?");
                if (friendsName != null) {
                    JOptionPane.showMessageDialog(friendsScreen1.this,
                            "Please enter a name.");
                    return;
                }

                boolean isFriend = true;
                boolean friendExists = true;

                if (friendExists) {
                    if (isFriend) {
                        JOptionPane.showMessageDialog(friendsScreen1.this,
                                "Friend removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(friendsScreen1.this,
                                "This person is not your friend");
                    }
                } else {
                    JOptionPane.showMessageDialog(friendsScreen1.this,
                            "User does not exist.");
                }

                dispose();

            }
        });


        bottomBar.add(addFriendsButton);
        bottomBar.add(removeFriendsButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}

class blockedScreen extends JFrame {
    public blockedScreen() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Blocked Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 300));
        bottomBar.setLayout(new GridLayout(2, 1, 10, 10));


        JButton addFriendsButton = new JButton("Block User");
        addFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 40));
        addFriendsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String friendsName = JOptionPane.showInputDialog(blockedScreen.this,
                        "Who do you want to add?");
                if (friendsName != null) {
                    JOptionPane.showMessageDialog(blockedScreen.this,
                            "Please enter a name.");
                    return;
                }

//                    String doubleCheckFriend = FRIENDS_ADD + ";" + friendName;
//                    System.out.println(doubleCheckFriend);

                boolean friendExists = true;
                boolean alreadyFriends = false;

                if (friendExists) {
                    if (alreadyFriends) {
                        JOptionPane.showMessageDialog(blockedScreen.this,
                                "You are already friends");
                    } else {
                        JOptionPane.showMessageDialog(blockedScreen.this,
                                "Friend added successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(blockedScreen.this,
                            "User does not exist.");
                }

                dispose();
            }
        });


        JButton removeFriendsButton = new JButton("Unblock User");
        removeFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 40));
        removeFriendsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String friendsName = JOptionPane.showInputDialog(blockedScreen.this,
                        "Who do you want to remove?");
                if (friendsName != null) {
                    JOptionPane.showMessageDialog(blockedScreen.this,
                            "Please enter a name.");
                    return;
                }

                boolean isFriend = true;
                boolean friendExists = true;

                if (friendExists) {
                    if (isFriend) {
                        JOptionPane.showMessageDialog(blockedScreen.this,
                                "Friend removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(blockedScreen.this,
                                "This person is not your friend");
                    }
                } else {
                    JOptionPane.showMessageDialog(blockedScreen.this,
                            "User does not exist.");
                }

                dispose();

            }
        });


        bottomBar.add(addFriendsButton);
        bottomBar.add(removeFriendsButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}


class messageMenu1 extends JFrame {
    public messageMenu1() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Message Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 400));
        bottomBar.setLayout(new GridLayout(3, 1, 10, 10));


        JButton sendTextMessagesButton = new JButton("SEND TEXT MESSAGES");
        sendTextMessagesButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        sendTextMessagesButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new textMenu().setVisible(true));
            dispose();
        });

        JButton messagePhotoButton = new JButton("SEND PHOTO MESSAGES");
        messagePhotoButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        messagePhotoButton.addActionListener(e -> {
            new messageMenu1.MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new PhotoMessageScreen().setVisible(true));
            dispose();
        });

        JButton removeMessageButton = new JButton("DELETE MESSAGES");
        removeMessageButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        removeMessageButton.addActionListener(e -> {
            new messageMenu1.MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new textMenu().setVisible(true));
            dispose();
        });


        bottomBar.add(sendTextMessagesButton);
        bottomBar.add(messagePhotoButton);
        bottomBar.add(removeMessageButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}


class textMenu extends JFrame {
    public textMenu() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Main Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 400));
        bottomBar.setLayout(new GridLayout(3, 1, 10, 10));


        JButton textAllFriends = new JButton("TEXT ALL FRIENDS");
        textAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text all friends?");
            }
        });

        JButton textAllUsers = new JButton("TEXT ALL USERS");
        textAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text all users??");
            }
        });

        JButton textAFriend = new JButton("TEXT A FRIEND");
        textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String person = JOptionPane.showInputDialog(textMenu.this,
                        "Who would you like to text");

                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text this person??");
            }
        });


        bottomBar.add(textAllFriends);
        bottomBar.add(textAllUsers);
        bottomBar.add(textAFriend);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}



class PhotoMessageScreen extends JFrame {

    public PhotoMessageScreen() {

        setTitle("Photo Message Options");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Send Photo Message", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        JButton allFriendsButton = new JButton("Send Photo to All Friends");
        allFriendsButton.addActionListener(e -> {
            new SendPhotoToAllFriendsScreen().setVisible(true);
            dispose();
        });
        add(allFriendsButton);

        JButton allUsersButton = new JButton("Send Photo to All Users");
        allUsersButton.addActionListener(e -> {
            new SendPhotoToAllUsersScreen().setVisible(true);
            dispose();
        });
        add(allUsersButton);

        JButton singleFriendButton = new JButton("Send Photo to a Single Friend");
        singleFriendButton.addActionListener(e -> {
            new SendPhotoToSingleFriendScreen().setVisible(true);
            dispose();
        });
        add(singleFriendButton);


    }


}

class SendPhotoToAllFriendsScreen extends JFrame {
    public SendPhotoToAllFriendsScreen() {
        setTitle("Send Photo to All Friends");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Enter the path to the photo:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(instructionLabel, BorderLayout.NORTH);

        JTextField photoPathField = new JTextField();
        add(photoPathField, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String photoPath = photoPathField.getText();
            //We need to add the comunication here

            new PhotoMessageScreen().setVisible(true);
            dispose();
        });
        add(submitButton, BorderLayout.SOUTH);
    }
}

class SendPhotoToAllUsersScreen extends JFrame {
    public SendPhotoToAllUsersScreen() {
        setTitle("Send Photo to All Users");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Enter the path to the photo:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(instructionLabel, BorderLayout.NORTH);

        JTextField photoPathField = new JTextField();
        add(photoPathField, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String photoPath = photoPathField.getText();
            //We need to add the comunication here

            new PhotoMessageScreen().setVisible(true);
            dispose();
        });
        add(submitButton, BorderLayout.SOUTH);
    }
}

class SendPhotoToSingleFriendScreen extends JFrame {
    public SendPhotoToSingleFriendScreen() {
        setTitle("Send Photo to a Single Friend");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JLabel instructionLabel1 = new JLabel("Enter the friend's username:", SwingConstants.CENTER);
        instructionLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        add(instructionLabel1);

        JTextField friendNameField = new JTextField();
        add(friendNameField);

        JLabel instructionLabel2 = new JLabel("Enter the path to the photo:", SwingConstants.CENTER);
        instructionLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        add(instructionLabel2);

        JTextField photoPathField = new JTextField();
        add(photoPathField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String friendName = friendNameField.getText();
            String photoPath = photoPathField.getText();
            //We need to add the comunication here

            new PhotoMessageScreen().setVisible(true);
            dispose();
        });
        add(submitButton);
    }
}


class DeleteMessageScreen extends JFrame {
    public DeleteMessageScreen() {
        setTitle("Delete Message Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titleLabel = new JLabel("Select an Option to Delete a Message:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel);

        JButton deleteAllFriendsButton = new JButton("Delete Message to All Friends");
        deleteAllFriendsButton.addActionListener(e -> {
            new DeleteMessageToAllFriendsScreen().setVisible(true);
            dispose();
        });
        add(deleteAllFriendsButton);

        JButton deleteAllUsersButton = new JButton("Delete Message to All Users");
        deleteAllUsersButton.addActionListener(e -> {
//                        new DeleteMessageToAllUsersScreen().setVisible(true);
            dispose();
        });
        add(deleteAllUsersButton);

        JButton deleteSingleFriendButton = new JButton("Delete Message to Single Friend");
        deleteSingleFriendButton.addActionListener(e -> {
//                        new DeleteMessageToSingleFriendScreen().setVisible(true);
            dispose();
        });
        add(deleteSingleFriendButton);
    }
}

class DeleteMessageToAllFriendsScreen extends JFrame {
    public DeleteMessageToAllFriendsScreen() {
        setTitle("Delete Message to All Friends");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel instructionLabel = new JLabel("Enter the message you want to delete for all friends:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(instructionLabel, BorderLayout.NORTH);

        JTextField messageField = new JTextField();
        add(messageField, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String messageToDelete = messageField.getText();
            System.out.println("Deleting message for all friends: " + messageToDelete);

            new PhotoMessageScreen().setVisible(true);
            dispose();
        });
        add(submitButton, BorderLayout.SOUTH);
    }
}



