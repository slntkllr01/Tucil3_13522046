package backend;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {

    public static class Node {
        String currentWord;
        Node parent;
        int cost;
        int heuristic;
        int total;

        Node(String currentWord, Node parent, int cost, int heuristic) {
            this.currentWord = currentWord;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
            this.total = cost + heuristic;
        }
    }

    public static Utility.Result a_star(String start, String goal, Set<String> dict) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.total));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, null, 0, Heuristic.hammingDistance(start, goal)));
        visited.add(start);
        int count = 0;

        long startTime = System.nanoTime();

        while (!queue.isEmpty()) {
            Node head = queue.poll();
            count++;

            if (head.currentWord.equals(goal)) {
                long endTime = System.nanoTime();
                List<String> path = Utility.makeASPath(head);
                return new Utility.Result(path, count, (endTime - startTime) / 1000000);
            }

            for (String neighbor : Utility.getNeighbors(head.currentWord, dict)) {
                int cost = head.cost + 1;
                int heuristic = Heuristic.hammingDistance(neighbor, goal);

                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, head, cost, heuristic));
                    visited.add(neighbor);
                }
            }
        }

        return new Utility.Result(null, count, (System.nanoTime() - startTime) / 1000000);
    }
}
