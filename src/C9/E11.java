package C9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class E11 {
    public static Graph initGraph() {
        String[] edges = {
            "s A 1", "s D 4", "s G 6",
            "A B 2", "A E 2",
            "D A 3", "D E 3",
            "G D 2", "G E 1", "G H 6", 
            "B C 2",
            "E C 2", "E F 3", "E I 3",
            "H E 2", "H I 6",
            "C t 4",
            "F C 1", "F t 3",
            "I F 1", "I t 4"
        };

        // String[] edges = {
        //     "s a 4", "s b 2",
        //     "a b 1", "a c 2", "a d 4",
        //     "b d 2",
        //     "c t 3",
        //     "d t 3",
        // };

        return new Graph(edges, true);
    }

    public static void networkFlow(Graph graph, char start, char end) throws CloneNotSupportedException {
        Graph gr = graph.getNFGraph();

        Node grStartNode = gr.getNode(start);
        Node grEndNode = gr.getNode(end);
        MaxFlow maxFlow = findMaxFlowPath(gr, grStartNode, grEndNode);
        
        while(maxFlow != null) {
            for(Node n: maxFlow.path) {
                System.out.printf("%s ", n.name);
            }
            System.out.println(maxFlow.flow);

            Node pStart = maxFlow.path.removeFirst();
            Node pEnd = maxFlow.path.removeFirst();
            while (true) {
                // subtract m
                changeEdgeCostGR(pStart, pEnd, -maxFlow.flow);
                // add opposite m
                changeEdgeCostGR(pEnd, pStart, maxFlow.flow);
                // add flow
                changeEdgeCostGF(pStart, pEnd, maxFlow.flow);

                if (maxFlow.path.isEmpty()) break;
                pStart = pEnd;
                pEnd = maxFlow.path.removeFirst();
            }

            // reset
            for(Node node : gr.nodes) {
                node.visited = false;
                node.prev = null;
            }
            maxFlow = findMaxFlowPath(gr, grStartNode, grEndNode);
        }

        for(Node node : gr.nodes) {
            node.visited = false;
        }
        int[] res = {0};
        for(Node n : gr.gfNodes) {
            if (n.name == start){
                findMaxFlow(n, end, res);
                break;
            }
        }
        System.out.println(res[0]);
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

    public static void changeEdgeCostGF(Node start, Node end, int change) {
        boolean hasEdge = false;
        Node gfStart = start.GFnode;
        Node gfEnd = end.GFnode;

        for(Edge edge : gfStart.edges) {
            if(edge.tailNode.name == gfEnd.name) {
                edge.cost += change;
                hasEdge = true;
                return;
            }
        }

        if(!hasEdge) {
            for(Edge edge : gfEnd.edges) {
                if(edge.tailNode.name == gfStart.name) {
                    edge.cost -= change;
                    return;
                }
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

    // find the max-flow augmenting path
    public static MaxFlow findMaxFlowPath(Graph graph, Node start, Node end) {
        HashMap<Node, Integer> pathFlow = new HashMap<>();
        for(Node node : graph.nodes) {
            pathFlow.put(node, node.name == start.name ? Integer.MAX_VALUE : 0);
        }

        PriorityQueue<NPNode> pq = new PriorityQueue<>(graph.nodes.size());
        NPNode startNode = new NPNode(start, Integer.MAX_VALUE);
        pq.add(startNode);

        int count = 0;
        // run n times
        while(count < graph.nodes.size()) {
            NPNode current = pq.remove();
            
            // skip duplicate nodes
            if (current.node.visited) continue;

            count++;
            current.node.visited = true;
            int currentFlow = pathFlow.get(current.node);
            for(Edge edge : current.node.edges) {
                int endFlow = pathFlow.get(edge.tailNode);
                int newFlow = edge.cost < currentFlow ? edge.cost : currentFlow;
                NPNode newNode = new NPNode(edge.tailNode, newFlow);
                pq.add(newNode);
                if(newFlow > endFlow) {
                    edge.tailNode.prev = current.node;
                    pathFlow.put(edge.tailNode, newFlow);
                }
            }

            if (current.node.name == end.name) break;
        }


        int flow = pathFlow.get(end);
        if (flow == 0) {
            return null;
        }

        LinkedList<Node> path = new LinkedList<>();
        path.addFirst(end);
        Node prev = end.prev;
        while (prev != null) {
            path.addFirst(prev);
            prev = prev.prev;
        }

        return new MaxFlow(path, flow);
    }

    public static void main(String[] args) throws Exception {
        networkFlow(initGraph(), 's', 't');
    }
}

class NPNode implements Comparable<NPNode>{
    Node node;
    int cost;
    
    NPNode(Node node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int compareTo(NPNode pnode) {
        return -Integer.compare(this.cost, pnode.cost);
    }
}

class MaxFlow {
    int flow;
    LinkedList<Node> path;

    MaxFlow(LinkedList<Node> path, int flow) {
        this.path = path;
        this.flow = flow;
    }
}
