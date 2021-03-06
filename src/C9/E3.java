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
        Graph g = new Graph(edges, true);

        return g;
    }

    public static char[] topologicalSort(Graph graph) {
        // calculate indegree
        HashMap<Node, Integer> indegree = new HashMap<>();
        for(Node node : graph.nodes) {
            indegree.put(node, 0);
        }
        for(Node node : graph.nodes) {
            for(Edge edge: node.edges) {
                indegree.put(edge.tailNode, indegree.get(edge.tailNode)+1);
            }
        }

        // use a queue to store zero-indegree nodes
        LinkedList<Node> zeroNodes = new LinkedList<>();
        for(Node node : graph.nodes) {
            if(indegree.get(node) == 0) {
                zeroNodes.add(node);
            }
        }

        char[] result = new char[graph.nodes.size()];
        int idx = 0;

        while (zeroNodes.size() > 0) {
            Node current = zeroNodes.removeFirst();
            result[idx++] = current.name;
            for(Edge edge: current.edges) {
                int newDegree = indegree.get(edge.tailNode)-1;
                indegree.put(edge.tailNode, newDegree);
                if(newDegree == 0) {
                    zeroNodes.add(edge.tailNode);
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
    boolean visited = false;
    Node prev;

    // for E11
    Node GFnode;

    // for E21
    int number;
    int low;

    // for E32
    HashMap<Node, Edge> edgeMap = new HashMap<>();
    int edgeCount = 0;

    Node(char name) {
        this.name = name;
    }

    public void addEdge(Node tailNode, int cost) {
        Edge newEdge = new Edge(this, tailNode, cost);
        edgeMap.put(tailNode, newEdge);
        this.edges.add(newEdge);
    }
}

class Edge {
    Node tailNode;
    int cost;

    // for debug
    Node startNode;

    Edge(Node startNode, Node tailNode, int cost) {
        this.startNode = tailNode;
        this.tailNode = tailNode;
        this.cost = cost;
    }
}

class Graph {
    LinkedList<Node> nodes = new LinkedList<>();
    LinkedList<Node> gfNodes = new LinkedList<>();
    
    Graph() {}

    Graph(String[] allEdges, boolean hasDirection) {
        for(String edge : allEdges) {
            Scanner scan = new Scanner(edge);
            char start = scan.next().toCharArray()[0];
            char end = scan.next().toCharArray()[0];
            int cost = scan.nextInt();
            scan.close();
            
            Node startNode = initNode(start);
            Node endNode = initNode(end);

            startNode.addEdge(endNode, cost);
            if (!hasDirection) {
                endNode.addEdge(startNode, cost);
            }
        }
    }

    public Node initNode(char target) {
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

    public Node getNode(char target) {
        Node result = null;
        for(Node node : nodes) {
            if(node.name == target) {
                result = node;
            }
        }

        return result;
    }

    private Node initNode(char target, LinkedList<Node> targetNodes) {
        Node result = null;
        for(Node node : targetNodes) {
            if(node.name == target) {
                result = node;
            }
        }
        if (result == null) {
            result = new Node(target);
            targetNodes.add(result);
        }

        return result;
    }

    public Graph getNFGraph() {
        Graph res = new Graph();

        for(Node node : nodes) {
            Node newNode = initNode(node.name, res.nodes);
            Node newGFNode = initNode(node.name, res.gfNodes);
            newNode.GFnode = newGFNode;
            
            for(Edge edge : node.edges) {
                Node newEndNode = initNode(edge.tailNode.name, res.nodes);
                newNode.addEdge(newEndNode, edge.cost);

                Node newGFEndNode = initNode(edge.tailNode.name, res.gfNodes);
                newGFNode.addEdge(newGFEndNode, 0);
            }
        }
        return res;
    }
}
