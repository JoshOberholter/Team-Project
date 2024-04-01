import java.lang.System;

public class Message {
    private User user;
    private String message;
    private Boolean seen;
    private String photoPath;

    public Message(User user, String message, String photoPath) {
        this.user = user;
        this.message = message;
        this.seen = false;
        this.photoPath = photoPath;
    }

    public Message(User user, String message) {
        this.user = user;
        this.message = message;
        this.seen = false;
    }



    public User getUser() {
        return user;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getMessage() {
        return message;
    }


    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message msg = (Message)o;
            return (msg.user.equals(this.user) && msg.message.equals(this.message));
        }
        return false;
    }
}
