import java.util.HashMap;

public class CountNumberofBadPairs_2364 {
    public static void main(String[] args) {
        int[] nums = {4,1,3,3};
        System.out.println(countBadPairs_2(nums));
    }

    /**
     * Method 1: 暴力法 O(n^2)
     * */
    public static long countBadPairs(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                if(j-i != nums[j] - nums[i]) ++cnt;
            }
        }
        return cnt;
    }

    /**
     * Method 2: 转换
     * j-i != nums[j] - nums[i] 即 j-nums[j] != i-nums[i]
     * 一共有 n*(n-1)/2 个数对
     * j-nums[j] == i-nums[i] 的数对有多少个？
     * 设 a[i] = i-nums[i]
     * */
    public static long countBadPairs_2(int[] nums) {
        int n = nums.length;
        // 一共有多少个数对
        // n-1, n-2, ..., 1
        long total = (long) n * (n-1) / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 0,1,2,3,4
        // 1,2,3,4,5
        // -1,-1,-1,-1,-1
        long ans = 0;
        for(int i=0;i<n;i++) {
            int v = i-nums[i];
            // 当前这个 nums[i], 和 map 中存储的进行配对
            if(map.containsKey(v)) {
                ans += map.get(v);
            }
            map.merge(v, 1, Integer::sum);
        }
        return total - ans;
    }

}
