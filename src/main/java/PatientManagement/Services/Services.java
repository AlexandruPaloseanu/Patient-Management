package PatientManagement.Services;

import PatientManagement.BaseClasses.Appointment;
import PatientManagement.BaseClasses.Medic;
import PatientManagement.BaseClasses.Patient;
import PatientManagement.BaseClasses.PatientSheet;
import PatientManagement.Repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class Services {

    private Repository repository = null;

    public Services () {

        this.repository = new Repository();

    }


    public List<Medic> getMedics () {

        try {

            return repository.getMedics();

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Patient> getPatients () {

        try {


            return repository.getPatients();
        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public List<PatientSheet> getMedicPatientSheets (String username) {

        try {

            return repository.getMedicPatientSheets(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<PatientSheet> getPatientPatientSheets (String username) {

        try {

            return repository.getPatientPatientSheets(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Appointment> getMedicAppointments (String username) {

        try {

            return repository.getMedicAppointments(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public List<Appointment> getPatientAppointments (String username) {

        try {

            return repository.getPatientAppointments(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public boolean addPatient (String last_name, String first_name, String birth_date, String username, String password) {

        try {

            return repository.addPatient(last_name, first_name, birth_date, username, password);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean addMedic (String last_name, String first_name, String birth_date, String specialization, String username, String password) {

        try {

            return repository.addMedic(last_name, first_name, birth_date, specialization, username, password);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public boolean addPatientSheet (String medicName, String patientName, String diagnosis, String treatment) {



        try {

            return repository.addPatientSheet(medicName, patientName, diagnosis, treatment);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;
        }

    }

    public void modifyPatientSheet (int sheetID, String diagnosis, String treatment) {

        try {
            repository.modifyPatientSheet(sheetID, diagnosis, treatment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void markAppointment (int appID, String status) {

        try {
            repository.markAppointment(appID, status);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void modifyAppointment (int appID, String date, String time) {

        try {
            repository.modifyAppointment(appID, date, time);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteAppointment (int appID) {

        try {
            repository.deleteAppointment(appID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addAppointment (String patientUsername, int medicID, String appointmentDate, String appointmentTime) {

        try {
            repository.addAppointment(patientUsername, medicID, appointmentDate, appointmentTime);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String verifyUser (String username, String password) {

        try {

            return repository.verifyUser(username, password);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }

    public boolean checkUsernameAvailability (String username) {

        try {

            return repository.checkUsernameAvailability(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;
        }

    }

    public String getMedicName (String username) {

        try {

            return repository.getMedicName(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public String getPatientName(String username) {

        try {

            return repository.getPatientName(username);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;

        }

    }

    public boolean checkDoctorTimeAvailability (int medicID, String month, String day, String time) {

        try {

            return repository.checkDoctorTimeAvailability(medicID, month, day, time);

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return false;

        }

    }

    public void main(String[] args) {



    }
}
