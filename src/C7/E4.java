package C7;

import java.util.Arrays;

public class E4 {
    public static final int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    public static final int[] gap = {7, 3, 1};

    public static void shellSort(int[] nums) {
        for(int gapPos = 0; gapPos < gap.length; gapPos++) {
            int currentGap = gap[gapPos];
            int j;
            for(int i = currentGap; i < nums.length; i++) {
                int tmp = nums[i];
                for (j = i; j >= currentGap && tmp < nums[j - currentGap]; j -= currentGap) { // the same as insertionSort, set step to currentGap
                    nums[j] = nums[j-currentGap];
                }
                nums[j] = tmp;
            }
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void main(String[] args) throws Exception {
        shellSort(arr);
    }
}
