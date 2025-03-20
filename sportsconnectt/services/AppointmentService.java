package com.example.sportsconnectt.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sportsconnectt.models.Appointment;
import com.example.sportsconnectt.repositories.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DistanceService distanceService;

    public List<Appointment> getAppointmentsForAcademy(Long academyId) {
        return appointmentRepository.findByAcademyId(academyId);
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<String> getAvailableSlots(Long academyId) {
        int academyIdInt = Math.toIntExact(academyId);

        return distanceService.academies.stream()
            .filter(academy -> academy.get("id").equals(academyIdInt))
            .findFirst()
            .map(academy -> (List<String>) academy.get("availableSlots"))
            .orElse(Collections.emptyList());
    }
}
