import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ContainsDuplicateII_219 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        int k = 2;
        System.out.println(containsNearbyDuplicate_3(nums, k));
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int minDiff = Math.max(k+1,n+1);

        for(int i=0;i<n;i++) {
            List<Integer> row = map.getOrDefault(nums[i], new ArrayList<>());
            int pre = -Math.max(k+1,n+1);
            if(!row.isEmpty()) pre = row.getLast();

            minDiff = Math.min(minDiff, i-pre);
            if(minDiff <= k) return true;

            row.add(i);
            map.put(nums[i], row);
        }
        return minDiff <= k;
    }

    /**
     * Method 2: HashMap 维护上一个出现的最后的坐标
     * */
    public static boolean containsNearbyDuplicate_2(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            if(map.containsKey(nums[i])) {
                int lastIndex = map.get(nums[i]);
                if(i-lastIndex <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }


    /**
     * Method 3: 定长滑动窗口
     * */
    public static boolean containsNearbyDuplicate_3(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> seen = new HashSet<>();

        int i = 0, j = 0;
        while(j < n) {
            if(j - i <= k) {
                if(seen.contains(nums[j])) return true;
                seen.add(nums[j]);
                j++;
                continue;
            }

            // 退出 if 后, j-i >=k， 需要收缩
            seen.remove(nums[i++]);
        }
        return false;
    }

}
