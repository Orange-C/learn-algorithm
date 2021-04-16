package C9;

import java.util.ArrayList;
import java.util.LinkedList;

public class E3 {
    public static LinkedList<InputEdge> inputEdges = new LinkedList<>();
    public static ArrayList<Node> nodes = new ArrayList<>();

    public static void initEdges() {
        LinkedList<InputEdge> inputEdges = new LinkedList<>();
        inputEdges.addLast(new InputEdge('s', 'A', 1));
    }
    public static void main(String[] args) throws Exception {
    }

}

class InputEdge {
    char start;
    char end;
    int cost;
    
    InputEdge(char start, char end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

class Edge {
    char value;
    int cost;
    
    Edge(char value, int cost) {
        this.value = value;
        this.cost = cost;
    }
}

class Node {
    char value;
    LinkedList<Edge> edges;

    Node(char value) {
        this.value = value;
    }

    public void addEdge(char end, int cost) {
        Edge tmp = new Edge(end, cost);
        edges.addLast(tmp);
    }
}
