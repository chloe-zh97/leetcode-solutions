import java.util.TreeMap;

public class MyCalendarII_731 {
    public static void main(String[] args) {

    }

    /**
     * 重复的 event 不能 >=3 个
     * */
    class MyCalendarTwo {
        TreeMap<Integer, Integer> map;

        public MyCalendarTwo() {
            map = new TreeMap<>();
        }

        public boolean book(int startTime, int endTime) {
            // [start, end)
            map.merge(startTime, 1, Integer::sum);
            map.merge(endTime, -1, Integer::sum);
            // 寻找最大的标记
            int s = 0, ans = 0;
            for(int k: map.keySet()) {
                s += map.get(k);
                ans = Math.max(ans, s);
                if(ans > 2) {
                    // 还原回去
                    map.merge(startTime, -1, Integer::sum);
                    map.merge(endTime, 1, Integer::sum);
                    return false;
                }
            }
            return true;
        }
    }

}
