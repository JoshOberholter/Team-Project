import javax.swing.*;
import javax.swing.border.Border;
import javax.imageio.*;
import java.io.*;

import java.util.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

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

public class Client extends JComponent implements Runnable {
    public final Color darkGrey = new Color(35, 38, 40);
    public final Color grey = new Color(49, 54, 56);
    public final Color lightGrey = new Color(101, 109, 112);
    public final Color starColor = new Color(255, 212, 20);
    public final Color accentColor = new Color(66, 226, 184);
    public final Color warningColor = new Color(218, 41, 79);
    public final Font buttonFont = new Font("Monospaced", Font.PLAIN, 10);

    JFrame loginFrame;
    Container logincontent;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JFrame mainFrame;
    JPanel friendRequestDisp;
    JPanel gcDisp;
    JPanel friendsDisp;
    JPanel blockedDisp;
    JPanel addFriendDisp;
    JPanel addGroupchatDisp;
    JPanel deleteGroupchatDisp;
    JTextField username; //√
    JTextField password; //√
    JButton login; //√
    JButton newAccount; //√
    JButton friendRequest; //√
    JButton friends; //√
    JButton blocked; //√
    JButton gc1;
    JButton gc2;
    JButton gc3;
    JButton gc4;
    JButton gc5;
    JButton gc6;
    JButton gc7;
    JButton gc8;
    JButton gc9;
    JButton gc10;
    JButton addGC; //√
    JButton deleteGC; //√
    JButton addImage;
    JTextField messageText;
    JButton send;
    JButton deleteMessage;
    JButton fR1Accept;
    JButton fR1Deny;
    JButton fR2Accept;
    JButton fR2Deny;
    JButton fR3Accept;
    JButton fR3Deny;
    JButton fR4Accept;
    JButton fR4Deny;
    JButton fR5Accept;
    JButton fR5Deny;
    JButton addFriend; //√
    JButton f1Accept;
    JButton f1Deny;
    JButton f2Accept;
    JButton f2Deny;
    JButton f3Accept;
    JButton f3Deny;
    JButton f4Accept;
    JButton f4Deny;
    JButton f5Accept;
    JButton f5Deny;
    JButton b1Unblock;
    JButton b2Unblock;
    JButton b3Unblock;
    JButton b4Unblock;
    JButton b5Unblock;
    JTextField enterFriendUserRequest;
    JButton enterRequest;
    JTextField enterUsersRequest;
    JButton enterNames;
    JTextField enterUsersRequestD;
    JButton enterNamesD;
    JTextField profileNewPassword;
    JButton profileChangePassword;
    JButton changeStrangersCanMessage;

