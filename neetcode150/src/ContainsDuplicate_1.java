import java.util.HashMap;

public class ContainsDuplicate_1 {
    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(hasDuplicate(nums));
    }

    /**
     * Method 1: HashMap
     * case 1: 1,2,3
     * case 2: 1,1
     * case 3: 1,3,1
     * */
    public static boolean hasDuplicate(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            if(map.containsKey(num)) return true;
            map.merge(num, 1, Integer::sum);
        }
        return false;
    }
}
