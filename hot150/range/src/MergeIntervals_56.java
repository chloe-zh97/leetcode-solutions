import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals_56 {
    public static void main(String[] args) {
        int[][] intervals = {
                {1,4},
                {4,5}
        };

        intervals = merge(intervals);
        for(int[] in: intervals)
            System.out.print(Arrays.toString(in) +" ");
        System.out.println();
    }

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();

        Arrays.sort(intervals, (o1,o2)->o1[0]-o2[0]);

        ans.add(intervals[0]);
        for(int k=1;k<n;k++) {
            int[] cur = intervals[k];
            int[] pre = ans.getLast();

            if(cur[0] <= pre[1]) {
                ans.removeLast();
                cur[0] = Math.min(cur[0], pre[0]);
                cur[1] = Math.max(cur[1], pre[1]);
            }
            ans.add(cur);
        }

        int[][] res = new int[ans.size()][2];
        for(int i=0;i<ans.size();i++)
            res[i] = ans.get(i);

        return res;
    }
}
