# CS180 Project - L25-Team 2
## Group members
Sabareesh Vasudevan, Alex Paquette, Aiden Drepaniotis, Santhosh Vaddi, Linh Dang
## Overview
This program is a social communication platform that allows users to connect with friends by searching up for usernames. It also provides excellent privacy control by allowing its users to restrict or block others and deactivate their accounts if desired.
## Features
- Friends search and add: Search for friends via usernames and add them to your friend list.
- Privacy controls: Block or restrict other users, delete messages or accounts if desired.
- Secure profiles: User authentication with password validation and optional profile pictures.
- Photo messaging: Send and receive photos from friends to capture the moment.
## How it works

## Phase 1: The program is constructed by four main folders, each comprising an interface, an implementing class, and associated test cases.

### User interface and class: 
- Create a profile: Create a profile including username and password. Set up a profile picture if needed.
- Contains methods for managing user interactions, such as checking friends and blocked users
- Secure login: Validate their password while logging in for security.
- Used by Messaging, PhotoMessaging, and Friends classes for message and interaction tracking
### Friends interface and class: 
- Social interaction: Search up others' usernames and add friends.
- Manages a User's friends list and blocked users
- Provides methods to add, remove, and block/unblock users
- Privacy controls: Block or restrict other users.
- Used by Messaging and PhotoMessaging to validate interactions between users
### Messaging interface and class: 
- Message history: Store messages in the conversation history with friends or all users.
- Privacy: Delete messages from conversation history.
- Represents a text message sent from one user to another
- Contains fields for the sender, receiver, message content, date, and read status
- Provides methods for sending, deleting, and saving messages to files
### PhotoMessaging interface and class:
- Send and receive photos: Send and receive photos from each other.
- Uses the basic Messaging functionality to include photo messages
- Contains fields for storing image content utilizing BufferedImage
- Provides methods to send, delete, and save photo messages, as well as reporting content

## Phase 2: The program is now thread safe, with added servers that can work with clients concurrently.
### Client class:
- Allows users to connect with the server class
- Displays choices for users to choose (logging in/out, creating accounts, exiting, etc.)
- Displays messages for users to ensure that their commands have been successfully processed.
- Logging in/out: Successfully logging an user in after checking for the correct username and password. If the process fails, the password or username is incorrect or the user does not exist and needs to create an account.
- Creating accounts: Create an account and the account will be added to the database.
- Sending messages: Sending messages/photo messages to all existing users, all friends, or a single friend. Users can also delete desired sent messages/photo messages from the conversation or database.
- Adding friends: Users can add other existing users as friends. If a user wants to add a friend but the wished user does not exist, the future GUI in phase 3 will handle this and display an error message that the said user does not exist instead of crashing in phase 2.
### ThreadSafe class:
Processes every command that the user wishes to make, including logging in/out, creating accounts, adding/blocking/removing friends, and sending/deleting messages.
communicates with the client and receives the information for the client, runs the methods, than send information back to the client to process and than print a result to the terminal
### Server class:
Allows multiple clients to be online in the same server without running into errors/conflicts.
This class allows for multithreading and ensures that it allows for multiple client connections.

NOTE: There are no test cases as we do not have anything that does not relate to the client and server classes, along with concurrency.

## How to compile and run the project
- Ensure JUnit 5 library is set up and use an IDE such as IntelliJ or use command line
- Compile all files using the test cases in the src/test folder.
- To run the project, use a JUnit Platform Console Launcher on an IDE

## How to Test
- Run through the given options that are printed to the terminal when the client and server are run.
- Files will auto populate and allow you to use every option.
- When Logging in it will prompt a username and password that you will enter, but you have to create an account before you log in.
- When adding a friend note that any error that come from entering a friend that does not exist as a user will be handled in the GUI because with that implementation you will not be able to add someone who is not already a user on the platform using a possibly dropdown menu system
- The same applies for removing someone who us not a friend, this may cause an issue that will be handled by the GUI implementation
- The same goes throughout the program including blocking non-users not trying to unblock someone who is not blocked this will all be handled by the GUI in phase 3
- This also applies for the messaging class for deleting a message from a non friend or messages that never existed in the first place

## ComplexGUI class 
- This class outlines all the GUIs that we used in our client and server that replaced the scanners we used in phase 2
- This class is full of many different Jpanels for different screens we go to while executing the code
- You will start at the welome screen than go to the main menu where you can Login or Create account, and use the back button to go back to the main menu
- Once your logged in or you created an account you can start adding and removing friends and there see all your friends list
- You can also do the same with blocking and unblocking users and view those you have blocked
- Than the messaging where you can send a message to all your friends on the platform, all users on the platform, and a single friend on the platform
- And than you can view all the messages that you sent to either all friends, users, or a single friend
- Than you can delete the messages you send as well if you dont want them to be seen
- Lastly you can select from your directary a photo to send to the same 3 choices.

  
## Who submitted what
- Aiden submitted the report and the Vocareum workspace (Phase 1)
- Aiden submitted the report and the Vocareum workspace (Phase 2)
- Aiden submitted the report and the Vocareum workspace (Phase 3)
- Linh submitted the presentation (phase 3)





