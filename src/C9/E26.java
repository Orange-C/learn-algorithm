package C9;

public class E26 {
    public static Graph initGraph() {
        String[] edges = {
            "A B 1", "A C 1", 
            "B C 1", "B E 1", "B G 1", 
            "C D 1", "C E 1",
            "D A 1", "D F 1",
            "E D 1", "E F 1",
            "G E 1",
        };
        Graph g = new Graph(edges, true);

        return g;
    }

    public static int count = 1;

    public static void findStronglyComponents(Graph graph) {
        Node startNode = graph.nodes.getFirst();
        setNum(startNode);
        Graph gr = transposeGraph(graph);

        startNode = findMaxNode(gr);
        while(startNode != null) {
            showSC(startNode);
            System.out.println();
            startNode = findMaxNode(gr);
        }
    }

    public static void showSC(Node node) {
        node.visited = true;
        System.out.printf("%s ", node.name);
        for(Edge edge : node.edges) {
            if(!edge.tailNode.visited) {
                showSC(edge.tailNode);
            }
        }
    }

    public static Node findMaxNode(Graph graph) {
        int maxValue = 0;
        Node res = null;
        for(Node node : graph.nodes) {
            if (!node.visited && node.number > maxValue) {
                maxValue = node.number;
                res = node;
            }
        }
        
        return res;
    }


    public static Graph transposeGraph(Graph graph) {
        Graph res = new Graph();
        for(Node node : graph.nodes) {
            for(Edge edge : node.edges) {
                Node startNode = res.initNode(edge.tailNode.name);
                Node endNode = res.initNode(node.name);
                startNode.number = edge.tailNode.number;
                endNode.number = node.number;
                startNode.addEdge(endNode, edge.cost);
            }
        }

        return res;
    }


    public static void setNum(Node node) {
        node.visited = true;
        node.low = node.number;

        for(Edge edge : node.edges) {
            if(!edge.tailNode.visited) {
                setNum(edge.tailNode);
            }
        }

        node.number = count++;
    }
    
    public static void main(String[] args) throws Exception {
        findStronglyComponents(initGraph());
    }
}
