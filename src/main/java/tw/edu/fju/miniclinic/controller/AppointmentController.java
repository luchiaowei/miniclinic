package tw.edu.fju.miniclinic.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import tw.edu.fju.miniclinic.model.AppointmentForm;
import tw.edu.fju.miniclinic.model.DoctorRepository;
import tw.edu.fju.miniclinic.model.PatientRepository;

@Controller
public class AppointmentController {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @GetMapping("/appointment/new")
    public String newAppointmentForm(Model model) {
        model.addAttribute("form", new AppointmentForm());
        model.addAttribute("doctors", doctorRepo.findAll());
        return "appointment-new";
    }

    @PostMapping("/appointment/new")
    public String submitAppointment(@ModelAttribute AppointmentForm form, Model model) {

        if (form.getChartNo() == null || form.getChartNo().isBlank() || form.getDoctorId() == null || form.getDoctorId().isBlank() || form.getApptDate() == null || form.getApptDate().isBlank() || form.getTimeSlot() == null || form.getTimeSlot().isBlank()) {
            model.addAttribute("form", form);
            model.addAttribute("doctors", doctorRepo.findAll());
            model.addAttribute("errorMessage", "請完整填寫表單");
            return "appointment-new";
        }

        var patient = patientRepo.findByID(form.getChartNo()).orElse(null);
        var doctor = doctorRepo.findById(form.getDoctorId()).orElse(null);

        if (patient == null || doctor == null) {
            model.addAttribute("errorMessage", "病歷號或醫師編號無效");
            model.addAttribute("form", form);
            model.addAttribute("doctors", doctorRepo.findAll());
            return "appointment-new";
        }

        model.addAttribute("form", form);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patient", patient);

        return "appointment-result";
    }
}
