import java.util.*;

public class EncodeandDecodeStrings_6 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: map 统计
     * */
    // 存储 key -> list
    private static Map<String, List<String>> keyMap = new HashMap<>();

    // word -> key
    // private static Map<String, String> wordToKey = new HashMap<>();

    public static String encode(List<String> strs) {
        // 按照字典序排序, word, cnt
        Map<String, Integer> cntMap = new TreeMap<>();
        for(String word: strs)
            cntMap.merge(word, 1, Integer::sum);

        // 组成 key
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry: cntMap.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();
            sb.append(word);
            sb.append(freq);
        }

        String key = sb.toString();
//        for(String word: strs)
//            wordToKey.put(word, key);

        keyMap.put(key, strs);

        return key;
    }

    public static List<String> decode(String str) {
        return keyMap.getOrDefault(str, new ArrayList<>());
    }

    /**
     * Method 2: 使用 6# 此类分隔符放在单词最前面
     * */
    public static String encode_2(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for(String word: strs) {
            int len = word.length();
            sb.append(len);
            sb.append("#");
            sb.append(word);
        }

        return sb.toString();
    }

    public static List<String> decode_2(String str) {
        int n =str.length();
        List<String> res = new ArrayList<>();

        int i = 0;
        while(i<n) {
            int j = i;
            while(j < n && str.charAt(j) != '#') {
                j++;
            }
            int len = Integer.parseInt(str.substring(i,j));
            String word = str.substring(j+1, j+1+len);
            res.add(word);
            i = j+len;
        }

        return res;
    }

}
