import java.util.*;

public class MeetingRoomsII_131 {
    public static void main(String[] args) {

    }

    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Method 1: 前缀和
     * */
    public static int minMeetingRooms(List<Interval> intervals) {
        int[] suffix = new int[1000005];
        for(Interval inter: intervals) {
            int start = inter.start;
            int end = inter.end;
            suffix[start]++;
            suffix[end]--;
        }

        int ans = 0, sum = 0;
        for(int i=0;i<suffix.length;i++) {
            sum += suffix[i];
            ans = Math.max(sum, ans);
        }

        return ans;
    }

    /**
     * Method: 用hashMap 替换数组
     * */
    public static int minMeetingRooms_2(List<Interval> intervals) {
        Map<Integer, Integer> suffixMap = new TreeMap<>();

        for(Interval inter: intervals) {
            suffixMap.merge(inter.start, 1, Integer::sum);
            suffixMap.merge(inter.end, -1, Integer::sum);
        }

        int sum = 0, ans = 0;
        for(Map.Entry<Integer, Integer> entry: suffixMap.entrySet()) {
            sum += entry.getValue();
            ans = Math.max(ans, sum);
        }
        return ans;
    }


    /**
     * Method 2: 双指针，将 start 和 end 拆分成两个数组
     * */
    public static int minMeetingRooms_3(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];

        for(int i=0;i<n;i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }

        // sort
        Arrays.sort(start);
        Arrays.sort(end);

        int ans = 0, cnt = 0;

        int i = 0, j = 0;
        while(i < n) {
            if(start[i] < end[j]) {
                cnt++;
                i++;
            } else {
                cnt--;
                j++;
            }
            ans = Math.max(cnt, ans);
        }
        return ans;
    }

}
