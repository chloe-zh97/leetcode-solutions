import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning_74 {
    public static void main(String[] args) {
        String s = "a";
        List<List<String>> ans = partition(s);
        for(List<String> a: ans)
            System.out.println(a);
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * 将 s 分割成 [0..i-1], [i,n-1] 两段
     * */
    private static void backtracking(String s, int i, List<String> path, List<List<String>> ans) {
        if(i >= s.length()) {
            // 所有的都已经被 partition 完毕
            ans.add(new ArrayList<>(path));
            return;
        }

        // i 为开始位置
        for(int j=i;j<s.length();j++) {
            if(isPalindrome(s, i, j)) {
                // [i...j] 是回文串
                path.add(s.substring(i, j+1));
                backtracking(s, j+1, path, ans);
                path.remove(path.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String s, int i, int j) {
        char[] chs = s.toCharArray();
        while(i < j) {
            if(chs[i] != chs[j]) return false;
            i++;
            j--;
        }
        return true;
    }
}
