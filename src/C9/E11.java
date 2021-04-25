package C9;

import java.util.LinkedList;

public class E11 {
    public static Graph initGraph() {
        // String[] edges = {
        //     "s A 1", "s D 4", "s G 6",
        //     "A B 2", "A E 2",
        //     "D A 3", "D E 3",
        //     "G D 2", "G E 1", "G H 6", 
        //     "B C 2",
        //     "E C 2", "E F 2", "E I 2",
        //     "H E 2", "H I 6",
        //     "C t 4",
        //     "F C 1", "F t 3",
        //     "I F 1", "I t 4"
        // };

        String[] edges = {
            "s a 4", "s b 2",
            "a d 4", "a b 1", "a c 2",
            "b d 2",
            "c t 3",
            "d t 3",
        };

        return new Graph(edges);
    }

    public static void networkFlow(Graph graph, char start, char end) throws CloneNotSupportedException {
        Graph gr = graph.getNFGraph();
        // Graph gf = new Graph();
        // gf.nodes = gr.gfNodes;

        Node grStartNode = gr.getNode(start);
        LinkedList<Node> path = new LinkedList<>();
        int[] pathMin = { Integer.MAX_VALUE };
        boolean hasPath = findAugmentPath(grStartNode, end, Integer.MAX_VALUE, path, pathMin);

        while(hasPath) {
            for(Node n: path) {
                System.out.printf("%s ", n.name);
            }
            System.out.println(pathMin[0]);

            Node pStart = path.removeFirst();
            Node pEnd = path.removeFirst();
            while (true) {
                // subtract m
                changeEdgeCostGR(pStart, pEnd, -pathMin[0]);
                // add opposite m
                changeEdgeCostGR(pEnd, pStart, pathMin[0]);
                // add flow
                changeEdgeCostGF(pStart, pEnd, pathMin[0]);

                if (path.isEmpty()) break;
                pStart = pEnd;
                pEnd = path.removeFirst();
            }
            

            // reset
            for(Node node : gr.nodes) {
                node.visited = false;
            }
            pathMin[0] = Integer.MAX_VALUE;
            hasPath = findAugmentPath(grStartNode, end, Integer.MAX_VALUE, path, pathMin);
        }

        for(Node node : gr.nodes) {
            node.visited = false;
        }
        int[] maxFlow = {0};
        for(Node n : gr.gfNodes) {
            if (n.name == start){
                findMaxFlow(n, end, maxFlow);
                break;
            }
        }
        System.out.println(maxFlow[0]);
    }

    public static void findMaxFlow(Node node, char end, int[] maxValue) {
        node.visited = true;
        for(Edge edge : node.edges) {
            if (edge.tailNode.name == end) {
                maxValue[0] += edge.cost;
            }
            
            if (!edge.tailNode.visited) {
                findMaxFlow(edge.tailNode, end, maxValue);
            }
        }
    }

    public static boolean findAugmentPath(Node node, char end, int min, LinkedList<Node> path, int[] pathMin) {
        path.add(node);
        node.visited = true;
        if (node.name == end) {
            pathMin[0] = min;
            return true;
        } else {
            for(Edge edge : node.edges) {
                // skip zero-cost edges
                if (!edge.tailNode.visited && edge.cost > 0) {
                    int currentMin = edge.cost < min ? edge.cost : min;
                    boolean finded = findAugmentPath(edge.tailNode, end, currentMin, path, pathMin);
                    if (finded) {
                        return true;
                    }
                }
            }
        }
        path.removeLast();
        return false;
    }

    public static void changeEdgeCostGF(Node start, Node end, int change) {
        Node gfStart = start.GFnode;
        Node gfEnd = end.GFnode;

        for(Edge edge : gfStart.edges) {
            if(edge.tailNode.name == gfEnd.name) {
                edge.cost += change;
                return;
            }
        }
    }

    public static void changeEdgeCostGR(Node start, Node end, int change) {
        boolean hasEdge = false;
        for(Edge edge : start.edges) {
            if(edge.tailNode.name == end.name) {
                edge.cost += change;
                hasEdge = true;
                return;
            }
        }

        if (!hasEdge) {
            start.addEdge(end, change);
        }
    }

    public static void main(String[] args) throws Exception {
        networkFlow(initGraph(), 's', 't');
    }
}
