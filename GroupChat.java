import java.util.ArrayList;
import java.util.List;

/**
 * A class to store group chats, the participants, and their messages.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtz, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
 * @version April 15th, 2024
 *
 */


public class GroupChat {
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public GroupChat(ArrayList<User> participants) {
        this.participants = participants;
        this.messages = new ArrayList<>();
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public boolean addParticipant(User user) throws UserNotFoundException {
        try {
            if (!this.participants.contains(user)) {
                this.participants.add(user);
                return true;
            }
            throw new UserNotFoundException(String.format("%s is already in the group chat!", user.getUsername()));
        } catch (NullPointerException e) {
            this.participants.add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeParticipant(User user) throws UserNotFoundException {
        //return true if user is successfully removed, and false if user is not in the group
        try {
            if (this.participants.contains(user)) {
                this.participants.remove(user);
                return true;
            }
            throw new UserNotFoundException(String.format("%s is not in the group chat.", user.getUsername()));
        } catch (NullPointerException e) {
            throw new UserNotFoundException(String.format("%s is not in the group chat.", user.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public boolean addMessage(Message message) {
        try {
            if (!this.messages.contains(message)) {
                this.messages.add(message);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            this.messages.add(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeMessage(Message message) throws MessageNotFoundException {
        //assumes message is being removed by the user who wrote the message
        try {
            if (this.messages.contains(message)) {
                this.messages.remove(message);
                return true;
            }
            throw new MessageNotFoundException("Message is not in the group chat!");
        } catch (NullPointerException e) {
            throw new MessageNotFoundException("Message is not in the group chat!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getAllPhotoPaths() {
        List<String> photoPaths = new ArrayList<>();
        for (Message message : messages) {
            if (message.getPhotoPath() != null && !message.getPhotoPath().isEmpty()) {
                photoPaths.add(message.getPhotoPath());
            }
        }
        return photoPaths;
    }

    public String toString() {
        String format = "GroupChat<";

        for (User user : participants) {
            if (user.equals(participants.get(participants.size() - 1))) {
                format += String.format("%s", user.getUsername());
            } else {
                format += String.format("%s;", user.getUsername());
            }
        }
        format += ",";
        for (Message message : messages) {
            if (message.equals(messages.get(messages.size() - 1))) {
                format += String.format("%s", message.toString());
            } else {
                format += String.format("%s;", message.toString());
            }
        }
        format += ">";

        return format;
    }

    public GroupChat parseGroupchat(String line) {
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
}
