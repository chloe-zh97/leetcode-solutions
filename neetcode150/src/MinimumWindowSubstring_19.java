public class MinimumWindowSubstring_19 {
    public static void main(String[] args) {
        String s = "xyz";
        String t = "xyz";
        System.out.println(minWindow_2(s, t));
    }

    /**
     * Method 1: Sliding window
     * 在 s 中找最短子串，包含 t 中的全部字符，只含大小写字符，允许重复
     * 滑动窗口 s 中的字符个数要 >= t
     * while(valid)
     * */
    public static String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if(m < n) return "";

        char[] ss = s.toCharArray(), tt = t.toCharArray();

        // 统计 t 字符串字母出现的个数情况
        int[] cntT = new int[128];
        for(char c: tt) cntT[c]++;

        int[] cntS = new int[128];

        // sliding window
        int i = 0, j = 0, minLen = Integer.MAX_VALUE;
        String ans = "";

        while(j < m) {
            // element in
            cntS[ss[j]]++;

            if(j-i+1 < n) {
                // 窗口大小不够，扩张
                j++;
                continue;
            }

            // 窗口大小够了
            while(isValid(cntS, cntT)) {
                // 收缩直到窗口不合法
                cntS[ss[i++]]--;
            }

//            System.out.println("i="+i+" j="+j);

            // 退出循环时，不合法，合法区间为 【i-1,j]
            if(i>0 && minLen > j-i+2) {
                minLen = j-i+2;
                ans = s.substring(i-1,j+1);
            }

            j++;
        }
        return ans;
    }

    // cnt1 是否完全涵盖 cnt2
    private static boolean isValid(int[] cnt1, int[] cnt2) {
        for(int i=0;i<cnt1.length;i++)
            if(cnt1[i] < cnt2[i]) return false;
        return true;
    }


    /**
     * 优化
     * */
    public static String minWindow_2(String s, String t) {
        int m = s.length(), n = t.length();
        if(m < n) return "";
        char[] ss = s.toCharArray(), tt = t.toCharArray();

        int[] cntT = new int[128];
        int distinctT = 0;
        for(int i=0;i<n;i++) {
            cntT[tt[i]]++;
            if(cntT[tt[i]] == 1) distinctT++;
        }

        // sliding window
        int[] cntS = new int[128];
        int validCount = 0;

        String ans = "";
        int minLen = Integer.MAX_VALUE;

        int i=0,j=0;
        while(j < m) {
            // element in
            cntS[ss[j]]++;
            if(cntS[ss[j]] == cntT[ss[j]]) validCount++;

            if(j-i+1 < n) {
                j++;
                continue;
            }

            while(validCount == distinctT) {
                // [i,j] 满足
                if(j-i+1 < minLen) {
                    minLen = j-i+1;
                    ans = s.substring(i,j+1);
                }

                cntS[ss[i]]--;
                if(cntS[ss[i]]+1 == cntT[ss[i]]) validCount--;
                i++;
            }

            j++;
        }
        return ans;
    }
}
