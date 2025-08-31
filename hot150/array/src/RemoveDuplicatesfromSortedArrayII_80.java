public class RemoveDuplicatesfromSortedArrayII_80 {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n < 3) return n;

        int k = 2;
        for(int i=2;i<n;i++) {
            nums[k] = nums[i];
            if(nums[k] != nums[k-2]) k++;
        }
        return k;
    }
}
