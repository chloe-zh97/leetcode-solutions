import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumOperationstoMakeAllArrayElementsEqual_2602 {
    public static void main(String[] args) {
        int[] nums = {2,9,6,3};
        int[] queries = {10};
        List<Long> ans = minOperations(nums, queries);
        System.out.println(ans);
    }

    public static List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        List<Long> ans = new ArrayList<>();

        // sort 3,1,6,8
        // 1,3,6,8
        Arrays.sort(nums);

        long[] s = new long[n+1];
        for(int i=1;i<s.length;i++)
            s[i] = s[i-1] + nums[i-1];

        for(int i=0;i<m;i++) {
            // all elment change to target
            int target = queries[i];

            // 二分找到 >= target 的位置
            int pos = binarySearchGreaterThan(nums, target);

            // pos-1 为 < target 的位置
            long left = (long)target * pos - s[pos];
            long right = s[n] - s[pos] - (long)(n-pos) * target;
            ans.add(left+right);
        }

        return ans;
    }

    /**
     * 在 nums 中找 >= target 的第一个位置
     * */
    private static int binarySearchGreaterThan(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return nums[left] >= target ? left : n;
    }
}
