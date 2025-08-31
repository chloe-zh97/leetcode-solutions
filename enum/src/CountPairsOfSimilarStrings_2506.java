import java.util.HashMap;

public class CountPairsOfSimilarStrings_2506 {
    public static void main(String[] args) {
        String[] words = {"nba","cba","dba"};
        System.out.println(similarPairs_2(words));
    }

    public static int similarPairs(String[] words) {
        // abc, 1
        int ans = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<words.length;i++) {
            String word = words[i];
            String key = func(word);
            int c = map.getOrDefault(key, 0);
            ans += c;
            map.merge(key, 1, Integer::sum);
        }
        return ans;
    }

    private static String func(String word) {
        int[] cnt = new int[26];
        char[] chs = word.toCharArray();

        for(int i=0;i<chs.length;i++) {
            cnt[chs[i]-'a'] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++) {
            if(cnt[i] > 0) sb.append((char)('a'+i));
        }

        return sb.toString();
    }

    /**
     * Method 2: 用 mask 存储 word 中字符出现次数
     * */
    public static int similarPairs_2(String[] words) {
        // mask, cnt
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(String word: words) {
            int mask = 0;
            for(char c: word.toCharArray()) {
                mask |= 1 << (c-'a');
            }
            int v = map.getOrDefault(mask, 0);
            ans += v;
            map.merge(mask, 1, Integer::sum);
        }
        return ans;
    }
}
