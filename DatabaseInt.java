import java.util.ArrayList;

public class Database {
    private ArrayList<User> participants;
    private ArrayList<Message> messages;

    public Database() {
        this.participants = null;
        this.messages = null;
    }

    public Database(ArrayList<User> participants, ArrayList<Message> posts) {
        this.participants = participants;
        this.messages = posts;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public boolean addUser(User user) throws UserNotFoundException {
        //adds a user if no other user has the same username
        try {
            for (int i = 0; i < participants.size(); i++) {
                if (this.participants.get(i).getUsername().equals(user.getUsername())) {
                    throw new UserNotFoundException("Username already taken!");
                }
            }
            this.participants.add(user);
            return true;
        } catch (NullPointerException e) {
            this.participants.add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(User user) throws UserNotFoundException {
        try {
            if (this.participants.contains(user)) {
                this.participants.remove(user);
                return true;
            }
            throw new UserNotFoundException(String.format("%s is not a user.", user.getUsername()));
        } catch (NullPointerException e) {
            throw new UserNotFoundException(String.format("%s is not a user.", user.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    

    public ArrayList<Message> getMessages() {
        return messages;
    }


}
