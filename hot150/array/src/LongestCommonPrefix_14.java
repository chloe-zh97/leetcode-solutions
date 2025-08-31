import java.util.Arrays;
import java.util.Comparator;

public class LongestCommonPrefix_14 {
    public static void main(String[] args) {
        String[] strs = {"a"};
        System.out.println(longestCommonPrefix_2(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        // 按照长度排列
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        for(int len=strs[0].length();len > 0;len--) {
            String pre = strs[0].substring(0,len);
            boolean flag = true;

            for(int i=1;i<n;i++) {
                if(!strs[i].startsWith(pre)) {
                    flag = false;
                    break;
                }
            }

            if(flag) return pre;
        }

        return "";
    }


    /**
     * Method 2: 竖着比较
     * */
    public static String longestCommonPrefix_2(String[] strs) {
        int n = strs.length;
        String s0 = strs[0];

        for(int j=0;j<s0.length();j++) {
            char ch = s0.charAt(j);

            // 遍历每个 str
            for(int i=0;i<n;i++) {
                if(j == strs[i].length() || strs[i].charAt(j) != ch)
                    return s0.substring(0, j);
            }
        }
        return s0;
    }
}
