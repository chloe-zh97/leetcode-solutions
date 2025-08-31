public class MinimumInsertionstoBalanceaParenthesesString_1541 {
    public static void main(String[] args) {
        String s = "())";
        System.out.println(minInsertions_2(s));
    }

    /**
     * Method 1: 替换成 ], 然后计数 10%
     * */
    public static int minInsertions(String s) {
        int n = s.length();
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        char[] chs = s.toCharArray();

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '(') sb.append(c);
            else if(c == ')') {
                if(i == n-1) {
                    // 已经到了最末尾的字符
                    sb.append(']');
                    // 补一个 )
                    ans++;
                }
                else if(chs[i+1] == ')') {
                    i++;
                    sb.append(']');
                } else {
                    // 下一个字符不是 )
                    sb.append(']');
                    ans++;
                }
            }
        }

        // 已经补齐
        chs = sb.toString().toCharArray();
        int balanced = 0;
        for(int i=0;i<chs.length;i++) {
            char c = chs[i];
            if(c == '(') balanced++;
            else {
                // c == ']'
                balanced--;
                if(balanced < 0) {
                    // 右括号多了2个，添加1个左括号
                    ans += 1;
                    balanced = 0;
                }
            }
        }

        // balance > 0
        return ans + balanced * 2;
    }

    /**
     * Method 2: 遇到右括号需要 += 2 跳过
     * */
    public static int minInsertions_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        int ans = 0, balanced = 0;

        for(int i=0;i<n;i++) {
            char c = chs[i];

            if(c == '(') {
                balanced++;
            } else {
                // 遇到右括号
                balanced--;
                if(balanced < 0) {
                    // 右括号太多了, 插入左括号
                    ans++;
                    balanced = 0;
                }

                if(i == n-1 || chs[i+1] != ')') {
                    ans++;
                } else {
                    // 下一个还是 )
                    i++;
                }
            }
        }
        return ans + balanced * 2;
    }
}
