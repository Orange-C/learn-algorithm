
public class E6 {
    public static int count = 0;
    public static void permute(String str) {
        char[] arr = str.toCharArray();
        permute(arr, 0, arr.length);
    }

    private static void permute(char[] str, int low, int high) {
        if (low == high - 1) {
            System.out.println(str);
            count++;
        } 

        for(int i = low; i < high; i++) {
            if (i == low) {
                permute(str, low+1, high);
            } else if(i != low && str[i] != str[low]) {
                swap(str, i, low);
                permute(str, low+1, high);
                swap(str, i, low);
            }
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) throws Exception {
        permute("aabc");
        System.out.println(count);
    }
}
