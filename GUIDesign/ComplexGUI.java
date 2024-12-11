import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Team Project -- ComplexGUI Class
 *
 * @author Santhosh, Sabareesh, Aiden, Linh, Lab Number: 26043
 * @version November 17, 2024
 */


public class ComplexGUI {


    public static String firstMenuItemGUI = "0";
    public static String usernameGUI;
    public static String passwordGUI;
    public static String bioGUI;
    public static User waypoint;
    public static String whichMessage;
    public static String friend;
    public static String message;
    public static String logout;
    public static String secondMenuItem;
    public static String photoPathGUI;
    public static String photoRecipientGUI;
    public static String photoMessageTypeGUI;
    public static String back;
    public static String LoopMessage;

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
            ComplexGUI.firstMenuItemGUI = "1";
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new loginMenu().setVisible(true));
            dispose();
        });

        JButton createButton = new JButton("Create User");
        createButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        createButton.addActionListener(e -> {
            ComplexGUI.firstMenuItemGUI = "2";
            new MainGUI().setVisible(true);
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

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.usernameGUI = "qwertyuiop";
                ComplexGUI.passwordGUI = "back";
                new mainMenu().setVisible(true);
                dispose();
            }
        });


        bottomBar.add(backButton, BorderLayout.SOUTH);
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

        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.usernameGUI = "cqwbofnfewhnefwnhfeqnhfqewx";
                ComplexGUI.bioGUI = "buyfwbfhowhfw";
                ComplexGUI.passwordGUI = "back";
                new mainMenu().setVisible(true);
                dispose();
            }
        });

        bottomBar.add(backButton, BorderLayout.SOUTH);

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
        bottomBar.setLayout(new GridLayout(4, 1, 10, 10));


        JButton friendButton = new JButton("Add or Remove Friends");
        friendButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        friendButton.addActionListener(e -> {
            ComplexGUI.secondMenuItem = "1";
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new friendsScreen1().setVisible(true));
            dispose();
        });

        JButton blockButton = new JButton("Block or Unblock Users");
        blockButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        blockButton.addActionListener(e -> {
            ComplexGUI.secondMenuItem = "2";
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new blockedScreen().setVisible(true));
            dispose();
        });

        JButton messageButton = new JButton("Message");
        messageButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        messageButton.addActionListener(e -> {
            ComplexGUI.secondMenuItem = "3";
            new MainGUI().setVisible(true);
            SwingUtilities.invokeLater(() -> new messageMenu1().setVisible(true));
            dispose();
            return;
        });


        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        logoutButton.addActionListener(e -> {
            ComplexGUI.usernameGUI = null;
            ComplexGUI.passwordGUI = null;
            ComplexGUI.firstMenuItemGUI = "0"; // Optional: Reset to default value
            ComplexGUI.secondMenuItem = null;
            ComplexGUI.message = null;
            ComplexGUI.logout = null;
            ComplexGUI.back = "back";
            ComplexGUI.waypoint = null;

            SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
            dispose();
        });


        bottomBar.add(friendButton);
        bottomBar.add(blockButton);
        bottomBar.add(messageButton);
        bottomBar.add(logoutButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}

class friendsScreen1 extends JFrame {
    JPanel panel = new JPanel(new BorderLayout());

