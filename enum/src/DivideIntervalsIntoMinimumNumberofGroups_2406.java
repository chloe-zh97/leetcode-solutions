import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class DivideIntervalsIntoMinimumNumberofGroups_2406 {
    public static void main(String[] args) {
        int[][] intervals = {
                {1,3},
                {5,6},
                {8,10},
                {11,13}
        };
        System.out.println(minGroups(intervals));
    }

    /**
     * Method 1: 差分数组，找最多的交集个数
     * */
    public static int minGroups(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int[] inter: intervals) {
            map.merge(inter[0], 1, Integer::sum);
            map.merge(inter[1]+1, -1, Integer::sum);
        }

        // 寻找最大的 flag
        int s = 0, ans = 1;
        for(int k: map.keySet()) {
            s += map.get(k);
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
