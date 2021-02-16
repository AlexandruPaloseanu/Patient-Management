package PatientManagement.Services;

import PatientManagement.Repository.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Services {

    public static String verifyUser (String username, String password) throws SQLException {
        // IF CREDENTIALS ARE CORRECT - RETURN PATIENT/MEDIC
        // IF CREDENTIALS ARE INCORRECT - RETURN NULL

        Connection con = DatabaseUtils.getConnection();

        Statement statement1 = con.createStatement();
        ResultSet resultSet = statement1.executeQuery("SELECT USER_USERNAME, USER_PASSWORD, USER_TYPE FROM USERS");

        String userSearch = null;
        String passwordSearch = null;
        String accountType = null;

        while (resultSet.next()) {

            userSearch = resultSet.getString(1);
            passwordSearch = resultSet.getString(2);

            if ((username.equals(userSearch)) && (password.equals(passwordSearch))) {

                accountType = resultSet.getString(3);
                return accountType;

            }

        }

        return accountType;

    }

    public static boolean checkUsernameAvailability (String username) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT USER_USERNAME FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        if(rs.next()) {

            String searchUsername = rs.getString(1);

            if(username.equals(searchUsername)) return false;
            else return true;

        } else return true;


    }

    public static String getMedicName (String username) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        int userID = 0;

        if (rs1.next()) userID = rs1.getInt(1);

        Statement statement2 = con.createStatement();
        ResultSet rs2 = statement2.executeQuery("SELECT LAST_NAME, FIRST_NAME FROM MEDICS WHERE USER_ID = " + userID);

        String medicName = null;

        if (rs2.next()) medicName = "Dr. " + rs2.getString(1) + " " + rs2.getString(2);

        return medicName;

    }

    public static String getPatientName(String username) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT USER_ID FROM USERS WHERE USER_USERNAME = \"" + username + "\"");

        int userID = 0;

        if (rs1.next()) userID = rs1.getInt(1);

        Statement statement2 = con.createStatement();
        ResultSet rs2 = statement2.executeQuery("SELECT LAST_NAME, FIRST_NAME FROM PATIENTS WHERE USER_ID = " + userID);

        String patientName = null;

        if (rs2.next()) patientName = rs2.getString(1) + " " + rs2.getString(2);

        return patientName;

    }

    public static boolean checkDoctorTimeAvailability (int medicID, String month, String day, String time) throws SQLException {

        Connection con = DatabaseUtils.getConnection();
        Statement statement1 = con.createStatement();
        ResultSet rs1 = statement1.executeQuery("SELECT APPOINTMENT_ID FROM APPOINTMENTS WHERE MEDIC_ID = " + medicID +
                " AND APPOINTMENT_DATE = \"2021-" + month + "-" + day +"\" AND APPOINTMENT_TIME = \"" + time + "\"");

        int appID = 0;

        if (rs1.next()) appID = 1;

        if (appID == 0) return true;
        else return false;

    }


    public static void main(String[] args) throws SQLException {

        // System.out.println(addPatient("Popa", "Florin", "2020-01-07", "testUser3", "testPassword2"));
        // System.out.println(addMedic("NumeFamilie1", "Prenume1", "2020-01-08", "Cardiology", "username3", "password1"));
        // addMedic("NumeFamilie2", "Prenume2", "2020-01-08", "Pneumology", "username2", "password2");

        // System.out.println(verifyUser("alexandru.paloseanu", "alexandruNR1"));
        // System.out.println(verifyUser("alexandru.paloseanu", "alexandruNR2"));
        // System.out.println(verifyUser("alexandru.paloseanu1", "alexandruNR1"));

        //System.out.println(checkUsernameAvailability("alexandru.paloseanu"));
        //System.out.println(checkUsernameAvailability("userNou"));
        //System.out.println(checkUsernameAvailability("maria.moraru"));

        //System.out.println(checkDoctorTimeAvailability(3, "04", "01", "11:00:00"));


    }
}