    private Friends friends;
    JComboBox<String> friendDropdown = new JComboBox<>();
    private JComboBox<String> userDropdown = new JComboBox<>();
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public friendsScreen1() {
        this.friends = new Friends();
//        setTitle("Friend Manager");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to The Friend Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 50));
        bottomBar.setLayout(new GridLayout(4, 1, 10, 10));

        mainPanel.add(createMainMenuPanel(), "Menu");
        mainPanel.add(createAddFriendPanel(), "AddFriend");
        mainPanel.add(createRemoveFriendPanel(), "RemoveFriend");
        mainPanel.add(viewFriendsPanel(), "ViewFriends");
        add(mainPanel);

        JButton backButton = new JButton("CANCEL");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(e -> {
            new mainMenu1().setVisible(true);
            dispose();
        });


        add(backButton, BorderLayout.SOUTH);
    }


    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Friend Manager", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        JButton addFriendButton = new JButton("ADD FRIEND");
        addFriendButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        addFriendButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "AddFriend");
            updateUserDropdown();
        });

        JButton removeFriendButton = new JButton("REMOVE FRIEND");
        removeFriendButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        removeFriendButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "RemoveFriend");
            updateFriendDropdown();
        });

        JButton viewFriends = new JButton("VIEW FRIENDS");
        viewFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        viewFriends.addActionListener(e -> {
            cardLayout.show(mainPanel, "ViewFriends");
        });

        buttonPanel.add(addFriendButton);
        buttonPanel.add(removeFriendButton);
        buttonPanel.add(viewFriends);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel viewFriendsPanel() {
        JPanel viewFriendPanel = new JPanel(new BorderLayout());

        JLabel friendListLabel = new JLabel("Friend List", SwingConstants.CENTER);
        friendListLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        viewFriendPanel.add(friendListLabel, BorderLayout.NORTH);

        JTextArea friendsTextArea = new JTextArea();
        friendsTextArea.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        friendsTextArea.setEditable(false);
        friendsTextArea.setLineWrap(true);
        friendsTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(friendsTextArea);
        viewFriendPanel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("REFRESH FRIENDS");
        refreshButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        refreshButton.addActionListener(e -> {
            updateFriendDropdown();
            updateFriendsTextArea(friendsTextArea);
        });
        viewFriendPanel.add(refreshButton, BorderLayout.SOUTH);

        updateFriendsTextArea(friendsTextArea);

        JButton backButton = new JButton("CANCEL");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(e -> {
            new mainMenu1().setVisible(true);
            dispose();
        });

        panel.add(viewFriendPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return viewFriendPanel;
    }

    private void updateFriendsTextArea(JTextArea friendsTextArea) {
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadFriends();
        ArrayList<User> friendsList = Friends.getFriendsList();

        friendsTextArea.setText("");

        if (friendsList.isEmpty()) {
            friendsTextArea.append("No friends found.");
            return;
        }

        String friendsText = "Your Friends:\n" +
                "---------------\n";

        for (int i = 0; i < friendsList.size(); i++) {
            friendsText += (i + 1) + ". " + friendsList.get(i).getUsername() + "\n";
        }

        friendsText += "\nTotal Friends: " + friendsList.size();

        friendsTextArea.setText(friendsText);
    }


    private JPanel createAddFriendPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("ADD FRIEND", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        userDropdown = new JComboBox<>();
        userDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 20));
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
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
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
        friendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        friendDropdown.setPreferredSize(new Dimension(300, 50));
        updateFriendDropdown();
        formPanel.add(friendDropdown);

        JButton removeButton = new JButton("REMOVE");
        removeButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        removeButton.setBackground(Color.BLACK);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                friends = new Friends(ComplexGUI.waypoint);
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
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void updateUserDropdown() {

        userDropdown.removeAllItems();
        JComboBox<String> userDropdownNoF = new JComboBox<>();
        userDropdownNoF.removeAllItems();
        User tempUser = new User("temp");
        tempUser.loadUsers();
        Friends waypoint = new Friends(ComplexGUI.waypoint);
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            userDropdownNoF.addItem(user.getUsername());
        }

        friendDropdown.removeAllItems();
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadFriends();
        ArrayList<User> friendsList = Friends.getFriendsList();
        System.out.println(friendsList);
        for (User friend : friendsList) {
            friendDropdown.addItem(friend.getUsername());
        }

        for (int i = 0; i < friendDropdown.getItemCount(); i++) {
            String friendName = friendDropdown.getItemAt(i);
            userDropdownNoF.removeItem(friendName);
        }

        for (int i = 0; i < userDropdownNoF.getItemCount(); i++) {
            userDropdown.addItem(userDropdownNoF.getItemAt(i));
        }
    }


    private void updateFriendDropdown() {

        friendDropdown.removeAllItems();
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadFriends();
        ArrayList<User> friendsList = Friends.getFriendsList();
        System.out.println(friendsList);
        for (User friend : friendsList) {
            friendDropdown.addItem(friend.getUsername());
        }
    }


    class MainGUI extends JFrame {

    }
}

class blockedScreen extends JFrame {
    JPanel panel = new JPanel(new BorderLayout());

