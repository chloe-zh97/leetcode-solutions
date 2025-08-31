import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CorporateLadder_blackrock {
    static class Node {
        String value;
        List<Node> in;
        List<Node> out;

        public Node(String val) {
            value = val;
            in = new LinkedList<>();
            out = new LinkedList<>();
        }
    }

    private static Map<String, Node> map  = new HashMap<>();
    private static int countLevel(String name1, String name2) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(map.get(name1));

        int level = 0;
        while(!deque.isEmpty()) {
            Node cur = deque.poll();
            if(cur.value.equals(name2)) break;

            // add its out list
            level++;
            for(Node o: cur.out) {
                deque.offer(o);
            }
        }
        return level;
    }

    /**
     * receive command and create graph
     * */
    private static void constructGraph(String employee, String manager) {
        Node emp = map.getOrDefault(employee, new Node(employee));
        Node man = map.getOrDefault(manager, new Node(manager));

        emp.out.add(man);
        man.in.add(emp);
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;

        int i = 0;
        String employee=null, manager=null;
        while ((line = in.readLine()) != null) {
            String[] names = line.split("/");
            if(i == 0) {
                employee = names[0];
                manager = names[1];
            } else {
                constructGraph(names[0], names[1]);
            }
            i++;
        }

        System.out.println(countLevel(employee, manager));
    }
}
