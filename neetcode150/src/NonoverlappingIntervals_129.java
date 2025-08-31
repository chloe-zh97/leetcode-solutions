import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NonoverlappingIntervals_129 {
    public static void main(String[] args) {
        int[][] intervals = {
                {1,2},
                {2,4}
        };
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        // sort
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1]-b[1];
                return a[0] - b[0];
            }
        });

        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        int ans = 0;
        for(int i=1;i<n;i++) {
            int[] pre = list.get(list.size()-1);

            if(intervals[i][0] >= pre[1]) {
                // 没有重叠
                list.add(intervals[i]);
            } else {
                // 有重叠
                if(pre[1] > intervals[i][1]) list.set(list.size()-1, intervals[i]);
                ans++;
            }
        }
        return ans;
    }
}
