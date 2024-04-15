import javax.swing.*;
import java.awt.event.*;
import java.io.File;
/**
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Joshia Oberholtz, Micheal Chen, Sonya Kraft, Suraj Pilla,  Purdue CS
 * @version April 15th, 2024
 *
 * the portnumber is 215
 */
public class UserProfileUI extends JFrame {
    private User user; 

    public UserProfileUI(User user) {
        this.user = user;
        initializeUI();
    }

    private void initializeUI() {
        this.setTitle("User Profile");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton uploadButton = new JButton("Upload Profile Picture");
        uploadButton.addActionListener(e -> uploadProfilePicture());

        this.getContentPane().add(uploadButton);
        this.setVisible(true);
    }

    private void uploadProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            user.setProfilePicturePath(selectedFile.getAbsolutePath());
            System.out.println("Profile picture updated to: " + selectedFile.getAbsolutePath());
        }
    }
}
