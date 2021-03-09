package PatientManagement.GUI;

import PatientManagement.BaseClasses.Appointment;
import PatientManagement.BaseClasses.Medic;
import PatientManagement.BaseClasses.PatientSheet;
import PatientManagement.Services.Services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientScreen extends JFrame{

    private Services services = null;

    private String username = null;
    private String patientName = null;

    private JFrame frame = new JFrame();
    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    // TOP PANEL

    private JLabel labelPatientName = new JLabel("", SwingConstants.LEFT);

    private JButton buttonViewPatientSheets = new JButton("View your patient sheets");
    private JButton buttonViewAppointments = new JButton("View your appointments");
    private JButton buttonLogOut = new JButton("Log out");

    // TOP PANEL



    // VIEW PATIENT SHEETS

    List<PatientSheet> patientSheetList;
    private JComboBox<String> boxPatientSheets;
    private JLabel labelMedicName = new JLabel("Doctor: ", SwingConstants.CENTER);
    private JTextField textFieldDoctorName = new JTextField();
    private JScrollPane scrollPaneDiagnosis = new JScrollPane();
    private JScrollPane scrollPaneTreatment = new JScrollPane();
    private JLabel labelDiagnosis = new JLabel("Diagnosis", SwingConstants.CENTER);
    private JLabel labelTreatment = new JLabel("Treatment", SwingConstants.CENTER);
    private JTextArea textAreaDiagnosis;
    private JTextArea textAreaTreatment;

    // VIEW PATIENT SHEETS



    // VIEW APPOINTMENTS

    List<Appointment> appointmentList;
    private JScrollPane scrollPaneAppointments;
    private JTable tableAppointments;
    private DefaultTableModel model;

    private JButton buttonAddAppointment = new JButton("Add an appointment");
    private JButton buttonAddAppointment2 = new JButton("Add appointment");
    private JButton buttonModifyAppointment = new JButton("Modify/Delete an appointment");
    private JButton buttonModifyAppointment2 = new JButton("Modify appointment");
    private JButton buttonDeleteAppointment = new JButton("Delete appointment");
    private JButton buttonAppointmentGoBack = new JButton("Go back");

    private JComboBox<String> comboBoxAppointments;
    private JRadioButton radioButtonModifyAppointment = new JRadioButton("Modify Date/Time");
    private JRadioButton radioButtonDeleteAppointment = new JRadioButton("Delete Appointment");
    private ButtonGroup buttonGroup = new ButtonGroup();

    private JLabel labelAppointmentModified = new JLabel("Appointment modified!", SwingConstants.CENTER);
    private JLabel labelAppointmentDeleted = new JLabel("Appointment deleted!", SwingConstants.CENTER);
    private JLabel labelSelectMedic = new JLabel("Select a medic", SwingConstants.CENTER);
    private JLabel labelSelectDate = new JLabel("Select a date", SwingConstants.CENTER);
    private JLabel labelSelectTime = new JLabel("Select the time", SwingConstants.CENTER);
    private JComboBox<String> comboBoxMedics;
    private JComboBox<String> comboBoxDay;
    private JComboBox<String> comboBoxTime;

    private JButton buttonCreateAppointment = new JButton("Create appointment");
    private JLabel labelAppointmentCreated = new JLabel("Appointment created!", SwingConstants.CENTER);
    private JLabel labelAppointmentAlreadyExists = new JLabel("Interval already taken. Try another one.", SwingConstants.CENTER);
    private JLabel labelSelectAppointment = new JLabel("Select an appointment to modify", SwingConstants.CENTER);
    private JLabel labelModificationNotDone = new JLabel("Please choose a valid interval", SwingConstants.CENTER);



    // VIEW APPOINTMENTS


    private void initializeAppointmentsTable () {

        tableAppointments = new JTable(50, 5);
        model = (DefaultTableModel) tableAppointments.getModel();

        tableAppointments.getColumnModel().getColumn(0).setHeaderValue("Nr.");
        tableAppointments.getColumnModel().getColumn(1).setHeaderValue("Medic");
        tableAppointments.getColumnModel().getColumn(2).setHeaderValue("Date");
        tableAppointments.getColumnModel().getColumn(3).setHeaderValue("Time");
        tableAppointments.getColumnModel().getColumn(4).setHeaderValue("Status");

        scrollPaneAppointments = new JScrollPane(tableAppointments);
        leftPanel.add(scrollPaneAppointments);
        scrollPaneAppointments.setBounds(0, 0, 500, 670);


        appointmentList = services.getPatientAppointments(username);

        if (appointmentList.size() < model.getRowCount()) {

            int count = 0;

            for (int i = 0; i < appointmentList.size(); i++) {

                count++;
                String medicName = appointmentList.get(i).getMedic().getLast_name() + " " + appointmentList.get(i).getMedic().getFirst_name();

                model.setValueAt(count, i, 0);
                model.setValueAt(medicName, i, 1);
                model.setValueAt(appointmentList.get(i).getAppointment_date() , i, 2);
                model.setValueAt(appointmentList.get(i).getAppointment_time() , i, 3);
                model.setValueAt(appointmentList.get(i).getAppointment_status() , i, 4);

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

        leftPanel.setBounds(0, 100, 500, 700);
        rightPanel.setBounds(500, 100, 500, 700);

        leftPanel.setBackground(new Color(255, 173, 173));
        rightPanel.setBackground(new Color(255, 173, 173));

        leftPanel.setLayout(null);
        rightPanel.setLayout(null);

        leftPanel.repaint();
        rightPanel.repaint();

        initializeAppointmentsTable();

        rightPanel.add(buttonAddAppointment);
        rightPanel.add(buttonModifyAppointment);

        buttonAddAppointment.setBounds(50, 50, 400, 250);
        buttonModifyAppointment.setBounds(50, 350, 400, 250);

    }


    private void initializeBoxPatientSheets () {

        patientSheetList = services.getPatientPatientSheets(username);

        String[] patientSheets = new String[patientSheetList.size()];

        for (int i = 0; i < patientSheetList.size(); i++) {

            patientSheets[i] = "Sheet " + i + ", Dr. " + patientSheetList.get(i).getMedic().getLast_name() + " " + patientSheetList.get(i).getMedic().getFirst_name();

        }

        boxPatientSheets = new JComboBox<>(patientSheets);

    }


    private void initializeBoxAppointments () {

        String[] appointments = new String[appointmentList.size()];

        for (int i = 0; i < appointmentList.size(); i++) {

            appointments[i] = "Appointment " + (i+1) + ", Dr. " + appointmentList.get(i).getMedic().getLast_name() + " " + appointmentList.get(i).getMedic().getFirst_name() +
            ", " + appointmentList.get(i).getAppointment_date() + ", " + appointmentList.get(i).getAppointment_time() + ", " + appointmentList.get(i).getAppointment_status();

        }

        comboBoxAppointments = new JComboBox<>(appointments);

    }

    private void initializeBoxMedics () {

        List<Medic> medicList = null;

        medicList = services.getMedics();

        String[] medics = new String[medicList.size()];

        for (int i = 0; i < medicList.size(); i++) {

            medics[i] = "" + medicList.get(i).getMedic_id() + ", Dr. " + medicList.get(i).getLast_name() + " " + medicList.get(i).getFirst_name();

        }

        comboBoxMedics = new JComboBox<>(medics);

    }

    private void initializeBoxDays () {

        LocalDate localDate = LocalDate.now();
        List<String> days = new ArrayList<>();

        for (int i = 1; i < 61; i++) {

            String dayOfWeek = String.valueOf(localDate.plusDays(i).getDayOfWeek());

            if ((dayOfWeek.equals("MONDAY")) || (dayOfWeek.equals("TUESDAY")) || (dayOfWeek.equals("WEDNESDAY")) || (dayOfWeek.equals("THURSDAY")) || (dayOfWeek.equals("FRIDAY"))) {

                days.add("" + localDate.plusDays(i).getDayOfWeek() + ", " + localDate.plusDays(i).getDayOfMonth() + " " + localDate.plusDays(i).getMonth());
            }

        }

        String[] daysArray = days.toArray(new String[days.size()]);

        comboBoxDay = new JComboBox<>(daysArray);

    }


    private void initializeBoxTimeAddApp () {

        String selectedDay = (String) comboBoxDay.getSelectedItem();
        String[] selectedDayArray = selectedDay.split(", ");
        String selectedDay2 = selectedDayArray[1];
        String[] selectedDayArray2 = selectedDay2.split(" ");
        String dayOfMonth = selectedDayArray2[0];
        String month = selectedDayArray2[1];

        List<String> timeSlots = new ArrayList<>();

        if (month.equals("JANUARY")) month = "01";
        else if (month.equals("FEBRUARY")) month = "02";
        else if (month.equals("MARCH")) month = "03";
        else if (month.equals("APRIL")) month = "04";
        else if (month.equals("MAY")) month = "05";
        else if (month.equals("JUNE")) month = "06";
        else if (month.equals("JULY")) month = "07";
        else if (month.equals("AUGUST")) month = "08";
        else if (month.equals("SEPTEMBER")) month = "09";
        else if (month.equals("OCTOBER")) month = "10";
        else if (month.equals("NOVEMBER")) month = "11";
        else if (month.equals("DECEMBER")) month = "12";

        String medicSelection = (String) comboBoxMedics.getSelectedItem();
        String[] medicSelectionArray = medicSelection.split(", ");
        int medicID = Integer.parseInt(medicSelectionArray[0]);

        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "10:00:00")) timeSlots.add("10:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "12:00:00")) timeSlots.add("12:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "14:00:00")) timeSlots.add("14:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "16:00:00")) timeSlots.add("16:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "18:00:00")) timeSlots.add("18:00");

        String[] timeSlotsArray = timeSlots.toArray(new String[timeSlots.size()]);

        comboBoxTime = new JComboBox<>(timeSlotsArray);


    }

    private void initializeBoxTimeModifyApp ()  {

        String selectedDay = (String) comboBoxDay.getSelectedItem();
        String[] selectedDayArray = selectedDay.split(", ");
        String selectedDay2 = selectedDayArray[1];
        String[] selectedDayArray2 = selectedDay2.split(" ");
        String dayOfMonth = selectedDayArray2[0];
        String month = selectedDayArray2[1];

        List<String> timeSlots = new ArrayList<>();

        if (month.equals("JANUARY")) month = "01";
        else if (month.equals("FEBRUARY")) month = "02";
        else if (month.equals("MARCH")) month = "03";
        else if (month.equals("APRIL")) month = "04";
        else if (month.equals("MAY")) month = "05";
        else if (month.equals("JUNE")) month = "06";
        else if (month.equals("JULY")) month = "07";
        else if (month.equals("AUGUST")) month = "08";
        else if (month.equals("SEPTEMBER")) month = "09";
        else if (month.equals("OCTOBER")) month = "10";
        else if (month.equals("NOVEMBER")) month = "11";
        else if (month.equals("DECEMBER")) month = "12";

        String selection = (String) comboBoxAppointments.getSelectedItem();
        String[] selectionArray = selection.split(", ");
        String[] selectionArray2 = selectionArray[0].split(" ");
        int appNr = Integer.parseInt(selectionArray2[1]);
        int medicID = appointmentList.get(appNr-1).getMedic().getMedic_id();


        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "10:00:00")) timeSlots.add("10:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "12:00:00")) timeSlots.add("12:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "14:00:00")) timeSlots.add("14:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "16:00:00")) timeSlots.add("16:00");
        if (services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, "18:00:00")) timeSlots.add("18:00");

        String[] timeSlotsArray = timeSlots.toArray(new String[timeSlots.size()]);

        comboBoxTime = new JComboBox<>(timeSlotsArray);


    }


    public void actions () {

        buttonLogOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                LoginScreen loginScreen = new LoginScreen(services);

            }
        });


        buttonViewPatientSheets.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                rightPanel.removeAll();
                leftPanel.removeAll();
                frame.remove(rightPanel);
                frame.remove(leftPanel);

                frame.add(leftPanel);

                leftPanel.setBounds(0, 100, 1000, 700);
                leftPanel.setBackground(new Color(250, 199, 152));
                leftPanel.setLayout(null);
                leftPanel.repaint();

                initializeBoxPatientSheets();

                leftPanel.add(boxPatientSheets);
                boxPatientSheets.setBounds(50, 50, 900, 50);

                leftPanel.add(labelMedicName);
                labelMedicName.setBounds(50, 110, 100, 50);


                leftPanel.add(textFieldDoctorName);
                textFieldDoctorName.setBounds(150, 110, 200, 50);
                textFieldDoctorName.setBackground(Color.LIGHT_GRAY);
                textFieldDoctorName.setEditable(false);


                leftPanel.add(labelDiagnosis);
                labelDiagnosis.setBounds(50, 160, 400, 40);

                textAreaDiagnosis = new JTextArea();
                scrollPaneDiagnosis.getViewport().add(textAreaDiagnosis);
                leftPanel.add(scrollPaneDiagnosis);

                scrollPaneDiagnosis.setBounds(50, 200, 400, 400);
                textAreaDiagnosis.setBackground(Color.LIGHT_GRAY);
                textAreaDiagnosis.setWrapStyleWord(true);
                textAreaDiagnosis.setLineWrap(true);
                textAreaDiagnosis.setEditable(false);
                textAreaDiagnosis.setText("");
                textAreaDiagnosis.isFocusable();


                leftPanel.add(labelTreatment);
                labelTreatment.setBounds(500, 160, 400, 40);

                textAreaTreatment = new JTextArea();
                scrollPaneTreatment.getViewport().add(textAreaTreatment);
                leftPanel.add(scrollPaneTreatment);

                scrollPaneTreatment.setBounds(500, 200, 400, 400);
                textAreaTreatment.setBackground(Color.LIGHT_GRAY);
                textAreaTreatment.setWrapStyleWord(true);
                textAreaTreatment.setLineWrap(true);
                textAreaTreatment.setEditable(false);
                textAreaTreatment.setText("");
                textAreaTreatment.isFocusable();


                boxPatientSheets.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String selection = (String) boxPatientSheets.getSelectedItem();
                        String[] selectionArray = selection.split(", ");
                        String[] selectionArray2 = selectionArray[0].split(" ");
                        int sheetNr = Integer.parseInt(selectionArray2[1]);

                        textFieldDoctorName.setText(patientSheetList.get(sheetNr).getMedic().getLast_name() + " " + patientSheetList.get(sheetNr).getMedic().getFirst_name());
                        textAreaDiagnosis.setText(patientSheetList.get(sheetNr).getDiagnosis());
                        textAreaTreatment.setText(patientSheetList.get(sheetNr).getTreatment());

                    }
                });

            }
        });

        buttonViewAppointments.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                viewAppointments();

                buttonAppointmentGoBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        viewAppointments();

                    }
                });

                buttonAddAppointment.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.removeAll();
                        rightPanel.repaint();
                        rightPanel.setBounds(500, 100, 500, 700);
                        rightPanel.setLayout(null);

                        rightPanel.add(buttonAppointmentGoBack);
                        buttonAppointmentGoBack.setBounds(400, 600, 80, 50);
                        buttonAppointmentGoBack.setBackground(new Color(135, 119, 119));


                        rightPanel.add(labelSelectMedic);
                        labelSelectMedic.setBounds(50, 50, 400, 50);

                        initializeBoxMedics();

                        rightPanel.add(comboBoxMedics);
                        comboBoxMedics.setBounds(50, 100, 400, 50);



                        comboBoxMedics.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.remove(labelAppointmentCreated);
                                rightPanel.remove(labelAppointmentAlreadyExists);

                                if (!Objects.isNull(comboBoxDay)) rightPanel.remove(comboBoxDay);
                                rightPanel.repaint();

                                rightPanel.add(labelSelectDate);
                                labelSelectDate.setBounds(50, 200, 190, 50);

                                initializeBoxDays();

                                rightPanel.add(comboBoxDay);
                                comboBoxDay.setBounds(50, 250, 190, 50);


                                comboBoxDay.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        rightPanel.remove(labelAppointmentCreated);
                                        rightPanel.remove(labelAppointmentAlreadyExists);


                                        if (!Objects.isNull(comboBoxTime)) rightPanel.remove(comboBoxTime);
                                        rightPanel.repaint();


                                        rightPanel.add(labelSelectTime);
                                        labelSelectTime.setBounds(260, 200, 190, 50);


                                        initializeBoxTimeAddApp();

                                        rightPanel.add(comboBoxTime);
                                        comboBoxTime.setBounds(260, 250, 190, 50);


                                        comboBoxTime.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                rightPanel.remove(labelAppointmentCreated);
                                                rightPanel.remove(labelAppointmentAlreadyExists);
                                                rightPanel.add(buttonCreateAppointment);
                                                buttonCreateAppointment.setBounds(50, 350, 400, 100);

                                                rightPanel.add(labelSelectTime);
                                                labelSelectTime.setBounds(260, 200, 190, 50);


                                                rightPanel.remove(comboBoxTime);

                                                initializeBoxTimeAddApp();

                                                rightPanel.add(comboBoxTime);
                                                comboBoxTime.setBounds(260, 250, 190, 50);


                                            }
                                        });

                                    }
                                });


                            }
                        });

                    }
                });

                buttonCreateAppointment.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String medicSelection = (String) comboBoxMedics.getSelectedItem();
                        String[] medicSelectionArray = medicSelection.split(", ");
                        int medicID = Integer.parseInt(medicSelectionArray[0]);

                        String selectedDay = (String) comboBoxDay.getSelectedItem();
                        String[] selectedDayArray = selectedDay.split(", ");
                        String selectedDay2 = selectedDayArray[1];
                        String[] selectedDayArray2 = selectedDay2.split(" ");
                        String dayOfMonth = selectedDayArray2[0];
                        String month = selectedDayArray2[1];

                        if (month.equals("JANUARY")) month = "01";
                        else if (month.equals("FEBRUARY")) month = "02";
                        else if (month.equals("MARCH")) month = "03";
                        else if (month.equals("APRIL")) month = "04";
                        else if (month.equals("MAY")) month = "05";
                        else if (month.equals("JUNE")) month = "06";
                        else if (month.equals("JULY")) month = "07";
                        else if (month.equals("AUGUST")) month = "08";
                        else if (month.equals("SEPTEMBER")) month = "09";
                        else if (month.equals("OCTOBER")) month = "10";
                        else if (month.equals("NOVEMBER")) month = "11";
                        else if (month.equals("DECEMBER")) month = "12";

                        if (!Objects.isNull(comboBoxTime.getSelectedItem())) {

                            String time = (String) comboBoxTime.getSelectedItem() + ":00";

                            String appointmentDate = "2021-" + month + "-" + dayOfMonth;

                            boolean condition = false;

                            if (true) {

                                condition = services.checkDoctorTimeAvailability(medicID, month, dayOfMonth, time);

                            }

                            if (condition) {

                                services.addAppointment(username, medicID, appointmentDate, time);

                                rightPanel.remove(comboBoxTime);
                                rightPanel.repaint();

                                initializeBoxTimeAddApp();

                                rightPanel.add(comboBoxTime);
                                comboBoxTime.setBounds(260, 250, 190, 50);


                                rightPanel.add(labelAppointmentCreated);
                                labelAppointmentCreated.setBounds(50, 450, 400, 50);
                                labelAppointmentCreated.setForeground(Color.GREEN);


                                leftPanel.removeAll();
                                frame.remove(leftPanel);
                                frame.add(leftPanel);
                                leftPanel.setBounds(0, 100, 500, 700);
                                leftPanel.setBackground(new Color(255, 173, 173));
                                leftPanel.setLayout(null);
                                leftPanel.repaint();

                                initializeAppointmentsTable();

                            } else {

                                rightPanel.remove(labelAppointmentCreated);

                                rightPanel.add(labelAppointmentAlreadyExists);
                                labelAppointmentAlreadyExists.setBounds(50, 450, 400, 50);
                                labelAppointmentAlreadyExists.setForeground(Color.RED);

                                rightPanel.repaint();

                            }

                        } else {

                            rightPanel.remove(labelAppointmentCreated);

                            rightPanel.add(labelAppointmentAlreadyExists);
                            labelAppointmentAlreadyExists.setBounds(50, 450, 400, 50);
                            labelAppointmentAlreadyExists.setForeground(Color.RED);

                            rightPanel.repaint();

                        }




                    }
                });



                buttonModifyAppointment.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.removeAll();
                        rightPanel.repaint();
                        rightPanel.setBounds(500, 100, 500, 700);
                        rightPanel.setLayout(null);

                        rightPanel.add(labelSelectAppointment);
                        labelSelectAppointment.setBounds(50, 10, 400, 40);

                        initializeBoxAppointments();
                        rightPanel.add(comboBoxAppointments);
                        comboBoxAppointments.setBounds(50, 50, 400, 50);
                        comboBoxAppointments.setSelectedIndex(0);

                        buttonGroup.add(radioButtonModifyAppointment);
                        buttonGroup.add(radioButtonDeleteAppointment);

                        rightPanel.add(buttonAppointmentGoBack);
                        buttonAppointmentGoBack.setBounds(400, 600, 80, 50);
                        buttonAppointmentGoBack.setBackground(new Color(135, 119, 119));


                        comboBoxAppointments.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.add(radioButtonModifyAppointment);
                                rightPanel.add(radioButtonDeleteAppointment);

                                radioButtonModifyAppointment.setBounds(50, 110, 200, 50);
                                radioButtonDeleteAppointment.setBounds(250, 110, 200, 50);

                            }
                        });

                    }
                });


                radioButtonDeleteAppointment.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (radioButtonDeleteAppointment.isSelected()) {

                            //rightPanel.remove();
                            // REMOVE THE DATE/TIME OBJECTS
                            rightPanel.remove(labelModificationNotDone);
                            rightPanel.remove(buttonModifyAppointment2);
                            rightPanel.remove(labelAppointmentDeleted);
                            rightPanel.remove(labelAppointmentModified);
                            rightPanel.remove(labelSelectDate);
                            rightPanel.remove(labelSelectTime);
                            if (!Objects.isNull(comboBoxTime)) rightPanel.remove(comboBoxTime);
                            if (!Objects.isNull(comboBoxDay)) rightPanel.remove(comboBoxDay);
                            rightPanel.repaint();

                            rightPanel.repaint();

                            rightPanel.add(buttonDeleteAppointment);
                            buttonDeleteAppointment.setBounds(50, 200, 400, 100);


                        }

                    }
                });

                buttonDeleteAppointment.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String selection = (String) comboBoxAppointments.getSelectedItem();
                        String[] selectionArray = selection.split(", ");
                        String selection2 = selectionArray[0];
                        String[] selectionArray2 = selection2.split(" ");
                        int appNr = (Integer.parseInt(selectionArray2[1])) - 1;

                        services.deleteAppointment(appointmentList.get(appNr).getAppointment_id());

                        rightPanel.remove(comboBoxAppointments);


                        leftPanel.removeAll();
                        leftPanel.setLayout(null);
                        leftPanel.repaint();

                        initializeAppointmentsTable();


                        initializeBoxAppointments();
                        rightPanel.add(comboBoxAppointments);
                        comboBoxAppointments.setBounds(50, 50, 400, 50);


                        rightPanel.add(labelAppointmentDeleted);
                        labelAppointmentDeleted.setBounds(50, 300, 400, 50);
                        labelAppointmentDeleted.setForeground(Color.GREEN);

                        rightPanel.remove(buttonDeleteAppointment);
                        rightPanel.repaint();

                    }
                });


                radioButtonModifyAppointment.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(buttonDeleteAppointment);
                        rightPanel.remove(labelAppointmentDeleted);
                        rightPanel.remove(labelAppointmentModified);
                        rightPanel.remove(labelModificationNotDone);
                        rightPanel.repaint();

                        rightPanel.add(buttonModifyAppointment2);
                        buttonModifyAppointment2.setBounds(50, 400, 400, 50);

                        rightPanel.add(labelSelectDate);
                        labelSelectDate.setBounds(50, 200, 190, 50);

                        initializeBoxDays();

                        rightPanel.add(comboBoxDay);
                        comboBoxDay.setBounds(50, 250, 190, 50);


                        comboBoxDay.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                rightPanel.remove(labelModificationNotDone);
                                rightPanel.remove(labelAppointmentModified);
                                rightPanel.remove(labelSelectTime);
                                if (!Objects.isNull(comboBoxTime)) rightPanel.remove(comboBoxTime);


                                rightPanel.add(labelSelectTime);
                                labelSelectTime.setBounds(260, 200, 190, 50);

                                rightPanel.repaint();

                                initializeBoxTimeModifyApp();

                                rightPanel.add(comboBoxTime);
                                comboBoxTime.setBounds(260, 250, 190, 50);

                                comboBoxTime.addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        rightPanel.remove(labelModificationNotDone);
                                        rightPanel.remove(labelAppointmentModified);
                                        if (!Objects.isNull(comboBoxTime)) rightPanel.remove(comboBoxTime);

                                        initializeBoxTimeModifyApp();

                                        rightPanel.add(comboBoxTime);
                                        comboBoxTime.setBounds(260, 250, 190, 50);

                                    }
                                });

                            }
                        });


                    }
                });

                buttonModifyAppointment2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        rightPanel.remove(labelModificationNotDone);
                        rightPanel.repaint();

                        String selection = (String) comboBoxAppointments.getSelectedItem();
                        String[] selectionArray = selection.split(", ");
                        String[] selectionArray2 = selectionArray[0].split(" ");
                        int appNr = (Integer.parseInt(selectionArray2[1])) - 1;
                        int appID = appointmentList.get(appNr).getAppointment_id();

                        String selectedDay = (String) comboBoxDay.getSelectedItem();
                        String[] selectedDayArray = selectedDay.split(", ");
                        String selectedDay2 = selectedDayArray[1];
                        String[] selectedDayArray2 = selectedDay2.split(" ");
                        String dayOfMonth = selectedDayArray2[0];
                        String month = selectedDayArray2[1];

                        if (month.equals("JANUARY")) month = "01";
                        else if (month.equals("FEBRUARY")) month = "02";
                        else if (month.equals("MARCH")) month = "03";
                        else if (month.equals("APRIL")) month = "04";
                        else if (month.equals("MAY")) month = "05";
                        else if (month.equals("JUNE")) month = "06";
                        else if (month.equals("JULY")) month = "07";
                        else if (month.equals("AUGUST")) month = "08";
                        else if (month.equals("SEPTEMBER")) month = "09";
                        else if (month.equals("OCTOBER")) month = "10";
                        else if (month.equals("NOVEMBER")) month = "11";
                        else if (month.equals("DECEMBER")) month = "12";

                        String appointmentDate = "2021-" + month + "-" + dayOfMonth;

                        if (!Objects.isNull(comboBoxTime.getSelectedItem())) {

                            String time = (String) comboBoxTime.getSelectedItem() + ":00";

                            services.modifyAppointment(appID, appointmentDate, time);

                            leftPanel.removeAll();
                            leftPanel.setLayout(null);
                            leftPanel.repaint();

                            initializeAppointmentsTable();

                            if (!Objects.isNull(comboBoxTime)) rightPanel.remove(comboBoxTime);

                            initializeBoxTimeModifyApp();

                            rightPanel.add(comboBoxTime);
                            comboBoxTime.setBounds(260, 250, 190, 50);

                            rightPanel.remove(comboBoxAppointments);

                            initializeBoxAppointments();
                            rightPanel.add(comboBoxAppointments);
                            comboBoxAppointments.setBounds(50, 50, 400, 50);
                            comboBoxAppointments.setSelectedIndex(appNr);

                            rightPanel.add(labelAppointmentModified);
                            labelAppointmentModified.setBounds(50, 550, 400, 50);
                            labelAppointmentModified.setForeground(Color.GREEN);


                            /*
                            rightPanel.remove(radioButtonModifyAppointment);
                            rightPanel.remove(radioButtonDeleteAppointment);
                            rightPanel.remove(labelSelectDate);
                            rightPanel.remove(labelSelectTime);
                            rightPanel.remove(comboBoxDay);
                            rightPanel.remove(comboBoxTime);
                            rightPanel.remove(buttonModifyAppointment2);
                            rightPanel.repaint();

                             */



                        } else {

                            rightPanel.remove(labelAppointmentModified);
                            rightPanel.add(labelModificationNotDone);
                            labelModificationNotDone.setBounds(50, 550, 400, 50);
                            labelModificationNotDone.setForeground(Color.RED);


                        }

                    }
                });

            }
        });


    }


    public PatientScreen (Services services, String username) {

        this.services = services;
        this.username = username;
        this.patientName = services.getPatientName(username);

        frame.setTitle("Patient Screen");
        frame.setSize(1000,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(245, 238, 189, 255));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(topPanel);
        topPanel.setBounds(0, 0, 1000, 100);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color (174, 255, 169));

        topPanel.add(labelPatientName);
        labelPatientName.setBounds(10, 0, 200, 50);
        labelPatientName.setText("Patient: " + patientName);
        labelPatientName.setForeground(Color.BLUE);

        topPanel.add(buttonViewPatientSheets);
        topPanel.add(buttonViewAppointments);
        topPanel.add(buttonLogOut);

        buttonViewPatientSheets.setBounds(0, 50, 500, 50);
        buttonViewPatientSheets.setBackground(new Color(250, 192, 144));

        buttonViewAppointments.setBounds(500, 50, 500, 50);
        buttonViewAppointments.setBackground(new Color(250, 192, 144));

        buttonLogOut.setBounds(890, 5, 90, 40);
        buttonLogOut.setBackground(new Color(135, 119, 119));

        actions();

    }


    public static void main(String[] args)  {


    }
}
