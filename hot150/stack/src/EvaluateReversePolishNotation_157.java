import java.util.Stack;

public class EvaluateReversePolishNotation_157 {
    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }


    public static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        int n = tokens.length;
        int i = 0;
        while(i < n) {
           if(isOperation(tokens[i])) {
               int a = st.pop();
               int b = st.pop();
               int r = 0;
               if(tokens[i].equals("+")) {
                   r = a + b;
               } else if(tokens[i].equals("-")) {
                   r = b - a;
               } else if(tokens[i].equals("*")) {
                   r = a * b;
               } else {
                   r = b / a;
               }
               st.push(r);
           } else {
               st.push(Integer.parseInt(tokens[i]));
           }
           i++;
        }
        return st.pop();
    }

    private static boolean isOperation(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}
