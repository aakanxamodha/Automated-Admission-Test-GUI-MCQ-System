//Name: Aakanxa Modha
//Reg. No.: 230970031
//Section: A


// Import necessary packages
package page2_loginpage;

// Importing AWT and Swing classes
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import page4_quizpage.Quiz;

// Class declaration for the login page
public class MyLoginPage extends JFrame implements ActionListener {

    // Declaration of username and password fields
    private JTextField username;
    private JPasswordField password;

    // Database connection details
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "am12";

    // Constructor for the login page
    public MyLoginPage() {
        // Setting default close operation, size, and location for the frame
    	setTitle("Login Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Creating panel for login components
        JPanel jp = new JPanel(new GridLayout(3, 2, 10, 10)); // Increased gap between components
        jp.setBackground(Color.BLACK); // Set panel background color
        jp.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add padding

        // Creating fonts for labels, text fields, and buttons
        Font labelFont = new Font("Arial", Font.BOLD, 24); // Create font for labels
        Font textFieldFont = new Font("Arial", Font.PLAIN, 24); // Create font for text fields
        Font buttonFont = new Font("Arial", Font.BOLD, 24); // Create font for buttons

        // Adding username label and text field to the panel
        JLabel lblusername = new JLabel("Username: ");
        lblusername.setForeground(Color.WHITE); // Set label text color
        lblusername.setFont(labelFont); // Set label font size
        username = new JTextField(8); // Set preferred width for username field
        username.setFont(textFieldFont); // Set username field font size
        username.setPreferredSize(new Dimension(200, 40)); // Increase width and height of username field
        jp.add(lblusername);
        jp.add(username);

        // Adding password label and password field to the panel
        JLabel lblpassword = new JLabel("Password: ");
        lblpassword.setForeground(Color.WHITE); // Set label text color
        lblpassword.setFont(labelFont); // Set label font size
        password = new JPasswordField(8); // Set preferred width for password field
        password.setFont(textFieldFont); // Set password field font size
        password.setPreferredSize(new Dimension(200, 40)); // Increase width and height of password field
        jp.add(lblpassword);
        jp.add(password);

        // Adding login button to the panel
        JButton jbtn = new JButton("Login");
        jbtn.addActionListener(this);
        jbtn.setFont(buttonFont); // Increase font size of login button text
        jbtn.setPreferredSize(new Dimension(150, 50)); // Increase size of login button
        jp.add(new JLabel());
        jp.add(jbtn);

        // Adding panel to the frame
        this.add(jp, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.BLACK); // Set frame background color

        // Making the frame visible
        setVisible(true);
    }

    // Action performed method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        // Handling login button click
        if (e.getActionCommand().equals("Login")) {
            // Retrieving username and password entered by the user
            String user = username.getText();
            String pass = new String(password.getPassword());

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
                // Prepare SQL statement to select password for the entered username
                String sql = "SELECT password FROM LoginDetails WHERE username = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, user);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            // Check if the retrieved password matches the entered password
                            String storedPassword = rs.getString("password");
                            if (pass.equals(storedPassword)) {
                                // Login successful
                                JOptionPane.showMessageDialog(this, "Login Successful!");
                                // Clear the fields after successful login
                                username.setText("");
                                password.setText("");
                                new Quiz();
                            } else {
                                // Invalid password
                                JOptionPane.showMessageDialog(this, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Username not found
                            JOptionPane.showMessageDialog(this, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException ex) {
                // Handle database connection errors
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        // Load Oracle JDBC driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            // Exit the program if JDBC driver is not found
            e.printStackTrace();
            System.exit(1);
        }

        // Invoke the GUI creation in the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            new MyLoginPage();
        });
    }
}
