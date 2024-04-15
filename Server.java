import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * A server for the social media site.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtz, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
 * @version April 15th, 2024
 *
 * the portnumber is 215
 */

public class Server {
    private static Database newDatabase;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(215);
            System.out.println("Server is waiting for clients to connect...");
            newDatabase = new Database();
            newDatabase.readDatabase();

            while (true) {
                Socket socket = serverSocket.accept(); // Accept a new client
                new Thread(new ClientHandler(socket)).start(); // Handle each client in a new thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private User clientUser;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.clientUser = null;
        }

        public synchronized boolean sendFriendRequest(User threadedUser, String username) {
            for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                if (username.equals(newDatabase.getUsers().get(i).getUsername())) {
                    Boolean isBlocked = newDatabase.getUsers().get(i).addFriendRequest(threadedUser);
                    return !isBlocked;
                }
            }
            return false;
        }
    
        public synchronized boolean removeFriend(User threadedUser, String username) {
            for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                if (username.equals(newDatabase.getUsers().get(i).getUsername())) {
                    newDatabase.getUsers().get(i).getFriends().remove(threadedUser);
                    return true;
                }
            }
            return false;
        }
    
        public synchronized boolean respondFriendRequest(User threadedUser, String username, Boolean accepted) {
            for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                if (username.equals(newDatabase.getUsers().get(i).getUsername())) {
                    newDatabase.getUsers().get(i).addFriend(threadedUser);
                    newDatabase.getUsers().get(i).getFriendRequests().remove(threadedUser);
                    return true;
                }
            }
            return false;
        }
    
        public synchronized boolean blocked(User threadedUser, String username) {
            for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                if (username.equals(newDatabase.getUsers().get(i).getUsername())) {
                    if (newDatabase.getUsers().get(i).getFriends().contains(threadedUser)) {
                        try {
                            newDatabase.getUsers().get(i).removeFriend(threadedUser);
                        } catch (UserNotFoundException e) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    
        public synchronized void startGroupchat(ArrayList<User> participantsInGroupChat) {
            GroupChat newGroupChat = new GroupChat(participantsInGroupChat);
            newDatabase.addGroupChat(newGroupChat);
        }
    
        public synchronized boolean deleteGroupchat(GroupChat groupchat) {
            for (int i = 0; i < newDatabase.getGroupChats().size(); i++) {
                if (newDatabase.getGroupChats().get(i).equals(groupchat)) {
                    newDatabase.getGroupChats().remove(groupchat);
                    return true;
                }
            }
            return false;
        }
    
        public synchronized boolean addMessage(GroupChat groupchat, Message message) {
            for (int i = 0; i < newDatabase.getGroupChats().size(); i++) {
                if (newDatabase.getGroupChats().get(i).equals(groupchat)) {
                    newDatabase.getGroupChats().get(i).addMessage(message);
                    return true;
                }
            }
            return false;
        }
    
        public synchronized boolean deleteMessage(GroupChat groupchat, Message message) {
            for (int i = 0; i < newDatabase.getGroupChats().size(); i++) {
                if (newDatabase.getGroupChats().get(i).equals(groupchat)) {
                    try {
                        newDatabase.getGroupChats().get(i).removeMessage(message);
                        return true;
                    } catch (MessageNotFoundException e) {
                        return false;
                    }
                }
            }
            return false;
        }

        public Message parseMessage(String line) {
            String username = line.substring(0, line.indexOf(";"));
            Message message;
            User user = null;
            for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                if (newDatabase.getUsers().get(i).getUsername().equals(username)) {
                    user = newDatabase.getUsers().get(i);
                }
            }
            if (user == null) {
                return null;
            }
            line = line.substring(line.indexOf(";") + 1);
            String messageTxt = line.substring(0, line.indexOf(";"));
            line = line.substring(line.indexOf(";") + 1);
            Boolean seen = Boolean.parseBoolean(line.substring(0, line.indexOf(";")));
            line = line.substring(line.indexOf(";") + 1);
            double time = Double.parseDouble(line);
            message = new Message(user, messageTxt, seen, time);
            return message;
        }

        public GroupChat parseGroupchat(String line) {
            if (line.contains("GroupChat<")) {
                String[] groupChatThings = line.substring(10, line.length() - 1) .split(",");
                String[] usernames = groupChatThings[0].split(";");
                ArrayList<User> members = new ArrayList<>();
                for (String username : usernames) {
                    for (User user : newDatabase.getUsers()) {
                        if (user.getUsername().equals(username)) {
                            members.add(user);
                        }
                    }
                }
    
                String[] messageStrings = groupChatThings[1].split("#");
                ArrayList<Message> messages = new ArrayList<>();
    
                for (String message : messageStrings) {
                    if (!message.isEmpty()) { // this is because of how the string is formatted
                        String[] messageThings = message.split(";");
                        String username = messageThings[0];
                        User sender = null;
                        for (User u : members) {
                            if (u.getUsername().equals(username)) {
                                sender = u;
                            }
                        }
                        String messageText = messageThings[1];
                        boolean seen = Boolean.parseBoolean(messageThings[2]);
                        double time = Double.parseDouble(messageThings[3]);
                        if (messageThings.length == 4) {
                            String photoPath  = messageThings[3];
                            Message thisMessage = new Message(sender, messageText, photoPath, time);
                            thisMessage.setSeen(seen);
                            messages.add(thisMessage);
    
                        } else {
                            Message thisMessage = new Message(sender, messageText, time);
                            thisMessage.setSeen(seen);
                            messages.add(thisMessage);
    
                        }
                    }
                }
    
                GroupChat thisGroupChat = new GroupChat(members);
                thisGroupChat.setMessages(messages);
                return thisGroupChat;
            } else {
                return null;
            }
        }

        public void run() {
            try {
                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                boolean cont = true;
                String username = "";
                while (cont) {
                    // Read username and password sent by client
                    username = reader.readLine();
                    String password = reader.readLine();

                    // Placeholder authentication logic
                    for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                        if (newDatabase.getUsers().get(i).getUsername().equals(username)){
                            if (newDatabase.getUsers().get(i).getPassword().equals(password)) {
                                writer.println("Login successful!");
                                cont = false;
                            } else {
                                writer.println("Username or Password is incorrect");
                            }
                        }
                    }
                    writer.println("Username or Password is incorrect");
                }
                //Sets up the User.
                for (User user : newDatabase.getUsers()) {
                    if (user.getUsername().equals(username)) {
                        this.clientUser = user;
                    }
                }
                cont = true;
                while (cont) {
                    String dataLine = reader.readLine();
                    switch (dataLine) {
                        case "addFriend":
                            String otherUsername = reader.readLine();
                            Boolean success = sendFriendRequest(clientUser, otherUsername);
                            writer.println("" + success);
                            break;
                        case "removeFriend":
                            otherUsername = reader.readLine();
                            success = removeFriend(clientUser, otherUsername);
                            writer.println("" + success);
                            break;
                        case "respondFriendRequest":
                            otherUsername = reader.readLine();
                            Boolean accepted = Boolean.parseBoolean(reader.readLine());
                            success = respondFriendRequest(clientUser, otherUsername, accepted);
                            writer.println("" + success);
                            break;
                        case "blockUser":
                            otherUsername = reader.readLine();
                            success = blocked(clientUser, otherUsername);
                            writer.println("" + success);
                            break;
                        case "startGroupchat":
                            ArrayList<User> participantsInGroupChat = new ArrayList<User>();
                            try {
                                int numberOfParticipants = Integer.parseInt(reader.readLine());
                                for (int i = 0; i < numberOfParticipants; i++) {
                                    String usernameOfUserToAdd = reader.readLine();
                                    User userToAdd = null;
                                    for (int j = 0; j < newDatabase.getUsers().size(); j++) {
                                        if (newDatabase.getUsers().get(j).getUsername().equals(usernameOfUserToAdd)) {
                                            userToAdd = newDatabase.getUsers().get(j);
                                        }
                                    }
                                    participantsInGroupChat.add(userToAdd);
                                }
                                startGroupchat(participantsInGroupChat);
                                writer.println("true");
                            } catch (Exception e) {
                                writer.println("false");
                            }
                            break;
                        case "deleteGroupchat":
                            GroupChat groupchat = parseGroupchat(reader.readLine());
                            success = deleteGroupchat(groupchat);
                            writer.println("" + success);
                            break;
                        case "addMessage":
                            groupchat = parseGroupchat(reader.readLine());
                            Message message = parseMessage(reader.readLine());
                            if (groupchat == null || message == null) {
                                success = false;
                            } else {
                                success = addMessage(groupchat, message);
                            }
                            writer.println("" + success);
                            break;
                        case "deleteMessage":
                            groupchat = parseGroupchat(reader.readLine());
                            message = parseMessage(reader.readLine());
                            if (groupchat == null || message == null) {
                                success = false;
                            } else {
                                success = deleteMessage(groupchat, message);
                            }
                            writer.println("" + success);
                            break;
                        case "exit":
                            cont = false;
                            break;
                    }
                }

            } catch (IOException e) {
                System.out.println("Error handling client " + clientSocket.getInetAddress().getHostAddress());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close(); // Ensure the socket is closed after handling
                } catch (IOException e) {
                    System.out.println("Could not close the socket properly.");
                }
            }
        }
    }
}
