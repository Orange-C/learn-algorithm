package C2;

import java.util.*;

public class E17 {
    public static int[] arr = {4, -1, -1, 1, -1, 2, -6, -2};

    public static int minSubSum(int[] nums) {
        int thisSum = 0;
        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            thisSum += nums[i];

            if(thisSum < minSum) {
                minSum = thisSum;
            } else if (thisSum > 0) {
                thisSum = 0;
            }
        }

        return minSum;
    }

    // TODO
    public static int minPositiveSubSum(int[] nums) {
        int thisSum = 0;
        int MPSS = Integer.MAX_VALUE;

        TreeMap<PrefixSum, Integer> sumMap = new TreeMap<>();
        for(int i = 0; i < nums.length; i++) {
            thisSum += nums[i];
            if (thisSum > 0) MPSS = Math.min(thisSum, MPSS);
            PrefixSum prefixSum = new PrefixSum();
            prefixSum.value = thisSum;
            prefixSum.index = i;

            PrefixSum lastMax = sumMap.lowerKey(prefixSum);
            if (lastMax != null) {
                int lastMaxValue = lastMax.value;
                MPSS = Math.min(thisSum - lastMaxValue, MPSS);
            }

            sumMap.put(prefixSum, i);
        }

        return MPSS;
    }

    public static int maxSubProduct(int[] nums) {
        int thisPro = 1;
        int firstNegPos = -1;
        int firstNegPro = 1;
        int lastNegPos = -1;
        int lastNegPro = 1;
        for(int i = 0; i < nums.length; i++) {
            thisPro *= nums[i];

            if (firstNegPos == -1 && nums[i] < 0) {
                firstNegPos = i;
                firstNegPro = thisPro;
            }

            if (nums[i] < 0) {
                lastNegPos = i;
            }
        }

        if (thisPro > 0) {
            return thisPro;
        } else {
            int cutLeft = thisPro / firstNegPro;
            for(int i = nums.length - 1; i >= lastNegPos; i--) {
                lastNegPro *= nums[i];
            }
            int cutRight = thisPro / lastNegPro;
            
            return Math.max(cutLeft, cutRight);
        }
    }

    public static void main(String[] args) throws Exception {
        int res = minSubSum(arr);
        int res2 = minPositiveSubSum(arr);
        int res3 = maxSubProduct(arr);
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
    }
}


class PrefixSum implements Comparable<PrefixSum> {
    public int value;
    public int index;

    @Override
    public int compareTo(PrefixSum a) {
        if (this.value > a.value) {
            return 1;
        } else if (this.value < a.value) {
            return -1;
        } else if (this.value == a.value) {
            if (this.index < a.index) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }
}