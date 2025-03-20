package com.example.sportsconnectt.controllers;

import com.example.sportsconnectt.models.Appointment;
import com.example.sportsconnectt.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@RequestParam Long academyId) {
        List<Appointment> appointments = appointmentService.getAppointmentsForAcademy(academyId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/slots")
    public ResponseEntity<List<String>> getAvailableSlots(@RequestParam Long academyId) {
        List<String> slots = appointmentService.getAvailableSlots(academyId);
        return ResponseEntity.ok(slots);
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {
        try {
            if (appointment.getAcademyId() == null || appointment.getUserName() == null || appointment.getTimeSlot() == null) {
                return ResponseEntity.badRequest().body("Missing required fields: academyId, userName, or timeSlot");
            }

            Appointment savedAppointment = appointmentService.saveAppointment(appointment);
            return ResponseEntity.ok(savedAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to book the appointment. Error: " + e.getMessage());
        }
    }
}
