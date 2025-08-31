import java.util.Arrays;

public class RotateArray_189 {
    public static void main(String[] args) {
        int[] nums = {-1};
        // -100, -1, 99, 3
        int k = 2;
        rotate(nums, k);

        System.out.println(Arrays.toString(Arrays.stream(nums).toArray()));
    }

    /**
     * Method 1: 翻转
     * */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 前面翻转 n-k个
        rotateInRange(nums, 0, n-k-1);
        rotateInRange(nums, n-k, n-1);
        rotateInRange(nums, 0, n-1);
    }

    private static void rotateInRange(int[] nums, int start, int end) {
        while(start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;

            start++;
            end--;
        }
    }
}
