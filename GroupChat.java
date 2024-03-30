import java.util.ArrayList;
import java.util.List;

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
}
