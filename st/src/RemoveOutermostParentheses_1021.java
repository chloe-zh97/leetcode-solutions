public class RemoveOutermostParentheses_1021 {
    public static void main(String[] args) {

    }

    public static String removeOuterParentheses(String s) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(char ch: s.toCharArray()) {
            if(ch == '(' && cnt++ > 0) sb.append(ch);
            else if(ch == ')' && cnt-- > 1) sb.append(ch);
        }
        return sb.toString();
    }
}
