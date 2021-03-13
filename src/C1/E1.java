package C1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class E1 {
    public static final int n = 30000;
    public static final int k = n / 2;

    public static int[] createArr() {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        List<Integer> list = Arrays.asList(arr);
        Collections.shuffle(list);
        int[] res = list.stream().mapToInt(Integer::valueOf).toArray();

        return res;
    }

    public static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    public static int ListSort(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());;
        Collections.sort(list);
        
        return list.get(list.size()/2 - 1);
    }


    public static int BubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }

        return arr[arr.length/2 - 1];
    }

    public static int KBubbleSort(int[] arr) {
        int[] tempArr = Arrays.copyOfRange(arr, 0, k);
        BubbleSort(tempArr);

        for (int i = k; i < n; i++) {
            if (arr[i] < tempArr[k - 1]) {
                arrInsert(tempArr, arr[i]);
            }
        }

        return tempArr[k - 1];
    }

    public static void arrInsert(int[] arr, int num) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (num < arr[i]) {
                index = i;
                break;
            }
        }


        for (int i = arr.length - 1; i >= index + 1; i--) {
            arr[i] = arr[i -1];
        }
        arr[index] = num;
    }

    public static void main(String[] args) throws Exception {
        int[] arr;
        int res;
        long start, end;
        // arr = createArr();
        // start = System.currentTimeMillis();
        // res = BubbleSort(arr);
        // end = System.currentTimeMillis();
        // System.out.printf("%d time: %d \n", res, end - start);

        // arr = createArr();
        // start = System.currentTimeMillis();
        // res = KBubbleSort(arr);
        // end = System.currentTimeMillis();
        // System.out.printf("%d time: %d \n", res, end - start);

        arr = createArr();
        
        start = System.currentTimeMillis();
        res = ListSort(arr);
        end = System.currentTimeMillis();
        System.out.printf("%d time: %d \n", res, end - start);
    }
}
