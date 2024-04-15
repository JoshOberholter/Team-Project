import java.io.*;
import java.net.*;

/**
 * A server for the social media site.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022 -- Project 5 -- Phase 2
 *
 * @author Suraj Pilla Purdue CS
 * @version April 13th, 2024
 *
 * the portnumber is 215
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(215);
            System.out.println("Server is waiting for clients to connect...");

            while (true) {
                Socket socket = serverSocket.accept(); // Accept a new client
                new Thread(new ClientHandler(socket)).start(); // Handle each client in a new thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read username and password sent by client
                String username = reader.readLine();
                String password = reader.readLine();

                // Placeholder authentication logic
                if (username.equals("admin") && password.equals("password")) {
                    writer.println("Login successful!");
                } else {
                    writer.println("Login failed!");
                }
            } catch (IOException e) {
                System.out.println("Error handling client " + clientSocket.getInetAddress().getHostAddress());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close(); // Ensure the socket is closed after handling
                } catch (IOException e) {
                    System.out.println("Could not close the socket properly.");
                }
            }
        }
    }
}
