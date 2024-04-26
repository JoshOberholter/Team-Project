
/**
 * A class to store messages, the person who sent them,
 * if they have a photo, if they've been seen, then the time sent
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtz, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
 * @version April 15th, 2024
 *
 */

public class Message {
    private User user;
    private String message;
    private Boolean seen;
    private String photoPath;
    private double time;

    public Message(User user, String message, String photoPath, double time) {
        this.user = user;
        this.message = message;
        this.seen = false;
        this.photoPath = photoPath;
        if (time == 0) {
            this.time = System.currentTimeMillis();
        } else {
            this.time = time;
        }
    }

    public Message(User user, String message, double time) {
        this.user = user;
        this.message = message;
        this.seen = false;
        this.photoPath = "";
        if (time == 0) {
            this.time = System.currentTimeMillis();
        } else {
            this.time = time;
        }
    }

    public Message(User user, String message, String photoPath, Boolean seen, double time) {
        this.user = user;
        this.message = message;
        this.seen = seen;
        this.photoPath = photoPath;
        if (time == 0) {
            this.time = System.currentTimeMillis();
        } else {
            this.time = time;
        }
    }

    public Message(User user, String message, Boolean seen, double time) {
        this.user = user;
        this.message = message;
        this.seen = seen;
        this.photoPath = "";
        if (time == 0) {
            this.time = System.currentTimeMillis();
        } else {
            this.time = time;
        }
    }
    public Message(User user, String message) {
        this.user = user;
        this.message = message;
    }
    public Message(User user, String message, String photoPath) {
        this.user = user;
        this.message = message;
        this.photoPath = "";
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

    public double getTime() {
        return time;
    }

    public String toString() {
        String format = "";

        format += String.format("#%s;%s;%b;%f", this.user.getUsername(), this.message, this.seen, this.time);

        if (this.photoPath.isEmpty()) {
            format += "#";
        } else {
            format += String.format(";%s#", this.photoPath);
        }

        return format;
    }
}
