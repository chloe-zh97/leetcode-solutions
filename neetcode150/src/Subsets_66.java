import java.util.ArrayList;
import java.util.List;

public class Subsets_66 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> ans = subsets_3(nums);
        for(List<Integer> list: ans)
            System.out.println(list);
    }

    /**
     * Method 1: 回溯
     *
     * backtracking 模版
     *
     * if(终止条件) {
     *     存储结果
     *     return
     * }
     *
     * for(node in this level) {
     *     处理节点
     *     backtracking
     *     回退
     * }
     * */
    private static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0, new ArrayList<>());
        return ans;
    }

    private static void backtracking(int[] nums, int index, List<Integer> tmp) {
        if(index >= nums.length) {
            // 这里注意copy一个新的
            ans.add(new ArrayList<>(tmp));
            return;
        }

        tmp.add(nums[index]);
        backtracking(nums, index+1, tmp);

        tmp.remove(tmp.size()-1);
        backtracking(nums, index+1, tmp);
    }


    /**
     * Method 2: 遍历 res, 判断每个 num 是否加入 res, 产生不同结果
     * */
    public static List<List<Integer>> subsets_2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        // 入口，把空 list 加入
        ans.add(new ArrayList<>());

        for(int num: nums) {
            int size = ans.size();
            for(int i=0;i<size;i++) {
                // 防止 modify 原来
                List<Integer> tmp = new ArrayList<>(ans.get(i));
                tmp.add(num);
                ans.add(tmp);
            }
        }
        return ans;
    }

    /**
     * Method 3: backtracking, 第 i 个数选谁
     * */
    public static List<List<Integer>> subsets_3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backtracking_3(nums, 0, path, ans);
        return ans;
    }

    /**
     * 第 j 个位置选谁
     * */
    private static void backtracking_3(int[] nums, int j, List<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));

        for(int i=j;i<nums.length;i++) {
            path.add(nums[i]);
            backtracking_3(nums, i+1, path, ans);

            path.remove(path.size()-1);
        }
    }

}
