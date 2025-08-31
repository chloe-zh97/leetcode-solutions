import java.util.Arrays;

public class ZeroArrayTransformationII_3356 {
    public static void main(String[] args) {
        int[] nums = {10};
        int[][] queries = {
                {0,0,5},
                {0,0,3},
                {0,0,2}
        };
        System.out.println(minZeroArray_2(nums, queries));
    }

    /**
     * Method 1: 54%
     * */
    public static int minZeroArray(int[] nums, int[][] queries) {
        int m = queries.length;

        int curMax = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++)
            curMax = Math.max(curMax, nums[i]);
        if(curMax <= 0) return 0;

        int left = 0, right = m-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(check(nums, queries, mid)) right = mid;
            else left = mid + 1;
        }
        return check(nums, queries, left) ? left+1 : -1;
    }

    /**
     * queries[0,k] 是否能将 nums 中的所有数变成 <= 0
     * */
    private static boolean check(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] diff = new int[n+1];
        for(int i=0;i<=k;i++) {
            int from = queries[i][0], to = queries[i][1], val = queries[i][2];
            diff[from] -= val;
            diff[to+1] += val;
        }

        System.out.println("diff:"+ Arrays.toString(diff)+ " k="+k);
        int s = 0;
        // 还原 nums
        for(int i=0;i<n;i++) {
            s += diff[i];
            if(nums[i] + s > 0) return false;
        }
        return true;
    }

    /**
     * Method 2：差分 + 双指针
     * */
    public static int minZeroArray_2(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] diff = new int[n+1];
        int s = 0, k = 0;

        for(int i=0;i<n;i++) {
            // 累积减少
            s += diff[i];
            while(k < m && s < nums[i]) {
                int from = queries[k][0], to = queries[k][1], val = queries[k][2];
                diff[from] += val;
                diff[to+1] -= val;
                if(i>=from && i <= to) {
                    s += val;
                }
                k++;
            }

            if(s < nums[i]) return -1;
        }
        return k;
    }
}
