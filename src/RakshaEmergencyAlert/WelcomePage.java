package RakshaEmergencyAlert;

import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JFrame
{
    WelcomePage()
    {
        // Create the frame
        JFrame frame = new JFrame("Welcome to RAKSHA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        // Define the color palette
        Color bgLight = new Color(0xfffcf2);  // Light beige
        Color bgMedium = new Color(0xccc5b9); // Medium beige
        Color bgDark = new Color(0x403d39);   // Dark gray
        Color bgDarker = new Color(0x252422); // Darker gray
        Color helpRed = new Color(0xeb5e28);  // Red for highlights or buttons

        // Get the content pane of the frame
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(bgLight);  // Light beige background for the container

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Padding

        // Heading "Welcome"
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        welcomeLabel.setForeground(bgDark);  // Dark gray text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(welcomeLabel, gbc);  // Add to the content pane

        // Subheading "Mission Raksha"
        JLabel missionLabel = new JLabel("Mission Raksha");
        missionLabel.setFont(new Font("Arial", Font.ITALIC, 40));
        missionLabel.setForeground(helpRed);  // Red text
        gbc.gridy = 1;
        contentPane.add(missionLabel, gbc);  // Add to the content pane

        // Button Panel for "Sign Up" and "Log In"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(bgLight);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setBackground(bgMedium);  // Medium beige background
        signUpButton.setForeground(bgDarker);  // Darker gray text
        signUpButton.setFocusPainted(false);
        signUpButton.setPreferredSize(new Dimension(150, 50));

        signUpButton.addActionListener(
                a->{
                    new SignUpPage();
                    dispose();
                }
        );

        JButton loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(bgMedium);  // Medium beige background
        loginButton.setForeground(bgDarker);  // Darker gray text
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.addActionListener(
                a->{
                    new LoginPage();
                    dispose();
                }
        );

        // Add buttons to the button panel
        buttonPanel.add(signUpButton);
        buttonPanel.add(loginButton);

        // Positioning the buttons under the subheading
        gbc.gridy = 2;
        contentPane.add(buttonPanel, gbc);  // Add to the content pane

        // Make the frame visible
        frame.setVisible(true);
    }
}