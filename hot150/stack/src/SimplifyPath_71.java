import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath_71 {
    public static void main(String[] args) {
        String path = "/..hidden";
        System.out.println(simplifyPath(path));
    }

    public static String simplifyPath(String path) {
        String[] strs = path.split("/");

        Deque<String> queue = new ArrayDeque<>();
        for(String s: strs) {
            if(s.isEmpty() || s.equals(".")) continue;
            else if(s.equals("..")) queue.pollLast();
            else queue.offer(s);
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            sb.append("/");
            sb.append(queue.pollFirst());
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }
}
