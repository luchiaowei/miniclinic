package tw.edu.fju.miniclinic.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class AppointmentRepository {

    private final Map<Long, Appointment> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(0);

    public AppointmentRepository(DoctorRepository doctorRepo, PatientRepository patientRepo) {
        // seed from data.sql
        try {
            Patient p1 = patientRepo.findByID("TEST0001").orElse(null);
            Patient p2 = patientRepo.findByID("TEST0002").orElse(null);
            Patient p3 = patientRepo.findByID("TEST0003").orElse(null);
            doctorRepo.findById("D001").ifPresent(d -> save(new Appointment(1L, p1, d, LocalDate.parse("2026-05-01"), "AM", "BOOKED")));
            doctorRepo.findById("D002").ifPresent(d -> save(new Appointment(2L, p2, d, LocalDate.parse("2026-05-01"), "AM", "BOOKED")));
            doctorRepo.findById("D003").ifPresent(d -> save(new Appointment(3L, p3, d, LocalDate.parse("2026-05-02"), "PM", "BOOKED")));
        } catch (Exception ignored) {}
    }

    public List<Appointment> findByApptDate(LocalDate apptDate) {
        List<Appointment> r = new ArrayList<>();
        for (Appointment a : store.values()) {
            if (a.getApptDate() != null && a.getApptDate().equals(apptDate)) r.add(a);
        }
        return r;
    }

    public List<Appointment> findByDoctor(Doctor doctor) {
        List<Appointment> r = new ArrayList<>();
        for (Appointment a : store.values()) {
            if (a.getDoctor() != null && a.getDoctor().getDoctorId().equals(doctor.getDoctorId())) r.add(a);
        }
        return r;
    }

    public List<Appointment> findByPatient(Patient patient) {
        List<Appointment> r = new ArrayList<>();
        for (Appointment a : store.values()) {
            if (a.getPatient() != null && a.getPatient().getChartNo().equals(patient.getChartNo())) r.add(a);
        }
        return r;
    }

    public long countByApptDateBetween(LocalDate from, LocalDate to) {
        return store.values().stream().filter(a -> a.getApptDate() != null && (a.getApptDate().compareTo(from) >= 0 && a.getApptDate().compareTo(to) <= 0)).count();
    }

    public List<Appointment> findByDoctorAndApptDate(Doctor doctor, LocalDate apptDate) {
        List<Appointment> r = new ArrayList<>();
        for (Appointment a : store.values()) {
            if (a.getDoctor() != null && a.getDoctor().getDoctorId().equals(doctor.getDoctorId()) && a.getApptDate() != null && a.getApptDate().equals(apptDate)) r.add(a);
        }
        return r;
    }

    public Optional<Appointment> findById(Long id) { return Optional.ofNullable(store.get(id)); }

    public List<Appointment> findAll() { return new ArrayList<>(store.values()); }

    public Appointment save(Appointment appt) {
        if (appt.getApptId() == null) {
            long id = idGen.incrementAndGet();
            appt.setApptId(id);
            store.put(id, appt);
            return appt;
        }
        store.put(appt.getApptId(), appt);
        return appt;
    }

    public long count() { return store.size(); }
}