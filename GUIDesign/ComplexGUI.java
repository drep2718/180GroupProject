import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ComplexGUI {


    public static String firstMenuItemGUI = "0";
    public static String usernameGUI;
    public static String passwordGUI;
    public static String bioGUI;
    public static User waypoint;
    public static String whichMessage;
    public static String friend;
    public static String message;

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
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
        continueButton.setFont(new Font("Bernard MT", Font.PLAIN, 16));
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
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
        loginButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        loginButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            ComplexGUI.firstMenuItemGUI = "1";
            SwingUtilities.invokeLater(() -> new loginMenu().setVisible(true));
            dispose();
        });

        JButton createButton = new JButton("Create User");
        createButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        createButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            ComplexGUI.firstMenuItemGUI = "2";
            SwingUtilities.invokeLater(() -> new createMenu().setVisible(true));
            dispose();
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        exitButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            ComplexGUI.firstMenuItemGUI = "3";
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
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
        usernameLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        central.add(usernameLabel);

        JTextField username = new JTextField();
        username.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        central.add(username);

        JLabel passwordLabel = new JLabel("Password:", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        central.add(passwordLabel);

        JTextField password = new JTextField();
        password.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        central.add(password);

        centerPanel.add(central, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ComplexGUI.usernameGUI = username.getText();
                ComplexGUI.waypoint = new User(ComplexGUI.usernameGUI);
                ComplexGUI.passwordGUI = password.getText();
                dispose();
            }
        });

        bottomBar.add(enterButton, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }
}

class createMenu extends JFrame {
    public createMenu() {
        setTitle("Create Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Create User Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
        usernameLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        central.add(usernameLabel);

        JTextField username = new JTextField();
        username.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        central.add(username);

        JLabel passwordLabel = new JLabel("Password:", SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        central.add(passwordLabel);

        JTextField password = new JTextField();
        password.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        central.add(password);

        JLabel bioLabel = new JLabel("Bio:", SwingConstants.CENTER);
        bioLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        central.add(bioLabel);

        JTextField bio = new JTextField();
        bio.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        central.add(bio);

        centerPanel.add(central, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.usernameGUI = username.getText();
                ComplexGUI.passwordGUI = password.getText();
                ComplexGUI.bioGUI = bio.getText();
                dispose();
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
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
        friendButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        friendButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new friendsScreen1().setVisible(true));
            dispose();
        });

        JButton blockButton = new JButton("Block or Unblock Users");
        blockButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        blockButton.addActionListener(e -> {
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new blockedScreen().setVisible(true));
            dispose();
        });

        JButton messageButton = new JButton("Message");
        messageButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
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

    private Friends friends;
    JComboBox<String> friendDropdown = new JComboBox<>();
    private JComboBox<String> userDropdown = new JComboBox<>();
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public friendsScreen1() {
        this.friends = new Friends();
        setTitle("Friend Manager");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createMainMenuPanel(), "Menu");
        mainPanel.add(createAddFriendPanel(), "AddFriend");
        mainPanel.add(createRemoveFriendPanel(), "RemoveFriend");

        add(mainPanel);
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Friend Manager", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        JButton addFriendButton = new JButton("ADD FRIEND");
        addFriendButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        addFriendButton.setBackground(Color.BLACK); // DOESNT WORK FOR SOME REASON
        addFriendButton.addActionListener(e -> cardLayout.show(mainPanel, "AddFriend"));

        JButton removeFriendButton = new JButton("REMOVE FRIEND");
        removeFriendButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        removeFriendButton.setBackground(Color.YELLOW); // SAME ISSUE
        removeFriendButton.addActionListener(e -> cardLayout.show(mainPanel, "RemoveFriend"));

