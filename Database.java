import java.util.ArrayList;

public class Database {
    private ArrayList<User> participants;
    private ArrayList<Message> posts;

    public Database() {
        this.participants = null;
        this.posts = null;
    }

    public Database(ArrayList<User> participants, ArrayList<Message> posts) {
        this.participants = participants;
        this.posts = posts;
    }

    public ArrayList<User> getUsers() {
        return participants;
    }

    public boolean addUser(User user) throws UserNotFoundException {
        //adds a user if no other user has the same username
        try {
            for (int i = 0; i < participants.size(); i++) {
                if (this.participants.get(i).getUsername().equals(user.getUsername())) {
                    throw new UserNotFoundException("Username already taken!");
                }
            }
            this.participants.add(user);
            return true;
        } catch (NullPointerException e) {
            this.participants.add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(User user) throws UserNotFoundException {
        try {
            if (this.participants.contains(user)) {
                this.participants.remove(user);
                return true;
            }
            throw new UserNotFoundException(String.format("%s is not a user.", user.getUsername()));
        } catch (NullPointerException e) {
            throw new UserNotFoundException(String.format("%s is not a user.", user.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Message> getPosts() {
        return posts;
    }

    public boolean addPost(Post post) {
        try {
            if (!this.posts.contains(post)) {
                this.posts.add(post);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            this.posts.add(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePost(Post post) throws MessageNotFoundException {
        try {
            if (this.posts.contains(post)) {
                this.posts.remove(post);
                return true;
            }
            throw new MessageNotFoundException("Post not found!");
        } catch (NullPointerException e) {
            throw new MessageNotFoundException("Post not found!");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}