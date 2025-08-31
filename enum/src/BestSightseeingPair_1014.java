public class BestSightseeingPair_1014 {
    public static void main(String[] args) {
        int[] values = {1,5,3,2,2};
        System.out.println(maxScoreSightseeingPair_3(values));
        System.out.println(maxScoreSightseeingPair_4(values));
    }

    /**
     * Method 1: 暴力法
     * */
    public static int maxScoreSightseeingPair(int[] values) {
        // score : values[i] + values[j] + i - j
        int n = values.length;
        int ans = Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                ans = Math.max(ans, values[i]+values[j]+i-j);
            }
        }
        return ans;
    }

    /**
     * Method 2: 枚举右边j, 维护左边i
     * 遍历 j, 同时存储 values[i] + i 的最大值
     * */
    public static int maxScoreSightseeingPair_2(int[] values) {
        int n = values.length;
        int maxI = values[0];
        int ans = Integer.MIN_VALUE;

        for(int i=1;i<n;i++) {
            int curJ = values[i]-i;
            ans = Math.max(ans, maxI+curJ);
            maxI = Math.max(maxI, values[i]+i);
        }
        return ans;
    }

    /**
     * 思考：所有 vi+vj+i-j 的和是多少
     * */
    public static long maxScoreSightseeingPair_3(int[] values) {
        int n = values.length;
        // 存储所有 vi+i 的值
        long sumI = values[0]; // (a+b+c)  // d  // 新增 a+d+b+d+c+d
        long ans = 0L;
        int cnt = 0;
        for(int i=1;i<n;i++) {
            ++cnt;
            int curJ = values[i]-i;
            ans += sumI + (long)curJ * cnt;
            sumI += values[i]+i;
        }
        return ans;
    }

    public static long maxScoreSightseeingPair_4(int[] values) {
        int n = values.length;
        int ans = 0;
        int pre = values[0];
        for (int i = 1; i < n; i++) {
            ans += (values[i] - i) * i + pre; // i之前所有元素跟i共形成i对运算
            pre += values[i] + i;
        }
        return ans;
    }

}
