package C8;

public class E3 {

    public static void main(String[] args) throws Exception {
        DisjointSet ds = new DisjointSet(10);
        
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

    public int find(int i) {
        if (s[i] < 0) {
            return i;
        } else {
            return s[i] = find(s[i]);
        }
    }

    public int union(int a, int b) {
        int aroot = find(a);
        int broot = find(b);

        if (s[aroot] > s[broot]) {
            s[broot] = aroot;
            s[aroot] += s[broot];
            return aroot;
        } else {
            s[aroot] = broot;
            s[broot] += s[aroot];
            return broot;
        }
    }

    public void print() {
        for(int i = 0; i < s)
    }
}
