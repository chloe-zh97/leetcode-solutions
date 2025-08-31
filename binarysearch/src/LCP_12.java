
public class LCP_12 {
    public static void main(String[] args) {
        int[] time = {1,2,3,3};
        int m = 2;
        System.out.println(minTime(time, m));
    }

    public static int minTime(int[] time, int m) {
        int left = 0, right = (int)1e9;
        while(left < right) {
            int mid = left + right >> 1;
            if(check(time, mid, m)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * time 序列，是否能划分为 < m 个时间段
     * */
    private static boolean check(int[] time, int t, int m) {
        int n = time.length;
        // day 从0开始计数, 满足条件的情况是 day < m
        int sum = 0, maxTime = 0, day = 1;

        for(int i=0;i<n;i++) {
            sum += time[i];
            maxTime = Math.max(maxTime, time[i]);
            if(sum - maxTime > t) {
                day++;
                sum = time[i];
                maxTime = time[i];
            }
            if(day > m) return false;
        }
        return day <= m;
    }
}
