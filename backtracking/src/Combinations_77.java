import java.util.ArrayList;
import java.util.List;

public class Combinations_77 {
    public static void main(String[] args) {
        int n = 1;
        int k = 1;
        List<List<Integer>> ans = combine(n, k);
        for(List<Integer> a: ans)
            System.out.println(a);
    }

    private static List<List<Integer>> ans;

    public static List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        backtracking(n, 1, k, new ArrayList<>());
        return ans;
    }

    /**
     * 在 1-n 中选数，以及选到了第 index 个
     * */
    private static void backtracking(int n, int index, int k, List<Integer> path) {
        if(path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 还剩 k-path.size() 个需要选
        // n-x+1 = k - path.size()
        // x = n+1-(k-path.size())
        for(int i=index;i<=n+1-(k-path.size());i++) {
            path.add(i);
            backtracking(n, i+1, k, path);
            path.remove(path.size()-1);
        }
    }
}
