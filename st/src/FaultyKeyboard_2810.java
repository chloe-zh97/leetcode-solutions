import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FaultyKeyboard_2810 {
    public static void main(String[] args) {
        String s = "poiinter";

        Deque<Character> deque = new ArrayDeque<>();
        for(char c: s.toCharArray())
            deque.offerLast(c);

        // ret...
        for(char c: deque)
            System.out.print(c+" ");
        System.out.println();

        System.out.println(finalString_2(s));
    }

    /**
     * Method 1: 暴力法 94%
     * */
    public static String finalString(String s) {
        StringBuilder tmp = new StringBuilder();
        for(char c: s.toCharArray()) {
            if(c != 'i') {
                tmp.append(c);
            }
            else {
                // reverse 之前的字符
                tmp.reverse();
            }
        }
        return tmp.toString();
    }

    /**
     * Method 2: 每遇到一次 i 就改变一次方向
     * string
     * 111000
     * rtsng
     *
     * poiinter
     * 22000000
     * */
    public static String finalString_2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        int[] dirs = new int[n];

        int d = 0;
        for(int i=n-1;i>=0;i--) {
            char c = chs[i];
            if(c == 'i') {
                d += 1;
            } else {
                dirs[i] = d;
            }
        }

        for(int i=0;i<n;i++) {
            if(chs[i] == 'i') continue;
            if(dirs[i]%2 == 0) deque.offerLast(chs[i]);
            else deque.offerFirst(chs[i]);
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty())
            sb.append(deque.pollFirst());
        return sb.toString();
    }


    /**
     * Method 3: 增加一个方向 boolean 值
     * */
    public static String finalString_3(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        // 是否向末尾添加
        boolean tail = true;
        for(char c: chs) {
            if(c == 'i') tail = !tail;
            else {
                if(tail) deque.offerLast(c);
                else deque.offerFirst(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        if(!tail) sb.reverse();
        return sb.toString();
    }
}
