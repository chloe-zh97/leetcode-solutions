import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval_127 {
    public static void main(String[] args) {
        int[][] intervals = {

        };
        int[] newInterval = {5,7};
        int[][] ans = insert_2(intervals, newInterval);
        for(int[] a: ans)
            System.out.println(Arrays.toString(a));
    }

    /**
     * Method 1: 二分
     * 找到 bi < target[0] 的最右边位置 c
     * 找到 ai > target[1] 的最左边位置 d
     * [c+1, d-1] 为待合并区间
     *
     * */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        int left = binarySearch_lessThan(intervals, newInterval) + 1;
        int right = binarySearch_largerThan(intervals, newInterval) - 1;
        // System.out.println("left="+left+" right="+right);

        List<int[]> list = new LinkedList<>();
        for(int i=0;i<left;i++)
            list.add(intervals[i]);


        for(int i=left;i<=right;i++) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
        }
        list.add(newInterval);

        for(int i=right+1;i<n && i>-1;i++) {
            list.add(intervals[i]);
        }

        int[][] ans = new int[list.size()][2];
        for(int i=0;i<list.size();i++)
            ans[i] = list.get(i);

        return ans;

    }

    /**
     * 左区间的右端点
     * 在 intervals 中找 [xi, yi] 区间位置 yi < target[0]
     * */
    private static int binarySearch_lessThan(int[][] intervals, int[] target) {
        if(intervals == null || intervals.length == 0) return -1;
        int left = 0, right = intervals.length-1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(intervals[mid][1] < target[0]) left = mid;
            else right = mid - 1;
        }

        return intervals[left][1] < target[0] ? left: -1;
    }

    private static int binarySearch_largerThan(int[][] intervals, int[] target) {
        if(intervals == null || intervals.length == 0) return -1;
        int left = 0, right = intervals.length-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(intervals[mid][0] <= target[1]) left = mid + 1;
            else right = mid;
        }
        return intervals[left][0] > target[1] ? left : intervals.length;
    }

    /**
     * Method 2: 不用二分
     * */
    public static int[][] insert_2(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> list = new LinkedList<>();

        for(int i=0;i<n;i++) {
            if(intervals[i][1] < newInterval[0]) {
                list.add(intervals[i]);
            } else if(intervals[i][0] > newInterval[1]) {
                // 如果从来没有走到过这里
                list.add(newInterval);
                for(int j=i;j<n;j++) {
                    list.add(intervals[j]);
                }
                return list.toArray(new int[list.size()][]);
            } else {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }

        list.add(newInterval);
        return list.toArray(new int[list.size()][]);
    }

}
