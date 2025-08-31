import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII_216 {
    public static void main(String[] args) {
        int k = 4, n = 1;
        List<List<Integer>> ans = combinationSum3(k, n);
        for(List<Integer> a: ans)
            System.out.println(a);
    }

    private static List<List<Integer>> ans;
    public static List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        backtracking(k, n, 1, new ArrayList<>());
        return ans;
    }

    private static void backtracking(int k, int n, int index, List<Integer> path) {
        // 剪枝
        if(n < 0) return;

        // terminate condition
        if(path.size() == k && n == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 还要选 k-path.size()
        // n-x+1 = k-path.size()
        // x = n-(k-path.size())+1
        for(int i=index;i<=9-(k-path.size())+1;i++) {
            path.add(i);
            backtracking(k, n-i, i+1, path);
            path.remove(path.size()-1);
        }
    }
}
