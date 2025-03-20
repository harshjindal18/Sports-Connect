package com.example.sportsconnectt.utils;

import java.util.*;

public class Graph {

    private final Map<Integer, Map<Integer, Double>> adjacencyList = new HashMap<>();

    public void addNode(int id, double lat, double lon) {
        adjacencyList.putIfAbsent(id, new HashMap<>());
    }

    public void addEdge(int from, int to, double weight) {
        adjacencyList.get(from).put(to, weight);
        adjacencyList.get(to).put(from, weight); // Assuming undirected graph
    }

    public Map<Integer, Double> dijkstra(int start) {
        Map<Integer, Double> distances = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));

        distances.put(start, 0.0);
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            double distance = current[1];

            if (distance > distances.getOrDefault(node, Double.MAX_VALUE)) {
                continue;
            }

            for (Map.Entry<Integer, Double> neighbor : adjacencyList.get(node).entrySet()) {
                int neighborNode = neighbor.getKey();
                double newDistance = distance + neighbor.getValue();

                if (newDistance < distances.getOrDefault(neighborNode, Double.MAX_VALUE)) {
                    distances.put(neighborNode, newDistance);
                    pq.add(new int[]{neighborNode, (int) newDistance});
                }
            }
        }

        return distances;
    }
}
