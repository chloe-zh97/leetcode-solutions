import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII_72 {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        List<List<Integer>> ans = subsetsWithDup_2(nums);
        for (List<Integer> item : ans)
            System.out.println(item);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * Method 1: 考虑第 i 个位置，加还是不加
     */
    private static void backtracking(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        if (i == nums.length) {
            // 考虑完了所有的
            ans.add(new ArrayList<>(path));
            return;
        }

        // 第 i 个位置加入
        path.add(nums[i]);
        backtracking(nums, i + 1, path, ans);
        path.remove(path.size() - 1);

        // 第 i 个位置的元素不加入
        i++;
        while (i < nums.length && nums[i] == nums[i - 1]) i++;
        backtracking(nums, i, path, ans);
    }



    /**
     * Method 2: 第 i 个位置选哪个元素
     * */
    public static List<List<Integer>> subsetsWithDup_2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backtracking_2(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private static void backtracking_2(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        // 这个位置什么都不选
        ans.add(new ArrayList<>(path));

        // 在 nums[i...n-1] 选一个位置 j, 放入 path 中，说明 [i,j-1]的元素都不会被选
        for(int j=i;j<nums.length;j++) {
            // 过滤重复元素
            if(j!=i && nums[j] == nums[j-1]) continue;

            path.add(nums[j]);
            // 选择下一个位置, j-1 的位置都不会被选了，要往 j后面走
            backtracking(nums, j+1, path, ans);
            // 还原现场
            path.remove(path.size()-1);
        }
    }

}
