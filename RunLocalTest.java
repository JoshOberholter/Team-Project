import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS
 * @version June 13, 2022
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.printf("Excellent - all local tests ran successfully.\n");
        } else {
            System.out.printf("Tests failed: %d.\n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class GroupChatTest {

        private GroupChat groupChat;
        private ArrayList<User> participants;

        @Before
        public void setUp() {
            participants = new ArrayList<>();
            participants.add(new User("Josh Ober", "1234", false));
            participants.add(new User("New Name", "abcd", true));
            groupChat = new GroupChat(participants);
        }

        @Test
        public void testAddParticipant() throws UserNotFoundException {
            User userToAdd = new User("guy", "abc123", true);
            assertTrue(groupChat.addParticipant(userToAdd));
            assertTrue(groupChat.getParticipants().contains(userToAdd));
        }

        @Test
        public void testAddExistingParticipant() {
            User existingUser = participants.get(0);
            assertThrows(UserNotFoundException.class, () -> groupChat.addParticipant(existingUser));
        }

        @Test
        public void testRemoveParticipant() throws UserNotFoundException {
            User userToRemove = participants.get(0);
            assertTrue(groupChat.removeParticipant(userToRemove));
            assertFalse(groupChat.getParticipants().contains(userToRemove));
        }

        @Test
        public void testRemoveNonExistingParticipant() {
            User nonExistingUser = new User("guy1", "abc123", true);
            assertThrows(UserNotFoundException.class, () -> groupChat.removeParticipant(nonExistingUser));
        }

        @Test
        public void testAddMessage() {
            Message message = new Message(participants.get(1), "new message1");
            assertTrue(groupChat.addMessage(message));
            assertTrue(groupChat.getMessages().contains(message));
        }

        @Test
        public void testRemoveMessage() throws MessageNotFoundException {
            Message messageToRemove = new Message(participants.get(1), "new message2");
            groupChat.addMessage(messageToRemove);
            assertTrue(groupChat.removeMessage(messageToRemove));
            assertFalse(groupChat.getMessages().contains(messageToRemove));
        }

        @Test
        public void testRemoveNonExistingMessage() {
            Message nonExistingMessage = new Message(participants.get(1), "new message3");
            assertThrows(MessageNotFoundException.class, () -> groupChat.removeMessage(nonExistingMessage));
        }

        @Test
        public void testGetAllPhotoPaths() {
            Message messageWithPhoto = new Message(participants.get(1), "new message4");
            messageWithPhoto.setPhotoPath("path/to/photo.jpg");
            groupChat.addMessage(messageWithPhoto);

            List<String> photoPaths = groupChat.getAllPhotoPaths();
            assertEquals(1, photoPaths.size());
            assertEquals("path/to/photo.jpg", photoPaths.get(0));
        }
    }
}
