import java.util.Arrays;

public class CheckifAlltheIntegersinaRangeAreCovered_1893 {
    public static void main(String[] args) {
        int[][] ranges = {
                {1,1}
        };
        int left = 50, right = 50;
        System.out.println(isCovered(ranges, left, right));
    }

    /**
     * Method 1: 暴力法
     * */
    public static boolean isCovered_2(int[][] ranges, int left, int right) {
        int n = ranges.length;
        final int N = 55;
        boolean[] visit = new boolean[N];
        for(int i=0;i<n;i++) {
            for(int j=ranges[i][0];j<=ranges[i][1];j++)
                visit[j] = true;
        }

        for(int i=left;i<=right;i++) {
            if(!visit[i]) return false;
        }
        return true;
    }

    public static boolean isCovered(int[][] ranges, int left, int right) {
        int n = ranges.length;
        int MAXVAL = Integer.MIN_VALUE;
        // 寻找最大值
        for(int i=0;i<n;i++) {
            MAXVAL = Math.max(MAXVAL, ranges[i][1]);
        }

        // construct diff array
        int[] d = new int[MAXVAL+2];
        for(int i=0;i<n;i++) {
            int a = ranges[i][0];
            int b = ranges[i][1];
            d[a]++;
            d[b+1]--;
        }

        int s = 0, cnt = 0;
        for(int i=0;i<d.length;i++) {
            s += d[i];
            if(i >= left && i <= right && s > 0) cnt++;
        }

        return cnt == right-left+1;
    }
}
