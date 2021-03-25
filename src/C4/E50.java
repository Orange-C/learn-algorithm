package C4;

public class E50 {
    public static TNode insert(TNode root, int value) {
        TNode current = root;
        TNode parent = null;
        while(current != null) {
            if (current.value == value) {
                return root;
            }

            parent = current;

            if (current.value > value) {
                if (current.lthread == false) {
                    current = current.left;
                } else {
                    break;
                }
            } else if (current.value < value) {
                if (current.rthread == false) {
                    current = current.right;
                } else {
                    break;
                }
            }
        }

        TNode tmp = new TNode();
        tmp.value = value;
        tmp.lthread = true;
        tmp.rthread = true;

        if(parent == null) {
            root = tmp;
            root.left = null;
            root.right = null;
        } else if (parent.value > value) {
            tmp.left = parent.left;
            tmp.right = parent;
            parent.left = tmp;
            parent.lthread = false;
        } else {
            tmp.right = parent.right;
            tmp.left = parent;
            parent.right = tmp;
            parent.rthread = false;
        }

        return root;
    }

    public static TNode inOrderNext(TNode t) {
        if (t.rthread == true) {
            return t.right;
        }

        t = t.right;
        while(t.lthread == false) {
            t = t.left;
        }
        return t;
    }

    public static void inOrder(TNode root) {
        TNode itr = root;
        while(itr.lthread == false) {
            itr = itr.left;
        }

        while(itr != null) {
            System.out.printf("%d ", itr.value);
            itr = inOrderNext(itr);
        }
    }

    public static void main(String[] args) throws Exception {
        TNode root = null;
        root = insert(root, 3);
        root = insert(root, 4);
        root = insert(root, 5);
        root = insert(root, 1);
        root = insert(root, 2);
        root = insert(root, 9);
        root = insert(root, 8);

        inOrder(root);
    }
}

class TNode {
    int value;
    TNode left;
    TNode right;
    boolean lthread;
    boolean rthread;
}
