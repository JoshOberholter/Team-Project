import java.lang.reflect.Array;
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
public interface GroupChatInt {

    ArrayList<User> getParticipants();
    boolean addParticipant(User user) throws UserNotFoundException;
    boolean removeParticipant(User user) throws UserNotFoundException;
    ArrayList<Message> getMessages();
    boolean addMessage(Message message);
    boolean removeMessage(Message message) throws MessageNotFoundException;
    List<String> getAllPhotoPaths();
    String toString();
}
