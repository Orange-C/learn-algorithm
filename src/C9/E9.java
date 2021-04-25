package C9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class E9 {
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

        return new Graph(edges, true);
    }

    public static void unweightedShortestPath(Graph graph, char start) {
        Queue<Node> queue = new LinkedList<>();
        
        Node startNode = graph.getNode(start);
        queue.add(startNode);

        while(!queue.isEmpty()) {
            Node current = queue.remove();

            for(Edge edge : current.edges) {
                if (!edge.tailNode.visited) {
                    edge.tailNode.visited = true;
                    edge.tailNode.prev = current;
                    queue.add(edge.tailNode);
                }
            }
        }

        for(Node node : graph.nodes) {
            int cost = 0;
            Stack<Character> path = new Stack<>();
            path.add(node.name);
            Node prev = node.prev;
            while (prev != null) {
                cost++;
                path.add(prev.name);
                prev = prev.prev;
            }

            System.out.printf("target %s cost %d : %s", node.name, cost, path.pop());

            while(!path.isEmpty()) {
                System.out.printf(" -> %s", path.pop());
            }
           
            System.out.println();
        }
    }   

    public static void weightedShortestPath(Graph graph, char start) {
        HashMap<Node, Integer> pathCost = new HashMap<>();
        for(Node node : graph.nodes) {
            pathCost.put(node, node.name == start ? 0 : Integer.MAX_VALUE);
        }

        PriorityQueue<PNode> pq = new PriorityQueue<>(graph.nodes.size());
        PNode startNode = new PNode(graph.getNode(start), 0);
        pq.add(startNode);

        int count = 0;
        // run n times
        while(count < graph.nodes.size()) {
            PNode current = pq.remove();
            
            // skip duplicate nodes
            if (current.node.visited) continue;

            count++;
            current.node.visited = true;
            for(Edge edge : current.node.edges) {
                int newCost = current.cost + edge.cost;
                PNode newNode = new PNode(edge.tailNode, newCost);
                // add updated pnode to pq despite of its newCost 
                pq.add(newNode);
                // update cost and previous node
                if(newCost < pathCost.get(edge.tailNode)) {
                    edge.tailNode.prev = current.node;
                    pathCost.put(edge.tailNode, newCost);
                }
            }
        }

        for(Node node : graph.nodes) {
            int cost = pathCost.get(node);
            Stack<Character> path = new Stack<>();
            path.add(node.name);
            Node prev = node.prev;
            while (prev != null) {
                path.add(prev.name);
                prev = prev.prev;
            }

            System.out.printf("target %s cost %d : %s", node.name, cost, path.pop());

            while(!path.isEmpty()) {
                System.out.printf(" -> %s", path.pop());
            }
           
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("unweightedShortestPath");
        System.out.println();
        unweightedShortestPath(initGraph(), 's');
        System.out.println();
        System.out.println("weightedShortestPath");
        System.out.println();
        weightedShortestPath(initGraph(), 's');
        System.out.println();
    }
}


class PNode implements Comparable<PNode>{
    Node node;
    int cost;
    
    PNode(Node node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int compareTo(PNode pnode) {
        return Integer.compare(this.cost, pnode.cost);
    }
}