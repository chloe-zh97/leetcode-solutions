import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildanArrayWithStackOperations_1441 {
    public static void main(String[] args) {
        int[] target = {1,2};
        int n = 4;
        List<String> ans = buildArray_2(target, n);
        System.out.println(ans);
    }

    public static List<String> buildArray(int[] target, int n) {
        Arrays.sort(target);
        int m = target.length;
        List<String> ans = new ArrayList<>();

        int[] seen = new int[n+1];
        for(int i=0;i<target.length;i++) {
            seen[target[i]] = 1;
        }

        for(int i=1;i<=Math.min(target[m-1], n);i++) {
            ans.add("Push");
            if(seen[i] == 0) ans.add("Pop");
        }
        return ans;
    }

    /**
     * 优化：去除辅助数组
     * */
    public static List<String> buildArray_2(int[] target, int n) {
        int m = target.length;
        Arrays.sort(target);

        List<String> ans = new ArrayList<>();
        int k = 0;
        for(int i=1;i<=Math.min(target[m-1], n);) {
            if(target[k] == i) {
                ans.add("Push");
                k++;
                i++;
            } else {
                ans.add("Push");
                ans.add("Pop");
                i++;
            }
        }
        return ans;
    }

}
