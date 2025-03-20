package com.example.sportsconnectt.services;

import com.example.sportsconnectt.utils.DistanceCalculator;
import com.example.sportsconnectt.utils.Graph;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistanceService {

    // Hardcoded list of academies
    final List<Map<String, Object>> academies = Arrays.asList(
        // Example data (Add other academies as needed)
        // Cricket Academies
        Map.of("id", 1, "name", "Blue Cricket Academy", "sportsCategory", "Cricket", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 2, "name", "Spin Masters Cricket", "sportsCategory", "Cricket", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("10:30 AM - 12:30 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 3, "name", "Delhi Daredevils Academy", "sportsCategory", "Cricket", "latitude", 28.5355, "longitude", 77.3910, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 4, "name", "Kolkata Cricket Club", "sportsCategory", "Cricket", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("12:00 PM - 2:00 PM", "2:30 PM - 4:00 PM")),
        Map.of("id", 5, "name", "Chennai Strikers Academy", "sportsCategory", "Cricket", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("10:00 AM - 12:00 PM", "1:00 PM - 3:00 PM")),

        // Football Academies
        Map.of("id", 6, "name", "Goal Kickers Football", "sportsCategory", "Football", "latitude", 12.9716, "longitude", 77.5946, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 7, "name", "Mumbai Soccer Academy", "sportsCategory", "Football", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 8, "name", "Delhi United Academy", "sportsCategory", "Football", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("12:00 PM - 2:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 9, "name", "Kolkata Football Hub", "sportsCategory", "Football", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("10:30 AM - 12:30 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 10, "name", "Chennai Soccer Kings", "sportsCategory", "Football", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "2:30 PM - 4:00 PM")),

        // Basketball Academies
        Map.of("id", 11, "name", "Hoops Academy", "sportsCategory", "Basketball", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 12, "name", "Mumbai Dunkers Club", "sportsCategory", "Basketball", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 13, "name", "Delhi Slam Dunk Academy", "sportsCategory", "Basketball", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("12:00 PM - 2:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 14, "name", "Kolkata Jump Shots", "sportsCategory", "Basketball", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("10:30 AM - 12:30 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 15, "name", "Chennai Hoop Stars", "sportsCategory", "Basketball", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "3:00 PM - 4:00 PM")),

        // Hockey Academies
        Map.of("id", 16, "name", "National Hockey Academy", "sportsCategory", "Hockey", "latitude", 23.2599, "longitude", 77.4126, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 17, "name", "Kolkata Field Hockey Club", "sportsCategory", "Hockey", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 18, "name", "Mumbai Hockey Hub", "sportsCategory", "Hockey", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("12:00 PM - 2:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 19, "name", "Delhi Hockey Stars", "sportsCategory", "Hockey", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("10:30 AM - 12:30 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 20, "name", "Chennai Stick Masters", "sportsCategory", "Hockey", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "3:00 PM - 4:00 PM")),

        // Swimming Academies
        Map.of("id", 21, "name", "Wave Swimming School", "sportsCategory", "Swimming", "latitude", 12.9716, "longitude", 77.5946, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 22, "name", "Blue Dolphin Swimming", "sportsCategory", "Swimming", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("11:00 AM - 1:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 23, "name", "Aqua Masters Academy", "sportsCategory", "Swimming", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("10:30 AM - 12:30 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 24, "name", "Kolkata Swim Club", "sportsCategory", "Swimming", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("12:00 PM - 2:00 PM", "2:30 PM - 4:00 PM")),
        Map.of("id", 25, "name", "Chennai Aqua Stars", "sportsCategory", "Swimming", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "3:00 PM - 4:00 PM")),

        // Athletics Academies
        Map.of("id", 26, "name", "Track Masters Academy", "sportsCategory", "Athletics", "latitude", 12.9716, "longitude", 77.5946, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 27, "name", "Mumbai Runners Club", "sportsCategory", "Athletics", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 28, "name", "Delhi Speed Academy", "sportsCategory", "Athletics", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("12:00 PM - 2:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 29, "name", "Kolkata Track Warriors", "sportsCategory", "Athletics", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("10:30 AM - 12:30 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 30, "name", "Chennai Sprint Stars", "sportsCategory", "Athletics", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "3:00 PM - 4:00 PM")),

        // Badminton Academies
        Map.of("id", 31, "name", "Shuttle Smash Academy", "sportsCategory", "Badminton", "latitude", 12.9716, "longitude", 77.5946, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 32, "name", "Mumbai Net Kings", "sportsCategory", "Badminton", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 33, "name", "Delhi Shuttle Stars", "sportsCategory", "Badminton", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("12:00 PM - 2:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 34, "name", "Kolkata Birdie Club", "sportsCategory", "Badminton", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("10:30 AM - 12:30 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 35, "name", "Chennai Smashers", "sportsCategory", "Badminton", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "3:00 PM - 4:00 PM")),

        // Golf Academies
        Map.of("id", 36, "name", "Green Golf Academy", "sportsCategory", "Golf", "latitude", 12.9716, "longitude", 77.5946, "availableSlots", List.of("10:00 AM - 12:00 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 37, "name", "Mumbai Swing Masters", "sportsCategory", "Golf", "latitude", 19.0760, "longitude", 72.8777, "availableSlots", List.of("11:00 AM - 1:00 PM", "1:30 PM - 3:30 PM")),
        Map.of("id", 38, "name", "Delhi Golf Academy", "sportsCategory", "Golf", "latitude", 28.7041, "longitude", 77.1025, "availableSlots", List.of("12:00 PM - 2:00 PM", "3:00 PM - 4:00 PM")),
        Map.of("id", 39, "name", "Kolkata Fairway Club", "sportsCategory", "Golf", "latitude", 22.5726, "longitude", 88.3639, "availableSlots", List.of("10:30 AM - 12:30 PM", "2:00 PM - 4:00 PM")),
        Map.of("id", 40, "name", "Chennai Eagle Swing", "sportsCategory", "Golf", "latitude", 13.0827, "longitude", 80.2707, "availableSlots", List.of("11:30 AM - 1:30 PM", "3:00 PM - 4:00 PM"))
    );

    /**
     * Finds the nearest academies to the user's location for a specific sport.
     *
     * @param userLatitude  User's latitude
     * @param userLongitude User's longitude
     * @param sport         Sport category
     * @param maxAcademies  Maximum number of academies to return
     * @return List of academies sorted by distance with details
     */
    public List<Map<String, Object>> findNearestAcademies(
            double userLatitude, double userLongitude, String sport, int maxAcademies) {

        // Filter academies by the specified sport category
        List<Map<String, Object>> filteredAcademies = academies.stream()
                .filter(academy -> academy.get("sportsCategory").toString().equalsIgnoreCase(sport))
                .collect(Collectors.toList());

        // Calculate distances and map data
        return filteredAcademies.stream()
                .map(academy -> {
                    double distance = DistanceCalculator.calculateDistance(
                            userLatitude,
                            userLongitude,
                            (double) academy.get("latitude"),
                            (double) academy.get("longitude")
                    );
                    Map<String, Object> academyDetails = new HashMap<>(academy);
                    academyDetails.put("distance", distance);
                    return academyDetails;
                })
                .sorted(Comparator.comparingDouble(a -> (double) a.get("distance")))
                .limit(maxAcademies)
                .collect(Collectors.toList());
    }

    /**
     * Finds the nearest academies using a graph-based approach (Dijkstra's Algorithm).
     *
     * @param userLatitude  User's latitude
     * @param userLongitude User's longitude
     * @param sport         Sport category
     * @param maxAcademies  Maximum number of academies to return
     * @return List of academies sorted by distance with details
     */
    public List<Map<String, Object>> findNearestAcademiesGraphBased(
            double userLatitude, double userLongitude, String sport, int maxAcademies) {

        // Filter academies by the specified sport category
        List<Map<String, Object>> filteredAcademies = academies.stream()
                .filter(academy -> academy.get("sportsCategory").toString().equalsIgnoreCase(sport))
                .collect(Collectors.toList());

        // Create a graph representation of the academies
        Graph graph = new Graph();

        // Add user as a temporary node in the graph
        int userNodeId = -1; // Use -1 as a unique ID for the user
        graph.addNode(userNodeId, userLatitude, userLongitude);

        // Add academies as nodes and edges
        for (Map<String, Object> academy : filteredAcademies) {
            int academyId = (int) academy.get("id");
            double academyLat = (double) academy.get("latitude");
            double academyLon = (double) academy.get("longitude");

            graph.addNode(academyId, academyLat, academyLon);

            // Add an edge between the user and the academy with the calculated distance
            double distance = DistanceCalculator.calculateDistance(userLatitude, userLongitude, academyLat, academyLon);
            graph.addEdge(userNodeId, academyId, distance);
        }

        // Use Dijkstra's algorithm to calculate shortest distances from the user
        Map<Integer, Double> distances = graph.dijkstra(userNodeId);

        // Sort academies by distance and return the top N
        return filteredAcademies.stream()
                .sorted(Comparator.comparingDouble(academy -> distances.get((int) academy.get("id"))))
                .limit(maxAcademies)
                .map(academy -> {
                    Map<String, Object> academyDetails = new HashMap<>(academy);
                    academyDetails.put("distance", distances.get((int) academy.get("id")));
                    return academyDetails;
                })
                .collect(Collectors.toList());
    }
}
