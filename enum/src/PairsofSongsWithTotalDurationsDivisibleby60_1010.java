import java.util.HashMap;

public class PairsofSongsWithTotalDurationsDivisibleby60_1010 {
    public static void main(String[] args) {
        int[] nums = {60,60,60};
        System.out.println(numPairsDivisibleBy60(nums));
    }

    /**
     * 对于 time[i], 左边有多少个模60是 60-time[i] 的数
     * time[i] = 1, 左边有多少个模是59的数
     * time[i] = 2, 左边有多少个模是58的数
     * */
    public static int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        int ans = 0;
        for(int i=0;i<n;i++) {
            int v = (60 - time[i] % 60) % 60;
            ans += map.getOrDefault(v, 0);
            map.merge(time[i]%60, 1, Integer::sum);
        }
        return ans;
    }


    /**
     * 优化
     * */
    public static int numPairsDivisibleBy60_2(int[] time) {
        int n = time.length;
        int[] cnt = new int[60];
        int ans = 0;
        for(int i=0;i<n;i++) {
            int v = (60 - time[i] % 60) % 60;
            ans += cnt[v];
            cnt[time[i]%60]++;
        }
        return ans;
    }
}
