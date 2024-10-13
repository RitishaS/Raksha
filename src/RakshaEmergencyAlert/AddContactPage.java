package RakshaEmergencyAlert;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class AddContactPage extends JFrame {

    AddContactPage()
    {
        // Create the frame
        JFrame frame = new JFrame("Add Contact - RAKSHA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Define the color palette from the RAKSHA page
        Color bgLight = new Color(0xfffcf2);  // Light beige
        Color bgMedium = new Color(0xccc5b9); // Medium beige
        Color bgDark = new Color(0x403d39);   // Dark gray
        Color bgDarker = new Color(0x252422); // Darker gray
        Color helpRed = new Color(0xeb5e28);  // Red for highlights

        // Get the content pane of the frame
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(bgLight);  // Light beige background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Padding

        // Heading "Mission Raksha"
        JLabel missionLabel = new JLabel("Mission Raksha");
        missionLabel.setFont(new Font("Arial", Font.BOLD, 40));
        missionLabel.setForeground(bgDark);  // Dark gray text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(missionLabel, gbc);

        // Subheading "Add Contact"
        JLabel addContactLabel = new JLabel("Add Contact");
        addContactLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        addContactLabel.setForeground(helpRed);  // Red text
        gbc.gridy = 1;
        contentPane.add(addContactLabel, gbc);

        // First Contact Label and TextField
        JLabel contactLabel1 = new JLabel("Enter Contact Number 1:");
        contactLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
        contactLabel1.setForeground(bgDarker);  // Darker gray text
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPane.add(contactLabel1, gbc);

        JTextField contactField1 = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPane.add(contactField1, gbc);

        // Second Contact Label and TextField
        JLabel contactLabel2 = new JLabel("Enter Contact Number 2:");
        contactLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
        contactLabel2.setForeground(bgDarker);  // Darker gray text
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPane.add(contactLabel2, gbc);

        JTextField contactField2 = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPane.add(contactField2, gbc);

        // Third Contact Label and TextField
        JLabel contactLabel3 = new JLabel("Enter Contact Number 3:");
        contactLabel3.setFont(new Font("Arial", Font.PLAIN, 18));
        contactLabel3.setForeground(bgDarker);  // Darker gray text
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPane.add(contactLabel3, gbc);

        JTextField contactField3 = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPane.add(contactField3, gbc);

        // Add Contact Button
        JButton addButton = new JButton("Add Contacts");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.setBackground(bgMedium);  // Medium beige background
        addButton.setForeground(bgDarker);  // Darker gray text
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(addButton, gbc);

        // Add action listener for adding the contacts
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contactNumber1 = contactField1.getText();
                String contactNumber2 = contactField2.getText();
                String contactNumber3 = contactField3.getText();

                // Check if all fields are filled
                if (contactNumber1.isEmpty() || contactNumber2.isEmpty() || contactNumber3.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter all contact numbers.");
                } else {
                    // Save contacts to file
                    saveContactsToFile(contactNumber1, contactNumber2, contactNumber3);

                    // Show success message
                    JOptionPane.showMessageDialog(frame, "Contacts added successfully:\n" +
                            "Contact 1: " + contactNumber1 + "\n" +
                            "Contact 2: " + contactNumber2 + "\n" +
                            "Contact 3: " + contactNumber3);

                    // Clear the input fields
                    contactField1.setText("");
                    contactField2.setText("");
                    contactField3.setText("");
                }
                addButton.addActionListener(
                        a->{
                            new LoginPage();
                            dispose();
                        }
                );
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to save contacts to a file
    private static void saveContactsToFile(String contact1, String contact2, String contact3) {
        try (FileWriter writer = new FileWriter("contacts.txt", true)) {
            writer.write("Contact 1: " + contact1 + "\n");
            writer.write("Contact 2: " + contact2 + "\n");
            writer.write("Contact 3: " + contact3 + "\n");
            writer.write("-------------------------------\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}