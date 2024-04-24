//Name: Aakanxa Modha
//Reg. No.: 230970031
//Section: A

// Import necessary packages
package page1_homepage;

// Importing AWT and Swing classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Importing other page classes
import page2_loginpage.MyLoginPage;
import page3_registrationpage.RegistrationPage;
import page4_quizpage.Quiz;

// Class declaration for the home page
public class MyHomePage extends JFrame implements ActionListener {

    // Declaration of menu bar and menu items
    JMenuBar jmb;
    JMenu homePage, optionsMenu, guideMenu;
    JMenuItem login, registration, test, score;

    // Constructor for the home page
    public MyHomePage() {
        // Setting default close operation and size for the frame
    	setTitle("Home Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());

        // Creating and customizing the menu bar
        jmb = new JMenuBar();
        jmb.setBackground(Color.BLACK); // Set menu bar background color
        jmb.setPreferredSize(new Dimension(600, 40)); // Increase menu bar height

        // Creating and customizing menu items
        homePage = new JMenu("Home");
        // Setting menu appearance
        homePage.setForeground(Color.WHITE);
        homePage.setBackground(Color.BLACK);
        homePage.setFont(new Font("Arial", Font.PLAIN, 15));
        optionsMenu = new JMenu("Options");
        optionsMenu.setForeground(Color.WHITE);
        optionsMenu.setBackground(Color.BLACK);
        optionsMenu.setFont(new Font("Arial", Font.PLAIN, 15));
        guideMenu = new JMenu("Guidance");
        guideMenu.setForeground(Color.WHITE);
        guideMenu.setBackground(Color.BLACK);
        guideMenu.setFont(new Font("Arial", Font.PLAIN, 15));

        // Creating and customizing menu items
        login = new JMenuItem("Login");
        // Setting menu item appearance
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);
        login.setFont(new Font("Arial", Font.PLAIN, 18));
        registration = new JMenuItem("Registration");
        registration.setForeground(Color.WHITE);
        registration.setBackground(Color.BLACK);
        registration.setFont(new Font("Arial", Font.PLAIN, 18));
        test = new JMenuItem("Quiz");
        test.setForeground(Color.WHITE);
        test.setBackground(Color.BLACK);
        test.setFont(new Font("Arial", Font.PLAIN, 18));
        score = new JMenuItem("Score sheet");
        score.setForeground(Color.WHITE);
        score.setBackground(Color.BLACK);
        score.setFont(new Font("Arial", Font.PLAIN, 18));

        // Adding action listeners to menu items
        login.addActionListener(this);
        registration.addActionListener(this);
        test.addActionListener(this);
        score.addActionListener(this);

        // Setting mnemonics for keyboard shortcuts
        homePage.setMnemonic(KeyEvent.VK_H);
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        guideMenu.setMnemonic(KeyEvent.VK_G);
        login.setMnemonic(KeyEvent.VK_L);
        registration.setMnemonic(KeyEvent.VK_R);
        test.setMnemonic(KeyEvent.VK_Q);
        score.setMnemonic(KeyEvent.VK_S);

        // Adding menu items to respective menus
        optionsMenu.add(login);
        optionsMenu.add(registration);
        optionsMenu.add(test);
        optionsMenu.add(score);

        // Adding menus to the menu bar
        jmb.add(homePage);
        jmb.add(optionsMenu);
        jmb.add(guideMenu);

        // Creating and customizing the content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 1));
        contentPanel.setBackground(Color.WHITE);

        // Creating and customizing the title label
        JLabel titleLabel = new JLabel("Welcome to Quiz App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        contentPanel.add(titleLabel);

        // Setting border for the content panel
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adding content panel to the center of the frame
        this.add(contentPanel, BorderLayout.CENTER);

        // Setting menu bar for the frame
        this.setJMenuBar(jmb);
        // Making the frame visible
        this.setVisible(true);
    }

    // Action performed method to handle menu item clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handling Home menu item click
        if(e.getActionCommand().equals("Home")){
            new MyHomePage(); // Open a new instance of the home page
        }
        // Handling Login menu item click
        if (e.getActionCommand().equals("Login")) {
            new MyLoginPage(); // Open the login page
        }
        // Handling Quiz menu item click
        else if (e.getActionCommand().equals("Quiz")) {
            new Quiz(); // Open the quiz page
        }
        // Handling Registration menu item click
        else if (e.getActionCommand().equals("Registration")) {
            new RegistrationPage(); // Open the registration page
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        // Invoke the GUI creation in the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            new MyHomePage(); // Create an instance of the home page
        });
    }
}
