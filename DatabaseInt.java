import java.util.ArrayList;

public interface DatabaseInt {
    public ArrayList<GroupChat> getGroupChats();
    public ArrayList<User> getUsers();
    public boolean addUser(User user);
    public boolean removeUser(User user);
    public boolean addGroupChat(GroupChat groupChat);
    public boolean removeGroupChat(GroupChat groupchat);
}
