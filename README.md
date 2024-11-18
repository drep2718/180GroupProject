# CS180 Project - L25-Team 2
## Group members
Sabareesh Vasudevan, Alex Paquette, Aiden Drepaniotis, Santhosh Vaddi, Linh Dang
## Overview
This program is a social communication platform that allows users to connect with friends by searching up for usernames. It also provides excellent privacy control by allowing its users to restrict or block others and deactivate their accounts if desired.
## Features
- Friends search and add: Search for friends via usernames and add them to your friend list.
- Privacy controls: Block or restrict other users, delete messages or accounts if desired.
- Secure profiles: User authentification with password validation and optional profile pictures.
- Photo messaging: Send and receive photos from friends to capture the moment.
## How it works

Phase 1: The program is constructed by four main folders, each comprising an interface, an implementing class, and associated test cases.

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

Phase 2: The program is now thread safe, with added servers that can work with clients concurrently.
### Client class:
- Allows users to connect with the server class
- Displays choices for users to choose (logging in/out, creating accounts, exit, etc.)
### Server class:
Processes every command that the user wishes to make, including logging in/out, creating accounts, adding/blocking/removing friends, and sending/deleting messages.
### ThreadSafe class:
Allows multiple clients to be online in the same server without running into errors/conflicts.

NOTE: There is no test cases as we do not have anything that does not relate to the client and server classes, along with concurrency.

## How to compile and run the project
- Ensure JUnit 5 library is set up and use an IDE such as IntelliJ or use command line
- Compile all files using the test cases in the src/test folder.
- To run the project, use a JUnit Platform Console Launcher on an IDE
  
## Who submitted what
- Aiden submitted the report and the Vocareum workspace
