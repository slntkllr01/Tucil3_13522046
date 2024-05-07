package backend;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class UCS {

    public static class Node {
        String currentWord;
        Node parent;
        int cost;

        Node(String currentWord, Node parent, int cost) {
            this.currentWord = currentWord;
            this.parent = parent;
            this.cost = cost;
        }
    }

    public static Utility.Result ucs(String start, String goal, Set<String> dict) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, null, 0));
        visited.add(start);
        int count = 0;

        long startTime = System.nanoTime();
        
        while (!queue.isEmpty()) {
            Node head = queue.poll();
            count++;

            // Jika tujuan telah tercapai
            if (head.currentWord.equals(goal)) {
                long endTime = System.nanoTime();
                List<String> path = Utility.makeUCSPath(head);
                return new Utility.Result(path, count, (endTime - startTime) / 1000000);
            }

            for (String neighbor : Utility.getNeighbors(head.currentWord, dict)) {
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, head, head.cost + 1));
                    visited.add(neighbor);
                }
            }
        }
        return new Utility.Result(null, count, (System.nanoTime() - startTime) / 1000000);
    }
}