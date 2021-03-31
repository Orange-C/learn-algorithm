package C7;

import java.util.Arrays;

public class E16 {
    public static final int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
    public static int[] tmp = new int[arr.length];

    public static void mergeSort(int[] nums) {
        for(int gap = 2; gap < 2*nums.length; gap *= 2) { // run the final loop when gap > nums.length
            for(int i = 0; i < nums.length; i += gap){
                mergeSort(nums, i, i+gap-1);
            }
        }
    }

    public static void mergeSort(int[] nums, int low, int high) {
        if(high >= nums.length) high = nums.length - 1;
        if(low == high) return;

        int center = (low + high)/2;
        int idx = low;
        int i = low;
        int j = center + 1;
        while (i <= center && j <= high) {
            if (nums[i] <= nums[j]) {
                tmp[idx++] = nums[i++]; 
            } else {
                tmp[idx++] = nums[j++]; 
            }
        }

        while(i <= center) {
            tmp[idx++] = nums[i++]; 
        }

        while(j <= high) {
            tmp[idx++] = nums[j++]; 
        }

        for(i = low; i <= high; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void main(String[] args) throws Exception {
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
