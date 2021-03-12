package C2;

public class E8 {
    public static int size = 10;
    public static void main(String[] args) throws Exception {
        int[] arr = new int[10];
        int i;
        for (i = 1; i <= size; i++) {
            arr[i-1] = i;
        }

        for (i = 0; i < size; i++) {
            int ran = (int)(Math.random()*(i+1));
            int temp = arr[i];
            arr[i] = arr[ran];
            arr[ran] = temp; 
        }

        for(int j : arr) {
            System.out.println(j);
        }
    }
}