    private Friends friends;
    JComboBox<String> friendDropdown = new JComboBox<>();
    private JComboBox<String> userDropdown = new JComboBox<>();
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public blockedScreen() {
        this.friends = new Friends(ComplexGUI.waypoint);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to The Blocked User Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 50));
        bottomBar.setLayout(new GridLayout(4, 1, 10, 10));

        mainPanel.add(createMainMenuPanel(), "Menu");
        mainPanel.add(createBlockUserPanel(), "BlockUser");
        mainPanel.add(createUnblockUserPanel(), "UnblockUser");
        mainPanel.add(createViewBlockedPanel(), "ViewBlocked");
        add(mainPanel);

        JButton backButton = new JButton("CANCEL");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(e -> {
            new mainMenu1().setVisible(true);
            dispose();
        });

        add(backButton, BorderLayout.SOUTH);
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Blocked User Manager", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 20));

        JButton blockUserButton = new JButton("BLOCK USER");
        blockUserButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        blockUserButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "BlockUser");
            updateUserDropdown();
        });

        JButton unblockUserButton = new JButton("UNBLOCK USER");
        unblockUserButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        unblockUserButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "UnblockUser");
            updateFriendDropdown();
        });

        JButton viewBlockedUsers = new JButton("VIEW BLOCKED");
        viewBlockedUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        viewBlockedUsers.addActionListener(e -> {
            cardLayout.show(mainPanel, "ViewBlocked");
        });

        buttonPanel.add(blockUserButton);
        buttonPanel.add(unblockUserButton);
        buttonPanel.add(viewBlockedUsers);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createViewBlockedPanel() {
        JPanel viewBlockedPanel = new JPanel(new BorderLayout());

        JLabel blockedListLabel = new JLabel("Blocked List", SwingConstants.CENTER);
        blockedListLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        viewBlockedPanel.add(blockedListLabel, BorderLayout.NORTH);

        JTextArea blockedTextArea = new JTextArea();
        blockedTextArea.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        blockedTextArea.setEditable(false);
        blockedTextArea.setLineWrap(true);
        blockedTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(blockedTextArea);
        viewBlockedPanel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("REFRESH BLOCKED");
        refreshButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        refreshButton.addActionListener(e -> {
            updateFriendDropdown();
            updateBlockedTextArea(blockedTextArea);
        });
        viewBlockedPanel.add(refreshButton, BorderLayout.SOUTH);

        updateBlockedTextArea(blockedTextArea);

        JButton backButton = new JButton("Refresh");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        backButton.addActionListener(e -> {
            new mainMenu1().setVisible(true);
            dispose();
        });

        panel.add(viewBlockedPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return viewBlockedPanel;
    }


    private void updateBlockedTextArea(JTextArea friendsTextArea) {
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadBlocked();
        ArrayList<User> friendsList = Friends.getBlockedList();


        friendsTextArea.setText("");


        if (friendsList.isEmpty()) {
            friendsTextArea.append("No friends found.");
            return;
        }


        String blockedText = "Your Friends:\n" +
                "---------------\n";


        for (int i = 0; i < friendsList.size(); i++) {
            blockedText += (i + 1) + ". " + friendsList.get(i).getUsername() + "\n";
        }


        blockedText += "\nTotal Friends: " + friendsList.size();


        friendsTextArea.setText(blockedText);
    }


    private JPanel createBlockUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("BLOCK USER", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 40));
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
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 40));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUnblockUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("UNBLOCK USER", SwingConstants.CENTER);
        label.setFont(new Font("Bernard MT", Font.BOLD, 40));
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        friendDropdown = new JComboBox<>();
        friendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 20));
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
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 40));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private void updateUserDropdown() {

        userDropdown.removeAllItems();
        JComboBox<String> userDropdownNoF = new JComboBox<>();
        userDropdownNoF.removeAllItems();
        User tempUser = new User("temp");
        tempUser.loadUsers();
        Friends waypoint = new Friends(ComplexGUI.waypoint);
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            userDropdownNoF.addItem(user.getUsername());
        }

        friendDropdown.removeAllItems();
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadBlocked();
        ArrayList<User> blockedList = Friends.getBlockedList();
        System.out.println(blockedList);
        for (User blocked : blockedList) {
            friendDropdown.addItem(blocked.getUsername());
        }

        for (int i = 0; i < friendDropdown.getItemCount(); i++) {
            String friendName = friendDropdown.getItemAt(i);
            userDropdownNoF.removeItem(friendName);
        }

        for (int i = 0; i < userDropdownNoF.getItemCount(); i++) {
            userDropdown.addItem(userDropdownNoF.getItemAt(i));
        }
    }

    private void updateFriendDropdown() {

        friendDropdown.removeAllItems();
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadBlocked();
        ArrayList<User> blockedList = Friends.getBlockedList();
        System.out.println(blockedList);
        for (User blocked : blockedList) {
            friendDropdown.addItem(blocked.getUsername());
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
        bottomBar.setLayout(new GridLayout(5, 1, 10, 10));


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

        JButton view = new JButton("VIEW MESSAGES");
        view.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        view.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new viewMenu().setVisible(true));

            ComplexGUI.whichMessage = "1";
            dispose();
        });

        add(welcomeLabel);
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> {
            new mainMenu1().setVisible(true);
            dispose();
        });


        bottomBar.add(sendTextMessagesButton);
        bottomBar.add(messagePhotoButton);
        bottomBar.add(removeMessageButton);
        bottomBar.add(view);
        bottomBar.add(backButton);
        add(bottomBar, BorderLayout.SOUTH);
    }


    class MainGUI extends JFrame {

    }
}

