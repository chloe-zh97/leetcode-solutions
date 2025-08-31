public class MaximumNestingDepthoftheParentheses_1614 {
    public static void main(String[] args) {
        String s = "()(())((()()))";
        System.out.println(maxDepth(s));
    }
    public static int maxDepth(String s) {
        int cnt = 0, ans = 0;
        for(char ch: s.toCharArray()) {
            if(ch == '(') cnt++;
            else if(ch == ')') cnt--;
            ans = Math.max(cnt, ans);
        }
        return ans;
    }
}
