import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses_69 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(generateParenthesis_2(n));
    }

    /**
     * Method 1: 暴力法，生成所有可能的组合，判断是否valid
     * */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtracking(new StringBuilder(), n, ans);
        return ans;
    }

    private static void backtracking(StringBuilder sb, int n, List<String> ans) {
        if(sb.length() == n * 2) {
            if(isValid(sb.toString())) ans.add(sb.toString());
            return;
        }

        // 加入左括号
        sb.append("(");
        backtracking(sb, n, ans);
        // 恢复现场
        sb.deleteCharAt(sb.length()-1);

        sb.append(")");
        backtracking(sb, n, ans);
        sb.deleteCharAt(sb.length()-1);
    }

    private static boolean isValid(String s) {
        int left = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') left += 1;
            else left -= 1;
            if(left < 0) return false;
        }
        return left == 0;
    }

    /**
     * Method 2: 优化，记录 open 和 close 的数目，剪枝
     * */
    public static List<String> generateParenthesis_2(int n) {
        List<String> ans = new ArrayList<>();
        backtracking_2(new StringBuilder(), n, 0, 0, ans);
        return ans;
    }

    private static void backtracking_2(StringBuilder sb, int n, int open, int close, List<String> ans) {
        // 终止条件
        if(open == n && close == n) {
            ans.add(sb.toString());
            return;
        }

        if(close > open) return;

        if(open < n) {
            sb.append("(");
            backtracking_2(sb, n, open+1, close, ans);
            sb.deleteCharAt(sb.length()-1);
        }

        if(close < open) {
            sb.append(")");
            backtracking_2(sb, n, open, close+1, ans);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
