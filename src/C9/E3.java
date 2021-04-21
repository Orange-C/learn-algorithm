package C9;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class E3 {
    public static Graph initGraph() {
        String[] edges = {
            "s A 1", "s D 4", "s G 6",
            "A B 2", "A E 2",
            "D A 3", "D E 3",
            "G D 2", "G E 1", "G H 6", 
            "B C 2",
            "E C 2", "E F 2", "E I 2",
            "H E 2", "H I 6",
            "C t 4",
            "F C 1", "F t 3",
            "I F 1", "I t 4"
        };
        Graph g = new Graph(edges);

        return g;
    }

    public static char[] topologicalSort(Graph graph) {
        // calculate indegree
        HashMap<Character, Integer> indegree = new HashMap<>();
        for(Node node : graph.nodes) {
            indegree.put(node.name, 0);
        }
        for(Node node : graph.nodes) {
            for(Edge edge: node.edges) {
                indegree.put(edge.tailName, indegree.get(edge.tailName)+1);
            }
        }

        // use a queue to store zero-indegree nodes
        LinkedList<Node> zeroNodes = new LinkedList<>();
        for(Node node : graph.nodes) {
            if(indegree.get(node.name) == 0) {
                zeroNodes.add(node);
            }
        }

        char[] result = new char[graph.nodes.size()];
        int idx = 0;

        while (zeroNodes.size() > 0) {
            Node current = zeroNodes.removeFirst();
            result[idx++] = current.name;
            for(Edge edge: current.edges) {
                int newDegree = indegree.get(edge.tailName)-1;
                indegree.put(edge.tailName, newDegree);
                if(newDegree == 0) {
                    zeroNodes.add(graph.getNode(edge.tailName));
                }
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        Graph g = initGraph();
        char[] res = topologicalSort(g);
        System.out.println(Arrays.toString(res));
    }
}

class Node {
    char name;
    LinkedList<Edge> edges = new LinkedList<>();

    Node(char name) {
        this.name = name;
    }
}

class Edge {
    char tailName;
    int cost;

    Edge(char tailName, int cost) {
        this.tailName = tailName;
        this.cost = cost;
    }
}

class Graph {
    LinkedList<Node> nodes = new LinkedList<>();
    Graph(String[] allEdges) {
        for(String edge : allEdges) {
            Scanner scan = new Scanner(edge);
            char start = scan.next().toCharArray()[0];
            char end = scan.next().toCharArray()[0];
            int cost = scan.nextInt();
            scan.close();
            
            Node startNode = getNode(start);
            Node endNode = getNode(end);

            Edge newEdge = new Edge(endNode.name, cost);
            startNode.edges.add(newEdge);
        }
    }

    public Node getNode(char target) {
        Node result = null;
        for(Node node : nodes) {
            if(node.name == target) {
                result = node;
            }
        }
        if (result == null) {
            result = new Node(target);
            nodes.add(result);
        }

        return result;
    }
}
