public class PermutationinString_18 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bbbca";
        System.out.println(checkInclusion_2(s1, s2));
    }

    /**
     * Method 1: fixed sliding window
     * */
    public static boolean checkInclusion(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if(m > n) return false;

        char[] ss = s1.toCharArray(), tt = s2.toCharArray();

        // 统计 s1 中字符出现的情况
        int[] cnt1 = new int[128];
        for(char c: ss) cnt1[c]++;

        // 统计 s2 中字符出现的情况
        int[] cnt2 = new int[128];

        // sliding window
        int i = 0, j = 0;

        while(j < n) {
            // element in
            cnt2[tt[j]]++;

            if(j-i+1 < m) {
                j++;
                continue;
            }

            // 到了这里，说明长度够了
            if(isPermutation(cnt1, cnt2)) return true;

            // element out
            cnt2[tt[i++]]--;
            j++;
        }
        return false;
    }

    private static boolean isPermutation(int[] cnt1, int[] cnt2) {
        for(int i=0;i<cnt1.length;i++)
            if(cnt1[i] != cnt2[i]) return false;
        return true;
    }


    /**
     * Method 2:优化
     * */
    public static boolean checkInclusion_2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        char[] ss = s1.toCharArray(), tt = s2.toCharArray();

        // 统计 s1 出现的字符
        int distinctA = 0;
        int[] cntA = new int[26];
        for(int i=0;i<m;i++) {
            cntA[ss[i]-'a']++;
            if(cntA[ss[i]-'a'] == 1) distinctA++;
        }

        // System.out.println("distinctA="+distinctA);

        int[] cntB = new int[26];
        int distinctB = 0;
        int i = 0, j = 0;

        while(j < n) {
            // element in
            cntB[tt[j]-'a']++;
            if(cntB[tt[j]-'a'] == cntA[tt[j]-'a']) distinctB++;
            else if(cntB[tt[j]-'a']-1 == cntA[tt[j]-'a']) distinctB--;

            if(j-i+1 < m) {
                j++;
                continue;
            }

            // System.out.println("i="+i+" j="+j+" distinctB="+distinctB);

            // check
            if(distinctA == distinctB) return true;

            // element out
            cntB[tt[i]-'a']--;
            if(cntB[tt[i]-'a'] == cntA[tt[i]-'a']) distinctB++;
            else if(cntB[tt[i]-'a']+1 == cntA[tt[i]-'a']) distinctB--;

            i++;
            j++;
        }
        return false;
    }

}
