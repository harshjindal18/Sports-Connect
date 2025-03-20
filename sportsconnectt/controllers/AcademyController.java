package com.example.sportsconnectt.controllers;

import com.example.sportsconnectt.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/academies")
@CrossOrigin
public class AcademyController {

    @Autowired
    private DistanceService distanceService;

    /**
     * Fetch the nearest academies based on a hardcoded location near Dehradun and sport.
     *
     * @param sport Sport category
     * @return List of academies sorted by distance
     */
    @GetMapping("/nearest-academies")
    public ResponseEntity<?> getNearestAcademies(
            @RequestParam String sport) {

        // Hardcoded latitude and longitude near Dehradun
        double lat = 30.3165;
        double lon = 78.0322;

        if (sport == null || sport.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Sport category must not be null or empty."));
        }

        try {
            // Fetch nearest academies using distance service
            List<Map<String, Object>> nearestAcademies =
                    distanceService.findNearestAcademies(lat, lon, sport, 5);
            return ResponseEntity.ok(nearestAcademies);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "An unexpected error occurred.", "details", e.getMessage()));
        }
    }
}
