package RakshaEmergencyAlert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpPage extends JFrame {

    SignUpPage() {
        // Create the frame
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        // Define the color palette
        Color bgLight = new Color(0xfffcf2);  // Light beige
        Color bgMedium = new Color(0x8E8475); // Medium beige
        Color bgDark = new Color(0x403d39);   // Dark gray
        Color bgDarker = new Color(0x252422); // Darker gray
        Color buttonRed = new Color(0xeb5e28);  // Red for Save button

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
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(bgLight); // Light beige background for the frame

        // Create a panel for the sign-up form (content inside the box)
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS)); // Vertical arrangement
        signUpPanel.setBackground(bgMedium); // Medium beige background for the form
        signUpPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 5, 5, new Color(0, 0, 0, 50)), // Simulated shadow
                BorderFactory.createEmptyBorder(30, 30, 30, 30))); // Padding inside the box

        // Heading - 'MISSION RAKSHA'
        JLabel missionHeading = new JLabel("MISSION RAKSHA", JLabel.CENTER);
        missionHeading.setFont(new Font("SansSerif", Font.BOLD, 36));
        missionHeading.setForeground(bgDarker); // Darker gray text for heading
        missionHeading.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Sign-Up Heading
        JLabel headingLabel = new JLabel("Sign Up", JLabel.CENTER);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        headingLabel.setForeground(bgDark);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels and Text Fields
        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        emailLabel.setForeground(bgDarker);
        JTextField emailField = new JTextField("Enter your email", 25);
        emailField.setForeground(Color.GRAY);

        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        phoneLabel.setForeground(bgDarker);
        JTextField phoneField = new JTextField("Enter your phone number", 25);
        phoneField.setForeground(Color.GRAY);

        JLabel usernameLabel = new JLabel("Create Username:");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        usernameLabel.setForeground(bgDarker);
        JTextField usernameField = new JTextField("Choose a username", 25);
        usernameField.setForeground(Color.GRAY);

        JLabel passwordLabel = new JLabel("Create Password:");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordLabel.setForeground(bgDarker);
        JPasswordField passwordField = new JPasswordField(25);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        confirmPasswordLabel.setForeground(bgDarker);
        JPasswordField confirmPasswordField = new JPasswordField(25);

        // Save Button
        JButton saveButton = new JButton("Sign Up");
        saveButton.setBackground(buttonRed); // Red background for save button
        saveButton.setForeground(Color.WHITE); // White text for save button
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String phone = phoneField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Check if the passwords match
                if (password.equals(confirmPassword)) {
                    try {
                        // Database connection and insertion
                        String url = "jdbc:mysql://localhost:3306/raksha"; // Update with your database name
                        try (Connection conn = DriverManager.getConnection(url, "root", "WJ28@krhps")) {
                            String sql = "INSERT INTO users2 (username, password, email, phone_number) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                                pst.setString(1, username);
                                pst.setString(2, password); // Store password as plain text
                                pst.setString(3, email);
                                pst.setString(4, phone); // Phone number can be null
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(frame, "Sign-Up successful for: " + username);
                                new AddContactPage(); // Assuming this is your next page
                                dispose(); // Close the sign-up page
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Connection Error: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.");
                }
            }
        });

        // Add components to the panel
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(missionHeading); // Adding 'MISSION RAKSHA' heading
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(headingLabel);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(createFormRow(emailLabel, emailField));
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(createFormRow(phoneLabel, phoneField));
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(createFormRow(usernameLabel, usernameField));
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(createFormRow(passwordLabel, passwordField));
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(createFormRow(confirmPasswordLabel, confirmPasswordField));
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        signUpPanel.add(saveButton);

        // Center the form inside the main panel
        mainPanel.add(signUpPanel, new GridBagConstraints());

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
