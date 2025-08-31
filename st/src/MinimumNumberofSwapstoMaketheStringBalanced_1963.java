public class MinimumNumberofSwapstoMaketheStringBalanced_1963 {
    public static void main(String[] args) {
        String s = "[]";
        System.out.println(minSwaps_3(s));
    }

    /**
     * Method 1: 栈模拟，交换
     * balance 的字符串，任意时刻左括号一定 >= 右括号的数量
     * */
    public static int minSwaps(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        int balanced = 0, j = n-1;
        int ans = 0;

        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '[') {
                balanced++;
            } else {
                // c = ]
                --balanced;
                if(balanced < 0) {
                    // 需要交换, 找到最后的 【
                    while(j > i && chs[j] != '[') j--;
                    if(chs[j] == '[') {
                        // swap
                        balanced += 2;
                        ans++;
                        chs[j] = ']';
                    }
                }
            }
        }
        return ans;
    }


    /**
     * Method 2: 优化，省去swap步骤
     * */
    public static int minSwaps_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        int ans = 0, balanced = 0, j = n-1;
        for(int i=0;i<n;i++) {
            char c = chs[i];
            if(c == '[') {
                balanced++;
            } else if(balanced > 0) {
                --balanced;
            } else {
                // c == 0，需要交换
                while(j>i && chs[j] == ']') j--;
                // 找到第一个 [
                balanced++;
                ans++;
            }
        }
        return ans;
    }

    /**
     * Method 3: 优化，去除掉所有抵消的 [], 剩下的一定都是 ]]] [[[ 的组合
     * */
    public static int minSwaps_3(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        int balanced = 0, c = 0;
        for(int i=0;i<n;i++) {
            if(chs[i] == '[') ++balanced;
            else {
                --balanced;
                if(balanced < 0) {
                    c++;
                    balanced = 0;
                }
            }
        }

        return (c+1) / 2;
    }
}
