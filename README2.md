This is Phase 2 of our Team Project


2. 
Joshua - Sumbitted Phase 1 to Vocaruem Workspace

3. 
Database.java - This is the database of our social media application. It contains ArrayLists of both the the users that have accessed the database as well as the groupchats that are a part of it. It can be initilized with or without a preexisting list of participants and groupchats. It contains getters for its ArrayLists, participants and groupChats, as well as methods to both add and remove from them.

GroupChat.java - This is where direct messaging takes place in our social media application. It contains an ArrayList of users that holds the members of the groupchat and an ArrayList of Messages that holds the chat's messages. It is initalized by calling the constructor with an Arraylist of the members you want in the chat. It has getters for the members and messages, as well as methods to add and remove both members and messages. It also has a method to return all of the photopaths from messages that contain them

Message.java - This is the message object for our social media application. It can be initialized with a User and a message, or a user, message, and photo path for sharing pictures. It also holds the boolean value seen, which will be set to false. It contains getters and setters for all of its fields, as well as an overwritten equals method

User.java  - This is the user object for out social media application. It is initialized with a username, password, and a boolean of whether you want strangers to be able to message you or not. The password is checked to see if it follows our requirements before it is initialized, and if it doesnt, the constructor throws an Invalid Password Exception. The other fields are the friends, freindRequests, blockedUsers, and dmsAnd GroupChats ArrayLists as well the string for a profile picture path. It contains getters and setters for many of the fields, a , methods that return whether or not a user is in the friends of blocked ArrayLists, methods to add or remove users from the blockedUsers list and groupChats from the dmsAndGroupChats list. It also contains functionality for sending, accepting, and denying friend requests. There are methods to add and remove messages from a groupchat using the specific user, and finally there is an overwritten equals method.

PhotoUploader.java - This file will be how we upload photos to messages and user profiles pictures down the line, using GUI to let the user upload pictures.

UserProfileUI.java - This file will be how we display a users profile down the line, using GUI to display their information as well as their profile picture

Exceptions:
InvalidPasswordException - will be thrown when a password does not meet the requirements when trying to create a new user
MessageNotFoundException - Will be thrown when trying to remove a message and it is not in the ArrayList
UserNotFoundException - Will be thrown when trying to remove a User and it is not in the ArrayList

Interfaces:
DatabaseInt.java - Interface for Database
GroupChatInt.java - Interface for GroupChat
MessageInt.java - Interface for Message
UserInt.java - Interface for User
UserProfileInt - Interface for UserProfileUI

