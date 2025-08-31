import java.util.HashMap;

public class BinarySubarraysWithSum_930 {
    public static void main(String[] args) {
        int[] nums = {0,0,0,0,0};
        int goal = 0;
        System.out.println(numSubarraysWithSum(nums, goal));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        // 存储前缀和
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;

        for(int i=0;i<n;i++) {
            sum += nums[i];
            int c = map.getOrDefault(sum-goal, 0);
            ans += c;
            map.merge(sum, 1, Integer::sum);
        }
        return ans;
    }


    /**
     * Method 2: Sliding window
     * */
    public static int numSubarraysWithSum_2(int[] nums, int goal) {
        return atMostGoal(nums, goal) - atMostGoal(nums, goal-1);
    }

    /**
     * <= goal 的子数组个数
     * */
    private static int atMostGoal(int[] nums, int goal) {
        if(goal < 0) return 0;
        int n = nums.length;
        int j = 0, i = 0;
        int sum = 0, ans = 0;
        while(j < n) {
            sum += nums[j];

            while(i<n && sum > goal) {
                sum -= nums[i++];
            }

            // 退出循环，sum <= goal
            ans += j-i+1;
            j++;
        }
        return ans;
    }
}
