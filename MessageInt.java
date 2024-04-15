/**
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtz, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
 * @version April 15th, 2024
 *
 * the portnumber is 215
 */
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
