package tw.edu.fju.miniclinic.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PatientRepository {

    private static final List<Patient> PATIENTS = Arrays.asList(
            new Patient("TEST0001", "測試病患甲", "男", "1985-03-15", "0912-345-678"),
            new Patient("TEST0002", "王小明", "男", "1990-07-22", "0923-456-789"),
            new Patient("TEST0003", "李小華", "男", "1978-11-30", "0934-567-890")
    );

    public Optional<Patient> findByID(String chartNo) {
        return PATIENTS.stream().filter(p -> p.getChartNo().equals(chartNo)).findFirst();
    }

    public List<Patient> findAll() { return PATIENTS; }
}
