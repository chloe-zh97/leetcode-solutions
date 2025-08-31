public class ShiftingLettersII_2381 {
    public static void main(String[] args) {
        String s = "dztz";
        int[][] shift = {
                {0,0,0},
                {1,1,1}
        };
        System.out.println(shiftingLetters(s, shift));
    }

    // a,b,c,d,e,f, a,b,c,d,e,f  N=6
    // b->f, 正向 +4   (N+b+4) % 12
    // f->b, 反向 -4    [N+(f-4)] % 12
    public static String shiftingLetters(String s, int[][] shifts) {
        char[] chs = s.toCharArray();
        int m = shifts.length, n = s.length();

        // 构造循环mmap
        final int N = 26;
        char[] mmap = new char[2*N];
        for(int i=0;i<N;i++) {
            char c = (char) ('a'+i);
            mmap[i] = c;
            mmap[i+N] = c;
        }

        // d[i]: s 字符串 [i,n-1) 位置 shift 多少个单位
        int[] d = new int[n+1];
        for(int i=0;i<m;i++) {
            int from = shifts[i][0], to = shifts[i][1], dir = shifts[i][2] == 0 ? -1 : 1;
            d[from]+=dir;
            d[to+1]-=dir;
        }

        StringBuilder sb = new StringBuilder();
        int shift = 0;
        for(int i=0;i<n;i++) {
            shift += d[i];
            int index = chs[i] - 'a'; // 原来的 id
            char c = mmap[(N + index + shift % N) % (2*N)];
            sb.append(c);
        }

        return sb.toString();
    }
}
