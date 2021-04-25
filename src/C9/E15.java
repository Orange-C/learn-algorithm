package C9;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import C8.DisjointSet;

// minimalSpanningTree
public class E15 {
    public static Graph initGraph() {
        String[] edges = {
            "A B 3", "A D 4", "A E 5",
            "B C 10", "B E 2", "B F 3",
            "C F 6", "C G 1",
            "D E 5", "D H 6",
            "E F 11", "E H 2", "E I 1",
            "F G 2", "F I 3", "F J 11",
            "G J 8",
            "H I 4",
            "I J 7"
        };
        Graph g = new Graph(edges, false);

        return g;
    }

    public static void PrimTree(Graph graph) {
        Node start = graph.nodes.getFirst();
        HashMap<Node, Integer> pathCost = new HashMap<>();
        for(Node node : graph.nodes) {
            pathCost.put(node, node.name == start.name ? 0 : Integer.MAX_VALUE);
        }

        PriorityQueue<PNode> pq = new PriorityQueue<>(graph.nodes.size());
        PNode startNode = new PNode(start, 0);
        pq.add(startNode);

        int count = 0;
        while(count < graph.nodes.size()) {
            PNode current = pq.remove();

            if (current.node.visited) continue;

            count++;
            current.node.visited = true;
            for(Edge edge : current.node.edges) {
                int newCost = edge.cost;
                PNode newNode = new PNode(edge.tailNode, newCost);
                // add updated pnode to pq despite of its newCost 
                pq.add(newNode);
                // update cost and previous node
                if(!edge.tailNode.visited && newCost < pathCost.get(edge.tailNode)) {
                    edge.tailNode.prev = current.node;
                    pathCost.put(edge.tailNode, newCost);
                }
            }
        }
        
        int total = 0;
        for(int i : pathCost.values()) {
            total += i;
        }
        System.out.println(total);
    }

    public static void KruskalTree(Graph graph) {
        DisjointSet ds = new DisjointSet(graph.nodes.size());
        HashMap<Node, Integer> nodeIdx = new HashMap<>();
        int idx = 0;
        for(Node node : graph.nodes) {
            nodeIdx.put(node, idx++);
        }

        LinkedList<FullEdge> allEdges = new LinkedList<>();
        for(Node node : graph.nodes) {
            for(Edge edge : node.edges) {
                FullEdge tmp = new FullEdge();
                tmp.start = node;
                tmp.end = edge.tailNode;
                tmp.cost = edge.cost;
                allEdges.add(tmp);
            }
        }
        allEdges.sort(new EdgeCompar());
        
        int count = 0;
        int total = 0;
        while(count < graph.nodes.size() - 1) {
            FullEdge current = allEdges.removeFirst();
            int aroot = ds.find(nodeIdx.get(current.start));
            int broot = ds.find(nodeIdx.get(current.end));
            if (aroot == broot) continue;

            count++;
            ds.union(aroot, broot);
            total += current.cost;
        }
        System.out.println(total);
    }

    public static void main(String[] args) throws Exception {
        PrimTree(initGraph());
        KruskalTree(initGraph());
    }
}

class EdgeCompar implements Comparator<FullEdge> {
    public int compare(FullEdge a, FullEdge b) {
        return Integer.compare(a.cost, b.cost);
    }
}

class FullEdge {
    Node start;
    Node end;
    int cost;
}
