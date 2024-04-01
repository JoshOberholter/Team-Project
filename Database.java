import java.util.ArrayList;

public class Database {
    private ArrayList<User> participants;
    private ArrayList<GroupChat> groupChats;


    public Database() {
        this.participants = null;
        this.groupChats = null;
    }

    public Database(ArrayList<User> participants, ArrayList<GroupChat> groupChats) {
        this.participants = participants;
        this.groupChats = groupChats;
    }

    public ArrayList<User> getUsers() {
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
    public boolean addGroupChat(GroupChat groupChat) {
        try {
            this.groupChats.add(groupChat);
             return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeGroupChat(GroupChat groupchat) {
        try {
            if (this.groupChats.contains(groupchat)) {
                this.groupChats.remove(groupchat);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
