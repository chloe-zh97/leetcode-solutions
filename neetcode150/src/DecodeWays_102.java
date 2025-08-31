import java.util.*;

public class DecodeWays_102 {
    public static void main(String[] args) {
        String s = "10";
        System.out.println(numDecodings(s));
        System.out.println(numDecodings_6(s));
    }

    /**
     * Method 1: 暴力法 超时
     * */
    private static Map<String, Character> map = new HashMap<>();

    private static Set<List<Character>> ans = new HashSet<>();

    public static int numDecodings(String s) {
        int n = s.length();
        map.put("1", 'A');
        map.put("2", 'B');
        map.put("3", 'C');
        map.put("4", 'D');
        map.put("5", 'E');
        map.put("6", 'F');
        map.put("7", 'G');
        map.put("8", 'H');
        map.put("9", 'I');
        map.put("10", 'J');
        map.put("11", 'K');
        map.put("12", 'L');
        map.put("13", 'M');
        map.put("14", 'N');
        map.put("15", 'O');
        map.put("16", 'P');
        map.put("17", 'Q');
        map.put("18", 'R');
        map.put("19", 'S');
        map.put("20", 'T');
        map.put("21", 'U');
        map.put("22", 'V');
        map.put("23", 'W');
        map.put("24", 'X');
        map.put("25", 'Y');
        map.put("26", 'Z');

        List<Character> path = new ArrayList<>();
        int res = dfs(s, 0, 1, path) + dfs(s, 0, 2, path);

        return ans.size();

    }

    /**
     * s[i...n-1] 分割 k 个字符，能分割成多少种
     * */
    private static int dfs(String s, int i, int k, List<Character> path) {
        int n = s.length();

        // 以及分完了
        if(i >= n) {
             // System.out.println("i="+i+" k="+k+ " path="+path);
            ans.add(new ArrayList<>(path));
            return 1;
        }

        String group = i+k <= n ? s.substring(i, i+k) : "";
        if(!map.containsKey(group)) return 0;

        path.add(map.get(group));
        int res1 = dfs(s, i+k, 1, path);
        int res2 = dfs(s, i+k, 2, path);
        path.remove(path.size()-1);
        return res1+res2;
    }

    /**
     * Method 2: 动态规划
     * dp[j]: chs[0...j] 的分割数是多少
     * */
    public static int numDecodings_2(String s) {
        int n = s.length();
        // dp[i]: s[0..i]能分割的种数
        int[] dp = new int[n];
        Set<String> set = new HashSet<>();
        for(int i=1;i<=26;i++) {
            set.add(String.valueOf(i));
        }

        dp[0] = set.contains(s.substring(0,1)) ? 1:0;
        if(n < 2) return dp[n-1];

        for(int i=1;i<n;i++) {
            if(set.contains(s.substring(i,i+1))) {
                dp[i] = dp[i-1];
            }

            if(set.contains(s.substring(i-1,i+1))) {
                dp[i] += i > 1 ? dp[i-2] : 1;
            }
        }

        return dp[n-1];
    }

    /**
     * Method 3: 优化 dfs
     * 如果当前字母以0开始，直接返回
     * 否则统计当前字母能组成的结果 res1
     * res2 是 i,i+2 开始遍历
     * */
    public static int numDecodings_3(String s) {
        return dfs_3(s, 0);
    }

    private static int dfs_3(String s, int i) {
        int n = s.length();
        if(i >= n) return 1;
        // 以0开始，直接返回
        if(s.charAt(i) == '0') return 0;

        int res1 = dfs_3(s, i+1);

        if(i+1 < n && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i+1) <= '6'))) {
            res1 += dfs_3(s, i+2);
        }

        return res1;
    }

    /**
     * Method 4: 动态规划，优化
     * dp[i] 的状态由 dp[i+1] 和 dp[i+2] 推理而来
     * */
    public static int numDecodings_4(String s) {
        int n = s.length();
        int[] dp = new int[n];
        dp[n-1] = s.charAt(n-1) == '0' ? 0: 1;

        for(int i=n-2;i>=0;i--) {
            if(s.charAt(i) == '0') dp[i] = 0;
            else {
                // 当前字母不为 0, 那么为任意数字都可以单独成一个 group
                dp[i] = dp[i+1];
                if(s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) <= '6') {
                    dp[i] += i+2 < n ? dp[i+2] : 1;
                }
            }
        }
        return dp[0];
    }


    /**
     * Method 5: space 优化
     * */
    public static int numDecodings_5(String s) {
        int n = s.length();
//        int f1 = s.charAt(n-1) == '0' ? 0 : 1, f2 = 0;
        int f1 = 1, f2 = 0;
        int newF;

        for(int i=n-1;i>=0;i--) {
            if(s.charAt(i) == '0') {
                newF = 0;
            } else {
                newF = f1;
                if(i+1 < n && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) < '7')) {
                    newF += f2;
                }
            }
            f2 = f1;
            f1 = newF;
        }

        return f1;
    }


    /**
     * Method 6: 记忆化搜索
     * */
    public static int numDecodings_6(String s) {
        int n = s.length();
        int[] memos = new int[n];
        Arrays.fill(memos, -1);

        return dfs(s, 0, memos);
    }

    private static int dfs(String s, int i, int[] memos) {
        if(i >= s.length()) return 1;
        if(s.charAt(i) == '0') return 0;

        if(memos[i] != -1) return memos[i];

        int res = dfs(s,i+1,memos);
        if(i+1 < s.length() && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) < '7')) {
            res += dfs(s, i+2, memos);
        }
        memos[i] = res;
        return res;
    }

    // 根据 Method 6 改 dp
    public static int numDecodings_7(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        // i >= s.length(), return 1
        dp[n] = 1;
        for(int i=n-1;i>=0;i--) {
            if(s.charAt(i) == '0') dp[i] = 0;
            else {
                // res = dfs(s, i+1, memos)
                dp[i] = dp[i+1];
                if(i+1 < n && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) < '7')) {
                    // 能进入到这，i 最大为 n-2
                    dp[i] += dp[i+2];
                }
            }
        }
        return dp[0];
    }

    /**
     * 根据 Method 6 的 dp, 继续优化
     * */
    public static int numDecodings_8(String s) {
        int n = s.length();
        int f1 = 1, f2 = 0, newF = 0;
        for(int i=n-1;i>=0;i--) {
            if(s.charAt(i) == '0') newF = 0;
            else {
                newF = f1;
                if(i+1 < n && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i+1) < '7')) {
                    newF += f2;
                }
            }
            f2 = f1;
            f1 = newF;
        }
        return f1;
    }


}
