import java.util.HashMap;

public class FindtheSubstringWithMaximumCost_2606 {
    public static void main(String[] args) {
        String s = "adaa";
        String chars = "d";
        int[] vals = {-1000};
        System.out.println(maximumCostSubstring_2(s, chars, vals));
    }

    /**
     * Method 1: 动态规划
     * */
    public static int maximumCostSubstring(String s, String chars, int[] vals) {
        char[] ss = s.toCharArray(), cc = chars.toCharArray();
        int m = s.length(), n = chars.length();

        // 存储 values
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            map.put(cc[i], vals[i]);
        }

        int sum = 0;
        int preSum = 0;

        for(int i=0;i<m;i++) {
            int v = map.getOrDefault(ss[i], ss[i]-'a'+1);
            preSum = Math.max(preSum+v, v);
            sum = Math.max(sum, preSum);
        }
        return sum;
    }


    /**
     * Method 2: 优化，取代 hashmap
     * */
    public static int maximumCostSubstring_2(String s, String chars, int[] vals) {
        int m = s.length(), n = chars.length();
        char[] ss = s.toCharArray(), cc = chars.toCharArray();

        int[] map = new int[26];
        for(int i=0;i<map.length;i++)
            map[i] = i+1;

        for(int i=0;i<n;i++) {
            map[cc[i]-'a'] = vals[i];
        }

        int preSum = 0, sum = 0;
        for(int i=0;i<m;i++) {
            char c = ss[i];
            preSum = Math.max(preSum+map[c-'a'], map[c-'a']);
            sum = Math.max(sum, preSum);
        }
        return sum;
    }
}
