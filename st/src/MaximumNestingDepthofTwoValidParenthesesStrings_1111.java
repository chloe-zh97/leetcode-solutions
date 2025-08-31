import java.util.Arrays;

public class MaximumNestingDepthofTwoValidParenthesesStrings_1111 {
    public static void main(String[] args) {
        String seq = "()(())()";
        int[] ans = maxDepthAfterSplit(seq);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * Method 1: 奇偶性分配
     * */
    public static int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        char[] chs = seq.toCharArray();
        // 记录当前栈的深度，左右括号要匹配
        int d = 0;
        int[] ans = new int[n];

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '(') {
                // 模拟 push, 栈深度+1
                d++;
                // 按奇偶数分配
                ans[i] = d % 2;
            } else {
                ans[i] = d % 2;
                --d;
            }
        }
        return ans;
    }


    /**
     * Method 2: max(A,B) 尽可能小，需要满足 A和B的深度差接近
     * 用 a 表示 A 的深度，b 表示B的深度
     * */
    public static int[] maxDepthAfterSplit_2(String seq) {
        int a = 0, b = 0;
        int n = seq.length();
        char[] chs = seq.toCharArray();
        int[] ans = new int[n];
        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '(') {
                // 看下给A和B哪个涨深度
                if(a <= b) {
                    a++;
                    ans[i] = 0;
                } else {
                    b++;
                    ans[i] = 1;
                }
            } else {
                // 看下给谁降深度，相等的时候要给 A 降深度
                if(a >= b) {
                    a--;
                    ans[i] = 0;
                } else {
                    b--;
                    ans[i] = 1;
                }
            }
        }
        return ans;
    }
}
