import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberofArrowstoBurstBalloons_452 {
    public static void main(String[] args) {
        int[][] points = {
                {-2147483646,-2147483645},
                {2147483646,2147483647}
        };
        System.out.println(findMinArrowShots_2(points));
    }

    public static int findMinArrowShots(int[][] points) {
        int n = points.length;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] < o2[0]) return -1;
                else if(o1[0] == o2[0]) return 0;
                else return 1;
            }
        });


        // merge array
        int[] pre = points[0];
        int cnt = 0;
        for(int i=1;i<n;i++) {
            int[] cur = points[i];
            if(cur[0] <= pre[1]) {
                System.out.println("cur0="+cur[0]+" pre1="+pre[1]);
                cnt++;
                cur[1] = Math.min(cur[1], pre[1]);
            }
            pre = cur;
        }
        return n - cnt;
    }

    public static int findMinArrowShots_2(int[][] points) {
        int n = points.length;
        // 按照右端点从小到大排序
        Arrays.sort(points, Comparator.comparingInt(p->p[1]));

        long pre = Long.MIN_VALUE;
        int cnt = 0;
        for(int i=0;i<n;i++) {
            if(points[i][0] > pre) {
                cnt++;
                pre = points[i][1];
            }
        }
        return cnt;
    }
}
