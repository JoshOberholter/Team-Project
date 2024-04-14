import java.io.*;
import java.util.*;
import java.net.*;

/**
 * A server for the social media site. 
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Sonya Kraft Purdue CS 
 * @version April 13th, 2024
 * 
 * the portnumber is 215
 */

 public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(215);
            System.out.println("Server is waiting for client to connect...");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                
                String username = reader.readLine(); // Read username sent by client
                String password = reader.readLine(); // Read password sent by client
                
                // Placeholder authentication logic
                if (username.equals("admin") && password.equals("password")) {
                    writer.println("Login successful!");
                } else {
                    writer.println("Login failed!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
