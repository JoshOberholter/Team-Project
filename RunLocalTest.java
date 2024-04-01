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
        Result result = JUnitCore.runClasses(GroupChatTest.class, UnitTestCase.class, MessageTestCase.class);
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
    public static class MessageTestCase {
        private User user1;
        private User user2;
        private String testMessageContent = "Hello, World!";
        private String testPhotoPath = "/path/to/photo.jpg";
        private Message messageWithPhoto;
        private Message messageWithoutPhoto;

        @Before
        public void setUp() {
            user1 = new User("johnDoe", "P@ssw0rd123!", true);
            user2 = new User("janeDoe", "SecureP@ss4!", false);
            messageWithPhoto = new Message(user1, testMessageContent, testPhotoPath);
            messageWithoutPhoto = new Message(user1, testMessageContent);
        }

        @Test
        public void testMessageConstructorWithPhoto() {
            assertNotNull(messageWithPhoto);
            assertEquals(user1, messageWithPhoto.getUser());
            assertEquals(testMessageContent, messageWithPhoto.getMessage());
            assertEquals(testPhotoPath, messageWithPhoto.getPhotoPath());
            assertFalse(messageWithPhoto.isSeen());
        }

        @Test
        public void testMessageConstructorWithoutPhoto() {
            assertNotNull(messageWithoutPhoto);
            assertEquals(user1, messageWithoutPhoto.getUser());
            assertEquals(testMessageContent, messageWithoutPhoto.getMessage());
            assertNull(messageWithoutPhoto.getPhotoPath());
            assertFalse(messageWithoutPhoto.isSeen());
        }

        @Test
        public void testSetPhotoPath() {
            String newPhotoPath = "/new/path/to/photo.jpg";
            messageWithoutPhoto.setPhotoPath(newPhotoPath);
            assertEquals(newPhotoPath, messageWithoutPhoto.getPhotoPath());
        }

        @Test
        public void testSetSeen() {
            messageWithoutPhoto.setSeen(true);
            assertTrue(messageWithoutPhoto.isSeen());
        }

        @Test
        public void testEqualsWithSameAttributes() {
            Message anotherMessage = new Message(user1, testMessageContent);
            assertTrue("Messages will be equal", messageWithoutPhoto.equals(anotherMessage));
        }

        @Test
        public void testEqualsWithDifferentUser() {
            Message anotherMessage = new Message(user2, testMessageContent);
            assertFalse("Messages from different users will not be equal", messageWithoutPhoto.equals(anotherMessage));
        }

        @Test
        public void testEqualsWithDifferentContent() {
            Message anotherMessage = new Message(user1, "Different content");
            assertFalse("Messages with different content will not be equal", messageWithoutPhoto.equals(anotherMessage));
        }

        @Test
        public void testEqualsWithPhotoPath() {
            // Note: Based on the provided equals method, photoPath does not affect equality.
            Message anotherMessageWithPhoto = new Message(user1, testMessageContent, "/another/path/to/photo.jpg");
            assertTrue("Messages with different photoPath will be equal", messageWithPhoto.equals(anotherMessageWithPhoto));
        }
    }
    public static class UnitTestCase {
        private static User user1;
        private static User user2;

        @Before
        public void setUp() {
            user1 = new User("johnDoe", "P@ssw0rd123!", true);
            user2 = new User("janeDoe", "P@ssw0rd123!", false);
            // Additional setup as needed
        }
        @Test
        public void testUserCreation() {
            User newUser = new User("newUser", "N3wP@ss!", true);
            assertEquals("newUser", newUser.getUsername());
            // Continue with other assertions for initial state
        }
        @Test
        public void testSetPasswordValid() {
            assertTrue(user1.setPassword("N3wP@ssw0rd!"));
        }

        @Test
        public void testSetPasswordInvalid() {
            assertFalse(user1.setPassword("short"));
        }
        @Test
        public void testAddFriend() {
            user1.addFriend(user2);
            assertTrue(user1.isFriend(user2));
        }

        @Test
        public void testRemoveFriend() throws UserNotFoundException {
            user1.addFriend(user2);
            user1.removeFriend(user2);
            assertFalse(user1.isFriend(user2));
        }

        @Test(expected = UserNotFoundException.class)
        public void testRemoveFriendNotFound() throws UserNotFoundException {
            user1.removeFriend(user2);
        }
        @Test
        public void testBlockUser() {
            user1.block(user2);
            assertTrue(user1.isBlocked(user2));
        }

        @Test
        public void testUnblockUser() throws UserNotFoundException {
            user1.block(user2);
            user1.unBlock(user2);
            assertFalse(user1.isBlocked(user2));
        }
        @Test
        public void testAddMessageToGroupChat() {
            GroupChat groupChat = new GroupChat(new ArrayList<>(Arrays.asList(user1, user2)));
            user1.addMessageToGroupChat("Hello, World!", groupChat);
            // Verify the message was added correctly
        }

        @Test(expected = MessageNotFoundException.class)
        public void testDeleteMessageInGroupChat() throws MessageNotFoundException {
            // Setup group chat and add a message
            // Attempt to delete the message and verify it's removed
            // Attempting to remove it again should throw MessageNotFoundException
        }

        @Test
        public void testProfilePicturePath() {
            String path = "/images/user1.jpg";
            user1.setProfilePicturePath(path);
            assertEquals(path, user1.getProfilePicturePath());
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
    public static class DatabaseTestCase {
        private User user1;
        private User user2;
        private Database database;
        private ArrayList<User> participants;
        private GroupChat groupChat1;
        private ArrayList<User> participants;
        

        @Before
        public void setUp() {
            user1 = new User("johnDoe", "P@ssw0rd123!", true);
            user2 = new User("janeDoe", "SecureP@ss4!", false);
            database = new Database();
            database.addUser(user1);
            database.addUser(user2);
            participants = new ArrayList<>();
            participants.add(user1);
            participants.add(user2);
            groupChat1 = new GroupChat(participants);
        }

        @Test
        public void testAddUser() {
            User user3 = new User("Max Mustermann", "xd565vgh@#$h", false);
            assertTrue(database.addUser(user3));
            assertTrue(database.getUsers().contains(user3));
        }

        @Test
        public void testAddExisitingUser() {
            User user3 = database.getUsers().get(0);
            assertFalse(groupChat.addParticipant(user3));
        }
        
        @Test
        public void testRemoveUser() {
            User user3 = database.getUsers().get(0);
            assertTrue(database.removeUser(user3));
            assertFalse(database.getUsers().contains(user3));
        }
        
        @Test
        public void testRemoveNewUser() {
            User newUser = new User("ThisIsAFakeUsername", "p928yugh!#", true);
            assertThrows(UserNotFoundException.class, () -> database.removeUser(newUser));
        }

        @Test
        public void testAddGroupchat() {
            ArrayList<User> participants2 = new ArrayList<>();
            User user3 = new User("Max Mustermann", "xd565vgh@#$h", false);
            participants2.add(user1);
            participants2.add(user3);
            GroupChat groupChatNew = new GroupChat(participants2);
            assertTrue(database.addGroupChat(groupChatNew));
            assertTrue(database.getGroupChats().contains(groupChatNew));
        }

        @Test
        public void testAddGroupchat() {
            ArrayList<User> participants2 = new ArrayList<>();
            User user3 = new User("Max Mustermann", "xd565vgh@#$h", false);
            participants2.add(user1);
            participants2.add(user3);
            GroupChat groupChatNew = new GroupChat(participants2);
            assertTrue(database.addGroupChat(groupChatNew));
            assertTrue(database.getGroupChats().contains(groupChatNew));
        }
        
        @Test
        public void testAddExistingGroupchat() {
            GroupChat groupChatExisting = database.getGroupChats().get(0);
            assertFalse(database.addGroupChat(groupChatExisting));
        }

        @Test
        public void testRemoveGroupChat() {
            GroupChat groupChatExisting = database.getGroupChats().get(0);
            assertTrue(database.removeGroupChat(groupChatNew));
            assertFalse(database.getGroupChats().contains(groupChatNew));
        }

        @Test
        public void testRemoveNewGroupchat() {
            ArrayList<User> participantsNew = new ArrayList<>();
            User newUser = new User("ThisIsAFakeUsername", "p928yugh!#", true);
            User user3 = new User("Max Mustermann", "xd565vgh@#$h", false);
            participantsNew.add(user3);
            participantsNew.add(newUser);
            GroupChat groupChatNew = new GroupChat(participantsNew);
            assertFalse(database.removeGroupChat(groupChatNew));
        }
    }     
}
