import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumDistanceinArrays_624 {
    public static void main(String[] args) {
        Integer[][] arrays = {
                {1},
                {1}
        };

        List<List<Integer>> list = new ArrayList<>();
        for(Integer[] arr: arrays) {
            list.add(Arrays.asList(arr));
        }

        System.out.println(maxDistance(list));
    }

    public static int maxDistance(List<List<Integer>> arrays) {
        int minVal = arrays.get(0).getFirst();
        int maxVal = arrays.get(0).getLast();

        int ans = Integer.MIN_VALUE;
        for(int i=1;i<arrays.size();i++) {
            int curMin = arrays.get(i).getFirst();
            int curMax = arrays.get(i).getLast();

            int d1 = Math.abs(minVal-curMax);
            int d2 = Math.abs(maxVal-curMin);
            ans = Math.max(ans, Math.max(d1, d2));
            minVal = Math.min(minVal, curMin);
            maxVal = Math.max(maxVal, curMax);
        }

        return ans;
    }
}
