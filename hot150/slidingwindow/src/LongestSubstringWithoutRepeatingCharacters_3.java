public class LongestSubstringWithoutRepeatingCharacters_3 {
    public static void main(String[] args) {
        String s = "";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        // 各个元素出现的次数
        int[] visit = new int[256];
        int ans = 0;

        int j = 0, i = 0;
        while(j < n) {
            // element in
            char ch = chs[j++];
            visit[ch]++;

            // check
            while(visit[ch] > 1) {
                visit[chs[i]] -= 1;
                i++;
            }

            // 退出循环时，[i,j-1] 满足条件
            ans = Math.max(ans, j-i);

        }
        return ans;
    }

}