        buttonPanel.add(addFriendButton);
        buttonPanel.add(removeFriendButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddFriendPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("ADD FRIEND", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        userDropdown = new JComboBox<>();
        userDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        userDropdown.setPreferredSize(new Dimension(300, 50));
        updateUserDropdown();
        formPanel.add(userDropdown);

        JButton addButton = new JButton("ADD FRIEND");
        addButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friends = new Friends(ComplexGUI.waypoint);
                String username = (String) userDropdown.getSelectedItem();
                if (username != null) {
                    User newFriend = new User(username);
                    friends.addFriend(newFriend);
                    JOptionPane.showMessageDialog(friendsScreen1.this, "User added: " + username);
                    updateUserDropdown();
                } else {
                    JOptionPane.showMessageDialog(friendsScreen1.this, "No user selected.");
                }
            }
        });
        formPanel.add(addButton);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRemoveFriendPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("REMOVE FRIEND", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        friendDropdown = new JComboBox<>();
        friendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        friendDropdown.setPreferredSize(new Dimension(300, 50));
        updateFriendDropdown();
        formPanel.add(friendDropdown);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        removeButton.setBackground(Color.BLACK);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendName = (String) friendDropdown.getSelectedItem();
                if (friendName != null) {
                    User friendToRemove = new User(friendName);
                    friends.removeFriend(friends, friendToRemove);
                    JOptionPane.showMessageDialog(friendsScreen1.this, "Friend removed: " + friendName);
                    updateFriendDropdown();
                } else {
                    JOptionPane.showMessageDialog(friendsScreen1.this, "No friend selected.");
                }
            }
        });
        formPanel.add(removeButton);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void updateUserDropdown() {

        userDropdown.removeAllItems();
        User tempUser = new User("temp");
        tempUser.loadUsers();
        ArrayList<User> users = User.getAllUsers();
        System.out.println(users);
        for (User user : users) {
            userDropdown.addItem(user.getUsername());
        }
    }

    private void updateFriendDropdown() {

        friendDropdown.removeAllItems();
        ArrayList<User> friendsList = Friends.getFriendsList();
        for (User friend : friendsList) {
            friendDropdown.addItem(friend.getUsername());
        }
    }


    class MainGUI extends JFrame {

    }
}

