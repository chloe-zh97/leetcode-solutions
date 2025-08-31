public class ReverseWordsinaString_151 {
    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords_2(s));
    }

    /**
     * Method 1: 库函数，暴力法
     * */
    public static String reverseWords(String s) {
        String[] ss = s.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();
        for(int i=ss.length-1;i>=0;i--) {
            sb.append(ss[i]).append(" ");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }


    /**
     * Method 2: 双指针
     * */
    public static String reverseWords_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while(i < n && j < n) {
            // 寻找开头
            if(chs[i] == ' ') {
                i++;
                continue;
            }

            // 退出循环后，i 为起始位置
            j = i;
            while(j< n && chs[j] != ' ') j++;
            // 退出循环后，chs[j] = ' '

            String tmp = " "+s.substring(i,j);
            sb.insert(0, tmp);
            i=j;
        }
        return sb.deleteCharAt(0).toString();
    }

}
