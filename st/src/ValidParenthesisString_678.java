public class ValidParenthesisString_678 {
    public static void main(String[] args) {
        String s = "(";
        System.out.println(checkValidString(s));
    }

    /**
     * case 1: (( )))
     * case 2: ((( ))
     * case 3: (* ))
     * case 4: (( *)
     * cas4 5: ((( ****
     * cas4 5: (
     *
     * 用 l, r 分别表示得分的下限和上限
     * 令遇到左括号得分+1，遇到右括号得分-1，合法的字符串必满足最终得分为0
     * */
    public static boolean checkValidString(String s) {
       int n = s.length();
       char[] chs = s.toCharArray();
       int l = 0, r = 0;
       for(int i=0;i<n;i++) {
           char c = chs[i];
           if(c == '(') {
               l++;
               r++;
           } else if(c == ')') {
               l--;
               r--;
           } else if(c == '*') {
               l--;
               r++;
           }

           // * 可以代表空串
           l = Math.max(l,0);
           // 右括号多了
           if(l > r) return false;
       }
       return l == 0;
    }
}
