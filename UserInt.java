import java.util.ArrayList;

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


}
