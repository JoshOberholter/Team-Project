import java.util.ArrayList;
/**
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtz, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
 * @version April 15th, 2024
 *
 * the portnumber is 215
 */
public interface DatabaseInt {
    public ArrayList<GroupChat> getGroupChats();
    public ArrayList<User> getUsers();
    public boolean addUser(User user);
    public boolean removeUser(User user);
    public boolean addGroupChat(GroupChat groupChat);
    public boolean removeGroupChat(GroupChat groupchat);
    public boolean readDatabase();
    public boolean writeDatabase();
}
