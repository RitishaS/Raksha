package RakshaEmergencyAlert;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RAKSHA  {

    private static int currentImageIndex = 0;
    private static JLabel imageLabel;
    private static String[] imagePaths = {
            "https://i.ibb.co/Wgd5T7L/raksha.png",
            "https://i.ibb.co/BzZZ9TG/raksha1.png",
            "https://i.ibb.co/q0LsWc1/raksha2.png"
    };

    private static ArrayList<String> emergencyContacts = new ArrayList<>(); // Store contacts

    RAKSHA() {
        // Create the frame
        JFrame frame = new JFrame("RAKSHA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        // Define the color palette
        Color bgLight = new Color(0xfffcf2);  // Light beige
        Color bgMedium = new Color(0xccc5b9); // Medium beige
        Color bgDark = new Color(0x403d39);   // Dark gray
        Color bgDarker = new Color(0x252422); // Darker gray
        Color helpRed = new Color(0xeb5e28);  // Red for HELP button

        // Top Section
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(bgLight);  // Light beige background for top panel

        JPanel headerPanel = new JPanel(new FlowLayout());
        headerPanel.setBackground(bgLight); // Light beige background for header

        JLabel taglineLabel = new JLabel("RAKSHA", JLabel.CENTER);
        taglineLabel.setFont(new Font("Arial", Font.BOLD, 80));
        taglineLabel.setForeground(bgDark); // Dark gray text for tagline

        JLabel headerLabel = new JLabel("MISSION ");
        headerLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        headerLabel.setForeground(bgMedium); // Medium beige for the header

        headerPanel.add(headerLabel);
        headerPanel.add(taglineLabel);
        topPanel.add(headerPanel, BorderLayout.NORTH);

        // Navigation Bar
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBackground(bgMedium);  // Medium beige background for navigation

        String[] navItems = {"Home", "About", "Features", "When It Use?"};

        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setBackground(bgMedium); // Light beige button background
            navButton.setForeground(bgDarker); // Darker gray text for buttons
            navButton.setFocusPainted(false);
            navButton.setFont(new Font("Arial", Font.BOLD, 15));
            navButton.setPreferredSize(new Dimension(120, 40));
            navButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            navButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding

            navButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Other navigation logic can go here
                    JOptionPane.showMessageDialog(frame, item + " clicked!");
                }
            });
            navPanel.add(navButton);
        }
        topPanel.add(navPanel, BorderLayout.SOUTH);

        // Second Section - Slideshow
        JPanel slideShowPanel = new JPanel();
        slideShowPanel.setBackground(bgLight); // Light beige background for slideshow
        imageLabel = new JLabel();
        loadAndDisplayImage(imagePaths[currentImageIndex]);
        slideShowPanel.add(imageLabel);

        // Slideshow Timer
        Timer slideshowTimer = new Timer();
        slideshowTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
                SwingUtilities.invokeLater(() -> loadAndDisplayImage(imagePaths[currentImageIndex]));
            }
        }, 3000, 3000); // Change image every 3 seconds

        // Bottom Section - Help Button
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(bgDarker); // Darker gray background for footer

        JButton helpButton = new JButton("HELP");
        helpButton.setFont(new Font("Arial", Font.BOLD, 150));
        helpButton.setBackground(Color.RED);  // Red background for HELP button
        helpButton.setForeground(Color.WHITE);  // White text for HELP button
        helpButton.setFocusPainted(false);
        helpButton.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding
        helpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement emergency alert logic here
                sendEmergencyAlert(); // Send the alert to the added contacts
            }
        });
        footerPanel.add(helpButton);

        // Adding Sections to Frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(slideShowPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }
    // Show Login Dialog

    // Show Add Contacts Dialog
    private static void showAddContactsDialog(JFrame parentFrame) {
        JDialog contactsDialog = new JDialog(parentFrame, "Add Emergency Contacts", true);
        contactsDialog.setSize(400, 300);
        contactsDialog.setLayout(new FlowLayout());

        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField(15);
        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contact = contactField.getText();
                emergencyContacts.add(contact); // Add contact to the list
                JOptionPane.showMessageDialog(contactsDialog, "Contact added: " + contact);
                contactField.setText(""); // Clear the input field
            }
        });

        contactsDialog.add(contactLabel);
        contactsDialog.add(contactField);
        contactsDialog.add(addButton);

        contactsDialog.setVisible(true);
    }

    // Load and display image
    private static void loadAndDisplayImage(String imagePath) {
        try {
            URL url = new URL(imagePath);
            Image image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image.getScaledInstance(1650, 550, Image.SCALE_SMOOTH));
            imageLabel.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Simulate sending an emergency alert
    private static void sendEmergencyAlert() {
        if (emergencyContacts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No emergency contacts added!");
            return;
        }

        String message = "Emergency alert! Help is needed!";
        // Logic to send the alert (to be implemented)
        for (String contact : emergencyContacts) {
            System.out.println("Sending alert to: " + contact);
        }
        JOptionPane.showMessageDialog(null, "Emergency alert sent to your contacts!");
    }
}

