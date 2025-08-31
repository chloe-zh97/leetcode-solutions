
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstringwithConcatenationofAllWords_30 {
    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        List<Integer> ans = findSubstring_3(s, words);
        System.out.println(ans);
    }

    /**
     * Method 1: 分组滑动窗口
     * */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = words.length, wordLen = words[0].length();

        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words)
            map.merge(word, 1, Integer::sum);

        // group
        go:for(int k=0;k<wordLen;k++) {
            // 存储访问过的单词
            HashMap<String, Integer> seen = new HashMap<>();

            int left = k, right = k;

            // put m words in sliding window
            for(int i=0;i<m;i++) {
                if(right + wordLen > n) continue go;
                // 待加入 word
                String cur = s.substring(right, right+wordLen);
                // 更新 seen
                seen.merge(cur, 1, Integer:: sum);
                right += wordLen;
            }

            // initial sliding window end, check valid
            if(seen.equals(map)) ans.add(left);

            // 滑动窗口开始
            while(right + wordLen <= n) {
                String in = s.substring(right, right+wordLen);
                String out = s.substring(left, left+wordLen);

                // element in
                seen.merge(in, 1, Integer::sum);

                // element out
                seen.merge(out, -1, Integer::sum);
                if(seen.get(out) == 0) seen.remove(out);

                right += wordLen;
                left += wordLen;

                if(seen.equals(map)) ans.add(left);
            }
        }
        return ans;
    }

    /**
     * Method 2: 模拟
     * */
    public static List<Integer> findSubstring_2(String s, String[] words){
        int n = s.length(), m = words.length, wordLen = words[0].length();
        int targetLen = m * wordLen;
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words)
            map.merge(word, 1, Integer::sum);

        List<Integer> ans = new ArrayList<>();

        // 枚举每个可能的起点
        for(int i=0;i<=n-targetLen;i++) {
            String cur = s.substring(i,i+targetLen);

            // 判断 cur 这个字符串是否能全部由 words 构成
            HashMap<String, Integer> seen = new HashMap<>();

            for(int j=0;j<=targetLen-wordLen;j+=wordLen) {
                String word = cur.substring(j,j+wordLen);
                seen.merge(word, 1, Integer::sum);
            }

            // check
            if(map.equals(seen)) ans.add(i);
        }
        return ans;
    }

    /**
     * Method 3: 优化滑动窗口
     * element in
     * shrink
     * check
     * */
    public static List<Integer> findSubstring_3(String s, String[] words) {
        int n = s.length(), m = words.length, wordLen = words[0].length();
        int targetLen = m * wordLen;

        List<Integer> ans = new ArrayList<>();

        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words)
            map.merge(word, 1, Integer::sum);

        // group
        for(int i=0;i<wordLen;i++) {
            HashMap<String, Integer> seen = new HashMap<>();

            // left 表示 str 开始位置
            int left = i, right = i;

            // element in
            while(right + wordLen <= n) {
                String cur = s.substring(right, right+wordLen);
                right += wordLen;

                if(!map.containsKey(cur)) {
                    // 当前的 word 无法加入， left 到达 right 的下一个位置
                    left = right;
                    seen.clear();
                } else {
                    // add to seen
                    seen.merge(cur, 1, Integer::sum);

                    // 虽然加入，但是超出了
                    while(seen.get(cur) > map.get(cur)) {
                        String out = s.substring(left, left+wordLen);
                        seen.merge(out, -1, Integer::sum);
                        if(seen.get(out) == 0) seen.remove(out);
                        left += wordLen;
                    }
                }
                // check
                if(seen.equals(map)) ans.add(left);
            }
        }
        return ans;
    }
}
