import java.util.Arrays;

public class CountVowelStringsinRanges_2559 {
    public static void main(String[] args) {
        String[] words = {"a","e","i"};
        int[][] queries = {
                {0,2},
                {0,1},
                {2,2}
        };
        int[] ans = vowelStrings(words, queries);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * 方法一：前缀和，cnt[i]: words[0..i-1]中有多少个满足条件的字符串
     * */
    public static int[] vowelStrings(String[] words, int[][] queries) {
        int m = words.length, n = queries.length;

        int[] ans = new int[n];
        int[] s = new int[m+1];

        // s[i]: words[0..i-1] 有多少个满足条件的字符串
        for(int i=1;i<s.length;i++) {
            s[i] = s[i-1] + (check(words[i-1]) ? 1: 0);
        }

        for(int i=0;i<n;i++) {
            int start = queries[i][0], end = queries[i][1];
            ans[i] = s[end+1] - s[start];
        }
        return ans;
    }

    private static boolean check(String s) {
        if(s == null || s.isEmpty()) return false;
        int n = s.length();
        return (s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i' || s.charAt(0) == 'o' || s.charAt(0) == 'u') &&
                (s.charAt(n-1) == 'a' || s.charAt(n-1) == 'e' || s.charAt(n-1) == 'i' || s.charAt(n-1) == 'o' || s.charAt(n-1) == 'u');
    }

}
