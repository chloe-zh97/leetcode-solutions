import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval_57 {
    public static void main(String[] args) {
        int[][] intervals = {
                {1,2},
                {3,5},
                {6,7},
                {8,10},
                {12,16}
        };
        int[] newInterval = {4,8};
        intervals = insert_2(intervals, newInterval);
        for(int[] in: intervals)
            System.out.print(Arrays.toString(in) +" ");
        System.out.println();

    }

    /**
     * Method 1: 二分+模拟 97%
     * */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int pos = binarySearch(intervals, newInterval);
        List<int[]> ans = new ArrayList<>();

        // [0,pos]的位置直接插入原来的元素
        for(int i=0;i<=pos;i++)
            ans.add(intervals[i]);

        ans.add(newInterval);

        for(int i=pos+1;i<n;i++)
            ans.add(intervals[i]);

        // merge
//        for(int[] a: ans)
//            System.out.print(Arrays.toString(a)+" ");
//        System.out.println();
        if(ans.size() < 2) return ans.toArray(new int[ans.size()][]);

        List<int[]> ans2 = new ArrayList<>();
        ans2.add(ans.getFirst());

        for(int i=1;i<ans.size();i++) {
            int[] pre = ans2.getLast();
            int[] cur = ans.get(i);

            if(pre[1] < cur[0]) ans2.add(cur);
            else {
                // pre[1] >= cur[0]
                cur[0] = Math.min(pre[0], cur[0]);
                cur[1] = Math.max(pre[1], cur[1]);
                ans2.removeLast();
                ans2.add(cur);
            }
        }
        return ans2.toArray(new int[ans2.size()][]);
    }

    /**
     * 在 nums 数组中寻找 <= inter[0] 的最大位置
     * */
    private static int binarySearch(int[][] nums, int[] inter) {
        if(nums.length == 0) return -1;
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right + 1 >> 1;
            if(nums[mid][0] > inter[0]) right = mid - 1;
            else left = mid;
        }
        return nums[left][0] <= inter[0] ? left : -1;
    }


    /**
     * Method 2: 分成三段
     * */
    public static int[][] insert_2(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();

        int i = 0;
        while(i < n && intervals[i][1] < newInterval[0]) {
            ans.add(intervals[i]);
            i++;
        }

        // 有交集
        while(i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        ans.add(newInterval);

        while(i<n)
            ans.add(intervals[i++]);

        return ans.toArray(new int[ans.size()][]);
    }
}
