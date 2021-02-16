package PatientManagement.BaseClasses;

public class PatientSheet {

    private int sheet_id;
    private Patient patient;
    private Medic medic;
    private String diagnosis;
    private String treatment;

    public PatientSheet(int sheet_id, Patient patient, Medic medic, String diagnosis, String treatment) {
        this.sheet_id = sheet_id;
        this.patient = patient;
        this.medic = medic;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getSheet_id() {
        return sheet_id;
    }

    public void setSheet_id(int sheet_id) {
        this.sheet_id = sheet_id;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "PatientManagement.BaseClasses.PatientSheet {" +
                "sheet_id=" + sheet_id +
                ", patient=" + patient +
                ", medic=" + medic +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
