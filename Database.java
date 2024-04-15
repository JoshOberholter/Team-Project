import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class Database {
    private ArrayList<User> participants;
    private ArrayList<GroupChat> groupChats;
    private static final Object o = new Object();


    public Database() {
        this.participants = new ArrayList<>();
        this.groupChats = new ArrayList<>();
    }

    public Database(ArrayList<User> participants, ArrayList<GroupChat> groupChats) {
        this.participants = participants;
        this.groupChats = groupChats;
    }

    public ArrayList<GroupChat> getGroupChats() {
        return groupChats;
    }

    public ArrayList<User> getUsers() {
        return participants;
    }

    public boolean addUser(User user) {
        //adds a user if no other user has the same username
        try {
            for (User myUser : participants) {
                if (user.getUsername().equals(myUser.getUsername())) {
                    return false;
                }
            }
            synchronized (o) {
                this.participants.add(user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean removeUser(User user) throws UserNotFoundException {
        try {
            if (this.participants.contains(user)) {
                synchronized (o) {
                    this.participants.remove(user);
                }
                return true;
            }
            throw new UserNotFoundException(String.format("%s is not a user.", user.getUsername()));
        } catch (NullPointerException e) {
            throw new UserNotFoundException(String.format("%s is not a user.", user.getUsername()));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean addGroupChat(GroupChat groupChat) {
        try {
            synchronized (o) {
                this.groupChats.add(groupChat);
            }
             return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeGroupChat(GroupChat groupchat) {
        try {
            if (this.groupChats.contains(groupchat)) {
                synchronized (o) {
                    this.groupChats.remove(groupchat);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean readDatabase() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("database.txt"));
            String line = bfr.readLine();

            while (line != null) {
                if (line.contains("User<")) {
                    try {
                        String[] userThings = line.substring(5, line.length() - 1) .split(",");
                        String username = userThings[0];
                        String password = userThings[1];
                        boolean strangersCanMessage = Boolean.parseBoolean(userThings[3]);
                        participants.add(new User(username, password, strangersCanMessage));
                    } catch (Exception e) {
                        line = bfr.readLine();
                        continue;
                    }
                }
                line = bfr.readLine();
            }

            bfr.close();

            BufferedReader newBFR = new BufferedReader(new FileReader("database.txt"));
            line = newBFR.readLine();

            while (line != null) {
                if (line.contains("User<")) {
                    String[] userThings = line.substring(5, line.length() - 1) .split(",");
                    String un = userThings[0];
                    ArrayList<User> friends = new ArrayList<>();

                    if (!userThings[4].equals("N/A")) {
                        String[] friendsUsernames = userThings[4].split(";");
                        for (String username : friendsUsernames) {
                            for (User user : participants) {
                                if (user.getUsername().equals(username)) {
                                    friends.add(user);
                                }
                            }
                        }
                    }
                    ArrayList<User> friendRequests = new ArrayList<>();

                    if (!userThings[5].equals("N/A")) {
                        String[] friendRequestsUsernames = userThings[5].split(";");
                        for (String username : friendRequestsUsernames) {
                            for (User user : participants) {
                                if (user.getUsername().equals(username)) {
                                    friendRequests.add(user);
                                }
                            }
                        }
                    }
                    ArrayList<User> blocked = new ArrayList<>();

                    if (!userThings[6].equals("N/A")) {
                        String[] blockedUsernames = userThings[6].split(";");
                        for (String username : blockedUsernames) {
                            for (User user : participants) {
                                if (user.getUsername().equals(username)) {
                                    blocked.add(user);
                                }
                            }
                        }
                    }





                    int newMessages = Integer.parseInt(userThings[2]);
                    String profilePicturePath = userThings[userThings.length - 1];

                    for (User user : participants) {
                        if (user.getUsername().equals(un)) {
                            user.setUnreadMessages(newMessages);
                            user.setFriends(friends);
                            user.setFriendRequests(friendRequests);
                            user.setBlockedUsers(blocked);
                        }
                    }

                }


                if (line.contains("GroupChat<")) {
                    String[] groupChatThings = line.substring(10, line.length() - 1) .split(",");
                    String[] usernames = groupChatThings[0].split(";");
                    ArrayList<User> members = new ArrayList<>();
                    for (String username : usernames) {
                        for (User user : participants) {
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
                            if (messageThings.length == 4) {
                                String photoPath  = messageThings[3];
                                Message thisMessage = new Message(sender, messageText, photoPath);
                                thisMessage.setSeen(seen);
                                messages.add(thisMessage);

                            } else {
                                Message thisMessage = new Message(sender, messageText);
                                thisMessage.setSeen(seen);
                                messages.add(thisMessage);

                            }
                        }
                    }

                    GroupChat thisGroupChat = new GroupChat(members);
                    thisGroupChat.setMessages(messages);

                    groupChats.add(thisGroupChat);


                }

                line = bfr.readLine();
            }

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean writeDatabase() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("database.txt"));

            for (User user : participants) {
                pw.println(user.toString());
            }

            for (GroupChat groupChat : groupChats) {
                pw.println(groupChat.toString());
            }
            pw.flush();
            pw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