class viewMenu extends JFrame {

    public viewMenu() {
        setTitle("View Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        JPanel viewMessagesPanel = createViewMessagesPanel();
        add(viewMessagesPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createViewMessagesPanel() {
        JPanel viewMessagesPanel = new JPanel(new BorderLayout());

        JLabel messagesListLabel = new JLabel("Messages", SwingConstants.CENTER);
        messagesListLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
        viewMessagesPanel.add(messagesListLabel, BorderLayout.NORTH);
        JTextArea messagesTextArea = new JTextArea();
        messagesTextArea.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        messagesTextArea.setEditable(false);
        messagesTextArea.setLineWrap(true);
        messagesTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(messagesTextArea);
        viewMessagesPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel topBarPanel = new JPanel(new BorderLayout());
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> {
            new mainMenu1().setVisible(true);
            dispose();
        });
        topBarPanel.add(backButton, BorderLayout.WEST);
        viewMessagesPanel.add(topBarPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton allUsersButton = new JButton("View All Users Messages");
        allUsersButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        allUsersButton.addActionListener(e -> {
            messagesTextArea.setText("");
            updateAllUsersText(messagesTextArea);
        });

        JButton allFriendsButton = new JButton("View All Friends Messages");
        allFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        allFriendsButton.addActionListener(e -> {
            messagesTextArea.setText("");
            updateAllFriendsText(messagesTextArea);
        });

        JButton singleFriendButton = new JButton("View Single Friend Messages");
        singleFriendButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        singleFriendButton.addActionListener(e -> {
            String friendName = JOptionPane.showInputDialog(this, "Enter Friend's Name:");
            if (friendName != null && !friendName.trim().isEmpty()) {
                messagesTextArea.setText("");
                updateSingleFriendMessages(messagesTextArea, friendName.trim());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid friend name entered.");
            }
        });

        buttonPanel.add(allUsersButton);
        buttonPanel.add(allFriendsButton);
        buttonPanel.add(singleFriendButton);

        viewMessagesPanel.add(buttonPanel, BorderLayout.SOUTH);

        return viewMessagesPanel;
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = getSize();
            setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        }
        super.setVisible(b);
    }

    private void updateAllUsersText(JTextArea userTextArea) {
        User user = new User(ComplexGUI.waypoint.getUsername());
        Messaging messageTemp = new Messaging(user, null, "content", "date", false);
        messageTemp.loadAllUsersMessages(user);
        ArrayList<Messaging> usersList = Messaging.getMessageHistory();

        userTextArea.setText("");

        if (usersList.isEmpty()) {
            userTextArea.append("No messages found.");
            return;
        }

        String allUsersMessages = "Messages from All Users:\n" +
                "--------------------------\n";

        for (int i = 0; i < usersList.size(); i++) {
            allUsersMessages += (i + 1) + ". " + usersList.get(i) + "\n";
        }

        allUsersMessages += "\nTotal Users: " + usersList.size();
        userTextArea.setText(allUsersMessages);
    }

    private void updateAllFriendsText(JTextArea friendsTextArea) {
        User user = new User(ComplexGUI.waypoint.getUsername());
        Messaging messageTemp = new Messaging(user, null, "content", "date", false);
        messageTemp.loadAllFriendMessages(user);
        ArrayList<Messaging> friendsList = Messaging.getMessageHistory();

        friendsTextArea.setText("");

        if (friendsList.isEmpty()) {
            friendsTextArea.append("No messages found.");
            return;
        }

        String allFriendsMessages = "Messages to All Friends:\n" +
                "--------------------------\n";

        for (int i = 0; i < friendsList.size(); i++) {
            allFriendsMessages += (i + 1) + ". " + friendsList.get(i) + "\n";
        }

        allFriendsMessages += "\nTotal Friends: " + friendsList.size();
        friendsTextArea.setText(allFriendsMessages);
    }

    private void updateSingleFriendMessages(JTextArea singleFriendTextArea, String friendName) {
        User user = new User(ComplexGUI.waypoint.getUsername());
        Messaging messageTemp = new Messaging(user, null, "content", "date", false);
        messageTemp.loadMessages(user);
        ArrayList<Messaging> messagesList = Messaging.getMessageHistory();

        singleFriendTextArea.setText("");

        if (messagesList.isEmpty()) {
            singleFriendTextArea.append("No messages found.");
            return;
        }

        String friendMessages = "Messages to " + friendName + ":\n" +
                "--------------------------\n";

        boolean found = false;

        for (Messaging message : messagesList) {
            String friendsName = message.toString().substring(message.toString().lastIndexOf(":") +1);
            if (message.getSender().getUsername().equalsIgnoreCase(friendName) ||
                    message.getReceiver().getUser().getUsername().equalsIgnoreCase(friendName)) {
                friendMessages += message + "\n";
                found = true;
            }
        }

        if (!found) {
            singleFriendTextArea.append("No messages found for " + friendName + ".");
        } else {
            singleFriendTextArea.setText(friendMessages);
        }
    }


}

class textMenu extends JFrame {

    JComboBox<String> friendDropdown = new JComboBox<>();
    private User user;
    private Friends friends;
    private Messaging messaging;
    private String date;
    private boolean isRead;

    public textMenu() {
        this.friends = new Friends(ComplexGUI.waypoint);
        this.user = new User(ComplexGUI.usernameGUI);
        this.messaging = new Messaging(user, friends, ComplexGUI.message, date, isRead);
        setTitle("Main Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Text Menu", SwingConstants.CENTER);
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
        bottomBar.setLayout(new GridLayout(4, 1, 10, 10));

        add(welcomeLabel);
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> {
            new messageMenu1().setVisible(true);
            dispose();
        });

        JButton textAllFriends = new JButton("TEXT ALL FRIENDS");
        textAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.whichMessage = "1"; // replace text type and below new globl
                String message = JOptionPane.showInputDialog(textMenu.this,
                        "What would you like to text all friends?");
                ComplexGUI.message = message;
                messaging.sendAllFriendsMessage(user, message, date, isRead);
                if (message != null) {
                    JOptionPane.showMessageDialog(textMenu.this, "Successfully" +
                            " sent");
                }

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
                if (message != null || !message.isEmpty()) {
                    ComplexGUI.message = message;
                    messaging.sendAllUsersMessage(user, message, date, isRead);
                    if (message != null) {
                        JOptionPane.showMessageDialog(textMenu.this, "Successfully" +
                                " sent");
                    }
                } else {
                    JOptionPane.showMessageDialog(textMenu.this,
                            "Message cannot be empty!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton textAFriend = new JButton("TEXT A FRIEND");
        textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        friendDropdown = new JComboBox<>();
        friendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        friendDropdown.setPreferredSize(new Dimension(300, 50));
        updateFriendDropdown();
        formPanel.add(friendDropdown);

        textAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplexGUI.whichMessage = "3";

                JPanel panel = new JPanel();
                panel.add(new JLabel("Who would you like to text?"));
                panel.add(friendDropdown);

                int result = JOptionPane.showConfirmDialog(
                        textMenu.this, panel, "Select Friend",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String person = (String) friendDropdown.getSelectedItem();
                    System.out.println("Selected person: " + person);

                    ComplexGUI.friend = person;
                    User user1 = new User("temp");
                    user1.loadUsers();

                    ArrayList<User> allUsers = User.getAllUsers();

                    User friendUser = null;
                    for (User user : allUsers) {
                        System.out.println("Checking user: " + user.getUsername());
                        if (user.getUsername().equals(person)) {
                            System.out.println("Match found: " + user.getUsername());
                            friendUser = user;
                            break;
                        }
                    }

                    if (friendUser == null) {
                        JOptionPane.showMessageDialog(textMenu.this,
                                "No friend found with the selected name!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Friends friendFriends = new Friends(friendUser);

                    String message = JOptionPane.showInputDialog(textMenu.this,
                            "What would you like to text this person?");
                    if (message != null && !message.isEmpty()) {
                        ComplexGUI.message = message;
                        Messaging messaging = new Messaging(user, friendFriends, message, date, isRead);
                        messaging.sendMessage(user, friendFriends, message, date, isRead);
                        JOptionPane.showMessageDialog(textMenu.this,
                                "Message successfully sent!");
                    } else {
                        JOptionPane.showMessageDialog(textMenu.this,
                                "Message cannot be empty!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });


        bottomBar.add(textAllFriends);
        bottomBar.add(textAllUsers);
        bottomBar.add(textAFriend);
        bottomBar.add(backButton);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private void updateFriendDropdown() {
        friendDropdown.removeAllItems();
        friends = new Friends(ComplexGUI.waypoint);
        friends.loadFriends();
        ArrayList<User> friendsList = Friends.getFriendsList();
        System.out.println(friendsList);
        for (User friend : friendsList) {
            friendDropdown.addItem(friend.getUsername());
        }
    }

    class MainGUI extends JFrame {

    }
}


class photoMenu extends JFrame {
    public photoMenu() {
        setTitle("Photo Menu");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to The Photo Menu", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Bernard MT", Font.BOLD, 40));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JButton choosePhotoButton = new JButton("Choose Photo");
        choosePhotoButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        choosePhotoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image files", "png", "jpg", "jpeg", "gif"));

            if (fileChooser.showOpenDialog(photoMenu.this) == JFileChooser.APPROVE_OPTION) {
                ComplexGUI.photoPathGUI = fileChooser.getSelectedFile().getAbsolutePath();
                JOptionPane.showMessageDialog(photoMenu.this,
                        "Photo selected: " + ComplexGUI.photoPathGUI);
            }
        });
        inputPanel.add(choosePhotoButton);

        centerPanel.add(inputPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setPreferredSize(new Dimension(getWidth(), 100));
        add(topBar, BorderLayout.NORTH);

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(new Color(229, 194, 31));
        bottomBar.setPreferredSize(new Dimension(getWidth(), 400));
        bottomBar.setLayout(new GridLayout(4, 1, 10, 10));

        JButton photoAllFriends = new JButton("PHOTO MESSAGE ALL FRIENDS");
        photoAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        photoAllFriends.addActionListener(e -> {
            if (ComplexGUI.photoPathGUI == null || ComplexGUI.photoPathGUI.isEmpty()) {
                JOptionPane.showMessageDialog(photoMenu.this,
                        "Please select a photo first", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ComplexGUI.photoMessageTypeGUI = "1";
            dispose();
        });

        JButton photoAllUsers = new JButton("PHOTO MESSAGE ALL USERS");
        photoAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        photoAllUsers.addActionListener(e -> {
            if (ComplexGUI.photoPathGUI == null || ComplexGUI.photoPathGUI.isEmpty()) {
                JOptionPane.showMessageDialog(photoMenu.this,
                        "Please select a photo first", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ComplexGUI.photoMessageTypeGUI = "2";
            dispose();
        });

        JButton photoSingleFriend = new JButton("PHOTO MESSAGE A FRIEND");
        photoSingleFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        photoSingleFriend.addActionListener(e -> {
            if (ComplexGUI.photoPathGUI == null || ComplexGUI.photoPathGUI.isEmpty()) {
                JOptionPane.showMessageDialog(photoMenu.this,
                        "Please select a photo first", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            ComplexGUI.photoRecipientGUI = JOptionPane.showInputDialog(photoMenu.this,
                    "Who would you like to send the photo to?");
            if (ComplexGUI.photoRecipientGUI != null && !ComplexGUI.photoRecipientGUI.isEmpty()) {
                ComplexGUI.photoMessageTypeGUI = "3";
                dispose();
            }
        });

        add(welcomeLabel);
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> {
            new messageMenu1().setVisible(true);
            dispose();
        });

        bottomBar.add(photoAllFriends);
        bottomBar.add(photoAllUsers);
        bottomBar.add(photoSingleFriend);
        bottomBar.add(backButton);
        add(bottomBar, BorderLayout.SOUTH);
    }

    class MainGUI extends JFrame {

    }
}

class deleteMenu extends JFrame {
    private JComboBox<String> allUsersDropdown;
    private JComboBox<String> allFriendsDropdown;
    private JComboBox<String> singleFriendDropdown;
    private JComboBox<String> friendDropdown;
    private User user;
    private Messaging messaging;
    JPanel mainPanel;
    private Friends receiver;
    private String content;
    private String date;
    private boolean isRead;

    public deleteMenu() {
        this.user = new User(ComplexGUI.usernameGUI);
        this.messaging = new Messaging(user, receiver, ComplexGUI.message, date, isRead);
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
        bottomBar.setLayout(new GridLayout(4, 1, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 1, 20, 20));

        allFriendsDropdown = new JComboBox<>();
        allFriendsDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        allFriendsDropdown.setPreferredSize(new Dimension(300, 50));
        updateAllFriendsMessages();
        formPanel.add(allFriendsDropdown);

        JButton textAllFriends = new JButton("DELETE MESSAGE TO ALL FRIENDS");
        textAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Messaging messageTemp = new Messaging(user, null, content, date, isRead);
                messageTemp.loadAllFriendMessages(user);
                ArrayList<Messaging> messages = Messaging.getMessageHistory();
                System.out.println(messages);


                JPanel friendMessagePanel = new JPanel();
                friendMessagePanel.add(new JLabel(
                        "What message to all your friends would you like to delete?"));
                friendMessagePanel.add(allFriendsDropdown);

                int result = JOptionPane.showConfirmDialog(deleteMenu.this,
                        friendMessagePanel, "Select Message", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String selectedMessage = (String) allFriendsDropdown.getSelectedItem();
                    String finalMessage = selectedMessage.substring(selectedMessage.indexOf(":") + 1);
                    System.out.println("Selected Message: " + finalMessage);

                    if (finalMessage != null && !finalMessage.trim().isEmpty()) {
                        messaging.deleteFriendsMessage(user, finalMessage, date, isRead);
                        JOptionPane.showMessageDialog(deleteMenu.this,
                                "Message deleted for all friends.");

                    } else {
                        JOptionPane.showMessageDialog(deleteMenu.this,
                                "No message selected or invalid selection.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(deleteMenu.this,
                            "No message selected.");
                }
            }
        });


        JPanel formPanel2 = new JPanel(new GridLayout(2, 1, 20, 20));

        allUsersDropdown = new JComboBox<>();
        allUsersDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        allUsersDropdown.setPreferredSize(new Dimension(300, 50));
        updateAllUsersMessages();
        formPanel2.add(allUsersDropdown);

        JButton textAllUsers = new JButton("DELETE MESSAGE TO ALL USERS");
        textAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAllUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Messaging messageTemp = new Messaging(user, null, content, date, isRead);
                messageTemp.loadAllUsersMessages(user);
                ArrayList<Messaging> messages = Messaging.getMessageHistory();
                System.out.println(messages);

                JPanel userMessagePanel = new JPanel();
                userMessagePanel.add(new JLabel(
                        "What message to all your users would you like to delete?"));
                userMessagePanel.add(allUsersDropdown);

                int result = JOptionPane.showConfirmDialog(deleteMenu.this,
                        userMessagePanel, "Select Message", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String selectedMessage = (String) allUsersDropdown.getSelectedItem();
                    String finalMessage = selectedMessage.substring(selectedMessage.indexOf(":") + 1);
                    System.out.println("Selected Message: " + finalMessage);

                    if (finalMessage != null && !finalMessage.trim().isEmpty()) {
                        messaging.deleteUsersMessage(user, finalMessage, date, isRead);
                        JOptionPane.showMessageDialog(deleteMenu.this,
                                "Message deleted for all users.");
                    } else {
                        JOptionPane.showMessageDialog(deleteMenu.this,
                                "No message selected or invalid selection.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(deleteMenu.this,
                            "No message selected.");
                }
            }
        });

        JPanel formPanel3 = new JPanel(new GridLayout(2, 1, 20, 20));

        friendDropdown = new JComboBox<>();
        friendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        friendDropdown.setPreferredSize(new Dimension(300, 50));
        updateFriendDropdown();
        formPanel3.add(friendDropdown);

        JPanel formPanel4 = new JPanel(new GridLayout(2, 1, 20, 20));

        singleFriendDropdown = new JComboBox<>();
        singleFriendDropdown.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        singleFriendDropdown.setPreferredSize(new Dimension(300, 50));
        updateSingleFriendMessage();
        formPanel4.add(singleFriendDropdown);

        JButton textAFriend = new JButton("DELETE MESSAGE TO A SINGLE FRIEND");
        textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));
        textAFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Messaging messageTemp = new Messaging(user, null, content, date, isRead);
                messageTemp.loadMessages(user);
                ArrayList<Messaging> messages = Messaging.getMessageHistory();
                System.out.println(messages);

                JPanel singleFriendMessagePanel = new JPanel();
                singleFriendMessagePanel.add(new JLabel("Who would you like to delete the message from?"));
                singleFriendMessagePanel.add(friendDropdown);

                int result = JOptionPane.showConfirmDialog(deleteMenu.this,
                        singleFriendMessagePanel, "Select Friend", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    String selectedFriend = (String) friendDropdown.getSelectedItem();

                    JPanel singleMessagePanel = new JPanel();
                    singleMessagePanel.add(new JLabel("What message would you like to delete?"));
                    singleMessagePanel.add(singleFriendDropdown);

                    int resultMessage = JOptionPane.showConfirmDialog(deleteMenu.this,
                            singleMessagePanel, "Select Message", JOptionPane.OK_CANCEL_OPTION);

                    if (resultMessage == JOptionPane.OK_OPTION) {
                        String selectedMessage = (String) singleFriendDropdown.getSelectedItem();
                        String finalMessage = selectedMessage.substring(
                                selectedMessage.indexOf(":") + 1,
                                selectedMessage.lastIndexOf(":")
                        );
                        System.out.println("Selected Message: " + finalMessage);
                        User user1 = new User("temp");
                        user1.loadUsers();

                        ArrayList<User> allUsers = User.getAllUsers();

                        User friendUser = null;
                        for (User user : User.getAllUsers()) {
                            if (user.getUsername().equals(selectedFriend)) {
                                friendUser = user;
                                break;
                            }
                        }

                        Friends friendFriends = new Friends(friendUser);


                        if (finalMessage != null && !finalMessage.trim().isEmpty()) {
                            messaging.deleteMessage(user, friendFriends, finalMessage, date, isRead);
                            JOptionPane.showMessageDialog(deleteMenu.this,
                                    "Message deleted.");
                            updateSingleFriendMessage();
                        } else {
                            JOptionPane.showMessageDialog(deleteMenu.this,
                                    "No message selected or invalid selection.",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(deleteMenu.this,
                                "No message selected.");
                    }
                } else {
                    JOptionPane.showMessageDialog(deleteMenu.this,
                            "No friend selected.");
                }
            }
        });

        add(welcomeLabel);
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Bernard MT", Font.PLAIN, 20));
        backButton.addActionListener(e -> {
            new messageMenu1().setVisible(true);
            dispose();
        });

        bottomBar.add(textAllFriends);
        bottomBar.add(textAllUsers);
        bottomBar.add(textAFriend);
        bottomBar.add(backButton);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private void updateAllFriendsMessages() {
        User tempUser = new User(ComplexGUI.usernameGUI);
        allFriendsDropdown.removeAllItems();
        messaging.loadAllFriendMessages(tempUser);
        ArrayList<Messaging> messages = messaging.getMessageHistory();
        System.out.println(messages);
        for (Messaging message : messages) {
            allFriendsDropdown.addItem(message.toString());
        }
    }

    private void updateAllUsersMessages() {
        User tempUser = new User(ComplexGUI.usernameGUI);
        allUsersDropdown.removeAllItems();
        messaging.loadAllUsersMessages(tempUser);
        ArrayList<Messaging> messages = messaging.getMessageHistory();
        System.out.println(messages);
        for (Messaging message : messages) {
            allUsersDropdown.addItem(message.toString());
        }
    }

    private void updateSingleFriendMessage() {
        User tempUser = new User(ComplexGUI.usernameGUI);
        singleFriendDropdown.removeAllItems();
        messaging.loadMessages(tempUser);
        ArrayList<Messaging> messages = messaging.getMessageHistory();
        System.out.println(messages);
        for (Messaging message : messages) {
            singleFriendDropdown.addItem(message.toString());
        }
    }

    private void updateFriendDropdown() {
        friendDropdown.removeAllItems();
        receiver = new Friends(ComplexGUI.waypoint);
        receiver.loadFriends();
        ArrayList<User> friendsList = Friends.getFriendsList();
        System.out.println(friendsList);
        for (User friend : friendsList) {
            friendDropdown.addItem(friend.getUsername());
        }
    }

    class MainGUI extends JFrame {

    }
}

// Final
