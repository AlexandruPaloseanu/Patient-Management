package PatientManagement.GUI;

import PatientManagement.Services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginScreen extends JFrame {

    Services services = null;

    private JFrame frame = new JFrame();
    private Panel panel = new Panel();


    public LoginScreen(Services services) {

        this.services = services;

        frame.setTitle("Login Screen");
        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        panel.setBounds(0, 0, 300, 400);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        panel.add(userNameLabel);
        userNameLabel.setBounds(5,10,80,50);
        userNameLabel.setBackground(Color.GRAY);

        panel.add(userNameField);
        userNameField.setBounds(85, 10, 200, 50);
        userNameField.setBackground(Color.GRAY);

        panel.add(passwordLabel);
        passwordLabel.setBounds(5,60, 80, 50);
        passwordLabel.setBackground(Color.GRAY);

        panel.add(passwordField);
        passwordField.setBounds(85,60, 200, 50);
        passwordField.setBackground(Color.GRAY);

        panel.add(logInButton);
        logInButton.setBounds(5,120,280,50);
        logInButton.setBackground(Color.ORANGE);

        panel.add(createAccountLabel);
        createAccountLabel.setBounds(5, 260, 280, 50);
        createAccountLabel.setBackground(Color.GRAY);

        panel.add(createAccountButton);
        createAccountButton.setBounds(5,310, 280, 50);
        createAccountButton.setBackground(Color.ORANGE);

        actions();

    }

    private JLabel userNameLabel = new JLabel("Username: ");
    private JTextField userNameField = new JTextField();

    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField passwordField = new JTextField();

    private JButton logInButton = new JButton("Log In");

    private JLabel createAccountLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
    private JButton createAccountButton = new JButton("Create an account");

    private JLabel wrongCombination1 = new JLabel(
            "You have introduced an incorrect username or", SwingConstants.CENTER);

    private JLabel wrongCombination2 = new JLabel(
            "password. Please try again.", SwingConstants.CENTER);


    private String username = null;
    private String password = null;
    private String accountType = null;



    public void actions() {

        logInButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.remove(wrongCombination1);
                panel.remove(wrongCombination2);
                panel.repaint();

                username = userNameField.getText();
                password = passwordField.getText();

                accountType = services.verifyUser(username, password);


                if (Objects.isNull(accountType)) {

                    panel.add(wrongCombination1);
                    wrongCombination1.setBounds(5, 190, 280, 25);
                    wrongCombination1.setBackground(Color.GRAY);
                    wrongCombination1.setForeground(Color.RED);

                    panel.add(wrongCombination2);
                    wrongCombination2.setBounds(5, 215, 280, 25);
                    wrongCombination2.setBackground(Color.GRAY);
                    wrongCombination2.setForeground(Color.RED);

                } else if (accountType.equals("PATIENT")) {

                    frame.dispose();

                    PatientScreen patientScreen = new PatientScreen(services, username);


                } else if (accountType.equals("MEDIC")) {

                    frame.dispose();

                    MedicScreen medicScreen = new MedicScreen(services, username);

                }

            }
        });

        createAccountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                CreateAccountScreen createAccountScreen = new CreateAccountScreen(services);


            }
        });
    }


    public static void main(String[] args) {

        LoginScreen loginScreen = new LoginScreen(new Services());

    }
}
