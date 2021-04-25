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
