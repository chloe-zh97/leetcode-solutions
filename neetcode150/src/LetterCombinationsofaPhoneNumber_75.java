import java.util.*;

public class LetterCombinationsofaPhoneNumber_75 {
    public static void main(String[] args) {
        String digits = "";
        List<String> ans = letterCombinations(digits);
        System.out.println(ans);
    }

    private static Map<Character, String> map;

    private static List<String> ans;

    /**
     * Input: 2 3 4
     * A, B, C
     * DEF, DEF, DEF
     * */

    public static List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        map = new HashMap<>();

        if(digits == null || digits.isEmpty()) return ans;

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtracking(digits, 0, new StringBuilder());
        return ans;
    }

    private static void backtracking(String digits, int index, StringBuilder sb) {
        if(index >= digits.length()) {
            // 把所有的 path 加入到 ans
            ans.add(sb.toString());
            return;
        }

        String s = map.get(digits.charAt(index));
        for(char c: s.toCharArray()) {
            sb.append(c);
            backtracking(digits, index+1, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }


}
