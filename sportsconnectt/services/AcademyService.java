package com.example.sportsconnectt.services;

import com.example.sportsconnectt.models.Academy;
import com.example.sportsconnectt.repositories.AcademyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AcademyService {

    @Autowired
    private AcademyRepository academyRepository;

    // Get all academies
    public List<Academy> getAllAcademies() {
        return academyRepository.findAll();
    }

    // Get academy by ID
    public Academy getAcademyById(int id) {
        Optional<Academy> academy = academyRepository.findById(id);
        return academy.orElse(null);  // Returns null if academy not found
    }

    // Save a new academy
    public Academy saveAcademy(Academy academy) {
        return academyRepository.save(academy);
    }

    // Update an existing academy
    public Academy updateAcademy(int id, Academy academy) {
        if (academyRepository.existsById(id)) {
            academy.setId(id);  // Ensure the ID is preserved during update
            return academyRepository.save(academy);
        }
        return null;  // Return null if the academy does not exist
    }

    // Delete an academy by ID
    public boolean deleteAcademy(int id) {
        if (academyRepository.existsById(id)) {
            academyRepository.deleteById(id);
            return true;  // Academy deleted successfully
        }
        return false;  // Return false if the academy does not exist
    }
    
}
