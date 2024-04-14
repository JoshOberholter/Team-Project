import javax.swing.*;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 * A client for the social media site. 
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Sonya Kraft Purdue CS 
 * @version April 13th, 2024
 * 
 * the portnumber is 215
 */

public class Client {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Hello!", "Greeting", JOptionPane.INFORMATION_MESSAGE);
        try {
            String hostName = JOptionPane.showInputDialog(null, "Please enter the host name.", JOptionPane.PLAIN_MESSAGE);
            if (hostName == null) {
                JOptionPane.showMessageDialog(null, "The connection could not be made. Sorry!", "Connection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int portNumber = 215; // Standardized on 215, no need to ask the user.
            Socket socket = new Socket(hostName, portNumber);
            JOptionPane.showMessageDialog(null, "Connection successfully established!", "Connection Status", JOptionPane.INFORMATION_MESSAGE);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            // New code for login
            String username = JOptionPane.showInputDialog(null, "Enter your username:", "Login", JOptionPane.PLAIN_MESSAGE);
            String password = JOptionPane.showInputDialog(null, "Enter your password:", "Login", JOptionPane.PLAIN_MESSAGE);
            writer.println(username);
            writer.println(password);
            
            // Handle server response
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response, "Server Response", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
