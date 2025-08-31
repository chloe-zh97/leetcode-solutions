import java.util.HashMap;

public class NumberofBoomerangs_447 {
    public static void main(String[] args) {
        int[][] points = {
                {1,1}
        };
        System.out.println(numberOfBoomerangs(points));
    }

    /**
     * Method 1: 固定i点枚举+Hashmap
     * */
    public static int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i=0;i<n;i++) {
            // 固定 i point 进行枚举
            int x0 = points[i][0], y0 = points[i][1];
            map.clear();
            for(int j=0;j<n;j++) {
                int x1 = points[j][0], y1 = points[j][1];
                int dis = (x1-x0)*(x1-x0) + (y1-y0)*(y1-y0);
                ans += 2 * map.getOrDefault(dis, 0);
                map.merge(dis, 1, Integer::sum);
            }
        }
        return ans;
    }
}
