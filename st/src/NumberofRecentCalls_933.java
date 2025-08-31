import java.util.ArrayList;
import java.util.List;

public class NumberofRecentCalls_933 {
    public static void main(String[] args) {
        RecentCounter rc = new RecentCounter();
        System.out.println(rc.ping(1));
        System.out.println(rc.ping(100));
        System.out.println(rc.ping(3001));
        System.out.println(rc.ping(3002));
    }

    /**
     * Method 1: 二分
     * */
    static class RecentCounter {
        private List<Integer> nums;

        public RecentCounter() {
            nums = new ArrayList<>();
        }

        public int ping(int t) {
            nums.add(t);
            int l = binarySearchGreaterThan(t-3000);
            // System.out.println("t="+t+" inserted="+inserted+ " l="+l);
            return nums.size()-l;
        }

        /**
         * 在 nums 中找 >= target 的元素位置
         * */
        private int binarySearchGreaterThan(int target) {
            if(nums.isEmpty()) return 0;
            int left = 0, right = nums.size()-1;
            while(left < right) {
                int mid = left + right >> 1;
                if(nums.get(mid) >= target) right = mid;
                else left = mid + 1;
            }
            return nums.get(left) >= target ? left : nums.size();
        }
    }
}
