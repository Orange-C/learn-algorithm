package C4;

public class E34 {
    public static int n = 10;

    public static int randomInt(int low, int high) {
        return (int)(Math.random()*(high - low + 1) + low);
    }

    public static Node makeRandomTree(int n) {
        return makeRandomTree(1, n);
    }

    public static Node makeRandomTree(int low, int high) {
        Node t = null;
        if (low <= high) {
            int value = randomInt(low, high);
            t = new Node(value, makeRandomTree(low, value - 1), makeRandomTree(value + 1, high));
        }

        return t;
    }

    public static void printTree(Node t) {
        if (t == null) return;

        printTree(t.left);
        System.out.printf("%d ", t.value);
        printTree(t.right);
    }

    public static void main(String[] args) throws Exception {
        Node tree = makeRandomTree(n);
        printTree(tree);
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value, Node left,Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    Node() {}
}
