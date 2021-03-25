package C4;

public class E37 {
    public static void outputValue(Node t, int low, int high) {
        if (t != null) {
            if (t.value > low) outputValue(t.left, low, high);
            if (t.value >= low && t.value <= high) System.out.println(t.value);
            if (t.value < high) outputValue(t.right, low, high);
        }
    }

    public static void main(String[] args) throws Exception {
        
    }
}

