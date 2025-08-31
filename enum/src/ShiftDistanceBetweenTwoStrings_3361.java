public class ShiftDistanceBetweenTwoStrings_3361 {
    public static void main(String[] args) {
       String s = "leet";
       String t = "code";
       int[] nextCost = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
       int[] previousCost = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println(shiftDistance(s,t,nextCost,previousCost));
    }


    /**
     * Method 1: 前缀和，左闭右开 【)
     * a -> b -> c, a->b 的值在 b 里面， b->c 的值在 c 里面，所以计算的是 [a,c)
     * a <- b <- c, b->a 的值在 b 里面，c->b 的值在 c 里面，计算的是 [b,c+1)
     * */
    public static long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int SIGMA = 26;
        // 两倍，处理循环
        long[] nextSum = new long[SIGMA*2+1];
        long[] preSum = new long[SIGMA*2+1];

        for(int i=0;i<2*SIGMA;i++) {
            nextSum[i+1] = nextSum[i] + nextCost[i%SIGMA]; // b->c的代价存储在 c 的位置
            preSum[i+1] = preSum[i] + previousCost[i%SIGMA];  // b->a的代价存储在c的位置
        }

        long ans = 0L;
        for(int i=0;i<s.length();i++) {
            int fr = s.charAt(i)-'a';  // a
            int to = t.charAt(i)-'a';  // c
            long d1 = 0, d2 = 0;
            // next 方向， a->c, [a,c)， a->b 的 cost 存的在 a 里
            d1 = to < fr ? nextSum[to+SIGMA] - nextSum[fr] : nextSum[to] - nextSum[fr];
            // pre 方向， b->a 的 cost 存的在 c 里
            d2 = fr < to ? preSum[fr+SIGMA+1] - preSum[to+1]: preSum[fr+1] - preSum[to+1];
            ans += Math.min(d1, d2);
        }
        return ans;
    }

}
