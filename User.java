import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private int newMessages;
    private Boolean strangersCanMessage;
    private ArrayList<User> friends;
    private ArrayList<User> friendRequests;
    private ArrayList<User> blockedUsers;
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;
    private ArrayList<GroupChat> dmsAndGroupChats;
    private ArrayList<Comment> likedComments;

    public User(String username, String password, boolean strangersCanMessage) {
	    this.username = username;
	    this.password = password;
	    this.strangersCanMessage = strangersCanMessage;
        this.newMessages = 0;
	    this.friends = null;
        this.friendRequests = null;
        this.blockedUsers = null;
        this.posts = null;
        this.dmsAndGroupChats = null;
        this.likedComments = null;
        this.comments = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean setPassword(String password) {
        //check if password can be used, then sets password to that and returns true
        int numbers = 0;
        int letters = 0;
        int specialCharacters = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                numbers++;
            } else if (Character.isAlphabetic(password.charAt(i))) {
                letters++;
            } else {
                specialCharacters++;
            }
        }
        if (numbers > 2 && letters > 2 && specialCharacters > 2 && password.length() > 10) { //Currently arbitrary password rule set
            this.password = password;
            return true;
        }
        return false;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public boolean isFriend(User user) {
        if (this.friends == null) {
            return false;
        }
        return this.friends.contains(user);
    }

    public boolean addFriend(User user) {
        //returns true if successfully added them as a friend, false if user is blocked or is already friended.
        if (this.friends == null) {
            this.friends.add(user);
            return true;
        }
        if (this.friends.contains(user) || this.blockedUsers.contains(user)) {
            return false;
        }
        this.friends.add(user);
        return true;
    }

    public boolean removeFriend(User user) throws UserNotFoundException {
        //returns true if successfully removed friend
        try {
            if (this.friends.contains(user)) {
                this.friends.remove(user);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            throw new UserNotFoundException("This user was not on your friends list!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> getBlocked() {
        return blockedUsers;
    }

    public boolean isBlocked(User user) {
        try {
            return (this.blockedUsers.contains(user));
        } catch (NullPointerException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Block(User user) {
        //returns true if user is successfully added to block list
        try {
            if (!this.blockedUsers.contains(user) && !this.friends.contains(user)) {
                this.blockedUsers.add(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean unBlock(User user) throws UserNotFoundException {
        try {
            if (this.blockedUsers.contains(user)) {
                this.blockedUsers.remove(user);
                return true;
            }
            throw new UserNotFoundException("User was not in your blocked list!");
        } catch (NullPointerException e) {
            throw new UserNotFoundException("User was not in your blocked list!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean likeComment(Comment comment) {
        //returns true if successfully liked comment, false if comment was already liked
        try {
            if (!this.likedComments.contains(comment)) {
                comment.setLikes(comment.getLikes() + 1);
                this.likedComments.add(comment);
                return true;
            }
            return false;
        } catch (NullPointerException e) { //catches if likedComments is empty
            comment.setLikes(comment.getLikes() + 1);
            this.likedComments.add(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(String text) {
        Post post = new Post(this, text);
        this.posts.add(post);
    }

    public boolean deletePost(Post post) throws MessageNotFoundException {
        try {
            if (this.posts.contains(post)) {
                this.posts.remove(post);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            throw new MessageNotFoundException("No post matching that one was found!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void addComment(Post post, String text) {
        Comment comment = new Comment(this, text);
        this.comments.add(comment);
        post.addComment(comment);

    }

    public boolean deleteComment(Post post, Comment comment) throws MessageNotFoundException {
        try {
            if (this.comments.contains(comment) && this.equals(comment.getUser())) {
                this.comments.remove(comment);
                post.deleteComment(comment);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            throw new MessageNotFoundException("No comments matching that one was found!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getUnreadMessages() {
        return newMessages;
    }

    public void setUnreadMessages(int newMessages) {
        this.newMessages = newMessages;
    }

    public boolean isStrangersCanMessage() {
        return strangersCanMessage;
    }

    public void setStrangersCanMessage(boolean strangersCanMessage) {
        this.strangersCanMessage = strangersCanMessage;
    }

    public ArrayList<GroupChat> GetDmsAndGroupChats() {
        return dmsAndGroupChats;
    }

    public boolean addDmsAndGroupChats(ArrayList<User> participantsInGroupChat) {
        GroupChat groupchat = new GroupChat(participantsInGroupChat);
        try {
            if (!this.dmsAndGroupChats.contains(groupchat)) {
                this.dmsAndGroupChats.add(groupchat);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            this.dmsAndGroupChats.add(groupchat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeDmsAndGroupChats(GroupChat groupchat) {
        try {
            if (this.dmsAndGroupChats.contains(groupchat)) {
                this.dmsAndGroupChats.remove(groupchat);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> getFriendRequests() {
        return friendRequests;
    }

    public boolean acceptFriendRequest(User user) throws UserNotFoundException {
        // true if friend request is accepted successfully, false if user hasnt sent a request
        // assumes if user is in friend request, then they arent already friended
        try {
            if (this.friendRequests.contains(user)) {
                if (this.addFriend(user)) {
                    this.friendRequests.remove(user);
                    return true;
                }
                throw new UserNotFoundException("You cannot friend users you have blocked.");
            }
            throw new UserNotFoundException(String.format("%s has not sent you a friend request!", user.getUsername()));
        } catch (NullPointerException e) {
            throw new UserNotFoundException(String.format("%s has not sent you a friend request!", user.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean denyFriendRequest(User user) throws UserNotFoundException {
        // true if friend request is denied successfully, false if user hasnt sent a request
        // assumes if user is in friend request, then they arent already friended
        try {
            if (this.friendRequests.contains(user)) {
                this.friendRequests.remove(user);
                return true;
            }
            throw new UserNotFoundException(String.format("%s has not sent you a friend request!", user.getUsername()));
        } catch (NullPointerException e) {
            throw new UserNotFoundException(String.format("%s has not sent you a friend request!", user.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addMessageToGroupChat(String text, GroupChat groupChat) {
        Message newMessage = new Message(this, text);
        groupChat.addMessage(newMessage);
    }

    public void deleteMessageInGroupChat(Message message, GroupChat groupChat) throws MessageNotFoundException {
        if (this.equals(message.getUser())) { //if the one deleting it is the one who posted it
            try {
                groupChat.removeMessage(message);
            } catch (MessageNotFoundException e) {
                throw e;
            }
        }
    }

    public boolean equals(User user) {
        try {
            return (this.username.equals(user.getUsername()) && this.password.equals(user.getPassword()) && 
                    this.strangersCanMessage == user.strangersCanMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}