package PatientManagement.Repository;

import PatientManagement.BaseClasses.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Repository {

    public static List<User> getUsers () throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");

        List<User> users = new ArrayList<User>();

        while (resultSet.next()) {

            users.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4))
            );
        }

        return users;

    }

    public static List<Medic> getMedics () throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from medics");

        List<Medic> medics = new ArrayList<Medic>();

        while (resultSet.next()) {

            // USER OBJECT

            int user_id = resultSet.getInt(6);
            Statement userStatement = con.createStatement();
            ResultSet userRS = userStatement.executeQuery("select * from users where user_id = " + user_id);

            User user = null;

            if (userRS.next()) {
                user = new User (
                        userRS.getInt(1),
                        userRS.getString(2),
                        userRS.getString(3),
                        userRS.getString(4)
                );
            }

            medics.add(new Medic(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    user) // user object
            );
        }

        return medics;

    }

    public static List<Patient> getPatients () throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from patients");

        List<Patient> patients = new ArrayList<Patient>();

        while (resultSet.next()) {

            // USER OBJECT

            int user_id = resultSet.getInt(5);
            Statement userStatement = con.createStatement();
            ResultSet userRS = userStatement.executeQuery("select * from users where user_id = " + user_id);

            User user = null;

            if (userRS.next()) {
                user = new User (
                        userRS.getInt(1),
                        userRS.getString(2),
                        userRS.getString(3),
                        userRS.getString(4)
                );
            }

            patients.add(new Patient (
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    user // user object
            ));

        }

        return patients;

    }

    public static List<Appointment> getAppointments () throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from appointments");

        List<Appointment> appointments = new ArrayList<Appointment>();

        while (resultSet.next()) {

            // PATIENT OBJECT

            int patient_id = resultSet.getInt(2);
            Statement patientStatement = con.createStatement();
            ResultSet patientRS = patientStatement.executeQuery("select * from patients where patient_id = " + patient_id);

            // USER OBJECT

            User patientUser = null;

            if (patientRS.next()) {

                int user_id = patientRS.getInt(5);
                Statement userStatement = con.createStatement();
                ResultSet userRS = userStatement.executeQuery("select * from users where user_id = " + user_id);

                if (userRS.next()) {
                    patientUser = new User (
                            userRS.getInt(1),
                            userRS.getString(2),
                            userRS.getString(3),
                            userRS.getString(4)
                    );
                }
            }

            // USER OBJECT CREATED

            Patient patient = new Patient (
                    patientRS.getInt(1),
                    patientRS.getString(2),
                    patientRS.getString(3),
                    patientRS.getString(4),
                    patientUser
            );

            // PATIENT OBJECT CREATED
            // MEDIC OBJECT

            int medic_id = resultSet.getInt(3);
            Statement medicStatement = con.createStatement();
            ResultSet medicRS = medicStatement.executeQuery("select * from medics where medic_id = " + medic_id);

            // USER OBJECT

            User medicUser = null;

            if (medicRS.next()) {

                int user_id = medicRS.getInt(6);
                Statement userStatement = con.createStatement();
                ResultSet userRS = userStatement.executeQuery("select * from users where user_id = " + user_id);

                if (userRS.next()) {

                    medicUser = new User (
                            userRS.getInt(1),
                            userRS.getString(2),
                            userRS.getString(3),
                            userRS.getString(4)
                    );
                }
            }

            // USER OBJECT CREATED

            Medic medic = new Medic (
                    medicRS.getInt(1),
                    medicRS.getString(2),
                    medicRS.getString(3),
                    medicRS.getString(4),
                    medicRS.getString(5),
                    medicUser
            );

            // MEDIC OBJECT CREATED

            appointments.add(new Appointment(
                    resultSet.getInt(1),
                    patient,
                    medic,
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));


        }

        return appointments;

    }

    public static List<PatientSheet> getPatientSheets () throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from patient_sheets");

        List<PatientSheet> patientSheets = new ArrayList<PatientSheet>();

        while (resultSet.next()) {

            // PATIENT OBJECT

            int patient_id = resultSet.getInt(2);
            Statement patientStatement = con.createStatement();
            ResultSet patientRS = patientStatement.executeQuery("select * from patients where patient_id = " + patient_id);

            // USER OBJECT

            User patientUser = null;

            if (patientRS.next()) {

                int user_id = patientRS.getInt(5);
                Statement userStatement = con.createStatement();
                ResultSet userRS = userStatement.executeQuery("select * from users where user_id = " + user_id);

                if (userRS.next()) {
                    patientUser = new User(
                            userRS.getInt(1),
                            userRS.getString(2),
                            userRS.getString(3),
                            userRS.getString(4)
                    );
                }
            }

            // USER OBJECT CREATED

            Patient patient = new Patient(
                    patientRS.getInt(1),
                    patientRS.getString(2),
                    patientRS.getString(3),
                    patientRS.getString(4),
                    patientUser
            );

            // PATIENT OBJECT CREATED
            // MEDIC OBJECT

            int medic_id = resultSet.getInt(3);
            Statement medicStatement = con.createStatement();
            ResultSet medicRS = medicStatement.executeQuery("select * from medics where medic_id = " + medic_id);

            // USER OBJECT

            User medicUser = null;

            if (medicRS.next()) {

                int user_id = medicRS.getInt(6);
                Statement userStatement = con.createStatement();
                ResultSet userRS = userStatement.executeQuery("select * from users where user_id = " + user_id);

                if (userRS.next()) {

                    medicUser = new User(
                            userRS.getInt(1),
                            userRS.getString(2),
                            userRS.getString(3),
                            userRS.getString(4)
                    );
                }
            }

            // USER OBJECT CREATED

            Medic medic = new Medic(
                    medicRS.getInt(1),
                    medicRS.getString(2),
                    medicRS.getString(3),
                    medicRS.getString(4),
                    medicRS.getString(5),
                    medicUser
            );

            // MEDIC OBJECT CREATED

            patientSheets.add(new PatientSheet(
                    resultSet.getInt(1),
                    patient,
                    medic,
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));


        }

        return patientSheets;
    }


    public static List<PatientSheet> getMedicPatientSheets (String username) throws SQLException {

        List<PatientSheet> fullList = getPatientSheets();

        List<PatientSheet> medicPatientSheets = new ArrayList<PatientSheet>();

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        if (rs1.next()) {

            int userID = rs1.getInt(1);

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT MEDIC_ID FROM MEDICS WHERE USER_ID = " + userID);

            if (rs2.next()) {

                int medicID = rs2.getInt(1);

                for (int i = 0; i < fullList.size(); i++) {

                    if (medicID == fullList.get(i).getMedic().getMedic_id()) medicPatientSheets.add(fullList.get(i));

                }
            }
        }

        return medicPatientSheets;

    }

    public static List<PatientSheet> getPatientPatientSheets (String username) throws SQLException {

        List<PatientSheet> fullList = getPatientSheets();

        List<PatientSheet> patientPatientSheets = new ArrayList<PatientSheet>();

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        if (rs1.next()) {

            int userID = rs1.getInt(1);

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT PATIENT_ID FROM PATIENTS WHERE USER_ID = " + userID);

            if (rs2.next()) {

                int patientID = rs2.getInt(1);

                for (int i = 0; i < fullList.size(); i++) {

                    if (patientID == fullList.get(i).getPatient().getPatient_id()) patientPatientSheets.add(fullList.get(i));

                }
            }
        }

        return patientPatientSheets;

    }


    public static List<Appointment> getMedicAppointments (String username) throws SQLException {

        List<Appointment> fullList = getAppointments();

        List<Appointment> medicAppointments = new ArrayList<>();

        Connection con = DatabaseUtils.getConnection();

        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        if (rs1.next()) {

            int userID = rs1.getInt(1);

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT MEDIC_ID FROM MEDICS WHERE USER_ID = " + userID);

            if (rs2.next()) {

                int medicID = rs2.getInt(1);

                for (int i = 0; i < fullList.size(); i++) {

                    if (medicID == fullList.get(i).getMedic().getMedic_id()) medicAppointments.add(fullList.get(i));

                }
            }
        }

        return medicAppointments;

    }

    public static List<Appointment> getPatientAppointments (String username) throws SQLException {

        List<Appointment> fullList = getAppointments();

        List<Appointment> patientAppointments = new ArrayList<>();

        Connection con = DatabaseUtils.getConnection();

        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        if (rs1.next()) {

            int userID = rs1.getInt(1);

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT PATIENT_ID FROM PATIENTS WHERE USER_ID = " + userID);

            if (rs2.next()) {

                int patientID = rs2.getInt(1);

                for (int i = 0; i < fullList.size(); i++) {

                    if (patientID == fullList.get(i).getPatient().getPatient_id()) patientAppointments.add(fullList.get(i));

                }
            }
        }

        return patientAppointments;

    }


    public static boolean addPatient (String last_name, String first_name, String birth_date, String username, String password) throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT USER_USERNAME FROM USERS");

        while (rs.next()) {

            String newUser = rs.getString(1);
            if (username.equals(newUser)) return false;
        }

        Statement statement1 = con.createStatement();
        Statement statement2 = con.createStatement();
        Statement statement3 = con.createStatement();

        ResultSet rs1 = statement1.executeQuery("SELECT MAX(USER_ID) FROM USERS");
        rs1.next();
        int userID = rs1.getInt(1) + 1;

        // CREATE USER
        statement1.execute("" +
                "INSERT INTO PATIENT_MANAGEMENT.USERS  VALUES (" + userID + ", \"" + username + "\", \"" + password + "\", \"PATIENT\")");


        ResultSet rs2 = statement2.executeQuery("SELECT MAX(PATIENT_ID) FROM PATIENTS");
        rs2.next();
        int patientID = rs2.getInt(1) + 1;

        statement3.execute("" +
                "INSERT INTO PATIENT_MANAGEMENT.PATIENTS  VALUES (" + patientID + ", \"" + last_name + "\", \""+ first_name + "\", \"" + birth_date + "\", " + userID + ")");

        return true;
    }

    public static boolean addMedic (String last_name, String first_name, String birth_date, String specialization, String username, String password) throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("SELECT USER_USERNAME FROM USERS");

        while (rs.next()) {

            String newUser = rs.getString(1);
            if (username.equals(newUser)) return false;
        }

        Statement statement1 = con.createStatement();
        Statement statement2 = con.createStatement();
        Statement statement3 = con.createStatement();

        ResultSet rs1 = statement1.executeQuery("SELECT MAX(USER_ID) FROM USERS");
        rs1.next();
        int userID = rs1.getInt(1) + 1;

        statement1.execute ("" +
                "INSERT INTO PATIENT_MANAGEMENT.USERS VALUES (" + userID + ", \"" + username + "\", \"" + password + "\", \"MEDIC\")");

        ResultSet rs2 = statement2.executeQuery("SELECT MAX(MEDIC_ID) FROM MEDICS");
        rs2.next();
        int medicID = rs2.getInt(1) + 1;

        statement3.execute(
                "INSERT INTO PATIENT_MANAGEMENT.MEDICS VALUES (" + medicID + ", \"" + last_name + "\", \"" + first_name + "\", \"" + birth_date + "\", \"" + specialization + "\", " + userID + ")");

        return true;
    }


    public static boolean addPatientSheet (String medicName, String patientName, String diagnosis, String treatment) throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        String[] medicStrings = medicName.split(" ");
        String[] patientStrings = patientName.split(" ");
        int medicID = 0;
        int patientID = 0;
        int patientSheetID = 0;

        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MEDIC_ID FROM MEDICS WHERE LAST_NAME = \"" + medicStrings[0] + "\" AND FIRST_NAME = \"" + medicStrings[1] + "\"");

        if (rs1.next()) {

            medicID = rs1.getInt(1);

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT PATIENT_ID FROM PATIENTS WHERE LAST_NAME = \"" + patientStrings[0] + "\" AND FIRST_NAME = \"" + patientStrings[1] + "\"");

            if (rs2.next()) {

                patientID = rs2.getInt(1);

                Statement statement3 = con.createStatement();
                ResultSet rs3 = statement3.executeQuery("SELECT MAX(SHEET_ID) FROM PATIENT_SHEETS");

                if (rs3.next()) {

                    patientSheetID = rs3.getInt(1) + 1;

                    Statement statement4 = con.createStatement();
                    statement4.execute("INSERT INTO PATIENT_SHEETS VALUES (" + patientSheetID + ", " + patientID + ", " + medicID + ", \"" + diagnosis + "\", \"" + treatment + "\")");

                    return true;

                } else return false;

            } else return false;

        } else return false;


    }

    public static void modifyPatientSheet (int sheetID, String diagnosis, String treatment) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();

        if (Objects.isNull(diagnosis) && Objects.isNull(treatment)) {

        } else if (Objects.isNull(diagnosis)) {

            statement.execute("UPDATE PATIENT_SHEETS SET TREATMENT = \"" + treatment + "\" WHERE SHEET_ID = " + sheetID);

        } else if (Objects.isNull(treatment)) {

            statement.execute("UPDATE PATIENT_SHEETS SET DIAGNOSIS = \"" + diagnosis + "\" WHERE SHEET_ID = " + sheetID);

        } else {

            statement.execute("UPDATE PATIENT_SHEETS SET DIAGNOSIS = \"" + diagnosis + "\", TREATMENT = \"" + treatment + "\"  WHERE SHEET_ID = " + sheetID);

        }

    }


    public static void markAppointment (int appID, String status) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();

        ResultSet rs1 = statement1.executeQuery("SELECT APPOINTMENT_ID FROM APPOINTMENTS WHERE APPOINTMENT_ID = " + appID);

        if (rs1.next()) {

            Statement statement2 = con.createStatement();

            statement2.execute("UPDATE APPOINTMENTS SET APPOINTMENT_STATUS = \"" + status + "\" WHERE APPOINTMENT_ID = " + appID);

        }

    }

    public static void modifyAppointment (int appID, String date, String time) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();

        ResultSet rs1 = statement1.executeQuery("SELECT APPOINTMENT_ID FROM APPOINTMENTS WHERE APPOINTMENT_ID = " + appID);

        if (rs1.next()) {

            Statement statement2 = con.createStatement();

            statement2.execute("UPDATE APPOINTMENTS SET APPOINTMENT_DATE = \"" + date + "\", APPOINTMENT_TIME = \"" + time + "\" WHERE APPOINTMENT_ID = " + appID);

        }

    }

    public static void deleteAppointment (int appID) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();

        ResultSet rs1 = statement1.executeQuery("SELECT APPOINTMENT_ID FROM APPOINTMENTS WHERE APPOINTMENT_ID = " + appID);

        if (rs1.next()) {

            Statement statement2 = con.createStatement();

            statement2.execute("DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID = " + appID);

        }

    }

    public static void addAppointment (String patientUsername, int medicID, String appointmentDate, String appointmentTime) throws SQLException {

        Connection con = DatabaseUtils.getConnection();

        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT MAX(APPOINTMENT_ID) FROM APPOINTMENTS");

        int appID = 0;

        if (rs1.next()) {

            appID = rs1.getInt(1) + 1;

            Statement statement2 = con.createStatement();
            ResultSet rs2 = statement2.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + patientUsername + "\"");

            int userID = 0;

            if (rs2.next()) {

                userID = rs2.getInt(1);

                Statement statement3 = con.createStatement();
                ResultSet rs3 = statement3.executeQuery("SELECT PATIENT_ID FROM PATIENTS WHERE USER_ID = " + userID);

                int patientID = 0;

                if (rs3.next()) {

                    patientID = rs3.getInt(1);

                    Statement statement4 = con.createStatement();

                    statement4.execute("INSERT INTO APPOINTMENTS VALUES (" + appID + ", " + patientID + ", " + medicID + ", \"" + appointmentDate + "\", \"" + appointmentTime + "\", \"FALSE\")");

                }

            }
        }

    }


    public static void main(String[] args) throws SQLException {

        // List<PatientManagement.BaseClasses.User> users = getUsers();
        // System.out.println(users);

        // List<PatientManagement.BaseClasses.Medic> medics = getMedics();
        // System.out.println(medics);

        // List<PatientManagement.BaseClasses.Patient> patients = getPatients();
        // System.out.println(patients);

        // List<PatientManagement.BaseClasses.Appointment> appointments = getAppointments();
        // System.out.println(appointments);

        // List<PatientManagement.BaseClasses.PatientSheet> patientSheets = getPatientSheets();
        // System.out.println(patientSheets);

        //addPatientSheet("Paloseanu Alexandru", "Popescu Calin", "test", "test");

        //addAppointment("tudor.campean", 3, "2021-04-01", "10:00:00");

    }
}
