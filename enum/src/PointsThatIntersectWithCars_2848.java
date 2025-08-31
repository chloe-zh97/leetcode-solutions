import java.util.ArrayList;
import java.util.List;

public class PointsThatIntersectWithCars_2848 {
    public static void main(String[] args) {
        int[][] nums = {
                {3,6},
                {1,5},
                {4,7}
        };

        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            List<Integer> row = new ArrayList<>();
            row.add(nums[i][0]);
            row.add(nums[i][1]);
            list.add(row);
        }
        System.out.println(numberOfPoints_2(list));
    }

    public static int numberOfPoints(List<List<Integer>> nums) {
        int[] seen = new int[105];
        for(int i=0;i<nums.size();i++) {
            int start = nums.get(i).get(0);
            int end = nums.get(i).get(1);
            for(int j=start;j<=end;j++)
                seen[j] = 1;
        }
        int cnt = 0;
        for(int i=1;i<seen.length;i++) {
            cnt += seen[i];
        }
        return cnt;
    }

    public static int numberOfPoints_2(List<List<Integer>> nums) {
        int n = nums.size();
        int MAXVAL = Integer.MIN_VALUE;
        // 寻找最大值
        for(int i=0;i<n;i++)
            MAXVAL = Math.max(MAXVAL, nums.get(i).get(1));

        // 构造差分数组
        int[] d = new int[MAXVAL+2];
        for(int i=0;i<n;i++) {
            int a = nums.get(i).get(0);
            int b = nums.get(i).get(1);
            d[a]++;
            d[b+1]--;
        }

        int s = 0, ans = 0;
        for(int i=0;i<d.length;i++) {
            s += d[i];
            if(s > 0) ans++;
        }
        return ans;
    }
}
