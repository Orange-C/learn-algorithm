package C7;

import java.util.Arrays;

public class E19 {
    public static final int[] arr = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 };
    public static final int CUTOFF = 3;

    public static void insertionSort(int[] nums, int low, int high) {
        int j;
        for(int i = low + 1; i <= high; i++) {
            int tmp = nums[i];
            for (j = i; j > 0 && tmp < nums[j - 1]; j--) { // tmp >= nums[j-1] means nums[i] has found its position for (0, j)
                nums[j] = nums[j-1]; // push elements backwards
            }
            // assign nums[i] to the right position
            nums[j] = tmp;
        }
    }

    public static void quickSort(int[] nums, int low, int high) {
        if (high - low + 1 > CUTOFF) {
            int center = (low + high)/2;

            // find the pivot
            if(nums[center] < nums[low]) {
                swap(nums, low, center);
            }

            if(nums[high] < nums[low]) {
                swap(nums, low, high);
            }

            if(nums[center] > nums[high]) {
                swap(nums, center, high);
            }

            // set pivot to high - 1
            swap(nums, center, high - 1);

            int i = low, j = high - 1;
            for(;;) {
                while(nums[++i] < nums[high - 1]) {}
                while(nums[--j] > nums[high - 1]) {}

                if (i < j) {
                    swap(nums, i, j);
                } else { // break loop when i cross j
                    break;
                }
            }

            // set pivot to i, the right position
            swap(nums, i, high - 1);

            quickSort(nums, low, i - 1);
            quickSort(nums, i + 1, high);
        } else {
            insertionSort(nums, low, high);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) throws Exception {
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
