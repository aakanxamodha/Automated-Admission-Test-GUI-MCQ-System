// Name: Aakanxa Modha
// Reg. No.: 230970031
// Section: A

// Import necessary packages
package page5_scoresheetpage;

//Importing AWT and Swing classes
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ScoreSheetPage {

    // Database connection details
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "system";
    private static final String DB_PASS = "am12";

    // Constructor to create the score sheet page
    public ScoreSheetPage() {
        // Create the main frame
        JFrame frame = new JFrame("Score Sheet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(0, 0, 0)); // Set background color

        // Panel for the heading
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel headingLabel = new JLabel("Score Sheet");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 38)); // Increase heading font size
        headingLabel.setForeground(new Color(255, 255, 255)); // Set text color
        headerPanel.add(headingLabel);
        headerPanel.setBackground(new Color(0, 0, 0)); // Set panel background color
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add padding
        frame.add(headerPanel, BorderLayout.NORTH);

        // Panel for the table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(0, 0, 0)); // Set panel background color

        // Table configuration
        JTable scoreTable = new JTable();
        scoreTable.setFont(new Font("Arial", Font.PLAIN, 20)); // Set the font size for the table
        scoreTable.setForeground(new Color(255, 255, 255)); // Set text color
        scoreTable.setBackground(new Color(0, 0, 0)); // Set background color
        scoreTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 26)); // Set header font
        scoreTable.getTableHeader().setForeground(new Color(255, 255, 255)); // Set header text color
        scoreTable.getTableHeader().setBackground(new Color(0, 0, 0)); // Set header background color

        // Model for the table
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        tableModel.addColumn("Username");
        tableModel.addColumn("Marks");

        // Retrieve data from the database and populate the table
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM ScoreSheet";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String username = rs.getString("Username");
                        int marks = rs.getInt("Marks");
                        tableModel.addRow(new Object[]{username, marks});
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        }

        scoreTable.setModel(tableModel);
        
        // Set row height
        scoreTable.setRowHeight(40); // Adjust the row height as needed

        // Set column widths
        scoreTable.getColumnModel().getColumn(0).setPreferredWidth(200); // Username column
        scoreTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Marks column

        // Center align cell data vertically
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        scoreTable.setDefaultRenderer(String.class, renderer);

        JScrollPane scrollPane = new JScrollPane(scoreTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true); // Make the frame visible
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScoreSheetPage::new);
    }
}
