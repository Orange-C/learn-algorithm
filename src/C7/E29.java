package C7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E29 {
    public static int[] createArr(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        List<Integer> list = Arrays.asList(arr);
        Collections.shuffle(list);
        int[] res = list.stream().mapToInt(Integer::valueOf).toArray();

        return res;
    }

    public static void quickSelect(int nums[], int low, int high, int k) {
        if (low >= high) return;
        // sort when len = 2
        if (high - low == 1) {
            if (nums[high] < nums[low]) {
                swap(nums, low, high);
            }
            return;
        }
        
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

        // enough for len = 3 
        if (high - low == 2) return;

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

        // restore pivot to i, the right position
        swap(nums, i, high - 1);

        if(k <= i) {
            quickSelect(nums, low, i-1, k);
        }

        if(k > i + 1) {
            quickSelect(nums, i+1, high, k);
        }

        if(k == i + 1) {
            return;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if(i==j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = createArr(30);
        // int[] arr = {9, 21, 17, 15, 14, 27, 5, 23, 26, 10, 18, 25, 0, 16, 12, 11, 13, 29, 19, 28, 24, 3, 6, 4, 8, 1, 20, 7, 2, 22};
        int k = 17;
        quickSelect(arr, 0, arr.length-1, k);
        int res = arr[k - 1];
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
    }
}
