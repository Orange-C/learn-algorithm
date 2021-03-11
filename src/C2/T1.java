package C2;

public class T1 {
    public static int[] arr = {4, -3, 5, -2, -1, 2, 6, -2};
    public static MaxResult division(int[] nums, int low, int high) {
        MaxResult result = new MaxResult();
        if (high - low == 1) {
            result.totalSum = nums[low] + nums[high];
            result.lMax = result.totalSum > nums[low] ? result.totalSum : nums[low];
            result.rMax = result.totalSum > nums[high] ? result.totalSum : nums[high];
            result.resMax = Math.max(Math.max(result.totalSum, nums[low]), nums[high]);
        } else {
            int mid = (high + low) / 2;
            int tempRes;
            MaxResult leftResult = division(nums, low, mid);
            MaxResult rightResult = division(nums, mid + 1, high);
            result.totalSum = leftResult.totalSum + rightResult.totalSum;

            tempRes = leftResult.totalSum + rightResult.lMax;
            result.lMax = tempRes > leftResult.lMax ? tempRes : leftResult.lMax;
            tempRes = rightResult.totalSum + leftResult.rMax;
            result.rMax = tempRes > rightResult.rMax ? tempRes : rightResult.rMax;
            result.resMax = Math.max(Math.max(leftResult.resMax, rightResult.resMax), leftResult.rMax + rightResult.lMax);
        }

        return result;
    }

    public static int maxSubSum(int[] nums) {
        int thisSum = 0;
        int maxSum = 0;
        for(int i = 0; i < nums.length; i++) {
            thisSum += nums[i];

            if(thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) throws Exception {
        MaxResult result = division(arr, 0, arr.length - 1);
        int res = maxSubSum(arr);
        System.out.println(result.resMax < 0 ? 0 : result.resMax);
        System.out.println(res);
    }
}

class MaxResult {
    public int resMax = -Integer.MIN_VALUE;
    public int lMax = -Integer.MIN_VALUE;
    public int rMax = -Integer.MIN_VALUE;
    public int totalSum = -Integer.MIN_VALUE;
}

