import java.util.HashMap;

public class CountSpecialQuadruplets_1995 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,3,5};
        System.out.println(countQuadruplets_2(nums));
    }

    /**
     * Method 1: 暴力法
     * */
    public static int countQuadruplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for(int a=0; a<n-3; a++) {
            for(int b=a+1;b<n-2;b++) {
                for(int c=b+1;c<n-1;c++) {
                    for(int d=c+1;d<n;d++) {
                        if(nums[a]+nums[b]+nums[c] == nums[d]) ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Method 2: 枚举+Hash
     * */
    public static int countQuadruplets_2(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for(int d=n-1;d>=3;d--) {
            for(int c=d-1;c>=2;c--) {
                int target = nums[d] - nums[c];
                // nums[i]+nums[j] == target
                HashMap<Integer, Integer> map = new HashMap<>();
                for(int j=0;j<c;j++) {
                    int v = map.getOrDefault(target-nums[j], 0);
                    ans += v;
                    map.merge(nums[j], 1, Integer::sum);
                }
            }
        }
        return ans;
    }
}
