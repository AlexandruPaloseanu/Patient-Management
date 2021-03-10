package PatientManagement.GUI;

import PatientManagement.BaseClasses.Appointment;
import PatientManagement.BaseClasses.Patient;
import PatientManagement.BaseClasses.PatientSheet;
import PatientManagement.Services.Services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;


public class MedicScreen extends JFrame{

    private Services services = null;

    private String medicName = null;
    private String username = null;

    private JFrame frame = new JFrame();
    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    // TOP PANEL

    private JLabel doctorNameLabel = new JLabel("", SwingConstants.LEFT);

    private JButton newPatientSheetButton = new JButton("Add new patient sheet");
    private JButton viewPatientSheetsButton = new JButton("View patient sheets");
    private JButton viewAppointmentsButton = new JButton("View appointments");
    private JButton logOutButton = new JButton("Log out");

    // TOP PANEL



    // VIEW PATIENT SHEETS

    private JTable patientSheetTable;
    private DefaultTableModel model;
    private JScrollPane patientSheetScrollPane;

    private JButton addPatientSheetButton = new JButton("Add patient sheet");
    private JButton modifyPatientSheetButton = new JButton("Modify patient sheet");
    private JButton returnToSheetListButton = new JButton("Back");

    private JLabel patientNamesLabel = new JLabel("Select patient");
    private JComboBox<String> patientsComboBox;
    private JLabel diagnosisLabel = new JLabel("Diagnosis:", SwingConstants.CENTER);
    private JLabel treatmentLabel = new JLabel("Treatment:", SwingConstants.CENTER);
    private JTextArea diagnosisTextArea;
    private JTextArea treatmentTextArea;
    private JButton addPatientSheetButton2 = new JButton("Add patient sheet");
    private JScrollPane diagnosisScrollPane = new JScrollPane();
    private JScrollPane treatmentScrollPane = new JScrollPane();
    private JLabel patientSheetAddedLabel = new JLabel("Patient sheet added.", SwingConstants.CENTER);
    private JLabel patientSheetNotAddedLabel = new JLabel("Something went wrong. Please try again.", SwingConstants.CENTER);

    private JCheckBox modifyDiagnosisBox = new JCheckBox("Modify Diagnosis");
    private JCheckBox modifyTreatmentBox = new JCheckBox("Modify Treatment");
    private JTextField modifyDiagnosisField = new JTextField();
    private JTextField modifyTreatmentField = new JTextField();
    private JButton modifyPatientSheetButton2 = new JButton("Modify patient sheet");
    private JLabel modifyDiagnosisLabel = new JLabel("New Diagnosis", SwingConstants.CENTER);
    private JLabel modifyTreatmentLabel = new JLabel("New Treatment", SwingConstants.CENTER);
    private JLabel bothItemsMissingLabel = new JLabel("Please add the new diagnosis and treatment.", SwingConstants.CENTER);
    private JLabel diagnosisMissingLabel = new JLabel("Please add a new diagnosis.", SwingConstants.CENTER);
    private JLabel treatmentMissingLabel = new JLabel("Please add a new treatment.", SwingConstants.CENTER);
    private JLabel successfulModificationLabel = new JLabel("Modificaiton successful!", SwingConstants.CENTER);

    // VIEW PATIENT SHEETS



    // VIEW APPOINTMENTS

    private JTable appointmentsTable;
    private JScrollPane appointmentScrollPane;

    private JLabel markAppointmentsLabel = new JLabel("Mark Appointments", SwingConstants.CENTER);
    private JComboBox<String> appointmentsBox;
    private JCheckBox appointmentDoneBox = new JCheckBox("Done");
    private JCheckBox appointmentNotDoneBox = new JCheckBox("Not done");
    private JButton updateAppointmentButton = new JButton("Update appointment");
    private JLabel noStatusSelectedLabel = new JLabel("Please select a status", SwingConstants.CENTER);
    private JLabel updateSuccessfulLabel = new JLabel("Update successful!", SwingConstants.CENTER);

    // VIEW APPOINTMENTS


