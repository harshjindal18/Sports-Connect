package com.example.sportsconnectt.controllers;

import com.example.sportsconnectt.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/distances")
@CrossOrigin
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    /**
     * Fetch the nearest academies using a graph-based approach.
     * Hardcoded location near Dehradun is used.
     *
     * @param sport Selected sport category
     * @return List of academies sorted by distance with additional details
     */
    @GetMapping("/graph-based-nearest-academies")
    public ResponseEntity<List<Map<String, Object>>> getNearestAcademiesGraphBased(@RequestParam String sport) {

        try {
            // Hardcoded latitude and longitude near Dehradun
            double userLat = 30.3165;
            double userLon = 78.0322;

            // Fetch nearest academies using the graph-based approach
            List<Map<String, Object>> nearestAcademies =
                    distanceService.findNearestAcademiesGraphBased(userLat, userLon, sport, 5);

            return ResponseEntity.ok(nearestAcademies);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
