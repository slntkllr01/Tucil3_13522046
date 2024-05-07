package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utility {

    public static List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'A'; c <= 'Z'; c++) {
                if (c != oldChar) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (dict.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            chars[i] = oldChar;
        }
        return neighbors;
    }

    public static List<String> makeUCSPath(UCS.Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.currentWord);
            node = node.parent;
        }
        return path;
    }

    public static List<String> makeGBFSPath(GBFS.Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.currentWord);
            node = node.parent;
        }
        return path;
    }

    public static List<String> makeASPath(AStar.Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.currentWord);
            node = node.parent;
        }
        return path;
    }

    public static class Result {
        private List<String> path;
        private int nodesVisited;
        private long timeTakenNano;
    
        public Result(List<String> path, int nodesVisited, long timeTakenNano) {
            this.path = path;
            this.nodesVisited = nodesVisited;
            this.timeTakenNano = timeTakenNano;
        }

        public int getNodesVisited() {
            return this.nodesVisited;
        }

        public List<String> getPath() {
            return this.path;
        }

        public long getTime() {
            return this.timeTakenNano;
        }
    }
}
