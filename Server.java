import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * A server for the social media site.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtzer, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
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

                do {
                    String choice;
                    // Either Login or create new user
                    choice = reader.readLine();
                    if (choice.equals("login")) {
                        String username = reader.readLine();
                        String password = reader.readLine();

                        String response = "failure";

                        for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                            if (newDatabase.getUsers().get(i).getUsername().equals(username)){
                                if (newDatabase.getUsers().get(i).getPassword().equals(password)) {
                                    response = "success";
                                    clientUser = newDatabase.getUsers().get(i);
                                }
                            }
                        }

                        writer.println(response);
                        writer.flush();

                        if (response.equals("success")) {
                            break;
                        }

                    } else if (choice.equals("newUser")) {
                        String username = reader.readLine();
                        String password = reader.readLine();

                        String response = "failure";

                        try {
                            // Checks if username is taken
                            for (int i = 0; i < newDatabase.getUsers().size(); i++) {
                                if (newDatabase.getUsers().get(i).getUsername().equals(username)){
                                    response = "taken";
                                    writer.write("taken");
                                    writer.println();
                                    writer.flush();
                                    break;
                                }
                            }

                            // if username is not taken, try to initialize the user
                            if (!response.equals("taken")) {
                                User newUser = new User(username, password, false);
                                newDatabase.addUser(newUser);
                                newDatabase.writeDatabase();
                                clientUser = newUser;
                                writer.write("success");
                                writer.println();
                                writer.flush();
                                break;
                            }
                        } catch (InvalidPasswordException ex) { // catch if password is invalid
                            writer.write("invalidPassword");
                            writer.println();
                            writer.flush();
                        }
                    }

                } while (true);

                while (cont) {
                    String dataLine = reader.readLine();
                    switch (dataLine) {
                        case "login":
                            String username = reader.readLine();
                            String password = reader.readLine();
                            String response = "failure";

                            for (User user : newDatabase.getUsers()) {
                                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                    response = "success";
                                    clientUser = user;
                                    break;
                                }
                            }
                            writer.println(response);
                            writer.flush();
                            break;
                        case "newUser":
                            String use = reader.readLine();
                            String pass = reader.readLine();
                            for (User user : newDatabase.getUsers()) {
                                if (user.getUsername().equals(use)) {
                                    writer.println("taken");
                                    writer.flush();

                                    return;
                                }
                            }

                            try {
                                User newUser = new User(use, pass, false);
                                newDatabase.addUser(newUser);
                                clientUser = newUser;
                                writer.println("success");
                                writer.flush();

                            } catch (InvalidPasswordException ex) {
                                writer.println("invalidPassword");
                                writer.flush();

                            }                            break;
                        case "addFriend":
                            String otherUsername = reader.readLine();
                            Boolean success = sendFriendRequest(clientUser, otherUsername);
                            writer.println("" + success);
                            writer.flush();

                            break;
                        case "removeFriend":
                            otherUsername = reader.readLine();
                            success = removeFriend(clientUser, otherUsername);
                            writer.println("" + success);
                            writer.flush();

                            break;
                        case "respondFriendRequest":
                            otherUsername = reader.readLine();
                            Boolean accepted = Boolean.parseBoolean(reader.readLine());
                            success = respondFriendRequest(clientUser, otherUsername, accepted);
                            writer.println("" + success);
                            writer.flush();

                            break;
                        case "blockUser":
                            otherUsername = reader.readLine();
                            success = blocked(clientUser, otherUsername);
                            writer.println("" + success);
                            writer.flush();

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
                                writer.flush();

                            } catch (Exception e) {
                                writer.println("false");
                                writer.flush();

                            }
                            break;
                        case "deleteGroupchat":
                            GroupChat groupchat = parseGroupchat(reader.readLine());
                            success = deleteGroupchat(groupchat);
                            writer.println("" + success);
                            writer.flush();

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
                            writer.flush();

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
                            writer.flush();

                            break;
                        case "isStrangersCanMessage":
                            writer.println(clientUser.isStrangersCanMessage());
                            writer.flush();

                            break;
                        case "setStrangersCanMessage":
                            try {
                                boolean newStrangersCanMessage = Boolean.parseBoolean(reader.readLine());
                                clientUser.setStrangersCanMessage(newStrangersCanMessage);
                                writer.println("true");
                                writer.flush();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                writer.println("false");
                                writer.flush();

                            }
                            break;
                        case "changePassword":
                            String newPassword = reader.readLine();
                            success = clientUser.setPassword(newPassword);
                            writer.println("" + success);
                            writer.flush();

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
