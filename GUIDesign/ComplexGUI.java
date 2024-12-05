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
                    dispose();
                }
            });

            bottomBar.add(enterButton, BorderLayout.CENTER);
            add(bottomBar, BorderLayout.SOUTH);
        }
    }

    class friendsScreen extends JFrame {
        public friendsScreen() {
            setTitle("Friends Screen");
            setSize(1000, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            JLabel titleLabel = new JLabel("CHOOSE AN OPTION", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
            add(titleLabel, BorderLayout.CENTER);

            JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            JButton addFriendsButton = new JButton("ADD FRIENDS");
            addFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
            JButton removeFriendsButton = new JButton("REMOVE FRIENDS");
            removeFriendsButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
            JButton messageButton = new JButton("SEND MESSAGES");
            messageButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));

            optionsPanel.add(addFriendsButton);
            optionsPanel.add(removeFriendsButton);
            optionsPanel.add(messageButton);
            add(optionsPanel, BorderLayout.CENTER);

            addFriendsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String friendsName = JOptionPane.showInputDialog(friendsScreen.this,
                            "Who do you want to add?");
                    if (friendsName != null) {
                        JOptionPane.showMessageDialog(friendsScreen.this,
                                "Please enter a name.");
                        return;
                    }

//                    String doubleCheckFriend = FRIENDS_ADD + ";" + friendName;
//                    System.out.println(doubleCheckFriend);

                    boolean friendExists = true;
                    boolean alreadyFriends = false;

                    if (friendExists) {
                        if (alreadyFriends) {
                            JOptionPane.showMessageDialog(friendsScreen.this,
                                    "You are already friends");
                        } else {
                            JOptionPane.showMessageDialog(friendsScreen.this,
                                    "Friend added successfully.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(friendsScreen.this,
                                "User does not exist.");
                    }

                    dispose();
                }
            });

            removeFriendsButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String friendsName = JOptionPane.showInputDialog(friendsScreen.this,
                            "Who do you want to remove?");
                    if (friendsName != null) {
                        JOptionPane.showMessageDialog(friendsScreen.this,
                                "Please enter a name.");
                        return;
                    }

                    boolean isFriend = true;
                    boolean friendExists = true;

                    if (friendExists) {
                        if (isFriend) {
                            JOptionPane.showMessageDialog(friendsScreen.this,
                                    "Friend removed successfully.");
                        } else {
                            JOptionPane.showMessageDialog(friendsScreen.this,
                                    "This person is not your friend");
                        }
                    } else {
                        JOptionPane.showMessageDialog(friendsScreen.this,
                                "User does not exist.");
                    }

                    dispose();

                }
            });

            messageButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(() -> new messageMenu().setVisible(true));
                    dispose();
                }

            });
        }

        class messageMenu extends JFrame {
            public messageMenu() {
                setTitle("Message Menu");
                setSize(1000, 700);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new BorderLayout());

                JLabel titleLabel = new JLabel("CHOOSE AN OPTION", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
                add(titleLabel, BorderLayout.CENTER);

                JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                JButton sendTextMessagesButton = new JButton("SEND TEXT MESSAGES");
                sendTextMessagesButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
                JButton messagePhotoButton = new JButton("SEND PHOTO MESSAGES");
                messagePhotoButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));
                JButton removeMessageButton = new JButton("DELETE MESSAGES");
                removeMessageButton.setFont(new Font("Bernard MT", Font.PLAIN, 30));

                optionsPanel.add(sendTextMessagesButton);
                optionsPanel.add(messagePhotoButton);
                optionsPanel.add(removeMessageButton);

                add(optionsPanel, BorderLayout.CENTER);

                sendTextMessagesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(() -> new textMessageMenu().setVisible(true));
                    }
                });
            }
        }

        class textMessageMenu extends JFrame {
            public textMessageMenu() {
                JLabel titleLabel = new JLabel("CHOOSE AN OPTION", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Bernard MT", Font.BOLD, 30));
                add(titleLabel, BorderLayout.CENTER);

                JPanel optionsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
                JButton textAllFriends = new JButton("TEXT ALL FRIENDS");
                textAllFriends.setFont(new Font("Bernard MT", Font.PLAIN, 30));
                JButton textAllUsers = new JButton("TEXT ALL USERS");
                textAllUsers.setFont(new Font("Bernard MT", Font.PLAIN, 30));
                JButton textAFriend = new JButton("TEXT A FRIEND");
                textAFriend.setFont(new Font("Bernard MT", Font.PLAIN, 30));

                textAllFriends.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
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
                        dispose();
                    }
                });

                bottomBar.add(enterButton, BorderLayout.CENTER);
                add(bottomBar, BorderLayout.SOUTH);
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
            new DeleteMessageToAllUsersScreen().setVisible(true);
            dispose();
        });
        add(deleteAllUsersButton);

        JButton deleteSingleFriendButton = new JButton("Delete Message to Single Friend");
        deleteSingleFriendButton.addActionListener(e -> {
            new DeleteMessageToSingleFriendScreen().setVisible(true);
            dispose();
        });
        add(deleteSingleFriendButton);
    }
}

          class DeleteMessageScreen extends JFrame {
    public DeleteMessageScreen() {
        setTitle("Delete Message Options");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel instructionLabel = new JLabel("Select a delete message option:");
        add(instructionLabel);

        JButton deleteAllFriendsButton = new JButton("Delete Message to All Friends");
        deleteAllFriendsButton.addActionListener(e -> {
             
            new DeleteMessageToAllFriendsScreen().setVisible(true);
            dispose();
        });
        add(deleteAllFriendsButton);

        JButton deleteAllUsersButton = new JButton("Delete Message to All Users");
        deleteAllUsersButton.addActionListener(e -> {
             
            new DeleteMessageToAllUsersScreen().setVisible(true);
            dispose();
        });
        add(deleteAllUsersButton);

        JButton deleteSingleFriendButton = new JButton("Delete Message to Single Friend");
        deleteSingleFriendButton.addActionListener(e -> {
            
             new DeleteMessageToSingleFriendScreen().setVisible(true);
            dispose();
        });
        add(deleteSingleFriendButton);
    }

          }
            class DeleteMessageToAllFriendsScreen extends JFrame {
    public DeleteMessageToAllFriendsScreen() {
        setTitle("Delete Message to All Friends");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Enter the message to delete for all friends:");
        JTextField messageField = new JTextField(20);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String message = messageField.getText();
            //have to put communication here
            JOptionPane.showMessageDialog(this, "Message to all friends deletion submitted!");
            dispose();
        });

        add(messageLabel, BorderLayout.NORTH);
        add(messageField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }
}
  class DeleteMessageToAllUsersScreen extends JFrame {
    public DeleteMessageToAllUsersScreen() {
        setTitle("Delete Message to All Users");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Enter the message to delete for all users:");
        JTextField messageField = new JTextField(20);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String message = messageField.getText();
            //have to put communication here
            JOptionPane.showMessageDialog(this, "Message to all users deletion submitted!");
            dispose();
        });

        add(messageLabel, BorderLayout.NORTH);
        add(messageField, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }
}
             class DeleteMessageToSingleFriendScreen extends JFrame {
    public DeleteMessageToSingleFriendScreen() {
        setTitle("Delete Message to Single Friend");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel friendLabel = new JLabel("Enter friend's name:");
        JTextField friendField = new JTextField(20);

        JLabel messageLabel = new JLabel("Enter the message to delete:");
        JTextField messageField = new JTextField(20);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String friend = friendField.getText();
            String message = messageField.getText();
            //have to put communication here
            JOptionPane.showMessageDialog(this, "Single friend's message deletion submitted!");
            dispose();
        });

        add(friendLabel);
        add(friendField);
        add(messageLabel);
        add(messageField);
        add(submitButton);
    }
}

            class MainGUI extends JFrame {

            }
        }
