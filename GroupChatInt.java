import java.lang.reflect.Array;
import java.util.ArrayList;

public interface GroupChatInt {

    ArrayList<User> getParticipants();
    boolean addParticipant(User user) throws UserNotFoundException;
    boolean removeParticipant(User user) throws UserNotFoundException;
    ArrayList<Message> getMessages();
    boolean addMessage(Message message);
    boolean removeMessage(Message message) throws MessageNotFoundException;

}
