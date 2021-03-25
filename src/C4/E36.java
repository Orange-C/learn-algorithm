package C4;

public class E36 {
    public static int h = 4;

    public static Node balancedBSTtree(int height, int[] lastNode) {
        Node t = null;
        
        if(height >= 0) {
            t = new Node();
            t.left = balancedBSTtree(height - 1, lastNode);
            t.value = ++lastNode[0];
            t.right = balancedBSTtree(height - 1, lastNode);
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
        Node tree = balancedBSTtree(h, lastNode);
        printTree(tree);
    }
}

