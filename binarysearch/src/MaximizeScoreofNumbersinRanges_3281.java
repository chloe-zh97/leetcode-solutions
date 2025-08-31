import java.util.Arrays;

public class MaximizeScoreofNumbersinRanges_3281 {
    public static void main(String[] args) {
        int[] start = {1000000000,0};
        int d = 1000000000;
        System.out.println(maxPossibleScore(start, d));
    }

    public static int maxPossibleScore(int[] start, int d) {
        // sort
        Arrays.sort(start);
        long left = 0, right = Integer.MAX_VALUE;

        while(left < right) {
            long mid = left + right + 1>> 1;
            if(check(start, d, mid)) left = mid;
            else right = mid - 1;
        }
        return (int)left;
    }

    private static boolean check(int[] start, int d, long score) {
        long x = Long.MIN_VALUE;

        for(int i=0;i<start.length;i++) {
            // 寻找下一个取数, 至少要为x的值
            x = Math.max(start[i], x+score);
            if(x > start[i]+d) return false;
        }
        return true;
    }

    private static boolean check_2(int[] start, int d, long score) {
        long x = start[0];
        for(int i=1;i<start.length;i++) {
            if(x+score > start[i]+d) return false;
            // 更新 x
            x = Math.max(x+score, start[i]);
        }
        return true;
    }
}
