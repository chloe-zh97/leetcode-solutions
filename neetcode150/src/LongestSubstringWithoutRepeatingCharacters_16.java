import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters_16 {
    public static void main(String[] args) {
        String s = "thequickbrownfoxjumpsoverthelazydogthequickbrownfoxjumpsovert";
        System.out.println(lengthOfLongestSubstring_2(s));

    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        int[] seen = new int[256];

        int j = 0, i = 0, ans = 0;
        while(j < n) {
            // element in
            char c = chs[j];
            seen[c] += 1;

            // 到这里说明有重复发生
            while(seen[c] > 1) {
                seen[chs[i]]--;
                i++;
            }

            // 退出循环时，seen <= 1, [i,j]
            ans = Math.max(ans, j-i+1);
            j++;
        }
        return ans;
    }

    /**
     * Method 2: HashMap + sliding window
     * map 记录 val, index
     * */
    public static int lengthOfLongestSubstring_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0, ans = 0;
        while(j < n) {
            // element in
            char c = chs[j];
            if(!map.containsKey(c)) {
                // 不包含当前字符，可以直接加入
                map.put(c, j);
                // 更新最长字符
                ans = Math.max(ans, j-i+1);
            } else {
                // 更新 i 的位置
                i = Math.max(map.get(c) + 1, i);
                map.put(c, j);
                ans = Math.max(ans, j-i+1);
            }
            j++;
        }
        return ans;
    }
}
