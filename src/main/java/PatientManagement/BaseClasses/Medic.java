package PatientManagement.BaseClasses;

import java.util.Date;

public class Medic {

    private int medic_id;
    private String last_name;
    private String first_name;
    private String birth_date;
    private String specialization;
    private User user;

    public Medic(int medic_id, String last_name, String first_name, String birth_date, String specialization, User user) {
        this.medic_id = medic_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birth_date = birth_date;
        this.specialization = specialization;
        this.user = user;
    }

    public int getMedic_id() {
        return medic_id;
    }

    public void setMedic_id(int medic_id) {
        this.medic_id = medic_id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PatientManagement.BaseClasses.Medic {" +
                "medic_id=" + medic_id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", birth_date=" + birth_date +
                ", specialization='" + specialization + '\'' +
                ", user=" + user +
                '}';
    }
}
