import java.util.HashMap;

public class NumberofPairsofInterchangeableRectangles_2001 {
    public static void main(String[] args) {
        int[][] rectangles = {
                {4,5},
                {7,8}
        };
        System.out.println(interchangeableRectangles_2(rectangles));
    }
    public static long interchangeableRectangles(int[][] rectangles) {
        int n = rectangles.length;
        HashMap<Double, Integer> map = new HashMap<>();

        long cnt = 0;
        for(int i=0;i<n;i++) {
            int w = rectangles[i][0], h = rectangles[i][1];
            double key = 1.0*w/h;
            int c = map.getOrDefault(key, 0);
            cnt += c;
            map.merge(key, 1, Integer::sum);
        }

        return cnt;
    }

    /**
     * Method 2: 最大公约数+哈希
     * */
    public static long interchangeableRectangles_2(int[][] rectangles) {
        HashMap<String, Integer> map = new HashMap<>();
        long ans = 0L;
        for(int[] r: rectangles) {
            int w = r[0], h = r[1];
            int c = gcd(w,h);
            w /= c;
            h /= c;
            String k = w + "-" + h;

            int cnt = map.getOrDefault(k, 0);
            ans += cnt;
            map.merge(k, 1, Integer::sum);
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        while(a!=0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}
