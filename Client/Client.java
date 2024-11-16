import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client implements FlagInterface {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
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

        while (true) {

            while (!loggedIn) {
                System.out.println("1- Login to account");
                System.out.println("2- Create account");
                System.out.println("3- Exit");

                String firstMenuItem = scan.nextLine();

                if (!firstMenuItem.equals("1") && !firstMenuItem.equals("2") && !firstMenuItem.equals("3")) {
                    System.out.println("Not a valid choice");
                    continue;
                } else if (firstMenuItem.equals("1")) {
                    System.out.println("Enter your username");
                    String username = scan.nextLine();
                    System.out.println("Enter your password");
                    String password = scan.nextLine();
                    String login = LOGIN + ";" + username + ";" + password;
                    writer.write(login);
                    writer.println();
                    writer.flush();
                    String message = reader.readLine();

                    if (message.contains(LOGIN)) {
                        String[] index = message.split(";");
                        String successMessage = index[1];
                        if (successMessage.equals("Correct username")) {
                            System.out.println("Correct username");
                        } else if (successMessage.equals("User does not exist.")) {
                            System.out.println("User does not exist. You may create User");
                            continue;
                        } else if (successMessage.equals("No username provided")) {
                            System.out.println("No username provided");
                            continue;
                        } else {
                            System.out.println("Failure try again");
                            continue;
                        }
                        message = reader.readLine();
                        index = message.split(";");
                        String passwordMessage = index[1];
                        if (passwordMessage.equals("Login Successful")) {
                            System.out.println("Login Successful");
                        } else if (passwordMessage.equals("Login Failed")) {
                            System.out.println("Login Failed Try Again");
                            continue;
                        } else if (passwordMessage.equals("No password provided")) {
                            System.out.println("No password provided try again");
                            continue;
                        } else {
                            System.out.println("Failure try again");
                            continue;
                        }

                    }
                    loggedIn = true;


                } else if (firstMenuItem.equals("2")) {
                    System.out.println("Enter you new username");
                    String newUsername = scan.nextLine();
                    System.out.println("Enter your new password");
                    String newPassword = scan.nextLine();
                    System.out.println("Enter your bio");
                    String bio = scan.nextLine();
                    String create = CREATE + ";" + newUsername + ";" + newPassword + ";" + bio;
                    writer.write(create);
                    writer.println();
                    writer.flush();
                    String message = reader.readLine();

                    if (message.contains(CREATE)) {
                        String[] index = message.split(";");
                        String successMessage = index[1];
                        if (successMessage.equals("true")) {
                            System.out.println("User Successfully Created");
                        } else if (successMessage.equals("false")) {
                            System.out.println("User does not exist. You may create User");
                            continue;
                        } else {
                            System.out.println("Failure try again");
                            continue;
                        }

                        loggedIn = true;
                    } else if (firstMenuItem.equals("3")) {
                        return;
                    }
                }

                while (loggedIn) {
                    System.out.println("1- Add or remove friends");
                    System.out.println("2- block or unblock users");
                    System.out.println("3- Message");
                    System.out.println("4- Logout");

                    String secondMenuItem = scan.nextLine();

                    if (!secondMenuItem.equals("1") && !secondMenuItem.equals("2") && !secondMenuItem.equals("3") && !secondMenuItem.equals("4")) {
                        System.out.println("Not a valid choice");
                        continue;
                    } else if (secondMenuItem.equals("1")) {
                        System.out.println("1- Add Friend");
                        System.out.println("2- Remove Friend");
                        String addOrRemove = scan.nextLine();
                        if (addOrRemove.equals("1")) {
                            System.out.println("What is the name of the user you want to add as a friend");
                            String friendName = scan.nextLine();
                            String friendToAdd = FRIENDS_ADD + ";" + friendName;
                            writer.write(friendToAdd);
                            writer.println();
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
                        String[] index = message.split(";");
                        String trueOrFalse = index[1];
                        if (message.contains(FRIENDS_ADD)) {
                            if (trueOrFalse.equals("true")) {
                                System.out.println("Friend added Successfully");
                            } else if (trueOrFalse.equals("false")) {
                                System.out.println("Cannot add friend, try again");
                                continue;
                            } else {
                                System.out.println("Friend does not exist, try again");
                                continue;
                            }
                        } else if (message.contains(FRIENDS_REMOVE)) {
                            if (trueOrFalse.equals("true")) {
                                System.out.println("Friend removed Successfully");
                            } else if (trueOrFalse.equals("false")) {
                                System.out.println("Cannot remove friend, try again");
                                continue;
                            } else {
                                System.out.println("Friend does not exist, try again");
                                continue;
                            }
                        }


                    } else if (secondMenuItem.equals("2")) {
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

                        String message = reader.readLine();
                        String[] index = message.split(";");
                        String trueOrFalse = index[1];
                        if (message.contains(FRIENDS_BLOCK)) {
                            if (trueOrFalse.equals("true")) {
                                System.out.println("Friend blocked Successfully");
                            } else if (trueOrFalse.equals("false")) {
                                System.out.println("Cannot block friend, try again");
                                continue;
                            } else {
                                System.out.println("Friend does not exist, try again");
                                continue;
                            }
                        } else if (message.contains(FRIENDS_UNBLOCK)) {
                            if (trueOrFalse.equals("true")) {
                                System.out.println("Friend unblocked Successfully");
                            } else if (trueOrFalse.equals("false")) {
                                System.out.println("Cannot unblock friend, try again");
                                continue;
                            } else {
                                System.out.println("Friend does not exist, try again");
                                continue;
                            }
                        }

                    } else if (secondMenuItem.equals("3")) {
                        System.out.println("1- Send a text message");
                        System.out.println("2- Send a photo message");
                        String whichMessage = scan.nextLine();
                        if (whichMessage.equals("1")) {
                            System.out.println("1- Send text message to all friends");
                            System.out.println("2- Send text message to all users");
                            System.out.println("3- send text message to single friend");
                            String textType = scan.nextLine();
                            if (textType.equals("1")) {
                                System.out.println("What would you like to text to all your friends");
                                String text = scan.nextLine();
                                String textALlFriends = TEXT_ALL_FRIENDS + ";" + text;
                                writer.write(textALlFriends);
                                writer.println();
                                writer.flush();
                            } else if (textType.equals("2")) {
                                System.out.println("What would you like to text to all your users");
                                String text = scan.nextLine();
                                String textAllUsers = TEXT_ALL_USERS + ";" + text;
                                writer.write(textAllUsers);
                                writer.println();
                                writer.flush();
                            } else if (textType.equals("3")) {
                                System.out.println("Who is the friend you want to text");
                                String friend = scan.nextLine();
                                System.out.println("What would you like to text to all users");
                                String text = scan.nextLine();
                                String textSingleFriend = TEXT_SINGLE_FRIEND + ";" + friend + ";" + text;
                                writer.write(textSingleFriend);
                                writer.println();
                                writer.flush();
                            }
                        } else if (whichMessage.equals("2")) {
                            System.out.println("1- Send photo message to all friends");
                            System.out.println("2- Send photo message to all users");
                            System.out.println("3- send photo message to single friend");
                            String textType = scan.nextLine();
                            if (textType.equals("1")) {
                                System.out.println("What would you like to message to all your friends");
                                String text = scan.nextLine();
                                String messageALlFriends = MESSAGE_ALL_FRIENDS + ";" + text;
                                writer.write(messageALlFriends);
                                writer.println();
                                writer.flush();
                            } else if (textType.equals("2")) {
                                System.out.println("What would you like to message to all users");
                                String text = scan.nextLine();
                                String messageAllUsers = MESSAGE_ALL_USERS + ";" + text;
                                writer.write(messageAllUsers);
                                writer.println();
                                writer.flush();
                            } else if (textType.equals("3")) {
                                System.out.println("Who is the friend you want to message");
                                String friend = scan.nextLine();
                                System.out.println("What would you like to message to all your users");
                                String text = scan.nextLine();
                                String textSingleFriend = TEXT_SINGLE_FRIEND + ";" + friend + ";" + text;
                                writer.write(textSingleFriend);
                                writer.println();
                                writer.flush();
                            }

                            String message = reader.readLine();
                            String[] index = message.split(";");
                            String trueOrFalse = index[1];
                            if (message.contains(TEXT_ALL_FRIENDS)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("You successfully texted all friends");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You cannot send a message to all friends, try again");
                                    continue;
                                } else {
                                    System.out.println("No friends exist");
                                    continue;
                                }
                            } else if (message.contains(TEXT_ALL_USERS)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("You successfully texted all users");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You cannot send a message to all users, try again");
                                    continue;
                                } else {
                                    System.out.println("Failure");
                                    continue;
                                }
                            } else if (message.contains(TEXT_SINGLE_FRIEND)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("You successfully texted a friend");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You cannot send a message to this friend, try again");
                                    continue;
                                } else {
                                    System.out.println("Not your friend");
                                    continue;
                                }
                            } else if (message.contains(MESSAGE_ALL_FRIENDS)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("You successfully messaged all friends");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You cannot send a messages to all friends");
                                    continue;
                                } else {
                                    System.out.println("No friends");
                                    continue;
                                }
                            } else if (message.contains(MESSAGE_ALL_USERS)) {
                                if (trueOrFalse.equals("true")) {
                                    System.out.println("You successfully messaged all users");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You cannot send a messages to all users");
                                    continue;
                                } else {
                                    System.out.println("Failure");
                                    continue;
                                }
                            } else if (message.contains(MESSAGE_SINGLE_FRIEND)) {
                                if (trueOrFalse.equals("true")) {
                                    

                                    System.out.println("You successfully messaged a friend");
                                } else if (trueOrFalse.equals("false")) {
                                    System.out.println("You cannot message this friend");
                                    continue;
                                } else {
                                    System.out.println("Failure");
                                    continue;
                                }
                            }


                        } else if (secondMenuItem.equals("4")) {
                            loggedIn = false;
                        }

                    }
                    writer.close();
                    reader.close();


                }
            }
        }
    }
}
