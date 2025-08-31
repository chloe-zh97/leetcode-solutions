import java.util.HashMap;

public class TwoSumIIInputArrayIsSorted_167 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: 二分,O(nlogn)
     * */
    public static int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int[] ans = {};
        for(int i=0;i<n-1;i++) {
            int pos = binarySearch(numbers, i+1, n-1, target-numbers[i]);
            if(pos != -1) return new int[]{i+1, pos+1};
        }
        return ans;
    }

    /**
     * 在 nums[start..end] 上查找 target
     * */
    private static int binarySearch(int[] nums, int left, int right, int target) {
        if(left < 0 || right >= nums.length || left > right) return -1;

        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * Method 2: dict
     * */
    public static int[] twoSum_2(int[] numbers, int target) {
        int n = numbers.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<n;i++) {
            if(map.containsKey(target - numbers[i])) {
                return new int[]{map.get(target-numbers[i])+1, i+1};
            } else {
                map.put(numbers[i], i);
            }
        }
        return new int[]{};
    }


    /**
     * Method 3: 双指针
     * */
    public static int[] twoSum_3(int[] numbers, int target) {
        int n = numbers.length;
        int i = 0, j = n-1;
        while(i < j) {
            if(numbers[i] + numbers[j] == target) return new int[]{i+1,j+1};
            else if(numbers[i] + numbers[j] < target) i++;
            else j--;
        }
        return new int[]{};
    }

}
