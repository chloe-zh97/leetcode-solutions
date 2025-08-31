import java.util.HashMap;
import java.util.Map;

public class NumberofGoodPairs_1512 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(numIdenticalPairs_2(nums));
    }
    public static int numIdenticalPairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.merge(num, 1, Integer::sum);
        }

        int cnt = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int v = entry.getValue();
            cnt += v * (v-1) / 2;
        }
        return cnt;
    }

    /**
     * Method: 优化，一次遍历
     * */
    public static int numIdenticalPairs_2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for(int num: nums) {
            int c = map.getOrDefault(num, 0);
            cnt += c;
            map.merge(num, 1, Integer::sum);
        }
        return cnt;
    }
}
