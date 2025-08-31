public class RemoveDuplicatesfromSortedArray_26 {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n == 1) return 1;
        int k = 1;
        for(int i=1;i<n;i++) {
            nums[k] = nums[i];
            if(nums[k] != nums[k-1]) k++;
        }
        return k;
    }
}
