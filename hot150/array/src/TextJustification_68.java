import java.util.ArrayList;
import java.util.List;

public class TextJustification_68 {
    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> ans = fullJustify(words, maxWidth);
        for(String s: ans)
            System.out.println(s);
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;

        for(int i=0;i<n;) {
            int a = i, cnt=0, curWidth = 0;
            while(a<n && curWidth+words[a].length()+cnt <= maxWidth) {
                // 当前 a 元素可以被加入进来
                curWidth += words[a].length();
                cnt++;
                a++;
            }

            StringBuilder sb = new StringBuilder();
            // 是否是最后一行
            if(a == n) {
                for(int k=i;k<a;k++) {
                    if(k!=i) sb.append(' ');
                    sb.append(words[k]);
                }
                while(sb.length() < maxWidth) sb.append(' ');
                ans.add(sb.toString());
                return ans;
            }

            // 只有一个单词
            if(cnt == 1) {
                sb.append(words[i]);
                for(int b=0;b<maxWidth-words[i].length();b++)
                    sb.append(' ');
                ans.add(sb.toString());
                i++;
            } else {
                // 至少有两个单词
                int remainBlanks = maxWidth - curWidth;
                // System.out.println("remainBlanks="+remainBlanks);

                for(int k=0;k<cnt;k++) {
                    sb.append(words[i+k]);

                    // 向上取整
                    int blank = cnt-1-k > 0 ? (remainBlanks+cnt-2-k) / (cnt-1-k) : remainBlanks;
                    remainBlanks -= blank;
                    // System.out.println("k="+k+" blank="+blank);

                    for(int b=0;b<blank;b++)
                        sb.append(' ');
                }
                ans.add(sb.toString());
                i = a;
            }
        }
        return ans;
    }
}
