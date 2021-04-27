package C9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class E32 {
    public static HashMap<Edge, Boolean> visitedEdgeMap = new HashMap<>();

    public static Graph initGraph() {
        String[] edges = {
            "1 3 1", "1 4 1",
            "2 3 1", "2 8 1",
            "3 4 1", "3 6 1", "3 7 1", "3 9 1",
            "4 5 1", "4 7 1", "4 10 1", "4 11 1",
            "5 10 1",
            "6 9 1",
            "7 9 1", "7 10 1",
            "8 9 1",
            "9 10 1", "9 12 1",
            "10 11 1", "10 12 1",
        };
        // use ABC because of char
        for(int i = 0; i < edges.length; i++) {
            Scanner scan = new Scanner(edges[i]);
            int start = scan.nextInt();
            int end = scan.nextInt();
            int cost = scan.nextInt();
            scan.close();
            edges[i] = getABC(start) + " " + getABC(end) + " " + cost;
        }
        Graph g = new Graph(edges, false);

        return g;
    }

    public static char getABC(int num) {
        char tmp = 'A';
        tmp += --num;
        return tmp;
    }

    public static int getNum(char abc) {
        char tmp = 'A';
        int res = abc - tmp;
        return ++res;
    }

    public static void findEulerResult(Graph graph) {
        LinkedList<Node> oddNodes = new LinkedList<>();
        for(Node node : graph.nodes) {
            boolean isOdd = node.edges.size()%2 != 0;
            if (isOdd) oddNodes.add(node);
            for(Edge edge : node.edges) {
                visitedEdgeMap.put(edge, false);
            }
        }

        if(oddNodes.size() == 0) {
            System.out.println("The graph has an Euler circuit");
            findEulerPath(graph.nodes.getFirst(), null);
        } else if (oddNodes.size() == 2) {
            System.out.println("The graph has an Euler tour");
            findEulerPath(oddNodes.remove(), oddNodes.remove());
        }
    }

    public static void findEulerPath(Node start, Node end) {
        LinkedList<Node> res = new LinkedList<>();
        LinkedList<Node> nodePath = new LinkedList<>();
        // find initial path
        getDfsPath(start, end == null ? start : end, true, nodePath);
        res.addAll(0, nodePath);

        printPath(nodePath);

        while(true) {
            Node newStart = getUnvisitedEdge(res);
            if(newStart == null) break;

            nodePath.clear();
            getDfsPath(newStart, newStart, true, nodePath);

            printPath(nodePath);

            int insertIdx = 0;
            for(Node tmp : res) {
                insertIdx++;
                if(tmp.name == newStart.name) {
                    break;
                }
            }
            nodePath.removeFirst();
            res.addAll(insertIdx, nodePath);
        }

        printPath(res);
    }

    public static void printPath(LinkedList<Node> path) {
        System.out.printf("Len %d: ", path.size() - 1);
        for(Node tmp : path) {
            System.out.printf("%s ", getNum(tmp.name));
        }
        System.out.println();
    }

    public static Node getUnvisitedEdge(LinkedList<Node> path) {
        for(Node node : path) {
            if (node.edgeCount != node.edges.size()) {
                return node;
            }
        }

        return null;
    }

    public static boolean getDfsPath(Node node, Node startNode, boolean isStart, LinkedList<Node> nodePath) {
        nodePath.add(node);
        if (!isStart && node.name == startNode.name) {
            return true;
        }
        for(Edge edge : node.edges) {
            if(!visitedEdgeMap.get(edge)) {
                visitedEdgeMap.put(edge, true);
                node.edgeCount++;
                visitedEdgeMap.put(edge.tailNode.edgeMap.get(node), true);
                edge.tailNode.edgeCount++;
                boolean finded = getDfsPath(edge.tailNode, startNode, false, nodePath);
                if (finded) {
                    return true;
                }
            }
        }
        nodePath.removeLast();

        return false;
    }

    public static void main(String[] args) throws Exception {
        findEulerResult(initGraph());
    }
}
