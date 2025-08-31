import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SumofDistances_2615 {
    public static void main(String[] args) {
        int[] nums = {0,5,3};
        long[] ans = distance(nums);
        System.out.println(Arrays.toString(ans));
    }

    public static long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];

        // group map
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            List<Integer> row = map.getOrDefault(nums[i], new ArrayList<>());
            row.add(i);
            map.put(nums[i], row);
        }

        long[] s = new long[n+1];

        for(List<Integer> g: map.values()) {
            int m = g.size();

            for(int i=1;i<=m;i++)
                s[i] = s[i-1] + g.get(i-1);

            for(int i=0;i<m;i++) {
                // index
                int target = g.get(i);
                long left = (long) i*target - s[i];
                long right = s[m] - s[i] - (long) (m-i) * target;
                ans[target] = left + right;
            }
        }
        return ans;
    }
}
