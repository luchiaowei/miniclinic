package tw.edu.fju.miniclinic.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class DoctorRepository {

    private static final List<Doctor> DOCTORS = Arrays.asList(
        new Doctor("D001", "陳志明醫師", "家庭醫學科", "一般內科、慢性病管理"),
        new Doctor("D002", "林佩君醫師", "內科", "心臟血管、高血壓"),
        new Doctor("D003", "王建華醫師", "復健醫學科", "運動傷害、脊椎復健"),
        new Doctor("D004", "李美玲醫師", "小兒科", "兒童感冒、疫苗接種"),
        new Doctor("D005", "張雅筑醫師", "身心醫學科", "焦慮、失眠、情緒調適")
    );

    static {
        // For testing: set all doctors' password to 'pass1234' so you can login with that
        String hashed = org.springframework.security.crypto.bcrypt.BCrypt.hashpw("pass1234", org.springframework.security.crypto.bcrypt.BCrypt.gensalt());
        DOCTORS.forEach(d -> d.setPasswordHash(hashed));
    }

    public List<Doctor> findAll() {
        return DOCTORS;
    }

    public Optional<Doctor> findById(String doctorId) {
        return DOCTORS.stream()
            .filter(d -> d.getDoctorId().equals(doctorId))
            .findFirst();
    }

    public List<Doctor> findByDepartment(String department) {
        return DOCTORS.stream()
            .filter(d -> d.getDepartment().equals(department))
            .toList();
    }

    public List<String> findAllDepartments() {
        return DOCTORS.stream()
            .map(Doctor::getDepartment)
            .distinct()
            .toList();
    }
}