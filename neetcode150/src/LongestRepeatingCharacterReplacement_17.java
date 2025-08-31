public class LongestRepeatingCharacterReplacement_17 {
    public static void main(String[] args) {
        String s = "XYYX";
        int k = 2;
        System.out.println(characterReplacement(s, k));
    }

    /**
     * Method 1: 只含同种字符, 当前的字符不一定是出现最多的字符
     * 需要动态维护 maxCur
     * */
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        char[] chs = s.toCharArray();

        int[] seen = new int[128];

        int i = 0, j = 0, ans = 0, maxCur = 0;
        while(j < n) {
            seen[chs[j]]++;
            maxCur = Math.max(maxCur, seen[chs[j]]);

            // 需要找的是最大的 j-i+1
            // 如果不更新 maxCur, maxCur 变小，len 也会变小，不影响最终的结果
            while(maxCur + k < j-i+1) {
                // System.out.println("i="+i+" j="+j);
                seen[chs[i]]--;
                for(int m=0;m<128;m++)
                    maxCur = Math.max(maxCur, seen[chs[i]]);
                i++;
            }

            ans = Math.max(ans, j-i+1);
            j++;
        }
        return ans;
    }

    /**
     * Sliding window
     * 最长的不重复的子串，可以最多替换 k 次
     * 假设子串中 distinct 的字符数为 d, 重复的次数为 r
     * 需要满足：1. r <= k
     * 2. len(str) <= 26
     * 条件1 满足多余的 r 个元素可以都被替换掉
     * */
    public static int characterReplacement_other(String s, int k) {
        int n = s.length();
        char[] chs = s.toCharArray();

        // 只包含大写字符
        int[] seen = new int[128];

        // 子串中不一样的字符数量
        int distinct = 0;
        int i = 0, j = 0, ans = 0;

        while(j < n) {
            // element in
            seen[chs[j]]++;
            if(seen[chs[j]] == 1) distinct++;

            // 不满足条件
            while(j-i+1 > distinct + k || j-i+1 > 26) {
                // 当前子串的长度超过了能替换的最大长度，说明重复的太多了
                // 或者本身长度过长，需要收缩
                seen[chs[i]]--;
                if(seen[chs[i]] == 0) distinct--;
                i++;
            }

            // 循环退出后，[i...j]满足条件
            ans = Math.max(ans, j-i+1);
            j++;
        }
        return ans;
    }
}
