import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams_4 {
    public static void main(String[] args) {
        String[] strs = {
                "bdddddddddd","bbbbbbbbbbc"
        };
        List<List<String>> ans = groupAnagrams_4(strs);
        for(List t: ans)
            System.out.println(t);
    }

    /**
     * Method 1: HashMap
     * */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        // O(m)
        for(String word: strs) {
            char[] chs = word.toCharArray();
            // O(nlogn)
            Arrays.sort(chs);
            String k = String.valueOf(chs);

            List<String> group = map.getOrDefault(k, new ArrayList<>());
            group.add(word);
            map.put(k, group);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Method 2: Hash table
     * 用 int[] cnt 数组，组成 String 作为 key
     * */
    public static List<List<String>> groupAnagrams_2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        // O(m)
        for(String word: strs) {
            // O(n)
            char[] cnt = countLetter(word);
            StringBuilder sb = new StringBuilder();

            // O(26)
            // format key
            for(int c:cnt) sb.append(c);
            System.out.println(sb);

            List<String> group = map.getOrDefault(sb.toString(), new ArrayList<>());
            group.add(word);
            map.put(sb.toString(), group);
        }
        return new ArrayList<>(map.values());
    }

    private static char[] countLetter(String word) {
        char[] cnt = new char[26];
        Arrays.fill(cnt, '0');
        for(char c: word.toCharArray())
            cnt[c-'a'] += 1;

        return cnt;
    }


    public static List<List<String>> groupAnagrams_3(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        // O(m)
        for(String word: strs) {
            // O(n)
            int[] cnt = countLetter_3(word);
            StringBuilder sb = new StringBuilder();

            // O(26)
            // format key
            for(int c:cnt) sb.append(c);
            System.out.println(sb);

            List<String> group = map.getOrDefault(sb.toString(), new ArrayList<>());
            group.add(word);
            map.put(sb.toString(), group);
        }
        return new ArrayList<>(map.values());
    }

    private static int[] countLetter_3(String word) {
        int[] cnt = new int[26];
        for(char c: word.toCharArray())
            cnt[c-'a']++;

        return cnt;
    }

    /**
     * Method 4: 优化
     * int[] cnt -> String
     * */
    public static List<List<String>> groupAnagrams_4(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        // O(m)
        for(String word: strs) {
            // O(n)
            char[] cnt = countLetter(word);
            String k = Arrays.toString(cnt);

            List<String> group = map.getOrDefault(k.toString(), new ArrayList<>());
            group.add(word);
            map.put(k, group);
        }
        return new ArrayList<>(map.values());
    }


}
