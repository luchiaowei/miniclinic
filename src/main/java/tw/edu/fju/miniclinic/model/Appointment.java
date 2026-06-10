package tw.edu.fju.miniclinic.model;

import java.time.LocalDate;

public class Appointment {
    private Long apptId;
    private Patient patient;
    private Doctor doctor;
    private LocalDate apptDate;
    private String timeSlot;
    private String status;

    public Appointment() {}

    public Appointment(Long apptId, Patient patient, Doctor doctor, LocalDate apptDate, String timeSlot, String status) {
        this.apptId = apptId;
        this.patient = patient;
        this.doctor = doctor;
        this.apptDate = apptDate;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    public Long getApptId() { return apptId; }
    public void setApptId(Long apptId) { this.apptId = apptId; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public LocalDate getApptDate() { return apptDate; }
    public void setApptDate(LocalDate apptDate) { this.apptDate = apptDate; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
