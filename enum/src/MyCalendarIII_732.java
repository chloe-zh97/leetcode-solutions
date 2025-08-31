import java.util.TreeMap;

public class MyCalendarIII_732 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: 差分数组
     * */
    class MyCalendarThree {

        TreeMap<Integer, Integer> map;
        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int startTime, int endTime) {
            // [start, end), 更新 diff dict
            map.merge(startTime, 1, Integer::sum);
            map.merge(endTime, -1, Integer::sum);

            // 统计最大标记
            int ans = 0, s = 0;
            for(int k: map.keySet()) {
                int v = map.get(k);
                s += v;
                ans = Math.max(ans, s);
            }
            return ans;
        }
    }
}
