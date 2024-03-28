import java.lang.System;

public class Message {
    private User user;
    private String message;
    private long date;
    private Boolean seen;

    public Message(User user, String message) {
        this.user = user;
        this.message = message;
        this.date = System.currentTimeMillis();
        this.seen = false;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public long getDate() {
        return date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setseen(boolean seen) {
        this.seen = seen;
    }

    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message msg = (Message)o;
            return (msg.user.equals(this.user) && msg.message.equals(this.message) && msg.date == this.date);
        }
        return false;
    }
}