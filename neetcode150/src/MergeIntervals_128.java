import java.util.*;

public class MergeIntervals_128 {
    public static void main(String[] args) {
        int[][] intervals = {
                {2,3},
                {2,2},
                {3,3},
                {1,3},
                {5,7},
                {2,2},
                {4,6}
        };
        int[][] ans = merge(intervals);
        for(int[] a: ans)
            System.out.println(Arrays.toString(a));
    }

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, new Comparator<int[]>() {
           @Override
           public int compare(int[] a, int[] b) {
               if(a[0] == b[0]) return a[1]-b[1];
               return a[0]-b[0];
           }
        });

        List<int[]> list = new LinkedList<>();

        for(int i=0;i<n;i++) {
            if(list.isEmpty()) list.add(intervals[i]);
            else {
                int[] pre = list.get(list.size()-1);
                if(intervals[i][0] > pre[1]) list.add(intervals[i]);
                else {
                    intervals[i][0] = Math.min(intervals[i][0], pre[0]);
                    intervals[i][1] = Math.max(intervals[i][1], pre[1]);

                    list.set(list.size()-1, intervals[i]);
                }
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
