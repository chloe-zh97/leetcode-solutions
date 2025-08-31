import java.util.Arrays;

public class PlatesBetweenCandles_2055 {
    public static void main(String[] args) {
        String s = "***|**|*****|**||**|*";
        int[][] queries = {
                {1,17},
                {4,5},
                {14,17},
                {5,11},
                {15,16}
        };
        int[] ans = platesBetweenCandles(s, queries);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 前缀和 后缀和，维护 i 位置左边最近的 candle 和 i 位置右边最近的 candle 位置
     * */
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int m = queries.length;
        char[] chs = s.toCharArray();

        // 前缀和，sum[i+1]: s[0..i] 的 plate 数量
        int[] sum = new int[n+1];
        int[] left = new int[n];

        // 最近的蜡烛位置
        int p = -1;
        for(int i=0;i<n;i++) {
           if(chs[i] == '|') {
               p = i;
               sum[i+1] = sum[i];
           }
           else {
               // is plate
               sum[i+1] = sum[i] + 1;
           }

           left[i] = p;
        }

        int[] right = new int[n];
        p = -1;
        for(int i=n-1;i>=0;i--) {
            if(chs[i] == '|') p = i;
            right[i] = p;
        }

        int[] ans = new int[m];
        for(int i=0;i<m;i++) {
            int l = queries[i][0], r = queries[i][1];
            l = right[l];
            r = left[r];

            if(l!=-1 && r!=-1 && r > l) {
                ans[i] = sum[r+1] - sum[l];
            }
        }
        return ans;
    }
}
