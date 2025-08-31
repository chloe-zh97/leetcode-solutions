import java.util.*;

public class CombinationSumII_68 {
    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> ans = combinationSum2_2(candidates, target);
        for(List<Integer> a: ans)
            System.out.println(a);
    }

    /**
     * Method 1: backtracking 在 [i,..,n-1]中枚举要添加到 path 中的元素，
     * 假设选了 j 对应的这个数，说明 [i,j-1]这些数和 j 这个数不一样，没有被选到
     * */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Set<List<Integer>> set = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        Arrays.sort(candidates);
        backtracking(candidates, 0, target, path, set);

        return new ArrayList<>(set);
    }

    private static void backtracking(int[] candidates, int index, int target, List<Integer> path, Set<List<Integer>> set) {
        if(target < 0) return;

        if(target == 0) {
            set.add(new ArrayList<>(path));
            return;
        }

        // 思考的角度是，第 index 个数选谁
        // 从 candidates[index...n-1]中选举一个数 j
        // candidates[index...j-1] 的数都不会被选
        for(int i=index;i<candidates.length;i++) {
            if(i>index && candidates[i] == candidates[i-1]) continue;

            path.add(candidates[i]);
            backtracking(candidates, i+1, target-candidates[i], path, set);
            path.remove(path.size()-1);
        }
    }


    /**
     * Method 2: 思考角度，选或者不选，二路回溯
     * */
    public static List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        backtracking_2(candidates, 0, target, path, ans);
        return ans;
    }

    private static void backtracking_2(int[] candidates, int i, int target, List<Integer> path, List<List<Integer>> ans) {
        if(target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if(target < 0 || i >= candidates.length) return;

        // 选 i 这个位置的数
        path.add(candidates[i]);
        backtracking_2(candidates, i+1, target-candidates[i], path, ans);
        // 恢复现场
        path.remove(path.size()-1);

        // 过滤重复, 不选 i 这个数
        i++;
        while(i < candidates.length && candidates[i] == candidates[i-1]) i++;
        backtracking_2(candidates, i, target, path, ans);
    }

}
