import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class HandofStraights_123 {
    public static void main(String[] args) {
        int[] hand = {1,2,3,6,2,3,4,7,8};
        int groupSize = 3;
        System.out.println(isNStraightHand(hand, groupSize));
    }

    /**
     * Method 1: TreeMap
     * */
    // [1,2,3,6,2,3,4,7,8] -> [1,2,2,3,3,4,6,7,8]
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if(n % groupSize != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: hand)
            map.merge(num, 1, Integer::sum);

        int cnt = 0;
        // 是否是一个 group 的最开始
        int pre = -1;

        while(cnt < n) {
            if(pre == -1) {
                // 每一组的第一个元素
                int k = map.firstEntry().getKey();
                int c = map.firstEntry().getValue();

                if(c == 1) map.remove(k);
                else map.put(k, c-1);

                pre = k;
            } else {
                // 不是每一组的最开始, 不包括下一个元素，返回 false
                pre++;
                if(!map.containsKey(pre)) return false;

                int c = map.get(pre);
                if(c == 1) map.remove(pre);
                else map.put(pre, c-1);
            }
            cnt++;
            if(cnt % groupSize == 0) pre = -1;
        }
        return true;
    }


    public static boolean isNStraightHand_2(int[] hand, int groupSize) {
        // sort
        Arrays.sort(hand);
        HashMap<Integer, Integer> map = new HashMap<>();
        // 统计数量
        for(int num: hand)
            map.merge(num, 1, Integer::sum);

        for(int num: hand) {
            if(map.containsKey(num)) {
                // num 可以作为开头
                for(int i=num;i<num+groupSize;i++) {
                    if(!map.containsKey(i)) return false;
                    map.merge(i, -1, Integer::sum);
                    if(map.get(i) == 0) map.remove(i);
                }
            }
        }
        return true;
    }

}
