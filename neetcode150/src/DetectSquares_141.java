import java.util.*;

public class DetectSquares_141 {


    public static void main(String[] args) {
        CountSquares countSquares = new CountSquares();
        countSquares.add(new int[]{1, 1});
        countSquares.add(new int[]{2, 2});
        countSquares.add(new int[]{1, 2});

        System.out.println(countSquares.count(new int[]{2, 1}));  // 1
        System.out.println(countSquares.count(new int[]{3, 3})); // 0
        countSquares.add(new int[]{2, 2});
        System.out.println(countSquares.count(new int[]{2, 1}));
    }

    /**
     * count: 选一个节点【x,y】,在当前所有存储的节点中，有多少种方法可以和这个点组成正方形
     * add: 将 point 加入到 map 中，可以重复
     * */

//    static class Point {
//        int x;
//        int y;
//
//        public Point() {}
//
//        public Point(int _x, int _y) {
//            x = _x;
//            y = _y;
//        }
//
//        public Point(int[] point) {
//            x = point[0];
//            y = point[1];
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(this == obj) return true;
//            if(obj == null || getClass() != obj.getClass()) return false;
//            Point other = (Point) obj;
//            return x == other.x && y == other.y;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(x,y);
//        }
//
//        @Override
//        public String toString() {
//            return "["+x+", "+y+"]";
//        }
//
//    }
//    static class CountSquares {
//
//        // row: (r, c1), (r, c2)
//        private Map<Integer, Set<Point>> rowMap;
//
//        // (r,c): count
//        public Map<Point, Integer> cntMap;
//
//        public CountSquares() {
//            rowMap = new HashMap<>();
//            cntMap = new HashMap<>();
//        }
//
//        public void add(int[] point) {
//            int r = point[0];
//
//            // 获取 r 行的所有点
//            Set<Point> set = rowMap.getOrDefault(r, new HashSet<>());
//
//            Point p = new Point(point);
//            set.add(p);
//            rowMap.put(r, set);
//            cntMap.merge(p, 1, Integer::sum);
//        }
//
//        public int count(int[] point) {
//            int r1 = point[0], c1 = point[1];
//            if(!rowMap.containsKey(r1)) return 0;
//
//            int ans = 0;
//            // 获取在这个 r 行上的所有点
//            Set<Point> set = rowMap.get(r1);
//
//            Point p1 = new Point(point);
//            // System.out.println("p1:"+ p1);
//
//            for(Point p: set) {
//              //  System.out.println("p2="+p);
//
//                int c2 = p.y;
//                if(c2 == c1) continue;
//
//                int size = Math.abs(c2-c1);
//                int n1 = cntMap.getOrDefault(p1, 1);
//                int n2 = cntMap.get(p);
//
//                Point p3 = new Point(new int[]{r1+size, c2});
//                Point p4 = new Point(new int[]{r1+size, c1});
////                int[] p3 = {r1+size, c2};
////                int[] p4 = {r1+size, c1};
//                int n3 = cntMap.getOrDefault(p3, 0);
//                int n4 = cntMap.getOrDefault(p4, 0);
//                //System.out.println("p3="+p3+" p4="+p4);
//               // System.out.println("size="+size+" n1="+n1+" n2="+n2+" n3="+n3+" n4="+n4);
//                ans += n1 * n2 * n3 * n4;
//
//                p3 = new Point(new int[]{r1-size, c2});
//                p4 = new Point(new int[]{r1-size, c1});
//                n3 = cntMap.getOrDefault(p3, 0);
//                n4 = cntMap.getOrDefault(p4, 0);
//
//                //System.out.println("p3="+p3+" p4="+p4);
//                //System.out.println("size="+size+" n1="+n1+" n2="+n2+" n3="+n3+" n4="+n4);
//                ans += n1 * n2 * n3 * n4;
//
//            }
//            return ans;
//        }
//
//    }

static class CountSquares {

    // {r: {c : cnt}}
    private Map<Integer, Map<Integer, Integer>> ptsCount;
    public CountSquares() {
        ptsCount = new HashMap<>();
    }

    public void add(int[] point) {
        Map<Integer, Integer> map = ptsCount.getOrDefault(point[0], new HashMap<>());
        map.merge(point[1], 1, Integer::sum);
        ptsCount.put(point[0], map);
    }

    public int count(int[] point) {
        int r1 = point[0], c1 = point[1];
        if(!ptsCount.containsKey(r1)) return 0;

        // private Map<Integer, Map<Integer, Integer>> ptsCount;
        // int n1 = ptsCount.getOrDefault(r1, new HashMap<>()).getOrDefault(c1, 1);
        Map<Integer, Integer> points = ptsCount.get(r1);

        int ans = 0;
        for(Map.Entry<Integer, Integer> entry: points.entrySet()) {
            int c2 = entry.getKey();
            int n2 = entry.getValue();
            if(c2 == c1) continue;

            int size = Math.abs(c1-c2);

            int n3 = ptsCount.getOrDefault(r1-size, new HashMap<>()).getOrDefault(c1, 0);
            int n4 = ptsCount.getOrDefault(r1-size, new HashMap<>()).getOrDefault(c2, 0);

            ans += n2 * n3 * n4;

            n3 = ptsCount.getOrDefault(r1+size, new HashMap<>()).getOrDefault(c1, 0);
            n4 = ptsCount.getOrDefault(r1+size, new HashMap<>()).getOrDefault(c2, 0);
            ans += n2 * n3 * n4;
        }
        return ans;
    }
}



}
