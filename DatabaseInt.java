import java.util.ArrayList;

public class Database {
    private ArrayList<User> participants;
    private ArrayList<GroupChat> groupChats;
    private static Object o = new Object();


    public Database() {
        this.participants = new ArrayList<>();
        this.groupChats = new ArrayList<>();
    }

    public Database(ArrayList<User> participants, ArrayList<GroupChat> groupChats) {
        this.participants = participants;
        this.groupChats = groupChats;
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
}
