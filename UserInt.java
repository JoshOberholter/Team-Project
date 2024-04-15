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
public interface UserInt {
    String getUsername();
    String getPassword();
    boolean setPassword(String password);
    ArrayList<User> getFriends();
    boolean isFriend(User user);
    boolean addFriend(User user);
    boolean removeFriend(User user) throws UserNotFoundException;
    ArrayList<User> getBlocked();
    boolean isBlocked(User user);
    boolean block(User user);
    boolean unBlock(User user) throws UserNotFoundException;
    int getUnreadMessages();
    void setUnreadMessages(int newMessages);
    boolean isStrangersCanMessage();
    void setStrangersCanMessage(boolean strangersCanMessage);
    ArrayList<GroupChat> getDmsAndGroupChats();
    boolean addDmsAndGroupChats(ArrayList<User> participantsInGroupChat);
    boolean removeDmsAndGroupChats(GroupChat groupchat);
    ArrayList<User> getFriendRequests();
    boolean acceptFriendRequest(User user) throws UserNotFoundException;
    boolean denyFriendRequest(User user) throws UserNotFoundException;
    void addMessageToGroupChat(String text, GroupChat groupChat);
    void deleteMessageInGroupChat(Message message, GroupChat groupChat) throws MessageNotFoundException;
    boolean equals(User user);
    String getProfilePicturePath();
    void setProfilePicturePath(String profilePicturePath);
    boolean addFriendRequest(User user);
    boolean sendFriendRequest(User user) throws UserNotFoundException;
    String toString();
}
