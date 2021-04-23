package C9;

import java.util.LinkedList;

public class E11 {
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

        return new Graph(edges);
    }

    public static void networkFlow(Graph graph, char start, char end) throws CloneNotSupportedException {
        Graph gr = graph.clone();
        Graph gf = graph.clone();
        for(Node node : gf.nodes) {
            for(Edge e : node.edges) {
                e.cost = 0;
            }
        }

        Node grStartNode = gr.getNode(start);
        LinkedList<Node> path = new LinkedList<>();
        boolean hasPath = findAugmentPath(grStartNode, end, path);
        if (hasPath) {
            for(Node n: path) {
                System.out.println(n.name);
            }
        }
    }

    public static void changeEdgeCost(Graph graph, Node start, Node end, int change) {
        boolean hasEdge = false;
        for(Edge edge : start.edges) {
            if (!edge.tailNode.visited) {

            }
        }
    }

    public static boolean findAugmentPath(Node node, char end, LinkedList<Node> path) {
        path.add(node);
        node.visited = true;
        if (node.name == end) {
            return true;
        } else {
            for(Edge edge : node.edges) {
                if (!edge.tailNode.visited) {
                    boolean finded = findAugmentPath(edge.tailNode, end, path);
                    if (finded) {
                        return true;
                    }
                }
            }
        }
        path.removeLast();
        return false;
    }

    public static void main(String[] args) throws Exception {
        networkFlow(initGraph(), 's', 't');
    }
}
