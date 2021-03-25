package C4;

public class E35 {
    public static int h = 4;

    public static Node minAVLTree(int height, int[] lastNode) {
        Node t = null;
        
        if(height >= 0) {
            t = new Node();
            t.left = minAVLTree(height - 1, lastNode);
            t.value = ++lastNode[0];
            t.right = minAVLTree(height - 2, lastNode);
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
        int[] lastNode = {0};
        Node tree = minAVLTree(h, lastNode);
        printTree(tree);
    }
}

