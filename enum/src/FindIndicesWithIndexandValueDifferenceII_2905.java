import java.util.Arrays;

public class FindIndicesWithIndexandValueDifferenceII_2905 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int indexDifference = 2, valueDifference = 4;

        nums = findIndices(nums, indexDifference, valueDifference);
        System.out.println(Arrays.toString(nums));
    }


    public static int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int minId = -1, maxId = -1;

        int[] res = {-1,-1};
        for(int j=indexDifference;j<n;j++) {
            if(nums[j-indexDifference] < min) {
                min = nums[j-indexDifference];
                minId = j-indexDifference;
            }

            if(nums[j-indexDifference] > max) {
                max = nums[j-indexDifference];
                maxId = j-indexDifference;
            }

            if(Math.abs(max-nums[j]) >= valueDifference) {
                res[0] = maxId;
                res[1] = j;
                return res;
            }

            if(Math.abs(min-nums[j]) >= valueDifference) {
                res[0] = minId;
                res[1] = j;
                return res;
            }
        }

        return res;
    }

    /**
     * Method 1: 暴力法
     * */
    public static int[] findIndices_2(int[] nums, int indexDifference, int valueDifference) {
        int[] res = {-1,-1};
        int n = nums.length;
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                if(Math.abs(i-j) >= indexDifference && Math.abs(nums[i]-nums[j]) >= valueDifference) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
}
