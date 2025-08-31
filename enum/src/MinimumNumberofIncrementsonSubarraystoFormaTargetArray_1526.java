public class MinimumNumberofIncrementsonSubarraystoFormaTargetArray_1526 {
    public static void main(String[] args) {
        int[] target = {3,1,1,2};
        System.out.println(minNumberOperations(target));
    }

    // 3,1,5,4,2
    // 2,0,4,3,1
    // 1,0,4,3,1
    // 0,0,4,3,1
    // 0,0,3,2,0
    // 0,0,2,1,0
    // 0,0,1,0,0
    // 0,0,0,0,0
    public static int minNumberOperations(int[] target) {
        int n = target.length;
        int ans = target[0];
        for(int i=1;i<n;i++) {
            ans += Math.max(target[i] - target[i-1], 0);
        }
        return ans;
    }
}
