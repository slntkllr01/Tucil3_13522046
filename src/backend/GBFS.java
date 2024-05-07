package backend;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class GBFS {

    public static class Node {
        String currentWord;
        Node parent;
        int heuristic;

        Node(String currentWord, Node parent, int heuristic) {
            this.currentWord = currentWord;
            this.parent = parent;
            this.heuristic = heuristic;
        }
    }

    public static Utility.Result gbfs(String start, String goal, Set<String> dict) {
        // Prioritas berdasarkan nilai heuristik terendah
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, null, Heuristic.hammingDistance(start, goal)));
        visited.add(start);
        int count = 0;

        long startTime = System.nanoTime();

        while (!queue.isEmpty()) {
            Node head = queue.poll();
            count++;

            // Jika tujuan ditemukan
            if (head.currentWord.equals(goal)) {
                long endTime = System.nanoTime();
                List<String> path = Utility.makeGBFSPath(head);
                return new Utility.Result(path, count, (endTime - startTime) / 1000000);
            }

            for (String neighbor : Utility.getNeighbors(head.currentWord, dict)) {
                int heuristic = Heuristic.hammingDistance(neighbor, goal);

                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, head, heuristic));
                    visited.add(neighbor);
                }
            }
        }

        return new Utility.Result(null, count, (System.nanoTime() - startTime) / 1000000);
    }
}
