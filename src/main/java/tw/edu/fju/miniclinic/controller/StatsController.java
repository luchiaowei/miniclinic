package tw.edu.fju.miniclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.edu.fju.miniclinic.model.Appointment;
import tw.edu.fju.miniclinic.model.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StatsController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @GetMapping("/stats")
    public String stats(Model model) {
        List<Appointment> all = appointmentRepo.findAll();
        long total = all.size();
        LocalDate today = LocalDate.now();
        LocalDate from7 = today.minusDays(6);
        long last7 = appointmentRepo.countByApptDateBetween(from7, today);

        Map<String, Long> statusCounts = all.stream()
                .collect(Collectors.groupingBy(a -> a.getStatus() == null ? "UNKNOWN" : a.getStatus(), Collectors.counting()));

        model.addAttribute("total", total);
        model.addAttribute("last7", last7);
        model.addAttribute("statusCounts", statusCounts);
        model.addAttribute("today", today);

        return "stats";
    }
}
