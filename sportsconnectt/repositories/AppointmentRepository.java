package com.example.sportsconnectt.repositories;

import com.example.sportsconnectt.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Define a query method to find appointments by academyId
    List<Appointment> findByAcademyId(Long academyId);
}
