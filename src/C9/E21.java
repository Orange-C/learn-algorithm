package C9;

public class E21 {
    public static Graph initGraph() {
        String[] edges = {
            "A C 1", "A D 1", 
            "B C 1", "B E 1", 
            "C D 1", "C F 1",
            "E F 1", "E H 1", "E I 1",
            "F G 1",
            "H J 1",
            "I K 1",
            "J K 1"
        };
        Graph g = new Graph(edges, false);

        return g;
    }

    public static int count = 1;

    public static void findArt(Graph graph) {
        Node startNode = graph.nodes.getFirst();
        setNumAndLow(startNode);

        for(Node node : graph.nodes) {
            System.out.printf("%s number %d low %d\n", node.name, node.number, node.low);
        }
    }

    public static void setNumAndLow(Node node) {
        node.visited = true;
        node.number = count++;
        node.low = node.number;

        for(Edge edge : node.edges) {
            if(!edge.tailNode.visited) {
                edge.tailNode.prev = node;
                setNumAndLow(edge.tailNode);
                if (edge.tailNode.low > node.number) {
                    System.out.println(node.name + " is an articulation potint");
                }
                node.low = Math.min(node.low, edge.tailNode.low);
            } else if(node.prev != edge.tailNode) {
                node.low = Math.min(node.low, edge.tailNode.number);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        findArt(initGraph());
    }
}
