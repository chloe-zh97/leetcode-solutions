import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations_70 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> ans = permute(nums);
        for(List<Integer> item: ans)
            System.out.println(item);
    }

    /**
     * Method 1: backtracking
     * */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(nums, 0, ans);
        return ans;
    }

    /**
     * 第 i 个位置选什么数
     * */
    private static void backtracking(int[] nums, int i, List<List<Integer>> ans) {
        if(i == nums.length) {
            // 到达叶子节点了，所有的元素都被选完了，此时的序列就是答案
            List<Integer> tmp = new ArrayList<>();
            for(int num: nums)
                tmp.add(num);
            ans.add(tmp);
            return;
        }

        // 选择第 i 个位置的数
        for(int j=i;j<nums.length;j++) {
            swap(nums, i, j);
            // 选择下一个数
            backtracking(nums, i+1, ans);
            swap(nums, i, j);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
