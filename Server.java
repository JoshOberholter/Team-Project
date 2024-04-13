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
            Server newServer = new Server();
            ServerSocket serverSocket = new ServerSocket(215);
            System.out.println("Waiting for the client to connect...");
            boolean cont = true;
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}