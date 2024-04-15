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
public class PhotoUploader {
    public static void main(String[] args) {
        // Create a frame
        JFrame frame = new JFrame("Photo Upload");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a button for choosing a photo
        JButton button = new JButton("Upload Photo");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // Here you can create a Message object with the selected photo path
                    // For example: Message message = new Message(user, "Hello", selectedFile.getAbsolutePath());
                    System.out.println("Photo path: " + selectedFile.getAbsolutePath());
                }
            }
        });

        // Adding button to the frame
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }
}
