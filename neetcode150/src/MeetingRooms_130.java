import java.util.Collections;
import java.util.List;

public class MeetingRooms_130 {
    public static void main(String[] args) {

    }

    static class Interval {
      public int start, end;
      public Interval(int start, int end) {
          this.start = start;
          this.end = end;
      }
  }

    public static boolean canAttendMeetings(List<Interval> intervals) {
        // 按照开始时间升序排列
        Collections.sort(intervals, (a,b) ->a.start-b.start);

        if(intervals.size() < 2) return true;

        Interval pre = intervals.get(0);

        for(int i=1;i<intervals.size();i++) {
            Interval cur = intervals.get(i);
            if(pre.end > cur.start) return false;
            pre = cur;
        }
        return true;
    }
}
