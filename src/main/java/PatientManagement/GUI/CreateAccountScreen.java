package PatientManagement.GUI;

import PatientManagement.Repository.Repository;
import PatientManagement.Services.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateAccountScreen extends JFrame {

    private JFrame frame = new JFrame();
    private Panel panel = new Panel();

    public CreateAccountScreen () {

        frame.setTitle("Create an Account");
        frame.setSize(420,840);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(panel);

        panel.setBounds(0, 0, 420, 840);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        panel.add(informationLabel);
        informationLabel.setBounds(5, 10, 400, 50);
        informationLabel.setBackground(Color.GRAY);
        informationLabel.setForeground(Color.GREEN);
        informationLabel.setOpaque(true);

        panel.add(lastNameLabel);
        lastNameLabel.setBounds(5, 110, 120, 50);

        panel.add(lastNameField);
        lastNameField.setBounds(125, 110, 260, 50);
        lastNameField.setBackground(Color.LIGHT_GRAY);

        panel.add(firstNameLabel);
        firstNameLabel.setBounds(5, 170, 120, 50);

        panel.add(firstNameField);
        firstNameField.setBounds(125, 170, 260, 50);
        firstNameField.setBackground(Color.LIGHT_GRAY);

        panel.add(dateOfBirthLabel);
        dateOfBirthLabel.setBounds(5, 230, 260, 30);

        panel.add(birthDayLabel);
        birthDayLabel.setBounds(5, 260, 50, 30);

        panel.add(birthDayBox);
        birthDayBox.setBounds(5, 290, 50, 30);

        panel.add(birthMonthLabel);
        birthMonthLabel.setBounds(145, 260, 50, 30);

        panel.add(birthMonthBox);
        birthMonthBox.setBounds(145, 290, 50, 30);

        panel.add(birthYearLabel);
        birthYearLabel.setBounds(295, 260, 50, 30);

        panel.add(birthYearBox);

        for (int i = 2021; i > 1899; i--) {
            birthYearBox.addItem(Integer.toString(i));
        }

        birthYearBox.setBounds(295, 290, 70, 30);

        buttonGroup.add(patientButton);
        buttonGroup.add(medicButton);

        panel.add(userTypeLabel);
        userTypeLabel.setBounds(5, 360, 100, 30);

        panel.add(patientButton);
        patientButton.setBounds(115, 360, 100, 30);
        panel.add(medicButton);
        medicButton.setBounds(215, 360, 100, 30);

        panel.add(usernameLabel);
        usernameLabel.setBounds(5, 460, 120, 50);
        panel.add(usernameField);
        usernameField.setBounds(125, 460, 260, 50);
        usernameField.setBackground(Color.LIGHT_GRAY);

        panel.add(passwordLabel);
        passwordLabel.setBounds(5, 510, 120, 50);
        panel.add(passwordField);
        passwordField.setBounds(125, 510, 260, 50);
        passwordField.setBackground(Color.LIGHT_GRAY);

        panel.add(passwordLabel2);
        passwordLabel2.setBounds(5, 560, 120, 50);
        panel.add(passwordField2);
        passwordField2.setBounds(125, 560, 260, 50);
        passwordField2.setBackground(Color.LIGHT_GRAY);

        panel.add(createAccountButton);
        createAccountButton.setBounds(5, 680, 280, 100);
        createAccountButton.setBackground(Color.ORANGE);

        panel.add(returnToLoginButton);
        returnToLoginButton.setBounds(295, 680, 110, 100);
        returnToLoginButton.setBackground(Color.ORANGE);


        actions();

    }

    private JLabel informationLabel = new JLabel("Please fill in the information and create your account.", SwingConstants.CENTER);

    private JTextField lastNameField = new JTextField();
    private JLabel lastNameLabel = new JLabel("Last Name: ");

    private JTextField firstNameField = new JTextField();
    private JLabel firstNameLabel = new JLabel("First Name: ");


    private String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
            "25", "26", "27", "28", "29", "30", "31"};

    private JLabel birthDayLabel = new JLabel("Day", SwingConstants.CENTER);
    private JComboBox<String> birthDayBox = new JComboBox<String>(days);


    private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    private JLabel birthMonthLabel = new JLabel("Month", SwingConstants.CENTER);
    private JComboBox<String> birthMonthBox = new JComboBox<String>(months);


    private JLabel birthYearLabel = new JLabel("Year", SwingConstants.CENTER);
    private JComboBox<String> birthYearBox = new JComboBox<String>();

    private JLabel dateOfBirthLabel = new JLabel("Date of Birth");

    private JLabel userTypeLabel = new JLabel("User Type: ");

    private JRadioButton patientButton = new JRadioButton("Patient");
    private JRadioButton medicButton = new JRadioButton("Medic");
    private ButtonGroup buttonGroup = new ButtonGroup();

    private JLabel specializationLabel = new JLabel("Specialization: ");
    private JTextField specializationField = new JTextField();

    private JLabel usernameLabel = new JLabel("Username: ");
    private JTextField usernameField = new JTextField();

    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField passwordField = new JTextField();

    private JLabel passwordLabel2 = new JLabel("Repeat password: ");
    private JTextField passwordField2 = new JTextField();

    private JButton createAccountButton = new JButton("Create Account");

    private JLabel takenUsernameLabel1 = new JLabel("The desired username is already taken.", SwingConstants.CENTER);
    private JLabel takenUsernameLabel2 = new JLabel("Please try a different one.", SwingConstants.CENTER);

    private JLabel wrongPasswordLabel1 = new JLabel("You have not introduced the same password.", SwingConstants.CENTER);
    private JLabel wrongPasswordLabel2 = new JLabel("Please try again.", SwingConstants.CENTER);

    private JButton returnToLoginButton = new JButton("Login Screen");

    private JLabel fillAllFieldsLabel = new JLabel("Please fill all fields.", SwingConstants.CENTER);
    private JLabel accountCreatedLabel1 = new JLabel("Account created successfully.", SwingConstants.CENTER);
    private JLabel accountCreatedLabel2 = new JLabel("You can now login with your new account.", SwingConstants.CENTER);


    private String lastName = null;
    private String firstName = null;
    private String birthDate = null;
    private String specialization = null;
    private String username = null;
    private String password = null;
    private String password2 = null;

    public void actions () {

        patientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.remove(specializationLabel);
                panel.remove(specializationField);
                panel.repaint();
            }
        });

        medicButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.add(specializationLabel);
                specializationLabel.setBounds(5, 410, 120, 50);

                panel.add(specializationField);
                specializationField.setBounds(125, 410, 260, 50);
                specializationField.setBackground(Color.LIGHT_GRAY);

                panel.repaint();
         }
        });

        createAccountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                panel.remove(fillAllFieldsLabel);
                panel.remove(takenUsernameLabel1);
                panel.remove(takenUsernameLabel2);
                panel.remove(wrongPasswordLabel1);
                panel.remove(wrongPasswordLabel2);
                panel.remove(accountCreatedLabel1);
                panel.remove(accountCreatedLabel2);
                panel.repaint();

                lastName = lastNameField.getText();
                firstName = firstNameField.getText();
                birthDate = "" + birthYearBox.getSelectedItem() + "-" + birthMonthBox.getSelectedItem() + "-" + birthDayBox.getSelectedItem();
                specialization = specializationField.getText();
                username = usernameField.getText();
                password = passwordField.getText();
                password2 = passwordField2.getText();

                int condition = 0;

                if (lastName.isBlank()) condition = 1;
                else if (firstName.isBlank()) condition = 1;
                else if (birthDate.isBlank()) condition = 1;
                else if (username.isBlank()) condition = 1;
                else if (password.isBlank()) condition = 1;
                else if (password2.isBlank()) condition = 1;
                else if ((!patientButton.isSelected()) && (!medicButton.isSelected())) condition = 1;
                else if (medicButton.isSelected()) {
                    if (specialization.isBlank()) condition = 1;
                }

                if (condition == 1) {

                    panel.add(fillAllFieldsLabel);
                    fillAllFieldsLabel.setBounds(5, 630, 400, 30);
                    fillAllFieldsLabel.setForeground(Color.RED);

                } else {

                    if (true) {

                        try {

                            if (Services.checkUsernameAvailability(username)) {

                                if (password.equals(password2)) {

                                    if (patientButton.isSelected()) {

                                        Repository.addPatient(lastName, firstName, birthDate, username, password);

                                        panel.add(accountCreatedLabel1);
                                        panel.add(accountCreatedLabel2);
                                        accountCreatedLabel1.setBounds(5, 610, 400, 30);
                                        accountCreatedLabel2.setBounds(5, 640, 400, 30);
                                        accountCreatedLabel1.setForeground(Color.GREEN);
                                        accountCreatedLabel2.setForeground(Color.GREEN);


                                    } else if (medicButton.isSelected()) {

                                        Repository.addMedic(lastName, firstName, birthDate, specialization, username, password);

                                        panel.add(accountCreatedLabel1);
                                        panel.add(accountCreatedLabel2);
                                        accountCreatedLabel1.setBounds(5, 610, 400, 30);
                                        accountCreatedLabel2.setBounds(5, 640, 400, 30);
                                        accountCreatedLabel1.setForeground(Color.GREEN);
                                        accountCreatedLabel2.setForeground(Color.GREEN);

                                    }


                                } else {

                                    panel.add(wrongPasswordLabel1);
                                    panel.add(wrongPasswordLabel2);
                                    wrongPasswordLabel1.setBounds(5, 610, 400, 30);
                                    wrongPasswordLabel2.setBounds(5, 640, 400, 30);
                                    wrongPasswordLabel1.setForeground(Color.RED);
                                    wrongPasswordLabel2.setForeground(Color.RED);
                                    repaint();

                                }


                            } else {

                                panel.add(takenUsernameLabel1);
                                panel.add(takenUsernameLabel2);
                                takenUsernameLabel1.setBounds(5, 610, 400, 30);
                                takenUsernameLabel2.setBounds(5, 640, 400, 30);
                                takenUsernameLabel1.setForeground(Color.RED);
                                takenUsernameLabel2.setForeground(Color.RED);
                                repaint();

                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                }
            }
        });

        returnToLoginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                LoginScreen loginScreen = new LoginScreen();

            }
        });


    }

    public static void main(String[] args) {

        CreateAccountScreen screen = new CreateAccountScreen();

    }
}