class blockedScreen extends JFrame {
    User temp = new User("temp");
    private Friends friends;
    JComboBox<String> friendDropdown = new JComboBox<>();
    private JComboBox<String> userDropdown = new JComboBox<>();
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public blockedScreen() {
        this.friends = new Friends(temp);
        setTitle("Privacy");
        setSize(200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createMainMenuPanel(), "Menu");
        mainPanel.add(createBlockUserPanel(), "BlockUser");
        mainPanel.add(creatUnblockUserPanel(), "UnblockUser");

        add(mainPanel);
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Privacy Settings", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        JButton blockUserButton = new JButton("BLOCK USER");
        blockUserButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        blockUserButton.setBackground(Color.BLACK); // DOESNT WORK FOR SOME REASON
        blockUserButton.addActionListener(e -> cardLayout.show(mainPanel, "BlockUser"));

        JButton unblockUserButton = new JButton("UNBLOCK USER");
        unblockUserButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        unblockUserButton.setBackground(Color.YELLOW); // SAME ISSUE
        unblockUserButton.addActionListener(e -> cardLayout.show(mainPanel, "UnblockUser"));

        buttonPanel.add(blockUserButton);
        buttonPanel.add(unblockUserButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBlockUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("BLOCK USER", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 20));
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        userDropdown = new JComboBox<>();
        userDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        userDropdown.setPreferredSize(new Dimension(300, 50));
        updateUserDropdown();
        formPanel.add(userDropdown);

        JButton blockUserButton = new JButton("BLOCK USER");
        blockUserButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        blockUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) userDropdown.getSelectedItem();
                if (username != null) {
                    User friend = new User(username);
                    friends.blockUser(friend);
                    JOptionPane.showMessageDialog(blockedScreen.this, "User blocked: " + username);
                    updateUserDropdown();
                } else {
                    JOptionPane.showMessageDialog(blockedScreen.this, "No user selected.");
                }
            }
        });
        formPanel.add(blockUserButton);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel creatUnblockUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("UNBLOCK USER", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        friendDropdown = new JComboBox<>();
        friendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        friendDropdown.setPreferredSize(new Dimension(300, 50));
        updateFriendDropdown();
        formPanel.add(friendDropdown);

        JButton unblockUserButton = new JButton("UNBLOCK USER");
        unblockUserButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        unblockUserButton.setBackground(Color.BLACK);
        unblockUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendName = (String) friendDropdown.getSelectedItem();
                if (friendName != null) {
                    User userToUnblock = new User(friendName);
                    friends.unblockUser(friends, userToUnblock);
                    JOptionPane.showMessageDialog(blockedScreen.this, "Friend removed: " + friendName);
                    updateFriendDropdown();
                } else {
                    JOptionPane.showMessageDialog(blockedScreen.this, "No friend selected.");
                }
            }
        });
        formPanel.add(unblockUserButton);

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void updateUserDropdown() {

        userDropdown.removeAllItems();
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            userDropdown.addItem(user.getUsername());
        }
    }

    private void updateFriendDropdown() {

        friendDropdown.removeAllItems();
        ArrayList<User> friendsList = Friends.getFriendsList();
        for (User friend : friendsList) {
            friendDropdown.addItem(friend.getUsername());
        }
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
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
            ComplexGUI.whichMessage = "1";
            dispose();
        });

        JButton messagePhotoButton = new JButton("SEND PHOTO MESSAGES");
        messagePhotoButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        messagePhotoButton.addActionListener(e -> {
            ComplexGUI.whichMessage = "2";
            new messageMenu1.MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new photoMenu().setVisible(true));
            dispose();
        });

        JButton removeMessageButton = new JButton("DELETE MESSAGES");
        removeMessageButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        removeMessageButton.addActionListener(e -> {
            ComplexGUI.whichMessage = "3";
            new messageMenu1.MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new deleteMenu().setVisible(true));
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
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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
                ComplexGUI.whichMessage = "1"; // replace text type and below new globl
                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text all friends?");
                ComplexGUI.message = message;

            }
        });

        JButton textAllUsers = new JButton("TEXT ALL USERS");
        textAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.whichMessage = "2";
                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text all users??");
                ComplexGUI.message = message;
            }
        });

        JButton textAFriend = new JButton("TEXT A FRIEND");
        textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.whichMessage = "3";
                String person = JOptionPane.showInputDialog(textMenu.this,
                        "Who would you like to text");
                ComplexGUI.friend = person;

                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text this person??");
                ComplexGUI.message = message;

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


class photoMenu extends JFrame {
    public photoMenu() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Photo Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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


        JButton textAllFriends = new JButton("PHOTO MESSAGE ALL FRIENDS");
        textAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath = JOptionPane.showInputDialog(photoMenu.this,
                        "Enter the Filepath to the Image you want to send to all friends.");
            }
        });

        JButton textAllUsers = new JButton("PHOTO MESSAGE ALL USERS");
        textAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath = JOptionPane.showInputDialog(photoMenu.this,
                        "Enter the Filepath to the Image you want to send to all friends.");
            }
        });

        JButton textAFriend = new JButton("PHOTO MESSAGE A FRIEND");
        textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String person = JOptionPane.showInputDialog(photoMenu.this,
                        "Who would you like to text");

                String filepath = JOptionPane.showInputDialog(photoMenu.this,
                        "Enter the Filepath to the Image you want to send to all friends?");
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

class deleteMenu extends JFrame {
    public deleteMenu() {
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Delete Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

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


        JButton textAllFriends = new JButton("DELETE MESSAGE TO ALL FRIENDS");
        textAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath = JOptionPane.showInputDialog(deleteMenu.this,
                        "What message to all your friends would you like to delete");
            }
        });

        JButton textAllUsers = new JButton("DELETE MESSAGE TO ALL USERS");
        textAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath = JOptionPane.showInputDialog(deleteMenu.this,
                        "What message to all your users would you like to delete");
            }
        });

        JButton textAFriend = new JButton("DELETE MESSAGE TO A SINGLE FRIEND");
        textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String person = JOptionPane.showInputDialog(deleteMenu.this,
                        "Who would you like to delete the message from.");

                String filepath = JOptionPane.showInputDialog(deleteMenu.this,
                        "What did the message say");
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

// Final
