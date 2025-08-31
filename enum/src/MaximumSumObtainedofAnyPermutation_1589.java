import java.util.Arrays;

public class MaximumSumObtainedofAnyPermutation_1589 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,10};
        int[][] requests = {
                {0,2},
                {1,3},
                {1,1}
        };
        System.out.println(maxSumRangeQuery(nums, requests));
        System.out.println(maxSumRangeQuery_2(nums, requests));
    }

    public static int maxSumRangeQuery(int[] nums, int[][] requests) {
        int m = requests.length, n = nums.length;
        Arrays.sort(nums);

        // construct diff array
        int[] d = new int[n+1];
        for(int i=0;i<m;i++) {
            int from = requests[i][0], to = requests[i][1];
            d[from]++;
            d[to+1]--;
        }

        // 每个 index 出现的次数
        int[] cnt = new int[n];
        int s = 0;
        for(int i=0;i<n;i++) {
            s += d[i];
            cnt[i] = Math.max(s, 0);
        }
        Arrays.sort(cnt);
        System.out.println("cnt:"+ Arrays.toString(cnt));

        long ans = 0;
        final int mod = (int)1e9+7;
        for(int i=0;i<n;i++) {
            ans += (long) nums[i] * cnt[i];
        }

        return (int)(ans % mod);
    }

    /**
     * Method 2: 省去 cnt
     * */
    public static int maxSumRangeQuery_2(int[] nums, int[][] requests) {
        int m = requests.length, n = nums.length;
        Arrays.sort(nums);

        // construct diff array
        int[] d = new int[n+1];
        for(int i=0;i<m;i++) {
            int from = requests[i][0], to = requests[i][1];
            d[from]++;
            d[to+1]--;
        }

        for(int i=1;i<n;i++)
            d[i] += d[i-1];
        Arrays.sort(d);

       // System.out.println("d:"+ Arrays.toString(d));

        long ans = 0;
        final int mod = (int)1e9+7;

        for(int i=0;i<n;i++) {
            ans += (long) Math.max(d[i+1], 0) * nums[i];
        }
        return (int)(ans % mod);
    }
}
