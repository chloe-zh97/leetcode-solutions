public class CountPairsThatFormaCompleteDayII_3185 {
    public static void main(String[] args) {
        int[] hours = {72,48,24,3};
        System.out.println(countCompleteDayPairs(hours));
    }

    /**
     * 对应 hours[i], 比如 hours[i] = 13, 需要找 mod 24 为11的数，
     * 比如 11, 35
     *
     * (24-hours[i]%24)%24
     * */
    public static long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        long[] cnt = new long[24];
        long ans = 0L;
        for(int i=0;i<n;i++) {
            int v = (24-hours[i]%24)%24;
            ans += cnt[v];
            cnt[hours[i]%24]++;
        }

        return ans;
    }
}
