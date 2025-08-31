import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams_49 {
    public static void main(String[] args) {
        String[] strs = {"a"};
        List<List<String>> ans = groupAnagrams(strs);
        for(List<String> t: ans)
            System.out.println(t);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s: strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);

            String key = String.valueOf(charArray);
            List<String> row = map.getOrDefault(key, new ArrayList<>());
            row.add(s);
            map.put(key, row);
        }

        return map.values().stream().toList();
    }
}