    PrintWriter writer;
    BufferedReader reader;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                try {
                    writer.write("login");
                    writer.println();
                    writer.flush();

                    writer.write(username.getText());
                    writer.println();
                    writer.flush();

                    writer.write(password.getText());
                    writer.println();
                    writer.flush();

                    String response = reader.readLine();
                    if (response.equals("success")) {
                        loginFrame.setVisible(false);
                        mainFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Username or Password",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    username.setText("");
                    password.setText("");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == newAccount) {
                try {
                    writer.write("newUser");
                    writer.println();
                    writer.flush();

                    writer.write(username.getText());
                    writer.println();
                    writer.flush();

                    writer.write(password.getText());
                    writer.println();
                    writer.flush();

                    String response = reader.readLine();

                    if (response.equals("success")) {
                        loginFrame.setVisible(false);
                        mainFrame.setVisible(true);
                    } else if (response.equals("invalidPassword")){
                        JOptionPane.showMessageDialog(null, "Invalid Password",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (response.equals("taken")) {
                        JOptionPane.showMessageDialog(null, "Username is Taken",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    username.setText("");
                    password.setText("");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == friendRequest) {
                try {
                    friendRequestDisp.setVisible(true);
                    gcDisp.setVisible(false);
                    friendsDisp.setVisible(false);
                    blockedDisp.setVisible(false);
                    addFriendDisp.setVisible(false);
                    addGroupchatDisp.setVisible(false);
                    deleteGroupchatDisp.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == friends) {
                try {
                    friendRequestDisp.setVisible(false);
                    gcDisp.setVisible(false);
                    friendsDisp.setVisible(true);
                    blockedDisp.setVisible(false);
                    addFriendDisp.setVisible(false);
                    addGroupchatDisp.setVisible(false);
                    deleteGroupchatDisp.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == blocked) {
                try {
                    friendRequestDisp.setVisible(false);
                    gcDisp.setVisible(false);
                    friendsDisp.setVisible(false);
                    blockedDisp.setVisible(true);
                    addFriendDisp.setVisible(false);
                    addGroupchatDisp.setVisible(false);
                    deleteGroupchatDisp.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == addFriend) {
                try {
                    friendRequestDisp.setVisible(false);
                    gcDisp.setVisible(false);
                    friendsDisp.setVisible(false);
                    blockedDisp.setVisible(false);
                    addFriendDisp.setVisible(true);
                    addGroupchatDisp.setVisible(false);
                    deleteGroupchatDisp.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == addGC) {
                try {
                    friendRequestDisp.setVisible(false);
                    gcDisp.setVisible(false);
                    friendsDisp.setVisible(false);
                    blockedDisp.setVisible(false);
                    addFriendDisp.setVisible(false);
                    addGroupchatDisp.setVisible(true);
                    deleteGroupchatDisp.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == deleteGC) {
                try {
                    friendRequestDisp.setVisible(false);
                    gcDisp.setVisible(false);
                    friendsDisp.setVisible(false);
                    blockedDisp.setVisible(false);
                    addFriendDisp.setVisible(false);
                    addGroupchatDisp.setVisible(false);
                    deleteGroupchatDisp.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client());
    }

    public void run() {
        try {
            String hostName = "localhost";
            int portNumber = 215;
            Socket socket = new Socket(hostName, portNumber);

            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream());
            loginFrame = new JFrame("StarRun Login"); {
                logincontent = loginFrame.getContentPane();
                logincontent.setLayout(null);
                logincontent.setBackground(darkGrey);
                usernameLabel = new JLabel(); {
                    usernameLabel.setText("Username: ");
                    usernameLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    usernameLabel.setForeground(accentColor);
                    usernameLabel.setBackground(darkGrey);
                    usernameLabel.setOpaque(true);
                    usernameLabel.setBounds(50, 20, 200, 25);
                }
                username = new JTextField(""); {
                    username.setBackground(grey);
                    username.setForeground(accentColor);
                    username.setOpaque(true);
                    username.setFont(buttonFont);
                    username.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    username.setBounds(50, 45, 200, 25);
                }
                passwordLabel = new JLabel(); {
                    passwordLabel.setText("Password: ");
                    passwordLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    passwordLabel.setForeground(accentColor);
                    passwordLabel.setBackground(darkGrey);
                    passwordLabel.setOpaque(true);
                    passwordLabel.setBounds(50, 85, 200, 25);
                }
                password = new JTextField(""); {
                    password.setBackground(grey);
                    password.setForeground(accentColor);
                    password.setOpaque(true);
                    password.setFont(buttonFont);
                    password.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    password.setBounds(50, 110, 200, 25);
                }
                login = new JButton("Login"); {
                    login.setText("Login");
                    login.setBackground(grey);
                    login.setForeground(accentColor);
                    login.setOpaque(true);
                    login.setFont(buttonFont);
                    login.setBounds(50, 160, 200, 25);
                    login.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    login.addActionListener(actionListener);
                }
                newAccount = new JButton("Make New Account"); {
                    newAccount.setBackground(grey);
                    newAccount.setForeground(accentColor);
                    newAccount.setOpaque(true);
                    newAccount.setFont(buttonFont);
                    newAccount.setBounds(50, 195, 200, 25);
                    newAccount.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    newAccount.addActionListener(actionListener);
                }
                logincontent.add(usernameLabel);
                logincontent.add(passwordLabel);
                logincontent.add(username);
                logincontent.add(password);
                logincontent.add(login);
                logincontent.add(newAccount);
                loginFrame.setSize(300, 300);
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginFrame.setVisible(true);
            }

            mainFrame = new JFrame("");
            Container content = mainFrame.getContentPane();
            content.setLayout(new BorderLayout());
            content.setBackground(grey);
            JPanel header = new JPanel(); {
                header.setLayout(new OverlayLayout(header));
                header.setPreferredSize(new Dimension(1000, 50));
                header.setBorder(BorderFactory.createLineBorder(grey, 2));
                header.setOpaque(true);
                header.setBackground(darkGrey);
                ImageIcon icon = new ImageIcon("StarRun.png");
                JLabel headerBackgroundImg = new JLabel(icon); {
                    headerBackgroundImg.setOpaque(true);
                    headerBackgroundImg.setAlignmentX(0.5f);
                }
                JPanel headerButtons = new JPanel(); {
                    headerButtons.setLayout(null);
                    headerButtons.setOpaque(false);
                }
                JLabel gcLabel = new JLabel(); {
                    gcLabel.setText(" Groupchats");
                    gcLabel.setBackground(grey);
                    gcLabel.setOpaque(true);
                    gcLabel.setForeground(starColor);
                    gcLabel.setFont(buttonFont);
                    gcLabel.setBorder(BorderFactory.createLineBorder(starColor, 2));
                    gcLabel.setBounds(7, 7, 76, 36);
                }
                JButton friendRequest = new JButton(" Friend\nRequests "); {
                    friendRequest.setBackground(grey);
                    friendRequest.setForeground(starColor);
                    friendRequest.setOpaque(true);
                    friendRequest.setFont(buttonFont);
                    friendRequest.setBorder(BorderFactory.createLineBorder(starColor, 2));
                    friendRequest.setBounds(1000 - 110 - 21 - 130, 7, 110, 36);
                }
                JButton friends = new JButton(" Friends "); {
                    friends.setBackground(grey);
                    friends.setForeground(starColor);
                    friends.setOpaque(true);
                    friends.setFont(buttonFont);
                    friends.setBorder(BorderFactory.createLineBorder(starColor, 2));
                    friends.setBounds(1000 - 130 - 14, 7, 65, 36);
                }
                JButton blocked = new JButton(" Blocked "); {
                    blocked.setBackground(grey);
                    blocked.setForeground(starColor);
                    blocked.setOpaque(true);
                    blocked.setFont(buttonFont);
                    blocked.setBorder(BorderFactory.createLineBorder(starColor, 2));
                    blocked.setBounds(1000 - 65 - 7, 7, 65, 36);
                }
                headerButtons.add(gcLabel);
                headerButtons.add(friendRequest);
                headerButtons.add(friends);
                headerButtons.add(blocked);
                header.add(headerButtons);
                header.add(headerBackgroundImg);
                content.add(header, BorderLayout.NORTH);
            }

            JPanel gcList = new JPanel(); {
                gcList.setBackground(darkGrey);
                gcList.setLayout(new BorderLayout());
                gcList.setPreferredSize(new Dimension(100, 1000));
                JPanel gcListList = new JPanel(); {
                    gcListList.setBackground(darkGrey);
                    gcListList.setLayout(null);

                    JButton gc1 = new JButton(" Filler "); {
                        gc1.setBackground(grey);
                        gc1.setForeground(starColor);
                        gc1.setOpaque(true);
                        gc1.setFont(buttonFont);
                        gc1.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc1.setBounds(5, 5, 90, 25);
                        gc1.setVisible(true);
                    }
                    JButton gc2 = new JButton(" Filler "); {
                        gc2.setBackground(grey);
                        gc2.setForeground(starColor);
                        gc2.setOpaque(true);
                        gc2.setFont(buttonFont);
                        gc2.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc2.setBounds(5, 35, 90, 25);
                        gc2.setVisible(true);
                    }
                    JButton gc3 = new JButton(" Filler "); {
                        gc3.setBackground(grey);
                        gc3.setForeground(starColor);
                        gc3.setOpaque(true);
                        gc3.setFont(buttonFont);
                        gc3.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc3.setBounds(5, 65, 90, 25);
                        gc3.setVisible(false);
                    }
                    JButton gc4 = new JButton(" Filler "); {
                        gc4.setBackground(grey);
                        gc4.setForeground(starColor);
                        gc4.setOpaque(true);
                        gc4.setFont(buttonFont);
                        gc4.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc4.setBounds(5, 95, 90, 25);
                        gc4.setVisible(false);
                    }
                    JButton gc5 = new JButton(" Filler "); {
                        gc5.setBackground(grey);
                        gc5.setForeground(starColor);
                        gc5.setOpaque(true);
                        gc5.setFont(buttonFont);
                        gc5.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc5.setBounds(5, 125, 90, 25);
                        gc5.setVisible(false);
                    }
                    JButton gc6 = new JButton(" Filler "); {
                        gc6.setBackground(grey);
                        gc6.setForeground(starColor);
                        gc6.setOpaque(true);
                        gc6.setFont(buttonFont);
                        gc6.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc6.setBounds(5, 155, 90, 25);
                        gc6.setVisible(false);
                    }
                    JButton gc7 = new JButton(" Filler "); {
                        gc7.setBackground(grey);
                        gc7.setForeground(starColor);
                        gc7.setOpaque(true);
                        gc7.setFont(buttonFont);
                        gc7.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc7.setBounds(5, 185, 90, 25);
                        gc7.setVisible(false);
                    }
                    JButton gc8 = new JButton(" Filler "); {
                        gc8.setBackground(grey);
                        gc8.setForeground(starColor);
                        gc8.setOpaque(true);
                        gc8.setFont(buttonFont);
                        gc8.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc8.setBounds(5, 215, 90, 25);
                        gc8.setVisible(false);
                    }
                    JButton gc9 = new JButton(" Filler "); {
                        gc9.setBackground(grey);
                        gc9.setForeground(starColor);
                        gc9.setOpaque(true);
                        gc9.setFont(buttonFont);
                        gc9.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc9.setBounds(5, 245, 90, 25);
                        gc9.setVisible(false);
                    }
                    JButton gc10 = new JButton(" Filler "); {
                        gc10.setBackground(grey);
                        gc10.setForeground(starColor);
                        gc10.setOpaque(true);
                        gc10.setFont(buttonFont);
                        gc10.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        gc10.setBounds(5, 275, 90, 25);
                        gc10.setVisible(false);
                    }
                    gcListList.add(gc1);
                    gcListList.add(gc2);
                    gcListList.add(gc3);
                    gcListList.add(gc4);
                    gcListList.add(gc5);
                    gcListList.add(gc6);
                    gcListList.add(gc7);
                    gcListList.add(gc8);
                    gcListList.add(gc9);
                    gcListList.add(gc10);
                }
                JPanel gcButtons = new JPanel(); {
                    gcButtons.setBackground(darkGrey);
                    gcButtons.setLayout(new BorderLayout());
                    gcButtons.setPreferredSize(new Dimension(100, 25));
                    JButton addGC = new JButton(" + "); {
                        addGC.setBackground(grey);
                        addGC.setForeground(starColor);
                        addGC.setOpaque(true);
                        addGC.setFont(buttonFont);
                        addGC.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        addGC.setAlignmentX(0.1f);
                    }
                    JButton deleteGC = new JButton(" - "); {
                        deleteGC.setBackground(grey);
                        deleteGC.setForeground(starColor);
                        deleteGC.setOpaque(true);
                        deleteGC.setFont(buttonFont);
                        deleteGC.setBorder(BorderFactory.createLineBorder(starColor, 2));
                        deleteGC.setAlignmentX(0.9f);
                    }
                    gcButtons.add(addGC, BorderLayout.WEST);
                    gcButtons.add(deleteGC, BorderLayout.EAST);
                }
                gcList.add(gcListList, BorderLayout.CENTER);
                gcList.add(gcButtons, BorderLayout.SOUTH);
                content.add(gcList, BorderLayout.WEST);
            }

            JPanel gcDisp = new JPanel(); {
                gcDisp.setBackground(grey);
                gcDisp.setLayout(new BorderLayout());
                gcDisp.setVisible(false);
                JPanel gcMessageDisp = new JPanel(); {
                    gcMessageDisp.setLayout(new BoxLayout(gcMessageDisp, BoxLayout.Y_AXIS));
                    gcMessageDisp.setBackground(grey);
                    gcMessageDisp.setOpaque(true);
                }
                JPanel msg1 = new JPanel(); {
                    msg1.setBackground(grey);
                    msg1.setOpaque(true);
                    msg1.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg1.setLayout(new BoxLayout(msg1, BoxLayout.Y_AXIS));
                    msg1.setVisible(false);
                    JLabel msg1Info = new JLabel(); {
                        msg1Info.setText("Username  time");
                        msg1Info.setForeground(accentColor);
                        msg1Info.setBackground(grey);
                        msg1Info.setOpaque(true);
                        msg1Info.setFont(buttonFont);
                        msg1Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg1MSG = new JLabel(); {
                        msg1MSG.setText("");
                        msg1MSG.setForeground(accentColor);
                        msg1MSG.setBackground(lightGrey);
                        msg1MSG.setOpaque(true);
                        msg1MSG.setFont(buttonFont);
                        msg1MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg1.add(msg1Info);
                    msg1.add(msg1MSG);
                }
                JPanel msg2 = new JPanel(); {
                    msg2.setBackground(grey);
                    msg2.setOpaque(true);
                    msg2.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg2.setLayout(new BoxLayout(msg2, BoxLayout.Y_AXIS));
                    msg2.setVisible(false);
                    JLabel msg2Info = new JLabel(); {
                        msg2Info.setText("Username  time");
                        msg2Info.setForeground(accentColor);
                        msg2Info.setBackground(grey);
                        msg2Info.setOpaque(true);
                        msg2Info.setFont(buttonFont);
                        msg2Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg2MSG = new JLabel(); {
                        msg2MSG.setText("");
                        msg2MSG.setForeground(accentColor);
                        msg2MSG.setBackground(lightGrey);
                        msg2MSG.setOpaque(true);
                        msg2MSG.setFont(buttonFont);
                        msg2MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg2.add(msg2Info);
                    msg2.add(msg2MSG);
                }
                JPanel msg3 = new JPanel(); {
                    msg3.setBackground(grey);
                    msg3.setOpaque(true);
                    msg3.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg3.setLayout(new BoxLayout(msg3, BoxLayout.Y_AXIS));
                    msg3.setVisible(false);
                    JLabel msg3Info = new JLabel(); {
                        msg3Info.setText("Username  time");
                        msg3Info.setForeground(accentColor);
                        msg3Info.setBackground(grey);
                        msg3Info.setOpaque(true);
                        msg3Info.setFont(buttonFont);
                        msg3Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg3MSG = new JLabel(); {
                        msg3MSG.setText("");
                        msg3MSG.setForeground(accentColor);
                        msg3MSG.setBackground(lightGrey);
                        msg3MSG.setOpaque(true);
                        msg3MSG.setFont(buttonFont);
                        msg3MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg3.add(msg3Info);
                    msg3.add(msg3MSG);
                }
                JPanel msg4 = new JPanel(); {
                    msg4.setBackground(grey);
                    msg4.setOpaque(true);
                    msg4.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg4.setLayout(new BoxLayout(msg4, BoxLayout.Y_AXIS));
                    msg4.setVisible(false);
                    JLabel msg4Info = new JLabel(); {
                        msg4Info.setText("Username  time");
                        msg4Info.setForeground(accentColor);
                        msg4Info.setBackground(grey);
                        msg4Info.setOpaque(true);
                        msg4Info.setFont(buttonFont);
                        msg4Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg4MSG = new JLabel(); {
                        msg4MSG.setText("");
                        msg4MSG.setForeground(accentColor);
                        msg4MSG.setBackground(lightGrey);
                        msg4MSG.setOpaque(true);
                        msg4MSG.setFont(buttonFont);
                        msg4MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg4.add(msg4Info);
                    msg4.add(msg4MSG);
                }
                JPanel msg5 = new JPanel(); {
                    msg5.setBackground(grey);
                    msg5.setOpaque(true);
                    msg5.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg5.setLayout(new BoxLayout(msg5, BoxLayout.Y_AXIS));
                    msg5.setVisible(false);
                    JLabel msg5Info = new JLabel(); {
                        msg5Info.setText("Username  time");
                        msg5Info.setForeground(accentColor);
                        msg5Info.setBackground(grey);
                        msg5Info.setOpaque(true);
                        msg5Info.setFont(buttonFont);
                        msg5Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg5MSG = new JLabel(); {
                        msg5MSG.setText("");
                        msg5MSG.setForeground(accentColor);
                        msg5MSG.setBackground(lightGrey);
                        msg5MSG.setOpaque(true);
                        msg5MSG.setFont(buttonFont);
                        msg5MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg5.add(msg5Info);
                    msg5.add(msg5MSG);
                }
                JPanel msg6 = new JPanel(); {
                    msg6.setBackground(grey);
                    msg6.setOpaque(true);
                    msg6.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg6.setLayout(new BoxLayout(msg6, BoxLayout.Y_AXIS));
                    msg6.setVisible(false);
                    JLabel msg6Info = new JLabel(); {
                        msg6Info.setText("Username  time");
                        msg6Info.setForeground(accentColor);
                        msg6Info.setBackground(grey);
                        msg6Info.setOpaque(true);
                        msg6Info.setFont(buttonFont);
                        msg6Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg6MSG = new JLabel(); {
                        msg6MSG.setText("");
                        msg6MSG.setForeground(accentColor);
                        msg6MSG.setBackground(lightGrey);
                        msg6MSG.setOpaque(true);
                        msg6MSG.setFont(buttonFont);
                        msg6MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg6.add(msg6Info);
                    msg6.add(msg6MSG);
                }
                JPanel msg7 = new JPanel(); {
                    msg7.setBackground(grey);
                    msg7.setOpaque(true);
                    msg7.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg7.setLayout(new BoxLayout(msg7, BoxLayout.Y_AXIS));
                    msg7.setVisible(false);
                    JLabel msg7Info = new JLabel(); {
                        msg7Info.setText("Username  time");
                        msg7Info.setForeground(accentColor);
                        msg7Info.setBackground(grey);
                        msg7Info.setOpaque(true);
                        msg7Info.setFont(buttonFont);
                        msg7Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg7MSG = new JLabel(); {
                        msg7MSG.setText("");
                        msg7MSG.setForeground(accentColor);
                        msg7MSG.setBackground(lightGrey);
                        msg7MSG.setOpaque(true);
                        msg7MSG.setFont(buttonFont);
                        msg7MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg7.add(msg7Info);
                    msg7.add(msg7MSG);
                }
                JPanel msg8 = new JPanel(); {
                    msg8.setBackground(grey);
                    msg8.setOpaque(true);
                    msg8.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg8.setLayout(new BoxLayout(msg8, BoxLayout.Y_AXIS));
                    msg8.setVisible(false);
                    JLabel msg8Info = new JLabel(); {
                        msg8Info.setText("Username  time");
                        msg8Info.setForeground(accentColor);
                        msg8Info.setBackground(grey);
                        msg8Info.setOpaque(true);
                        msg8Info.setFont(buttonFont);
                        msg8Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg8MSG = new JLabel(); {
                        msg8MSG.setText("");
                        msg8MSG.setForeground(accentColor);
                        msg8MSG.setBackground(lightGrey);
                        msg8MSG.setOpaque(true);
                        msg8MSG.setFont(buttonFont);
                        msg8MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg8.add(msg8Info);
                    msg8.add(msg8MSG);
                }
                JPanel msg9 = new JPanel(); {
                    msg9.setBackground(grey);
                    msg9.setOpaque(true);
                    msg9.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg9.setLayout(new BoxLayout(msg9, BoxLayout.Y_AXIS));
                    msg9.setVisible(false);
                    JLabel msg9Info = new JLabel(); {
                        msg9Info.setText("Username  time");
                        msg9Info.setForeground(accentColor);
                        msg9Info.setBackground(grey);
                        msg9Info.setOpaque(true);
                        msg9Info.setFont(buttonFont);
                        msg9Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg9MSG = new JLabel(); {
                        msg9MSG.setText("");
                        msg9MSG.setForeground(accentColor);
                        msg9MSG.setBackground(lightGrey);
                        msg9MSG.setOpaque(true);
                        msg9MSG.setFont(buttonFont);
                        msg9MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg9.add(msg9Info);
                    msg9.add(msg9MSG);
                }
                JPanel msg10 = new JPanel(); {
                    msg10.setBackground(grey);
                    msg10.setOpaque(true);
                    msg10.setBorder(BorderFactory.createLineBorder(grey, 20));
                    msg10.setLayout(new BoxLayout(msg10, BoxLayout.Y_AXIS));
                    msg10.setVisible(false);
                    JLabel msg10Info = new JLabel(); {
                        msg10Info.setText("Username  time");
                        msg10Info.setForeground(accentColor);
                        msg10Info.setBackground(grey);
                        msg10Info.setOpaque(true);
                        msg10Info.setFont(buttonFont);
                        msg10Info.setBorder(BorderFactory.createLineBorder(grey, 5));
                    }
                    JLabel msg10MSG = new JLabel(); {
                        msg10MSG.setText("");
                        msg10MSG.setForeground(accentColor);
                        msg10MSG.setBackground(lightGrey);
                        msg10MSG.setOpaque(true);
                        msg10MSG.setFont(buttonFont);
                        msg10MSG.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    msg10.add(msg10Info);
                    msg10.add(msg10MSG);
                }
                gcMessageDisp.add(msg1);
                gcMessageDisp.add(msg2);
                gcMessageDisp.add(msg3);
                gcMessageDisp.add(msg4);
                gcMessageDisp.add(msg5);
                gcMessageDisp.add(msg6);
                gcMessageDisp.add(msg7);
                gcMessageDisp.add(msg8);
                gcMessageDisp.add(msg9);
                gcMessageDisp.add(msg10);

                JPanel message = new JPanel(); {
                    message.setBackground(grey);
                    message.setOpaque(true);
                    message.setLayout(null);
                    message.setBorder(BorderFactory.createLineBorder(lightGrey, 1));
                    message.setPreferredSize(new Dimension(900, 80));
                    JButton addImage = new JButton(" + "); {
                        addImage.setForeground(accentColor);
                        addImage.setBackground(lightGrey);
                        addImage.setOpaque(true);
                        addImage.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        addImage.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        addImage.setBounds(20, 20, 40, 40);
                    }
                    JTextField messageText = new JTextField(); {
                        messageText.setForeground(accentColor);
                        messageText.setBackground(lightGrey);
                        messageText.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        messageText.setFont(buttonFont);
                        messageText.setOpaque(true);
                        messageText.setBounds(350, 20, 200, 40);
                    }
                    JButton send = new JButton("Send"); {
                        send.setForeground(accentColor);
                        send.setBackground(lightGrey);
                        send.setOpaque(true);
                        send.setFont(buttonFont);
                        send.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        send.setBounds(560, 20, 50, 40);
                    }
                    JButton deleteMessage = new JButton("Delete Messages"); {
                        deleteMessage.setForeground(accentColor);
                        deleteMessage.setBackground(lightGrey);
                        deleteMessage.setOpaque(true);
                        deleteMessage.setFont(buttonFont);
                        deleteMessage.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        deleteMessage.setBounds(780, 20, 100, 40);
                    }
                    message.add(addImage);
                    message.add(messageText);
                    message.add(send);
                    message.add(deleteMessage);
                }
                gcDisp.add(gcMessageDisp, BorderLayout.CENTER);
                gcDisp.add(message, BorderLayout.SOUTH);
            }
            content.add(gcDisp, BorderLayout.CENTER);

            JPanel friendRequestDisp = new JPanel(); {
                friendRequestDisp.setLayout(new BorderLayout());
                friendRequestDisp.setBackground(grey);
                friendRequestDisp.setVisible(false);
                JLabel friendRequestHeader = new JLabel("  Friend Requests"); {
                    friendRequestHeader.setBackground(grey);
                    friendRequestHeader.setForeground(accentColor);
                    friendRequestHeader.setOpaque(true);
                    friendRequestHeader.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    friendRequestHeader.setPreferredSize(new Dimension(900, 50));
                }
                JPanel friendRequestList = new JPanel(); {
                    friendRequestList.setBackground(grey);
                    friendRequestList.setLayout(new BoxLayout(friendRequestList, BoxLayout.Y_AXIS));
                    JPanel fR1 = new JPanel(); {
                        fR1.setBackground(grey);
                        fR1.setLayout(null);
                        fR1.setPreferredSize(new Dimension(900, 70));
                        JLabel fR1User = new JLabel(""); {
                            fR1User.setBackground(lightGrey);
                            fR1User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR1User.setForeground(accentColor);
                            fR1User.setOpaque(true);
                            fR1User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR1User.setBounds(20, 10, 700, 50);
                        }
                        JButton fR1Accept = new JButton("√"); {
                            fR1Accept.setBackground(lightGrey);
                            fR1Accept.setForeground(accentColor);
                            fR1Accept.setOpaque(true);
                            fR1Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR1Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR1Accept.setBounds(740, 10, 50, 50);
                        }
                        JButton fR1Deny = new JButton("X"); {
                            fR1Deny.setBackground(lightGrey);
                            fR1Deny.setForeground(accentColor);
                            fR1Deny.setOpaque(true);
                            fR1Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR1Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR1Deny.setBounds(810, 10, 50, 50);
                        }
                        fR1.add(fR1User);
                        fR1.add(fR1Accept);
                        fR1.add(fR1Deny);
                    }
                    JPanel fR2 = new JPanel(); {
                        fR2.setBackground(grey);
                        fR2.setLayout(null);
                        fR2.setPreferredSize(new Dimension(900, 70));
                        JLabel fR2User = new JLabel(""); {
                            fR2User.setBackground(lightGrey);
                            fR2User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR2User.setForeground(accentColor);
                            fR2User.setOpaque(true);
                            fR2User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR2User.setBounds(20, 10, 700, 50);
                        }
                        JButton fR2Accept = new JButton("√"); {
                            fR2Accept.setBackground(lightGrey);
                            fR2Accept.setForeground(accentColor);
                            fR2Accept.setOpaque(true);
                            fR2Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR2Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR2Accept.setBounds(740, 10, 50, 50);
                        }
                        JButton fR2Deny = new JButton("X"); {
                            fR2Deny.setBackground(lightGrey);
                            fR2Deny.setForeground(accentColor);
                            fR2Deny.setOpaque(true);
                            fR2Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR2Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR2Deny.setBounds(810, 10, 50, 50);
                        }
                        fR2.add(fR2User);
                        fR2.add(fR2Accept);
                        fR2.add(fR2Deny);
                    }
                    JPanel fR3 = new JPanel(); {
                        fR3.setBackground(grey);
                        fR3.setLayout(null);
                        fR3.setPreferredSize(new Dimension(900, 70));
                        JLabel fR3User = new JLabel(""); {
                            fR3User.setBackground(lightGrey);
                            fR3User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR3User.setForeground(accentColor);
                            fR3User.setOpaque(true);
                            fR3User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR3User.setBounds(20, 10, 700, 50);
                        }
                        JButton fR3Accept = new JButton("√"); {
                            fR3Accept.setBackground(lightGrey);
                            fR3Accept.setForeground(accentColor);
                            fR3Accept.setOpaque(true);
                            fR3Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR3Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR3Accept.setBounds(740, 10, 50, 50);
                        }
                        JButton fR3Deny = new JButton("X"); {
                            fR3Deny.setBackground(lightGrey);
                            fR3Deny.setForeground(accentColor);
                            fR3Deny.setOpaque(true);
                            fR3Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR3Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR3Deny.setBounds(810, 10, 50, 50);
                        }
                        fR3.add(fR3User);
                        fR3.add(fR3Accept);
                        fR3.add(fR3Deny);
                    }
                    JPanel fR4 = new JPanel(); {
                        fR4.setBackground(grey);
                        fR4.setLayout(null);
                        fR4.setPreferredSize(new Dimension(900, 70));
                        JLabel fR4User = new JLabel(""); {
                            fR4User.setBackground(lightGrey);
                            fR4User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR4User.setForeground(accentColor);
                            fR4User.setOpaque(true);
                            fR4User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR4User.setBounds(20, 10, 700, 50);
                        }
                        JButton fR4Accept = new JButton("√"); {
                            fR4Accept.setBackground(lightGrey);
                            fR4Accept.setForeground(accentColor);
                            fR4Accept.setOpaque(true);
                            fR4Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR4Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR4Accept.setBounds(740, 10, 50, 50);
                        }
                        JButton fR4Deny = new JButton("X"); {
                            fR4Deny.setBackground(lightGrey);
                            fR4Deny.setForeground(accentColor);
                            fR4Deny.setOpaque(true);
                            fR4Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR4Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR4Deny.setBounds(810, 10, 50, 50);
                        }
                        fR4.add(fR4User);
                        fR4.add(fR4Accept);
                        fR4.add(fR4Deny);
                    }
                    JPanel fR5 = new JPanel(); {
                        fR5.setBackground(grey);
                        fR5.setLayout(null);
                        fR5.setPreferredSize(new Dimension(900, 70));
                        JLabel fR5User = new JLabel(""); {
                            fR5User.setBackground(lightGrey);
                            fR5User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR5User.setForeground(accentColor);
                            fR5User.setOpaque(true);
                            fR5User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR5User.setBounds(20, 10, 700, 50);
                        }
                        JButton fR5Accept = new JButton("√"); {
                            fR5Accept.setBackground(lightGrey);
                            fR5Accept.setForeground(accentColor);
                            fR5Accept.setOpaque(true);
                            fR5Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR5Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR5Accept.setBounds(740, 10, 50, 50);
                        }
                        JButton fR5Deny = new JButton("X"); {
                            fR5Deny.setBackground(lightGrey);
                            fR5Deny.setForeground(accentColor);
                            fR5Deny.setOpaque(true);
                            fR5Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            fR5Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            fR5Deny.setBounds(810, 10, 50, 50);
                        }
                        fR5.add(fR5User);
                        fR5.add(fR5Accept);
                        fR5.add(fR5Deny);
                    }
                    friendRequestList.add(fR1);
                    friendRequestList.add(fR2);
                    friendRequestList.add(fR3);
                    friendRequestList.add(fR4);
                    friendRequestList.add(fR5);
                }
                friendRequestDisp.add(friendRequestHeader, BorderLayout.NORTH);
                friendRequestDisp.add(friendRequestList, BorderLayout.CENTER);
            }
            content.add(friendRequestDisp, BorderLayout.CENTER);

            JPanel friendsDisp = new JPanel(); {
                friendsDisp.setLayout(new BorderLayout());
                friendsDisp.setBackground(grey);
                friendsDisp.setVisible(false);
                JPanel friendsHeader = new JPanel(); {
                    friendsHeader.setBackground(grey);
                    friendsHeader.setPreferredSize(new Dimension(900, 50));
                    JLabel friendsHeaderTxt = new JLabel("  Friends"); {
                        friendsHeaderTxt.setBackground(grey);
                        friendsHeaderTxt.setForeground(accentColor);
                        friendsHeaderTxt.setOpaque(true);
                        friendsHeaderTxt.setFont(new Font("Monospaced", Font.PLAIN, 20));
                        friendsHeaderTxt.setPreferredSize(new Dimension(830, 50));
                    }
                    JButton addFriend = new JButton("+"); {
                        addFriend.setBackground(lightGrey);
                        addFriend.setForeground(accentColor);
                        addFriend.setFont(new Font("Monospaced", Font.PLAIN, 20));
                        addFriend.setPreferredSize(new Dimension(40, 40));
                        addFriend.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    friendsHeader.add(friendsHeaderTxt);
                    friendsHeader.add(addFriend);
                }
                JPanel friendsList = new JPanel(); {
                    friendsList.setBackground(grey);
                    friendsList.setLayout(new BoxLayout(friendsList, BoxLayout.Y_AXIS));
                    JPanel f1 = new JPanel(); {
                        f1.setBackground(grey);
                        f1.setLayout(null);
                        f1.setPreferredSize(new Dimension(900, 70));
                        JLabel f1User = new JLabel(""); {
                            f1User.setBackground(lightGrey);
                            f1User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f1User.setForeground(accentColor);
                            f1User.setOpaque(true);
                            f1User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f1User.setBounds(20, 10, 640, 50);
                        }
                        JButton f1Accept = new JButton("Unfriend"); {
                            f1Accept.setBackground(lightGrey);
                            f1Accept.setForeground(accentColor);
                            f1Accept.setOpaque(true);
                            f1Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f1Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f1Accept.setBounds(670, 10, 100, 50);
                        }
                        JButton f1Deny = new JButton("Block"); {
                            f1Deny.setBackground(lightGrey);
                            f1Deny.setForeground(accentColor);
                            f1Deny.setOpaque(true);
                            f1Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f1Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f1Deny.setBounds(780, 10, 100, 50);
                        }
                        f1.add(f1User);
                        f1.add(f1Accept);
                        f1.add(f1Deny);
                    }
                    JPanel f2 = new JPanel(); {
                        f2.setBackground(grey);
                        f2.setLayout(null);
                        f2.setPreferredSize(new Dimension(900, 70));
                        JLabel f2User = new JLabel(""); {
                            f2User.setBackground(lightGrey);
                            f2User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f2User.setForeground(accentColor);
                            f2User.setOpaque(true);
                            f2User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f2User.setBounds(20, 10, 640, 50);
                        }
                        JButton f2Accept = new JButton("Unfriend"); {
                            f2Accept.setBackground(lightGrey);
                            f2Accept.setForeground(accentColor);
                            f2Accept.setOpaque(true);
                            f2Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f2Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f2Accept.setBounds(670, 10, 100, 50);
                        }
                        JButton f2Deny = new JButton("Block"); {
                            f2Deny.setBackground(lightGrey);
                            f2Deny.setForeground(accentColor);
                            f2Deny.setOpaque(true);
                            f2Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f2Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f2Deny.setBounds(780, 10, 100, 50);
                        }
                        f2.add(f2User);
                        f2.add(f2Accept);
                        f2.add(f2Deny);
                    }
                    JPanel f3 = new JPanel(); {
                        f3.setBackground(grey);
                        f3.setLayout(null);
                        f3.setPreferredSize(new Dimension(900, 70));
                        JLabel f3User = new JLabel(""); {
                            f3User.setBackground(lightGrey);
                            f3User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f3User.setForeground(accentColor);
                            f3User.setOpaque(true);
                            f3User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f3User.setBounds(20, 10, 640, 50);
                        }
                        JButton f3Accept = new JButton("Unfriend"); {
                            f3Accept.setBackground(lightGrey);
                            f3Accept.setForeground(accentColor);
                            f3Accept.setOpaque(true);
                            f3Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f3Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f3Accept.setBounds(670, 10, 100, 50);
                        }
                        JButton f3Deny = new JButton("Block"); {
                            f3Deny.setBackground(lightGrey);
                            f3Deny.setForeground(accentColor);
                            f3Deny.setOpaque(true);
                            f3Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f3Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f3Deny.setBounds(780, 10, 100, 50);
                        }
                        f3.add(f3User);
                        f3.add(f3Accept);
                        f3.add(f3Deny);
                    }
                    JPanel f4 = new JPanel(); {
                        f4.setBackground(grey);
                        f4.setLayout(null);
                        f4.setPreferredSize(new Dimension(900, 70));
                        JLabel f4User = new JLabel(""); {
                            f4User.setBackground(lightGrey);
                            f4User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f4User.setForeground(accentColor);
                            f4User.setOpaque(true);
                            f4User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f4User.setBounds(20, 10, 640, 50);
                        }
                        JButton f4Accept = new JButton("Unfriend"); {
                            f4Accept.setBackground(lightGrey);
                            f4Accept.setForeground(accentColor);
                            f4Accept.setOpaque(true);
                            f4Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f4Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f4Accept.setBounds(670, 10, 100, 50);
                        }
                        JButton f4Deny = new JButton("Block"); {
                            f4Deny.setBackground(lightGrey);
                            f4Deny.setForeground(accentColor);
                            f4Deny.setOpaque(true);
                            f4Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f4Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f4Deny.setBounds(780, 10, 100, 50);
                        }
                        f4.add(f4User);
                        f4.add(f4Accept);
                        f4.add(f4Deny);
                    }
                    JPanel f5 = new JPanel(); {
                        f5.setBackground(grey);
                        f5.setLayout(null);
                        f5.setPreferredSize(new Dimension(900, 70));
                        JLabel f5User = new JLabel(""); {
                            f5User.setBackground(lightGrey);
                            f5User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f5User.setForeground(accentColor);
                            f5User.setOpaque(true);
                            f5User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f5User.setBounds(20, 10, 640, 50);
                        }
                        JButton f5Accept = new JButton("Unfriend"); {
                            f5Accept.setBackground(lightGrey);
                            f5Accept.setForeground(accentColor);
                            f5Accept.setOpaque(true);
                            f5Accept.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f5Accept.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f5Accept.setBounds(670, 10, 100, 50);
                        }
                        JButton f5Deny = new JButton("Block"); {
                            f5Deny.setBackground(lightGrey);
                            f5Deny.setForeground(accentColor);
                            f5Deny.setOpaque(true);
                            f5Deny.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            f5Deny.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            f5Deny.setBounds(780, 10, 100, 50);
                        }
                        f5.add(f5User);
                        f5.add(f5Accept);
                        f5.add(f5Deny);
                    }
                    friendsList.add(f1);
                    friendsList.add(f2);
                    friendsList.add(f3);
                    friendsList.add(f4);
                    friendsList.add(f5);
                }
                friendsDisp.add(friendsHeader, BorderLayout.NORTH);
                friendsDisp.add(friendsList, BorderLayout.CENTER);
            }
            content.add(friendsDisp, BorderLayout.CENTER);

            JPanel blockedDisp = new JPanel(); {
                blockedDisp.setLayout(new BorderLayout());
                blockedDisp.setBackground(grey);
                blockedDisp.setVisible(false);
                blockedDisp.setOpaque(true);
                JLabel blockedHeader = new JLabel("  Blocked"); {
                    blockedHeader.setBackground(grey);
                    blockedHeader.setForeground(accentColor);
                    blockedHeader.setOpaque(true);
                    blockedHeader.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    blockedHeader.setPreferredSize(new Dimension(900, 50));
                }
                JPanel blockedList = new JPanel(); {
                    blockedList.setBackground(grey);
                    blockedList.setLayout(new BoxLayout(blockedList, BoxLayout.Y_AXIS));
                    blockedHeader.setOpaque(true);
                    JPanel b1 = new JPanel(); {
                        b1.setBackground(grey);
                        b1.setLayout(null);
                        b1.setOpaque(true);
                        b1.setPreferredSize(new Dimension(900, 70));
                        JLabel b1User = new JLabel(""); {
                            b1User.setBackground(lightGrey);
                            b1User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b1User.setForeground(accentColor);
                            b1User.setOpaque(true);
                            b1User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b1User.setBounds(20, 10, 750, 50);
                        }
                        JButton b1Unblock = new JButton("Unblock"); {
                            b1Unblock.setBackground(lightGrey);
                            b1Unblock.setForeground(accentColor);
                            b1Unblock.setOpaque(true);
                            b1Unblock.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b1Unblock.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b1Unblock.setBounds(790, 10, 90, 50);
                        }
                        b1.add(b1User);
                        b1.add(b1Unblock);
                    }
                    JPanel b2 = new JPanel(); {
                        b2.setBackground(grey);
                        b2.setLayout(null);
                        b2.setOpaque(true);
                        b2.setPreferredSize(new Dimension(900, 70));
                        JLabel b2User = new JLabel(""); {
                            b2User.setBackground(lightGrey);
                            b2User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b2User.setForeground(accentColor);
                            b2User.setOpaque(true);
                            b2User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b2User.setBounds(20, 10, 750, 50);
                        }
                        JButton b2Unblock = new JButton("Unblock"); {
                            b2Unblock.setBackground(lightGrey);
                            b2Unblock.setForeground(accentColor);
                            b2Unblock.setOpaque(true);
                            b2Unblock.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b2Unblock.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b2Unblock.setBounds(790, 10, 90, 50);
                        }
                        b2.add(b2User);
                        b2.add(b2Unblock);
                    }
                    JPanel b3 = new JPanel(); {
                        b3.setBackground(grey);
                        b3.setLayout(null);
                        b3.setOpaque(true);
                        b3.setPreferredSize(new Dimension(900, 70));
                        JLabel b3User = new JLabel(""); {
                            b3User.setBackground(lightGrey);
                            b3User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b3User.setForeground(accentColor);
                            b3User.setOpaque(true);
                            b3User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b3User.setBounds(20, 10, 750, 50);
                        }
                        JButton b3Unblock = new JButton("Unblock"); {
                            b3Unblock.setBackground(lightGrey);
                            b3Unblock.setForeground(accentColor);
                            b3Unblock.setOpaque(true);
                            b3Unblock.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b3Unblock.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b3Unblock.setBounds(790, 10, 90, 50);
                        }
                        b3.add(b3User);
                        b3.add(b3Unblock);
                    }
                    JPanel b4 = new JPanel(); {
                        b4.setBackground(grey);
                        b4.setLayout(null);
                        b4.setOpaque(true);
                        b4.setPreferredSize(new Dimension(900, 70));
                        JLabel b4User = new JLabel(""); {
                            b4User.setBackground(lightGrey);
                            b4User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b4User.setForeground(accentColor);
                            b4User.setOpaque(true);
                            b4User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b4User.setBounds(20, 10, 750, 50);
                        }
                        JButton b4Unblock = new JButton("Unblock"); {
                            b4Unblock.setBackground(lightGrey);
                            b4Unblock.setForeground(accentColor);
                            b4Unblock.setOpaque(true);
                            b4Unblock.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b4Unblock.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b4Unblock.setBounds(790, 10, 90, 50);
                        }
                        b4.add(b4User);
                        b4.add(b4Unblock);
                    }
                    JPanel b5 = new JPanel(); {
                        b5.setBackground(grey);
                        b5.setLayout(null);
                        b5.setOpaque(true);
                        b5.setPreferredSize(new Dimension(900, 70));
                        JLabel b5User = new JLabel(""); {
                            b5User.setBackground(lightGrey);
                            b5User.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b5User.setForeground(accentColor);
                            b5User.setOpaque(true);
                            b5User.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b5User.setBounds(20, 10, 750, 50);
                        }
                        JButton b5Unblock = new JButton("Unblock"); {
                            b5Unblock.setBackground(lightGrey);
                            b5Unblock.setForeground(accentColor);
                            b5Unblock.setOpaque(true);
                            b5Unblock.setFont(new Font("Monospaced", Font.PLAIN, 15));
                            b5Unblock.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                            b5Unblock.setBounds(790, 10, 90, 50);
                        }
                        b5.add(b5User);
                        b5.add(b5Unblock);
                    }
                    blockedList.add(b1);
                    blockedList.add(b2);
                    blockedList.add(b3);
                    blockedList.add(b4);
                    blockedList.add(b5);
                }
                blockedDisp.add(blockedHeader, BorderLayout.NORTH);
                blockedDisp.add(blockedList, BorderLayout.CENTER);
            }
            content.add(blockedDisp, BorderLayout.CENTER);

            JPanel addFriendDisp = new JPanel(); {
                addFriendDisp.setLayout(new BorderLayout());
                addFriendDisp.setBackground(grey);
                addFriendDisp.setVisible(false);
                addFriendDisp.setOpaque(true);
                JLabel addFriendHeader = new JLabel("  Send Friend Request"); {
                    addFriendHeader.setBackground(grey);
                    addFriendHeader.setForeground(accentColor);
                    addFriendHeader.setOpaque(true);
                    addFriendHeader.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    addFriendHeader.setPreferredSize(new Dimension(900, 50));
                }
                JPanel addFriendPanel = new JPanel(); {
                    addFriendPanel.setLayout(null);
                    addFriendPanel.setBackground(grey);
                    JLabel putInUsername = new JLabel(); {
                        putInUsername.setText("Enter Username: ");
                        putInUsername.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        putInUsername.setForeground(accentColor);
                        putInUsername.setBackground(grey);
                        putInUsername.setOpaque(true);
                        putInUsername.setBounds(300, 20, 300, 25);
                    }
                    JTextField enterFriendUserRequest = new JTextField(); {
                        enterFriendUserRequest.setBackground(lightGrey);
                        enterFriendUserRequest.setForeground(accentColor);
                        enterFriendUserRequest.setOpaque(true);
                        enterFriendUserRequest.setFont(buttonFont);
                        enterFriendUserRequest.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        enterFriendUserRequest.setBounds(300, 50, 300, 25);
                    }
                    JButton enterRequest = new JButton("Send Friend Request"); {
                        enterRequest.setBackground(lightGrey);
                        enterRequest.setForeground(accentColor);
                        enterRequest.setOpaque(true);
                        enterRequest.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        enterRequest.setBounds(300, 85, 300, 25);
                        enterRequest.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    addFriendPanel.add(putInUsername);
                    addFriendPanel.add(enterFriendUserRequest);
                    addFriendPanel.add(enterRequest);
                }
                addFriendDisp.add(addFriendHeader, BorderLayout.NORTH);
                addFriendDisp.add(addFriendPanel, BorderLayout.CENTER);
            }
            content.add(addFriendDisp, BorderLayout.CENTER);

            JPanel addGroupchatDisp = new JPanel(); {
                addGroupchatDisp.setLayout(new BorderLayout());
                addGroupchatDisp.setBackground(grey);
                addGroupchatDisp.setVisible(false);
                addGroupchatDisp.setOpaque(true);
                JLabel addGroupchatHeader = new JLabel("  Add Groupchat"); {
                    addGroupchatHeader.setBackground(grey);
                    addGroupchatHeader.setForeground(accentColor);
                    addGroupchatHeader.setOpaque(true);
                    addGroupchatHeader.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    addGroupchatHeader.setPreferredSize(new Dimension(900, 50));
                }
                JPanel addGroupchatPanel = new JPanel(); {
                    addGroupchatPanel.setLayout(null);
                    addGroupchatPanel.setBackground(grey);
                    JLabel putInUsernames = new JLabel(); {
                        putInUsernames.setText("Enter Usernames Seperated By Commas:");
                        putInUsernames.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        putInUsernames.setForeground(accentColor);
                        putInUsernames.setBackground(grey);
                        putInUsernames.setOpaque(true);
                        putInUsernames.setBounds(250, 20, 400, 25);
                    }
                    JTextField enterUsersRequest = new JTextField(); {
                        enterUsersRequest.setBackground(lightGrey);
                        enterUsersRequest.setForeground(accentColor);
                        enterUsersRequest.setOpaque(true);
                        enterUsersRequest.setFont(buttonFont);
                        enterUsersRequest.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        enterUsersRequest.setBounds(250, 50, 400, 25);
                    }
                    JButton enterNames = new JButton("Create Groupchat"); {
                        enterNames.setBackground(lightGrey);
                        enterNames.setForeground(accentColor);
                        enterNames.setOpaque(true);
                        enterNames.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        enterNames.setBounds(250, 95, 400, 25);
                        enterNames.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    addGroupchatPanel.add(putInUsernames);
                    addGroupchatPanel.add(enterUsersRequest);
                    addGroupchatPanel.add(enterNames);
                }
                addGroupchatDisp.add(addGroupchatHeader, BorderLayout.NORTH);
                addGroupchatDisp.add(addGroupchatPanel, BorderLayout.CENTER);
            }
            content.add(addGroupchatDisp, BorderLayout.CENTER);

            JPanel deleteGroupchatDisp = new JPanel(); {
                deleteGroupchatDisp.setLayout(new BorderLayout());
                deleteGroupchatDisp.setBackground(grey);
                deleteGroupchatDisp.setVisible(false);
                deleteGroupchatDisp.setOpaque(true);
                JLabel deleteGroupchatHeader = new JLabel("  Delete Groupchat"); {
                    deleteGroupchatHeader.setBackground(grey);
                    deleteGroupchatHeader.setForeground(accentColor);
                    deleteGroupchatHeader.setOpaque(true);
                    deleteGroupchatHeader.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    deleteGroupchatHeader.setPreferredSize(new Dimension(900, 50));
                }
                JPanel deleteGroupchatPanel = new JPanel(); {
                    deleteGroupchatPanel.setLayout(null);
                    deleteGroupchatPanel.setBackground(grey);
                    JLabel putInUsernamesD = new JLabel(); {
                        putInUsernamesD.setText("Enter Usernames Seperated By Commas:");
                        putInUsernamesD.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        putInUsernamesD.setForeground(accentColor);
                        putInUsernamesD.setBackground(grey);
                        putInUsernamesD.setOpaque(true);
                        putInUsernamesD.setBounds(250, 20, 400, 25);
                    }
                    JTextField enterUsersRequestD = new JTextField(); {
                        enterUsersRequestD.setBackground(lightGrey);
                        enterUsersRequestD.setForeground(accentColor);
                        enterUsersRequestD.setOpaque(true);
                        enterUsersRequestD.setFont(buttonFont);
                        enterUsersRequestD.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                        enterUsersRequestD.setBounds(250, 50, 400, 25);
                    }
                    JButton enterNamesD = new JButton("Delete Groupchat"); {
                        enterNamesD.setBackground(lightGrey);
                        enterNamesD.setForeground(accentColor);
                        enterNamesD.setOpaque(true);
                        enterNamesD.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        enterNamesD.setBounds(250, 95, 400, 25);
                        enterNamesD.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    }
                    deleteGroupchatPanel.add(putInUsernamesD);
                    deleteGroupchatPanel.add(enterUsersRequestD);
                    deleteGroupchatPanel.add(enterNamesD);
                }
                deleteGroupchatDisp.add(deleteGroupchatHeader, BorderLayout.NORTH);
                deleteGroupchatDisp.add(deleteGroupchatPanel, BorderLayout.CENTER);
            }
            content.add(deleteGroupchatDisp, BorderLayout.CENTER);

            JPanel profileDisp = new JPanel(); {
                profileDisp.setLayout(null);
                profileDisp.setBackground(grey);
                profileDisp.setVisible(true);
                profileDisp.setOpaque(true);
                JLabel profileUsername = new JLabel(); {
                    profileUsername.setBackground(lightGrey);
                    profileUsername.setForeground(accentColor);
                    profileUsername.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    profileUsername.setText("Username: " + username.getText());
                    profileUsername.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    profileUsername.setBounds(300, 20, 300, 25);
                }
                JLabel profilePassword = new JLabel(); {
                    profilePassword.setBackground(lightGrey);
                    profilePassword.setForeground(accentColor);
                    profilePassword.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    profilePassword.setText("Password: " + password.getText());
                    profilePassword.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    profilePassword.setBounds(300, 55, 300, 25);
                }
                JLabel profileCPL = new JLabel(); {
                    profileCPL.setBackground(grey);
                    profileCPL.setForeground(accentColor);
                    profileCPL.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    profileCPL.setText("Change password:");
                    profileCPL.setBounds(300, 100, 300, 25);
                }
                JTextField profileNewPassword = new JTextField(); {
                    profileNewPassword.setBackground(lightGrey);
                    profileNewPassword.setForeground(accentColor);
                    profileNewPassword.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    profileNewPassword.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    profileNewPassword.setBounds(300, 135, 300, 25);
                }
                JButton profileChangePassword = new JButton("Change Password"); {
                    profileChangePassword.setBackground(lightGrey);
                    profileChangePassword.setForeground(accentColor);
                    profileChangePassword.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    profileChangePassword.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    profileChangePassword.setBounds(400, 170, 100, 25);
                }
                JLabel profileStrangersCanMessage = new JLabel(); {
                    profileStrangersCanMessage.setBackground(lightGrey);
                    profileStrangersCanMessage.setForeground(accentColor);
                    profileStrangersCanMessage.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    writer.println("isStrangersCanMessage");
                    writer.flush();
                    Boolean isStrangersCanMessage = Boolean.parseBoolean(reader.readLine());
                    profileStrangersCanMessage.setText("Strangers can message me: " + isStrangersCanMessage);
                    profileStrangersCanMessage.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    profileStrangersCanMessage.setBounds(300, 225, 300, 25);
                }
                JButton changeStrangersCanMessage = new JButton("Change if strangers can message me"); {
                    changeStrangersCanMessage.setBackground(lightGrey);
                    changeStrangersCanMessage.setForeground(accentColor);
                    changeStrangersCanMessage.setFont(new Font("Monospaced", Font.PLAIN, 20));
                    changeStrangersCanMessage.setBorder(BorderFactory.createLineBorder(accentColor, 2));
                    changeStrangersCanMessage.setBounds(400, 270, 100, 25);
                }
                profileDisp.add(profileUsername);
                profileDisp.add(profilePassword);
                profileDisp.add(profileCPL);
                profileDisp.add(profileNewPassword);
                profileDisp.add(profileChangePassword);
                profileDisp.add(profileStrangersCanMessage);
                profileDisp.add(changeStrangersCanMessage);
            }
            content.add(profileDisp, BorderLayout.CENTER);

            mainFrame.setSize(1000, 1000);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection Error.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
