package PatientManagement.BaseClasses;

import java.util.Date;

public class Patient {

    private int patient_id;
    private String last_name;
    private String first_name;
    private String birth_date;
    private User user;

    public Patient(int patient_id, String last_name, String first_name, String birth_date, User user) {
        this.patient_id = patient_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.user = user;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PatientManagement.BaseClasses.Patient {" +
                "patient_id=" + patient_id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", birth_date=" + birth_date +
                ", user=" + user +
                '}';
    }
}
