import java.util.TreeMap;

public class MyCalendarI_729 {
    public static void main(String[] args) {

    }

    class MyCalendar {
        TreeMap<Integer, Integer> map;
        public MyCalendar() {
            map = new TreeMap<>();
        }

        public boolean book(int startTime, int endTime) {
            map.merge(startTime, 1, Integer::sum);
            map.merge(endTime, -1, Integer::sum);

            int s = 0;
            // 统计最大 flag
            for(int k: map.keySet()) {
                s += map.get(k);
                if(s > 1) {
                    map.merge(startTime, -1, Integer::sum);
                    map.merge(endTime, 1, Integer::sum);
                    return false;
                }
            }
             return true;
        }
    }
}
