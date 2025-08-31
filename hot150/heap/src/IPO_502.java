import java.util.*;

class Project {
    int no;
    int capital;
    int profit;
    public Project() {}
    public Project(int i, int c, int p) {
        no = i;
        capital = c;
        profit = p;
    }
}
public class IPO_502 {
    public static void main(String[] args) {
        int k = 2, w = 0;
        int[] profits = {1,2,3};
        int[] capital = {0,1,1};
        System.out.println(findMaximizedCapital_2(k, w, profits, capital));
    }

    /**
     * Method 1: heap
     * */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // construct projects
        int n = profits.length;
        Project[] projects = new Project[n];
        for(int i=0;i<n;i++) {
            projects[i] = new Project(i, capital[i], profits[i]);
        }

        // 最大堆，按照 profit 从大到小排序
        PriorityQueue<Project> profitQueue = new PriorityQueue<>((o1,o2) -> o2.profit - o1.profit);

        // projects 按照 capital 从小到大排序
        Arrays.sort(projects, (o1, o2) -> o1.capital - o2.capital);
        int i = 0;
        while(i < n && projects[i].capital <= w) {
            profitQueue.offer(projects[i]);
            i++;
        }

        while(!profitQueue.isEmpty()) {
            Project p = profitQueue.poll();
            w += p.profit;

            while(i < n && projects[i].capital <= w) {
                profitQueue.offer(projects[i]);
                i++;
            }

            k--;
            if(k == 0) break;
        }

        return w;
    }


    /**
     * Method 2: List + 堆
     * */
    public static int findMaximizedCapital_2(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        List<int[]> projects = new ArrayList<>();
        for(int i=0;i<n;i++) {
            projects.add(new int[]{capital[i], profits[i]});
        }
        // 根据 capital 从小到大排序
        projects.sort((o1, o2) -> o1[0] - o2[0]);

        // 大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2 - o1);

        int i = 0;
        while(k-- > 0) {
            while(i < n && projects.get(i)[0] <= w) {
                pq.offer(projects.get(i)[1]);
                i++;
            }

            if(pq.isEmpty()) break;

            // 弹出
            w += pq.poll();
        }

        return w;
    }
}
