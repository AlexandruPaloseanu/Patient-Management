package PatientManagement.BaseClasses;

import java.util.Date;

public class Appointment {

    private int appointment_id;
    private Patient patient;
    private Medic medic;
    private String appointment_date;
    private String appointment_time;
    private String appointment_status;

    public Appointment(int appointment_id, Patient patient, Medic medic, String appointment_date, String appointment_time, String appointment_status) {
        this.appointment_id = appointment_id;
        this.patient = patient;
        this.medic = medic;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.appointment_status = appointment_status;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_status() {
        return appointment_status;
    }

    public void setAppointment_status(String appointment_status) {
        this.appointment_status = appointment_status;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    @Override
    public String toString() {
        return "Appointment {" +
                "appointment_id=" + appointment_id +
                ", patient=" + patient +
                ", medic=" + medic +
                ", appointment_date='" + appointment_date + '\'' +
                ", appointment_time='" + appointment_time + '\'' +
                ", appointment_status='" + appointment_status + '\'' +
                '}';
    }
}
