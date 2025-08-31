import java.util.Arrays;

public class FindtheDuplicateNumber_41 {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        System.out.println(findDuplicate_5(nums));
    }

    /**
     * Method 1: sort O(nlogn)
     * */
    public static int findDuplicate(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int i=0;
        while(i+1<n && nums[i] != nums[i+1]) i++;
        // 退出循环时 nums[i] == nums[i+1]
        return nums[i];
    }

    /**
     * Method 2: Hash table
     * */
    public static int findDuplicate_2(int[] nums) {
        int n = nums.length;
        int[] count = new int[n+1];

        for(int num: nums) {
            count[num]++;
            if(count[num] > 1) return num;
        }
        return -1;
    }

    /**
     * Method 3: 用 nums 数组本身做 hash table
     * 1,2,4,4,5
     * 每次遍历到 nums[i] 这个数，就把 nums[i]-1 对应的这个下标的数据改为负数
     * 如果这个下标的数据已经是负数了，说明 nums[i] 重复了
     * */
    public static int findDuplicate_3(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            int position = Math.abs(nums[i])-1;
            // 已经被改过了
            if(nums[position] < 0) return nums[i];

            nums[position] *= -1;
        }
        return -1;
    }

    /**
     * Method 4: Optimal, Floyd's Algorithm
     * */
    public static int findDuplicate_4(int[] nums) {
        int slow = 0, fast = 0;
        while(true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(slow == fast) break;
        }

        // 至此，找到第一个相遇点  slow
        int slow2 = 0;
        while(true) {
            slow = nums[slow];
            slow2 = nums[slow2];
            if(slow == slow2) break;
        }
        return slow;
    }

    /**
     * Method 5: Binary search
     * 二分，枚举重复的数字是 x
     * 统计 nums 中 <= x 的数量 count
     * */
    public static int findDuplicate_5(int[] nums) {
        int left = 1, right = nums.length;
        while(left < right) {
            int mid = left + right >> 1;
            if(check(nums, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 统计 nums 中 <= target 的数量
     * */
    private static boolean check(int[] nums, int target) {
        int count = 0;
        for(int num: nums) {
            count += num <= target ? 1: 0;
            if(count > target) return true;
        }
        return false;
    }
}
