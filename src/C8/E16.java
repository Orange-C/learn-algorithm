package C8;

public class E16 {

    public static void main(String[] args) throws Exception {
        DisjointSetPathHalving ds = new DisjointSetPathHalving(10);
        ds.print();
        ds.union(0, 1);
        ds.print();
        ds.union(2, 3);
        ds.union(3, 4);
        ds.print();
        ds.union(1, 2);
        ds.print();
        ds.find(4);
        ds.print();
    }
}

class DisjointSetPathHalving {
    private int[] s;

    DisjointSetPathHalving(int size) {
        s = new int[size];
        for(int i = 0;i < size; i++) {
            s[i] = i;
        }
    }

    // path-halving
    // make every other node link to its grandparent
    public int find(int i) {
        while(s[i] != i) {
            s[i] = s[s[i]];
            i = s[i];
        }

        return i;
    }

    public void union(int a, int b) {
        int aroot = find(a);
        int broot = find(b);
        
        if(aroot == broot) return;
        s[broot] = aroot; // set root
    }

    public void print() {
        for(int i = 0; i < s.length; i++) {
            System.out.printf("%d=%d, ", i, s[i]);
        }
        System.out.println();
    }
}