    private void showAppointmentsTable () {

        appointmentsTable = new JTable(50, 5);
        model = (DefaultTableModel) appointmentsTable.getModel();

        appointmentsTable.getColumnModel().getColumn(0).setHeaderValue("Nr.");
        appointmentsTable.getColumnModel().getColumn(1).setHeaderValue("Last Name");
        appointmentsTable.getColumnModel().getColumn(2).setHeaderValue("First Name");
        appointmentsTable.getColumnModel().getColumn(3).setHeaderValue("Date/Time");
        appointmentsTable.getColumnModel().getColumn(4).setHeaderValue("Status");

        appointmentScrollPane = new JScrollPane(appointmentsTable);
        leftPanel.add(appointmentScrollPane);
        appointmentScrollPane.setBounds(0, 0, 500, 670);

        List<Appointment> appointmentList;

        appointmentList = services.getMedicAppointments(username);

        if (appointmentList.size() < model.getRowCount()) {

            for (int i = 0; i < appointmentList.size(); i++) {

                model.setValueAt(appointmentList.get(i).getAppointment_id(), i, 0);
                model.setValueAt(appointmentList.get(i).getPatient().getLast_name(), i, 1);
                model.setValueAt(appointmentList.get(i).getPatient().getFirst_name(), i, 2);
                model.setValueAt(appointmentList.get(i).getAppointment_date() + " " + appointmentList.get(i).getAppointment_time(), i, 3);
                model.setValueAt(appointmentList.get(i).getAppointment_status(), i, 4);


            }
        }

    }

    private void viewAppointments () {

        rightPanel.removeAll();
        leftPanel.removeAll();
        frame.remove(rightPanel);
        frame.remove(leftPanel);

        frame.add(leftPanel);
        frame.add(rightPanel);

        leftPanel.setBounds(0,100, 500, 700);
        rightPanel.setBounds(500, 100, 500, 700);

        leftPanel.setBackground(new Color(229, 224, 105));
        rightPanel.setBackground(new Color(229, 224, 105));

        leftPanel.setLayout(null);
        rightPanel.setLayout(null);

        leftPanel.repaint();
        rightPanel.repaint();

        showAppointmentsTable();

    }


    private void initializePatientSheetTable() {

        patientSheetTable = new JTable(100, 4);
        model = (DefaultTableModel) patientSheetTable.getModel();

        patientSheetTable.getColumnModel().getColumn(0).setHeaderValue("Last Name");
        patientSheetTable.getColumnModel().getColumn(1).setHeaderValue("First Name");
        patientSheetTable.getColumnModel().getColumn(2).setHeaderValue("Diagnosis");
        patientSheetTable.getColumnModel().getColumn(3).setHeaderValue("Treatment");

        patientSheetScrollPane = new JScrollPane(patientSheetTable);
        leftPanel.add(patientSheetScrollPane);
        patientSheetScrollPane.setBounds(0, 0, 500, 670);


        List<PatientSheet> patientSheetList;

        patientSheetList = services.getMedicPatientSheets(username);

        if (patientSheetList.size() < model.getRowCount()) {


            for (int i = 0; i < patientSheetList.size(); i++) {

                model.setValueAt(patientSheetList.get(i).getPatient().getLast_name(), i, 0);
                model.setValueAt(patientSheetList.get(i).getPatient().getFirst_name(), i, 1);
                model.setValueAt(patientSheetList.get(i).getDiagnosis(), i, 2);
                model.setValueAt(patientSheetList.get(i).getTreatment(), i, 3);

            }
        }


    }

    private void viewPatientSheets() {

        rightPanel.removeAll();
        leftPanel.removeAll();
        frame.remove(rightPanel);
        frame.remove(leftPanel);

        frame.add(leftPanel);
        frame.add(rightPanel);

        leftPanel.setBounds(0,100, 500, 700);
        rightPanel.setBounds(500, 100, 500, 700);

        leftPanel.setBackground(new Color(229, 224, 105));
        rightPanel.setBackground(new Color(229, 224, 105));

        leftPanel.setLayout(null);
        rightPanel.setLayout(null);

        leftPanel.repaint();
        rightPanel.repaint();

        initializePatientSheetTable();


        rightPanel.add(addPatientSheetButton);
        addPatientSheetButton.setBounds(100, 100, 300, 200);
        addPatientSheetButton.setBackground(new Color(141, 219, 86));

        rightPanel.add(modifyPatientSheetButton);
        modifyPatientSheetButton.setBounds(100, 400, 300, 200);
        modifyPatientSheetButton.setBackground(new Color(141, 219, 86));

    }

