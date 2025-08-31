import java.util.ArrayList;
import java.util.List;

public class MinimumPositiveSumSubarray_3364 {
    public static void main(String[] args) {
        int[] nums = {-2, 2, -3, 1};
        int l = 2, r = 3;

        List<Integer> nns = new ArrayList<>();
        for(int num: nums)
            nns.add(num);

        System.out.println(minimumSumSubarray(nns, l, r));
    }
    public static int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] s = new int[n+1];
        for(int i=1;i<s.length;i++) {
            s[i] = s[i-1] + nums.get(i-1);
        }

        int ans = Integer.MAX_VALUE;
        for(int k=l;k<=r;k++) {
            for(int i=k;i<s.length;i++) {
                // 0,1,2,3
                if(s[i] > s[i-k]) ans = Math.min(s[i]-s[i-k], ans);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 :ans;
    }

}
