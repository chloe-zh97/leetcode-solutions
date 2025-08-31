import java.util.TreeMap;

public class CarPooling_1094 {
    public static void main(String[] args) {
        int[][] trips = {
                {2,1,5},
                {3,5,7}
        };
        int capacity = 3;
        System.out.println(carPooling_3(trips, capacity));
    }

    /**
     * Method 1: 暴力法 33.75%
     * */
    public static boolean carPooling_2(int[][] trips, int capacity) {
        int n = trips.length;
        int[] cnt = new int[1005];
        for(int i=0;i<n;i++) {
            int start = trips[i][1], end = trips[i][2], c = trips[i][0];
            for(int j=start;j<end;j++)
                cnt[j] += c;
        }

        for(int i=0;i<cnt.length;i++)
            if(cnt[i] > capacity) return false;

        return true;
    }

    /**
     * Method 2: 差分数组
     * */
    public static boolean carPooling(int[][] trips, int capacity) {
        int n = trips.length;
        final int N = 1005;
        // construct diff array, d[i] 车辆留在 i 位置时车上的人数
        int[] d = new int[N];
        for(int i=0;i<n;i++) {
            int a = trips[i][1];
            int b = trips[i][2];
            d[a]+=trips[i][0];
            d[b]-=trips[i][0];
        }

        int s = 0;
        // 还原数组
        for(int i=0;i<d.length;i++) {
            s += d[i];
            if(s >capacity) return false;
        }
        return true;
    }

    /**
     * Method 3: TreeMap 替代差分数组
     * */
    public static boolean carPooling_3(int[][] trips, int capacity) {
        int n = trips.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0;i<n;i++) {
            int from = trips[i][1], to = trips[i][2], c = trips[i][0];
            map.merge(from, c, Integer::sum);
            map.merge(to, -c, Integer::sum);
        }

        int s = 0;
        // 遍历 treemap
        for(int d: map.values()) {
            s += d;
            if(s > capacity) return false;
        }

        return true;
    }

}
