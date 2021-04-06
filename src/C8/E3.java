package C8;

public class E3 {

    public static void main(String[] args) throws Exception {
        DisjointSet ds = new DisjointSet(10);
        ds.print();
        ds.union(0, 1);
        ds.print();
        ds.union(2, 3);
        ds.print();
        ds.union(1, 2);
        ds.print();
    }
}

class DisjointSet {
    private int[] s;

    DisjointSet(int size) {
        s = new int[size];
        for(int i = 0;i < size; i++) {
            s[i] = -1;
        }
    }

    // path compression
    public int find(int i) {
        if (s[i] < 0) {
            return i;
        } else {
            return s[i] = find(s[i]);
        }
    }

    // union by size
    public int union(int a, int b) {
        int aroot = find(a);
        int broot = find(b);
        
        if (aroot == broot) return aroot;

        if (s[aroot] < s[broot]) { // because of their negative value of size
            s[aroot] += s[broot]; // add size
            s[broot] = aroot; // set root
            return aroot;
        } else {
            s[broot] += s[aroot];
            s[aroot] = broot;
            return broot;
        }
    }

    public void print() {
        for(int i = 0; i < s.length; i++) {
            System.out.printf("%d=%d, ", i, s[i]);
        }
        System.out.println();
    }
}
