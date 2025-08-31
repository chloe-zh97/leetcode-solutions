import java.util.Arrays;

public class SpecialArrayII_3152 {
    public static void main(String[] args) {
        int[] nums = {3,4,1,2,6};
        int[][] queries = {
                {0,4}
        };
        System.out.println(Arrays.toString(isArraySpecial(nums, queries)));
    }

    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        boolean[] ans = new boolean[m];

        // s[i]: 以 i 结尾的最长的 special 序列的起点在哪里
        int[] s = new int[n];
        for(int i=1;i<s.length;i++) {
            if(nums[i]%2 != nums[i-1]%2) {
                // 和前一个数奇偶不同
                s[i] = s[i-1];
            } else {
                s[i] = i;
            }
        }

        // System.out.println(Arrays.toString(s));
        for(int i=0;i<m;i++) {
            int start = queries[i][0], end = queries[i][1];
            ans[i] = s[start] == s[end];
        }
        return ans;
    }
}
