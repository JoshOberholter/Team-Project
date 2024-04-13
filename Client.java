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
            String hostName = JOptionPane.showInputDialog(null, "Please enter the host name.", 
                JOptionPane.PLAIN_MESSAGE);
            if (hostName == null) {
                JOptionPane.showMessageDialog(null, "The connection could not be made. Sorry!", 
                        "Connection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int portNumber = Integer.parseInt(JOptionPane.showInputDialog(null, 
                    "Please enter the port number.", JOptionPane.PLAIN_MESSAGE));
            Socket socket = new Socket(hostName, portNumber);
            JOptionPane.showMessageDialog(null, "Connection successfully established!", 
                    "Search Engine", JOptionPane.INFORMATION_MESSAGE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            boolean cont = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}