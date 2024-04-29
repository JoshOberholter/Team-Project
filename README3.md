This is Phase 3 of our Team Project

1.

For this phase, we implimented the GUI for our social media application. Unfortunately we were not able to fully impliment all the functionality we would have liked to due to issues in displaying aspects of the GUI in response to user input. Despite this we have a copmlete GUI that would have displayed all necessary information as well as methods that would correspond to actions that the user could do, which can be seen in the code as well as the presentation we made about our application. For using our application, we have the login or create new user screen, which will allow the user to access the applicaiton, and once that is completed, will send them to the main page. Further than that point, the functionality is not complete, but the layout for it can be observed in the actual code for the client and server.

2. 

Joshua - Sumbitted Phase 2 to Vocaruem Workspace

3. 

Server - This is the server, which will connect to a Client and communicate with it, performing specific functions. It has the capacity to accept multiple clients at once, using the ClientHandler class that will hold each thread. The run method holds the functionality for each thread, where we show the srver and client will communicate. It will connect with a client and go throuhg the login/create new user process, checking the database to do so. Past that, unfortuanately we were not able to impliment all the functionality, due the issues with the GUI, however in the code you can see the loop we created to accept information from the client depending on what button they press and perform some function, specifically those found in the methods of the ClientHandler subclass which are sendFriendRequest, remove Friend, respondFriendRequest, blocked, startGroupchat, deleteGroupchat, addMessage, deleteMessage, parseMessage, and parseGroupchat.

Client - This is the client which will connect to the Server and communicate with it. It will not store any information, rather it prompt input from the user and display information sent by the serve when those things are necessary. It holds all the GUI that will be displayed to the user depending on what they select. It begins with a login feature, and when you go through that it opens the main frame for our application. Unfortuanetely we were not able to get the GUI to chagne the ways we needed it to when buttons are pressed, but there are panels in the code (they are shown in our presentation) that would display the information.

Database.java - This is the database of our social media application. It contains ArrayLists of both the the users that have accessed the database as well as the groupchats that are a part of it. It can be initilized with or without a preexisting list of participants and groupchats. It contains getters for its ArrayLists, participants and groupChats, as well as methods to both add and remove from them. It also contains methods to read and write to a database text file that allows the context of the database to be stored. it is also thread safe, so it can be accessed by multiple threads at once.

GroupChat.java - This is where direct messaging takes place in our social media application. It contains an ArrayList of users that holds the members of the groupchat and an ArrayList of Messages that holds the chat's messages. It is initalized by calling the constructor with an Arraylist of the members you want in the chat. It has getters for the members and messages, as well as methods to add and remove both members and messages. It also has a method to return all of the photopaths from messages that contain them, as well as an overwritten toString method.

Message.java - This is the message object for our social media application. It can be initialized with a User and a message, or a user, message, and photo path for sharing pictures. It also holds the boolean value seen, which will be set to false. It contains getters and setters for all of its fields, as well as an overwritten equals and toString methods

User.java - This is the user object for out social media application. It is initialized with a username, password, and a boolean of whether you want strangers to be able to message you or not. The password is checked to see if it follows our requirements before it is initialized, and if it doesnt, the constructor throws an Invalid Password Exception. The other fields are the friends, freindRequests, blockedUsers, and dmsAnd GroupChats ArrayLists as well the string for a profile picture path. It contains getters and setters for many of the fields, a , methods that return whether or not a user is in the friends of blocked ArrayLists, methods to add or remove users from the blockedUsers list and groupChats from the dmsAndGroupChats list. It also contains functionality for sending, accepting, and denying friend requests. There are methods to add and remove messages from a groupchat using the specific user, and finally there is an overwritten equals method and an overwritten toString method.

PhotoUploader.java - We had initially planned to impliment photos, but were not able to get that done in time, but this is the layout of how we would have gone about uploading those pictures

UserProfileUI.java - This was an early example of how we might go about displaying the user's profile inlcuding profile picture, but was not implimented into the final product

Exceptions: InvalidPasswordException - will be thrown when a password does not meet the requirements when trying to create a new user MessageNotFoundException - Will be thrown when trying to remove a message and it is not in the ArrayList UserNotFoundException - Will be thrown when trying to remove a User and it is not in the ArrayList

Interfaces: DatabaseInt.java - Interface for Database GroupChatInt.java - Interface for GroupChat MessageInt.java - Interface for Message UserInt.java - Interface for User UserProfileInt - Interface for UserProfileUI
