import java.util.Stack;

public class ValidParentheses_20 {
    public static void main(String[] args) {
        String s = "(])";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<n;i++) {
            if(stack.empty()) {
                stack.push(chs[i]);
            } else {
                // not empty
                char top = stack.peek();
                char cur = chs[i];
                if(cur == '(' || cur == '[' || cur == '{') stack.push(cur);
                else if(cur == '}' && top == '{') stack.pop();
                else if(cur == ']' && top == '[') stack.pop();
                else if(cur == ')' && top == '(') stack.pop();
                else return false;
            }
        }
        return stack.empty();
    }
}
