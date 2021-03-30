package C7;

import java.util.Arrays;

public class E15 {
    public static final int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
    public static int[] tmp = new int[arr.length];

    public static void mergeSort(int[] nums, int low, int high) {
        if (low == high) return;

        int center = (low + high)/2;
        mergeSort(nums, low, center);
        mergeSort(nums, center + 1, high);

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
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
