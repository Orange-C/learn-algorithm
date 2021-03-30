package C7;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class E11 {
    public static final int[] arr = { 142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811, 102 };

    public static void heapSort(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>(nums.length, Collections.reverseOrder());
        int[] show = new int[nums.length];
        int i, tmp;
        for(i = 0; i < nums.length; i++) {
            q.add(nums[i]);
        }

        for(i = 1; i < nums.length; i++) {
            tmp = q.remove();
            // show routine
            showPQ(q, tmp, show);
        }
    }

    public static void showPQ(PriorityQueue<Integer> q, int tmp, int[] show) {
        Object[] tmpShow = q.toArray();
        for (int j = 0; j < tmpShow.length; j++) {
            show[j] = (int) tmpShow[j];
        }
        show[tmpShow.length] = tmp;
        System.out.println(Arrays.toString(show));
    }

    public static void main(String[] args) throws Exception {
        heapSort(arr);
    }
}
