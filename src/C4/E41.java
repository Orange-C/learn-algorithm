package C4;

import java.util.LinkedList;

public class E41 {
    public static LinkedList<Node> list = new LinkedList<>();

    public static int height(Node t) {
        if(t == null) {
            return 0;
        }
        return Math.max(height(t.left), height(t.right)) + 1;
    }

    public static void levelOrderPrint(Node t, int level) {
        if (t == null) {
            return;
        }
        if (level == 1) {
            System.out.printf("%d ", t.value);
        } else if (level > 1) {
            levelOrderPrint(t.left, level - 1);
            levelOrderPrint(t.right, level - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        int[] lastNode = {0};
        Node tree = E35.minAVLTree(3, lastNode);
        int h = height(tree);
        for(int i = 1; i <= h; i++) {
            levelOrderPrint(tree, i);
        }
    }
}
