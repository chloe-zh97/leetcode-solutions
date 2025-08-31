import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DesignBrowserHistory_1472 {
    public static void main(String[] args) {

    }

    /**
     * Method 1: 两个栈
     * */
    class BrowserHistory {
        private Deque<String> forwardQueue;
        private Deque<String> backQueue;

        public BrowserHistory(String homepage) {
            forwardQueue = new ArrayDeque<>();
            backQueue = new ArrayDeque<>();

            backQueue.offerLast(homepage);
        }

        public void visit(String url) {
           // 清空前进栈
            forwardQueue.clear();

            backQueue.offerLast(url);
        }

        public String back(int steps) {
            // 后退栈中至少保留一个
            for(int i=0;i<steps && backQueue.size() > 1;i++) {
                String p = backQueue.pollLast();
                forwardQueue.offerLast(p);
            }

            return backQueue.peekLast();
        }

        public String forward(int steps) {
            for(int i=0;i<steps && !forwardQueue.isEmpty();i++) {
                String p = forwardQueue.pollLast();
                backQueue.offerLast(p);
            }
            return backQueue.peekLast();
        }
    }


    class BrowserHistory_2 {
        private List<String> history;
        private int cur = 0;

        public BrowserHistory_2(String homepage) {
            history = new ArrayList<>();
            history.add(homepage);
        }

        public void visit(String url) {
            cur++;
            // 清空 [cur, n-1]
            history.subList(cur, history.size()).clear();
            history.add(url);
        }

        public String back(int steps) {
            cur = Math.max(0, cur-steps);
            return history.get(cur);
        }

        public String forward(int steps) {
            cur = Math.min(cur+steps, history.size()-1);
            return history.get(cur);
        }
    }
}
