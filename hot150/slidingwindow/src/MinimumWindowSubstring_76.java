public class MinimumWindowSubstring_76 {
    public static void main(String[] args) {
        String s = "a";
        String t = "a";
        System.out.println(minWindow_2(s, t));
    }

    public static String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        String ans = "";
        if(m < n) return ans;

        char[] ss = s.toCharArray(), tt = t.toCharArray();
        int[] tmap = new int[128], seen = new int[128];

        for(char c: tt) tmap[c] += 1;

        int i = 0, j = 0, minLen = Integer.MAX_VALUE;

        while(j < m) {
            // element in
            char in = ss[j++];
            seen[in]++;

            while(isCover(tmap, seen)) {
                if(j-i<minLen) {
                    minLen = j-i;
                    ans = s.substring(i,j);
                }

                char out = ss[i++];
                seen[out]--;
            }
        }
        return ans;
    }

    private static boolean isCover(int[] tmap, int[] seen) {
        for(int i=0;i<tmap.length;i++)
            if(tmap[i] > seen[i]) return false;
        return true;
    }

    /**
     * Method 2: 优化
     * */
    public static String minWindow_2(String s, String t) {
        int m = s.length(), n = t.length();
        String ans = "";
        if(n > m) return ans;

        char[] ss = s.toCharArray(), tt = t.toCharArray();

        int remain = 0;
        int[] tmap = new int[128];
        for(char c: tt) {
            // t 中含有不同字母的种数
            if(tmap[c] == 0) remain++;
            tmap[c]++;
        }

        // sliding window
        int i = 0, j = 0, minLen = Integer.MAX_VALUE;
        while(j < m) {
            // element in
            char in = ss[j++];
            tmap[in]--;
            if(tmap[in] == 0) --remain;

            while(remain == 0) {
                // 达标
                if(j-i < minLen) {
                    minLen = j-i;
                    ans = s.substring(i,j);
                }

                char out = ss[i++];
                // 吐出来一个
                tmap[out]++;
                if(tmap[out] == 1) remain++;
            }
        }

        return ans;
    }
}
