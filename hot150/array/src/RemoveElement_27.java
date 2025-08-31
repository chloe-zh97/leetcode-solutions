public class RemoveElement_27 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
    }
    public static int removeElement(int[] nums, int val) {
        int k = 0, n = nums.length;

        for(int i=0;i<n;i++) {
            nums[k] = nums[i];
            if(nums[i] != val) k++;
        }
        return k;
    }

}
