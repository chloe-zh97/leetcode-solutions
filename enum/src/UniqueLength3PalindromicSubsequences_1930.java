import java.util.HashSet;

public class UniqueLength3PalindromicSubsequences_1930 {
    public static void main(String[] args) {
        String s = "adc";
        System.out.println(countPalindromicSubsequence_2(s));
    }

    /**
     * Method 1: 前缀后缀
     * */
    public static int countPalindromicSubsequence(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        // preMask[i]: 26个字母出现的情况
        // preMask[i]: chs[0...i-1] 字母出现的情况
        int[] preMask = new int[n+1];
        for(int i=1;i<preMask.length;i++) {
            preMask[i] = preMask[i-1];
            preMask[i] |= (1 << (chs[i-1]-'a'));
        }

        int[] sufMask = new int[n+1];
        // sufMask[i]: chs[i..n-1] 字母出现的情况
        for(int i=n-1;i>=0;i--) {
            sufMask[i] = sufMask[i+1];
            sufMask[i] |= (1 << (chs[i] - 'a'));
        }

        HashSet<String> set = new HashSet<>();
        // 枚举中间的字母
        for(int i=1;i<n-1;i++) {
            int a = preMask[i];
            int b = sufMask[i+1];
            for(int j=0;j<32;j++) {
                if(((a >> j)&1) == 1 && ((b >> j) & 1) == 1) {
                    char t = (char)('a'+j);
                    String k = t + "" + chs[i] + t;
                    set.add(k);
                }
            }
        }
        return set.size();
    }


    /**
     * Method 2: 优化，前缀后缀，mask
     * */
    public static int countPalindromicSubsequence_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        int[] pre = new int[26];
        int[] suf = new int[26];
        // 去重
        int[] cnt = new int[26];

        // 统计后缀出现的字母情况
        for(int i=0;i<n;i++) {
            suf[chs[i]-'a']++;
        }

        // 枚举中间字符
        for(int j=0;j<n;j++) {
            // 将当前字符排除suf
            suf[chs[j]-'a']--;
            // 枚举26个字母, 判断左右字母
            for(int k=0;k<26;k++) {
                if(pre[k] > 0 && suf[k] > 0) {
                    // 以 chs[j] 为中间字符
                    cnt[chs[j]-'a'] |= 1 << k;
                }
            }
            // 统计前缀
            pre[chs[j]-'a']++;
        }

        int ans = 0;
        for(int c: cnt) {
            ans += Integer.bitCount(c);
        }
        return ans;
    }
}
