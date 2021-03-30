package C7;

import java.util.Arrays;

public class E1 {
    public static final int[] arr = {3,1,4,1,5,9,2,6,5};

    public static void insertionSort(int[] nums) {
        int j;
        for(int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            for (j = i; j > 0 && tmp < nums[j - 1]; j--) { // tmp >= nums[j-1] means nums[i] has found its position for (0, j)
                nums[j] = nums[j-1]; // push elements backwards
            }
            // assign nums[i] to the right position
            nums[j] = tmp;
        }
    }

    public static void main(String[] args) throws Exception {
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