    private void getPatientDiagnosisTreatment () {

        List<PatientSheet> patientSheets = null;

        patientSheets = services.getMedicPatientSheets(username);

        String[] patientSheetNames = new String[patientSheets.size()];

        for (int i = 0; i < patientSheets.size(); i++) patientSheetNames[i] = patientSheets.get(i).getSheet_id() + " " +
                patientSheets.get(i).getPatient().getLast_name() + " " + patientSheets.get(i).getPatient().getFirst_name();


        String selection = (String) patientsComboBox.getSelectedItem();
        String[] selectionArray = selection.split(" ");
        int sheetID = Integer.parseInt(selectionArray[0]);


        for (int i = 0; i < patientSheets.size(); i++) {

            if (sheetID == patientSheets.get(i).getSheet_id()) {

                diagnosisTextArea.setText(patientSheets.get(i).getDiagnosis());
                treatmentTextArea.setText(patientSheets.get(i).getTreatment());

                break;

            }

        }

    }


    public void actions () {

        logOutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                LoginScreen loginScreen = new LoginScreen(services);

            }
        });


        viewPatientSheetsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                viewPatientSheets();

                returnToSheetListButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewPatientSheets();
                    }
                });


                addPatientSheetButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.removeAll();
                        rightPanel.repaint();
                        rightPanel.setBounds(500, 100, 500, 700);
                        rightPanel.setLayout(null);

                        List<Patient> patients = null;

                        patients = services.getPatients();

                        String[] patientNames = new String[patients.size()];

                        for (int i = 0; i < patients.size(); i++) patientNames[i] = patients.get(i).getLast_name() + " " + patients.get(i).getFirst_name();

                        patientsComboBox = new JComboBox<String>(patientNames);

                        rightPanel.add(patientNamesLabel);
                        rightPanel.add(patientsComboBox);

                        patientNamesLabel.setBounds(50, 50, 150, 50);
                        patientsComboBox.setBounds(50, 100, 150, 50);


                        rightPanel.add(diagnosisLabel);
                        diagnosisLabel.setBounds(50, 200, 190, 50);

                        diagnosisTextArea = new JTextArea();
                        diagnosisScrollPane.getViewport().add(diagnosisTextArea);
                        rightPanel.add(diagnosisScrollPane);

                        diagnosisScrollPane.setBounds(50, 250, 190, 200);
                        diagnosisTextArea.setBackground(new Color(255, 255, 255));
                        diagnosisTextArea.setToolTipText("Please enter the diagnosis here");
                        diagnosisTextArea.setWrapStyleWord(true);
                        diagnosisTextArea.setLineWrap(true);


                        rightPanel.add(treatmentLabel);
                        treatmentLabel.setBounds(260, 200, 190, 50);

                        treatmentTextArea = new JTextArea();
                        treatmentScrollPane.getViewport().add(treatmentTextArea);
                        rightPanel.add(treatmentScrollPane);

                        treatmentScrollPane.setBounds(260, 250, 190, 200);
                        treatmentTextArea.setBackground(new Color(255, 255, 255));
                        treatmentTextArea.setToolTipText("Please enter the treatment here");
                        treatmentTextArea.setWrapStyleWord(true);
                        treatmentTextArea.setLineWrap(true);


                        rightPanel.add(addPatientSheetButton2);
                        addPatientSheetButton2.setBounds(50, 500, 400, 80);
                        addPatientSheetButton2.setBackground(new Color(79, 205, 36));


                        rightPanel.add(returnToSheetListButton);
                        returnToSheetListButton.setBounds(400, 600, 80, 50);
                        returnToSheetListButton.setBackground(new Color(135, 119, 119));


                    }

                });


                modifyPatientSheetButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.removeAll();
                        rightPanel.setBounds(500, 100, 500, 700);
                        rightPanel.setLayout(null);
                        rightPanel.repaint();

                        List<PatientSheet> patientSheets = null;

                        patientSheets = services.getMedicPatientSheets(username);

                        String[] patientSheetNames = new String[patientSheets.size()];

                        for (int i = 0; i < patientSheets.size(); i++) patientSheetNames[i] = patientSheets.get(i).getSheet_id() + " " +
                                patientSheets.get(i).getPatient().getLast_name() + " " + patientSheets.get(i).getPatient().getFirst_name();

                        patientsComboBox = new JComboBox<String>(patientSheetNames);

                        rightPanel.add(patientNamesLabel);
                        rightPanel.add(patientsComboBox);

                        patientNamesLabel.setBounds(50, 50, 150, 50);
                        patientsComboBox.setBounds(50, 100, 150, 50);
                        patientNamesLabel.setText("Select patient sheet");


                        rightPanel.add(diagnosisLabel);
                        diagnosisLabel.setBounds(50, 200, 190, 50);
                        diagnosisLabel.setText("Current Diagnosis:");


                        diagnosisTextArea = new JTextArea();
                        diagnosisScrollPane = new JScrollPane(diagnosisTextArea);
                        rightPanel.add(diagnosisScrollPane);

                        diagnosisScrollPane.setBounds(50, 250, 190, 50);
                        diagnosisTextArea.setBackground(new Color(199, 197, 197));
                        diagnosisTextArea.setWrapStyleWord(true);
                        diagnosisTextArea.setLineWrap(true);
                        diagnosisTextArea.setEditable(false);
                        diagnosisTextArea.setText("");


                        rightPanel.add(treatmentLabel);
                        treatmentLabel.setBounds(260, 200, 190, 50);
                        treatmentLabel.setText("Current Treatment:");

                        treatmentTextArea = new JTextArea();
                        treatmentScrollPane = new JScrollPane(treatmentTextArea);
                        rightPanel.add(treatmentScrollPane);

                        treatmentScrollPane.setBounds(260, 250, 190, 50);
                        treatmentTextArea.setBackground(new Color(199, 197, 197));
                        treatmentTextArea.setWrapStyleWord(true);
                        treatmentTextArea.setLineWrap(true);
                        treatmentTextArea.setEditable(false);
                        treatmentTextArea.setText("");


                        rightPanel.add(modifyDiagnosisBox);
                        rightPanel.add(modifyTreatmentBox);

                        modifyDiagnosisBox.setBounds(50, 360, 190, 50);
                        modifyTreatmentBox.setBounds(260, 360, 190, 50);

                        modifyDiagnosisBox.setSelected(false);
                        modifyTreatmentBox.setSelected(false);

                        rightPanel.add(returnToSheetListButton);
                        returnToSheetListButton.setBounds(400, 600, 80, 50);
                        returnToSheetListButton.setBackground(new Color(135, 119, 119));

                        modifyDiagnosisField.setText("");
                        modifyTreatmentField.setText("");

                        patientsComboBox.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                getPatientDiagnosisTreatment();

                            }
                        });

                    }
                });


                modifyDiagnosisBox.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (modifyDiagnosisBox.isSelected()) {

                            rightPanel.add(modifyDiagnosisLabel);
                            modifyDiagnosisLabel.setBounds(50, 410, 190, 50);

                            rightPanel.add(modifyDiagnosisField);
                            modifyDiagnosisField.setBounds(50, 460, 190, 50);
                            modifyDiagnosisField.setBackground(Color.lightGray);


                            if (!modifyTreatmentBox.isSelected()) {
                                rightPanel.add(modifyPatientSheetButton2);
                                modifyPatientSheetButton2.setBounds(50, 550, 400, 40);
                            }

                            rightPanel.repaint();

                        } else {

                            rightPanel.remove(modifyDiagnosisLabel);
                            rightPanel.remove(modifyDiagnosisField);

                            if (!modifyTreatmentBox.isSelected()) rightPanel.remove(modifyPatientSheetButton2);

                            rightPanel.repaint();

                        }
                    }
                });

                modifyTreatmentBox.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (modifyTreatmentBox.isSelected()) {

                            rightPanel.add(modifyTreatmentLabel);
                            modifyTreatmentLabel.setBounds(260, 410, 190, 50);

                            rightPanel.add(modifyTreatmentField);
                            modifyTreatmentField.setBounds(260, 460, 190, 50);
                            modifyTreatmentField.setBackground(Color.lightGray);

                            if (!modifyDiagnosisBox.isSelected()) {
                                rightPanel.add(modifyPatientSheetButton2);
                                modifyPatientSheetButton2.setBounds(50, 550, 400, 40);
                            }

                            rightPanel.repaint();

                        } else {

                            rightPanel.remove(modifyTreatmentLabel);
                            rightPanel.remove(modifyTreatmentField);

                            if (!modifyDiagnosisBox.isSelected()) rightPanel.remove(modifyPatientSheetButton2);

                            rightPanel.repaint();
                        }

                    }
                });

                modifyPatientSheetButton2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(bothItemsMissingLabel);
                        rightPanel.remove(diagnosisMissingLabel);
                        rightPanel.remove(treatmentMissingLabel);
                        rightPanel.remove(successfulModificationLabel);
                        rightPanel.repaint();

                        String selectedPatientSheet = (String) patientsComboBox.getSelectedItem();
                        String[] selectedPatientSheetArray = selectedPatientSheet.split(" ");

                        int sheetID = Integer.parseInt(selectedPatientSheetArray[0]);
                        String newDiagnosis = modifyDiagnosisField.getText();
                        String newTreatment = modifyTreatmentField.getText();

                        System.out.println(newDiagnosis);
                        System.out.println(newTreatment);


                        if ((modifyDiagnosisBox.isSelected()) && (modifyTreatmentBox.isSelected())) {

                            if (((Objects.isNull(newDiagnosis)) && (Objects.isNull(newTreatment))) ||
                                    ((newDiagnosis.isBlank()) && (newTreatment.isBlank()))) {

                                rightPanel.add(bothItemsMissingLabel);
                                bothItemsMissingLabel.setBounds(50, 600, 350, 50);
                                bothItemsMissingLabel.setForeground(Color.RED);
                                rightPanel.repaint();

                            } else if ((Objects.isNull(newDiagnosis)) || (newDiagnosis.isBlank())) {

                                rightPanel.add(diagnosisMissingLabel);
                                diagnosisMissingLabel.setBounds(50, 600, 350, 50);
                                diagnosisMissingLabel.setForeground(Color.RED);
                                rightPanel.repaint();

                            } else if ((Objects.isNull(newTreatment)) || (newTreatment.isBlank())) {

                                rightPanel.add(treatmentMissingLabel);
                                treatmentMissingLabel.setBounds(50, 600, 350, 50);
                                treatmentMissingLabel.setForeground(Color.RED);
                                rightPanel.repaint();

                            } else {

                                services.modifyPatientSheet(sheetID, newDiagnosis, newTreatment);

                                rightPanel.add(successfulModificationLabel);
                                successfulModificationLabel.setBounds(50, 600, 350, 50);
                                successfulModificationLabel.setForeground(Color.GREEN);
                                diagnosisTextArea.setText(newDiagnosis);
                                treatmentTextArea.setText(newTreatment);

                            }

                        } else if (modifyDiagnosisBox.isSelected()) {

                            if ((Objects.isNull(newDiagnosis)) || (newDiagnosis.isBlank())) {

                                rightPanel.add(diagnosisMissingLabel);
                                diagnosisMissingLabel.setBounds(50, 600, 350, 50);
                                diagnosisMissingLabel.setForeground(Color.RED);
                                rightPanel.repaint();

                            } else {

                                newTreatment = null;

                                services.modifyPatientSheet(sheetID, newDiagnosis, newTreatment);

                                rightPanel.add(successfulModificationLabel);
                                successfulModificationLabel.setBounds(50, 600, 350, 50);
                                successfulModificationLabel.setForeground(Color.GREEN);
                                diagnosisTextArea.setText(newDiagnosis);

                            }

                        } else if (modifyTreatmentBox.isSelected()) {

                            if ((Objects.isNull(newTreatment)) || (newTreatment.isBlank())) {

                                rightPanel.add(treatmentMissingLabel);
                                treatmentMissingLabel.setBounds(50, 600, 350, 50);
                                treatmentMissingLabel.setForeground(Color.RED);
                                rightPanel.repaint();

                            } else {

                                newDiagnosis = null;

                                services.modifyPatientSheet(sheetID, newDiagnosis, newTreatment);

                                rightPanel.add(successfulModificationLabel);
                                successfulModificationLabel.setBounds(50, 600, 350, 50);
                                successfulModificationLabel.setForeground(Color.GREEN);
                                treatmentTextArea.setText(newTreatment);

                            }
                        }

                        leftPanel.removeAll();
                        leftPanel.setBounds(0,100, 500, 700);
                        leftPanel.setBackground(new Color(229, 224, 105));
                        leftPanel.setLayout(null);
                        rightPanel.setLayout(null);
                        leftPanel.repaint();

                        initializePatientSheetTable();

                    }
                });


            }

        });

        addPatientSheetButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.remove(patientSheetAddedLabel);
                rightPanel.remove(patientSheetNotAddedLabel);
                rightPanel.setBounds(500, 100, 500, 700);
                rightPanel.setLayout(null);
                rightPanel.repaint();

                String[] medicNameArray = medicName.split(" ");
                String medicNameFinal = medicNameArray[1] + " " + medicNameArray[2];
                String patientName = (String) patientsComboBox.getSelectedItem();
                String diagnosis = diagnosisTextArea.getText();
                String treatment = treatmentTextArea.getText();


                if (services.addPatientSheet(medicNameFinal, patientName, diagnosis, treatment)) {

                    rightPanel.add(patientSheetAddedLabel);
                    patientSheetAddedLabel.setBounds(50, 600, 350, 50);
                    patientSheetAddedLabel.setForeground(Color.GREEN);

                } else {

                    rightPanel.add(patientSheetNotAddedLabel);
                    patientSheetNotAddedLabel.setBounds(50, 600, 350, 50);
                    patientSheetNotAddedLabel.setForeground(Color.RED);

                }


                leftPanel.removeAll();
                leftPanel.setBounds(0,100, 500, 700);
                leftPanel.setBackground(new Color(229, 224, 105));
                leftPanel.setLayout(null);
                rightPanel.setLayout(null);
                leftPanel.repaint();

                initializePatientSheetTable();
            }
        });


        viewAppointmentsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                viewAppointments();

                rightPanel.add(markAppointmentsLabel);
                markAppointmentsLabel.setBounds(25, 50, 450, 50);
                markAppointmentsLabel.setForeground(Color.BLACK);
                markAppointmentsLabel.setBackground(Color.lightGray);
                markAppointmentsLabel.setOpaque(true);

                List<Appointment> appointmentList = null;

                appointmentList = services.getMedicAppointments(username);

                String[] appointments = new String[appointmentList.size()];

                for (int i = 0; i < appointmentList.size(); i++) {

                    String appID = "" + appointmentList.get(i).getAppointment_id();
                    String lastName = appointmentList.get(i).getPatient().getLast_name();
                    String firstName = appointmentList.get(i).getPatient().getFirst_name();
                    String date = appointmentList.get(i).getAppointment_date() + " " + appointmentList.get(i).getAppointment_time();


                    appointments[i] =  appID + ", " + lastName + " " + firstName + ", " + date;

                }

                appointmentsBox = new JComboBox<>(appointments);

                rightPanel.add(appointmentsBox);
                appointmentsBox.setBounds(25, 120, 450, 80);

                rightPanel.add(appointmentDoneBox);
                rightPanel.add(appointmentNotDoneBox);

                appointmentDoneBox.setBounds(25, 250, 225, 50);
                appointmentNotDoneBox.setBounds(250, 250, 225, 50);

                rightPanel.add(updateAppointmentButton);
                updateAppointmentButton.setBounds(25, 350, 450, 100);

                appointmentDoneBox.setSelected(false);
                appointmentNotDoneBox.setSelected(false);


                appointmentsBox.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String selection = (String) appointmentsBox.getSelectedItem();
                        String[] selectionArray = selection.split(", ");

                        List<Appointment> appointmentList2 = null;

                        appointmentList2 = services.getMedicAppointments(username);

                        String status = null;

                        for (int i = 0; i < appointmentList2.size(); i++) {

                            if (Integer.parseInt(selectionArray[0]) == appointmentList2.get(i).getAppointment_id()) {
                                status = appointmentList2.get(i).getAppointment_status();
                                break;
                            }

                        }

                        if (status.equals("TRUE")) {

                            appointmentDoneBox.setSelected(true);
                            appointmentNotDoneBox.setSelected(false);

                        } else if (status.equals("FALSE")) {

                            appointmentDoneBox.setSelected(false);
                            appointmentNotDoneBox.setSelected(true);

                        }


                    }
                });

                appointmentDoneBox.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (appointmentDoneBox.isSelected()) appointmentNotDoneBox.setSelected(false);

                    }
                });

                appointmentNotDoneBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (appointmentNotDoneBox.isSelected()) appointmentDoneBox.setSelected(false);
                    }
                });

                updateAppointmentButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if ((!appointmentDoneBox.isSelected()) && (!appointmentNotDoneBox.isSelected())) {

                            rightPanel.remove(noStatusSelectedLabel);
                            rightPanel.remove(updateSuccessfulLabel);
                            rightPanel.repaint();

                            rightPanel.add(noStatusSelectedLabel);
                            noStatusSelectedLabel.setBounds(25, 450, 450, 50);
                            noStatusSelectedLabel.setForeground(Color.RED);

                        } else  {

                            rightPanel.remove(noStatusSelectedLabel);
                            rightPanel.remove(updateSuccessfulLabel);
                            rightPanel.repaint();

                            rightPanel.add(updateSuccessfulLabel);
                            updateSuccessfulLabel.setBounds(25, 450, 450, 50);
                            updateSuccessfulLabel.setForeground(Color.GREEN);

                            String selection = (String) appointmentsBox.getSelectedItem();
                            String[] selectionArray = selection.split(", ");

                            int appID = Integer.parseInt(selectionArray[0]);

                            if (appointmentDoneBox.isSelected()) {

                                services.markAppointment(appID, "TRUE");

                                for (int i = 0; i < appointments.length; i++) {

                                    String[] selectionArray2 = appointments[i].split(", ");

                                    if (selectionArray2[0].equals(String.valueOf(appID))) {
                                        appointments[i] = selectionArray2[0] + ", " + selectionArray2[1] + ", " + selectionArray2[2] + ", TRUE";
                                        break;
                                    }
                                }

                            } else if (appointmentNotDoneBox.isSelected()) {

                                services.markAppointment(appID, "FALSE");

                                for (int i = 0; i < appointments.length; i++) {

                                    String[] selectionArray2 = appointments[i].split(", ");

                                    if (selectionArray2[0].equals(String.valueOf(appID))) {
                                        appointments[i] = selectionArray2[0] + ", " + selectionArray2[1] + ", " + selectionArray2[2] + ", FALSE";
                                        break;
                                    }
                                }

                            }
                        }

                        leftPanel.removeAll();
                        leftPanel.setBounds(0,100, 500, 700);
                        leftPanel.setBackground(new Color(229, 224, 105));
                        leftPanel.setLayout(null);
                        leftPanel.repaint();

                        showAppointmentsTable();

                    }
                });



            }
        });


    }


    public MedicScreen (Services services, String username) {


        this.services = services;
        this.username = username;
        this.medicName = services.getMedicName(username);

        frame.setTitle("Medic Screen");
        frame.setSize(1000,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(219, 192, 144, 255));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(topPanel);
        topPanel.setBounds(0, 0, 1000, 100);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(219, 192, 144, 255));

        topPanel.add(doctorNameLabel);

        doctorNameLabel.setBounds(10, 0, 200, 50);
        doctorNameLabel.setText(medicName);
        doctorNameLabel.setForeground(Color.BLUE);

        topPanel.add(viewPatientSheetsButton);
        topPanel.add(viewAppointmentsButton);
        topPanel.add(logOutButton);

        viewPatientSheetsButton.setBounds(0, 50, 500, 50);
        viewPatientSheetsButton.setBackground(new Color(250, 192, 144));

        viewAppointmentsButton.setBounds(500, 50, 500, 50);
        viewAppointmentsButton.setBackground(new Color(250, 192, 144));

        logOutButton.setBounds(890, 5, 90, 40);
        logOutButton.setBackground(new Color(135, 119, 119));

        actions();

    }


    public static void main(String[] args) {

        new MedicScreen(new Services(), "alexandru.paloseanu");


    }
}
