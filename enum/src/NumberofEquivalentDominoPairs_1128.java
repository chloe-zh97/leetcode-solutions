import java.util.HashMap;

public class NumberofEquivalentDominoPairs_1128 {
    public static void main(String[] args) {
        int[][] dominoes = {
                {1,2},
                {2,1},
                {1,1},
                {1,2},
                {2,2}
        };
        System.out.println(numEquivDominoPairs(dominoes));
    }

    /**
     * Method 1: 哈希
     * */
    public static int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int[] d: dominoes) {
            int a = Math.min(d[0],d[1]);
            int b = Math.max(d[0],d[1]);
            String k = a + "-" + b;
            ans += map.getOrDefault(k, 0);
            map.merge(k, 1, Integer::sum);
        }
        return ans;
    }

    /**
     * 优化，d[][] 的范围是0-9
     * */
    public static int numEquivDominoPairs_2(int[][] dominoes) {
        int[][] cnt = new int[10][10];
        int ans = 0;
        for(int[] d: dominoes) {
            int a = Math.min(d[0], d[1]);
            int b = Math.max(d[0], d[1]);
            ans += cnt[a][b]++;
        }
        return ans;
    }
}
