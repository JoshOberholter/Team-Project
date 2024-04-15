public interface MessageInt {
    User getUser();
    String getMessage();
    long getData();
    boolean isSeen();
    void setSeen(boolean seen);
    boolean equals(Object o);
    String getPhotoPath();
    void setPhotoPath(String photoPath);
    String toString();
}
