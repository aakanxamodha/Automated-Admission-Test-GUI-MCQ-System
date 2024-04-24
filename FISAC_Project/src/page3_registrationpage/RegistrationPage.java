// Name: Aakanxa Modha
// Reg. No.: 230970031
// Section: A

// Import necessary packages
package page3_registrationpage;

//Importing AWT and Swing classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationPage extends JFrame implements ActionListener {

    // Text fields for user input
    private JTextField nameField, surnameField, usernameField, regNumberField;
    private JPasswordField passwordField;

    // Database connection details
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "am12";

    // Constructor for the registration page
    public RegistrationPage() {
        // Set frame properties
        setTitle("Registration Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel for organizing components
        JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));
        jp.setBackground(Color.BLACK); // Set panel background color
        jp.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add padding

        // Fonts for labels and text fields
        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24);

        // Labels and text fields for user input
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(labelFont);
        nameField = new JTextField();
        nameField.setFont(textFieldFont);
        jp.add(nameLabel);
        jp.add(nameField);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setForeground(Color.WHITE);
        surnameLabel.setFont(labelFont);
        surnameField = new JTextField();
        surnameField.setFont(textFieldFont);
        jp.add(surnameLabel);
        jp.add(surnameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(labelFont);
        usernameField = new JTextField();
        usernameField.setFont(textFieldFont);
        jp.add(usernameLabel);
        jp.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField();
        passwordField.setFont(textFieldFont);
        jp.add(passwordLabel);
        jp.add(passwordField);

        JLabel regNumberLabel = new JLabel("Registration No:");
        regNumberLabel.setForeground(Color.WHITE);
        regNumberLabel.setFont(labelFont);
        regNumberField = new JTextField();
        regNumberField.setFont(textFieldFont);
        jp.add(regNumberLabel);
        jp.add(regNumberField);

        // Button for registration
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        registerButton.setFont(new Font("Arial", Font.BOLD, 24));
        jp.add(new JLabel());
        jp.add(registerButton);

        // Add panel to frame
        add(jp, BorderLayout.CENTER);
        getContentPane().setBackground(Color.BLACK); // Set background color

        setVisible(true);
    }

    // Action performed method to handle button click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) {
            // Retrieve user input
            String name = nameField.getText();
            String surname = surnameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String regNumber = regNumberField.getText();

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                // SQL statement for inserting registration details into the database
                String sql = "INSERT INTO RegistrationDetails (name, surname, username, password, reg_number) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    // Set parameters for the SQL statement
                    stmt.setString(1, name);
                    stmt.setString(2, surname);
                    stmt.setString(3, username);
                    stmt.setString(4, password);
                    stmt.setString(5, regNumber);

                    // Execute the SQL statement
                    int rowsInserted = stmt.executeUpdate();
                    if (rowsInserted > 0) {
                        // Registration successful
                        JOptionPane.showMessageDialog(this, "Registration successful!");
                        clearFields();
                    } else {
                        // Registration failed
                        JOptionPane.showMessageDialog(this, "Registration failed!");
                    }
                }
            } catch (SQLException ex) {
                // Handle database connection error
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to clear input fields after registration
    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        regNumberField.setText("");
    }

    // Main method to start the registration page
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationPage::new);
    }
}
