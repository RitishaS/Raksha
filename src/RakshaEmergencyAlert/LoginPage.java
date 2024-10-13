package RakshaEmergencyAlert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame{

    LoginPage() {
        // Create the frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(new BorderLayout());

        // Define the color palette
        Color bgLight = new Color(0xfffcf2);  // Light beige
        Color bgMedium = new Color(0x8E8475); // Medium beige
        Color bgDark = new Color(0x403d39);   // Dark gray
        Color bgDarker = new Color(0x252422); // Darker gray
        Color buttonRed = new Color(0xeb5e28);  // Red for login button

        // Main container panel that centers everything
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, getWidth(), getHeight(), bgLight);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout()); // This centers the content
        mainPanel.setBackground(bgLight); // Light beige background for the frame

        // Create a panel for the login form (content inside the box)
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS)); // Vertical arrangement
        loginPanel.setBackground(bgMedium); // Medium beige background for the form
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 5, 5, new Color(0, 0, 0, 50)), // Simulated shadow
                BorderFactory.createEmptyBorder(30, 30, 30, 30))); // Padding inside the box

        // Heading - 'MISSION RAKSHA'
        JLabel missionHeading = new JLabel("MISSION RAKSHA", JLabel.CENTER);
        missionHeading.setFont(new Font("SansSerif", Font.BOLD, 36));
        missionHeading.setForeground(bgDarker); // Darker gray text for heading
        missionHeading.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login Heading
        JLabel headingLabel = new JLabel("Login", JLabel.CENTER);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        headingLabel.setForeground(bgDark);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels and Text Fields with Placeholder Text
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        usernameLabel.setForeground(bgDarker);
        JTextField usernameField = new JTextField("Enter your username", 25);
        usernameField.setForeground(Color.GRAY);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordLabel.setForeground(bgDarker);
        JPasswordField passwordField = new JPasswordField(25);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        confirmPasswordLabel.setForeground(bgDarker);
        JPasswordField confirmPasswordField = new JPasswordField(25);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(buttonRed); // Red background for login button
        loginButton.setForeground(Color.WHITE); // White text for login button
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform login logic here (e.g., authenticate the user)
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Password validation logic
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.");
                } else if (username.equals("admin") && password.equals("admin123")) {  // Dummy login check
                    JOptionPane.showMessageDialog(frame, "Login successful. Welcome " + username + "!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
                }
                loginButton.addActionListener(
                        a->{
                            new RAKSHA();
                            dispose();
                        }
                );
            }
        });

        // Hover effect on Login button
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0xFF4500)); // Change to a darker red on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0xEB5E28)); // Revert to original red
            }
        });

        // Add components to the panel
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        loginPanel.add(missionHeading); // Adding 'MISSION RAKSHA' heading
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        loginPanel.add(headingLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        loginPanel.add(createFormRow(usernameLabel, usernameField));
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        loginPanel.add(createFormRow(passwordLabel, passwordField));
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        loginPanel.add(createFormRow(confirmPasswordLabel, confirmPasswordField));
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        loginPanel.add(loginButton);

        // Center the form inside the main panel
        mainPanel.add(loginPanel, new GridBagConstraints());

        // Add main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);

    }
    // Utility function to create a row with a label and text field
    private static JPanel createFormRow(JLabel label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0x8E8475)); // Medium beige background for form rows
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }
}