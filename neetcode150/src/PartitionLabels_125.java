import java.util.*;

public class PartitionLabels_125 {
    public static void main(String[] args) {
        String s = "abcabc";
        List<Integer> ans = partitionLabels(s);
        System.out.println(ans);
    }

    public static List<Integer> partitionLabels(String s) {
        char[] chs = s.toCharArray();
        int n = s.length();

        // 保存26个字母最后出现的位置
        int[] tails = new int[26];
        Arrays.fill(tails, -1);

        for(int i=0;i<n;i++) {
            tails[chs[i]-'a'] = i;
        }

        List<Integer> ans = new ArrayList<>();

        // 遍历 chs
        int l = 0, r = 0, further = 0;
        while(r < n) {
            // 当前区间能到达的最远位置
            further = tails[chs[l]-'a'];

            for(int i=l;i<=further;i++) {
                further = Math.max(tails[chs[i]-'a'], further);
                // System.out.println("i="+i+" further="+further);
            }

            ans.add(further-l+1);
            //System.out.println("l="+l+" r="+r);
            l = further+1;
            r = l;
        }
        return ans;
    }
}
