public class FindPivotIndex_724 {
    public static void main(String[] args) {
        int[] nums = {2,1,-1};
        System.out.println(pivotIndex_2(nums));
    }

    /**
     * Method 1: 前缀和和后缀和
     * */
    public static int pivotIndex(int[] nums) {
        int n = nums.length;

        // left[i]: nums[0...i]的和
        int[] left = new int[n];
        left[0] = nums[0];
        for(int i=1;i<n;i++) {
            left[i] = left[i-1] + nums[i];
        }

        int[] right = new int[n];
        right[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--) {
            right[i] = right[i+1] + nums[i];
        }

        // find pivot
        for(int i=0;i<n;i++) {
            if(left[i] == right[i]) return i;
        }

        return -1;
    }



    /**
     * Method 2: 优化，leftS = nums[0...i-1]的和
     * leftS = sumS - leftS - nums[i]
     * */
    public static int pivotIndex_2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i=0;i<n;i++)
            sum += nums[i];

        int leftS = 0;
        for(int i=0;i<n;i++) {
            if(leftS * 2 == sum - nums[i]) return i;
            leftS += nums[i];
        }
        return -1;
    }
}
