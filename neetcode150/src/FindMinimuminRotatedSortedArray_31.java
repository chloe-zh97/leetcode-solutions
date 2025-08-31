public class FindMinimuminRotatedSortedArray_31 {
    public static void main(String[] args) {
        int[] nums = {2};
        System.out.println(findMin(nums));
    }

    /**
     * 4,5,0,1,2,3
     * 找到 <= nums[n-1] 的最小的元素
     * 如果当前位置 <= 末尾元素，答案在左边
     * 如果当前位置 > 末尾元素，答案在右边
     * 这种一分为二的性质可以用二分
     * 注意，元素没有重复
     *
     * case 1: 1,2,3
     * case 2: 2
     * case 3: 4,5,0,1,2
     * */
    public static int findMin(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] <= nums[n-1]) right = mid;
            else left = mid + 1;
        }

        return nums[left];
    }
}
